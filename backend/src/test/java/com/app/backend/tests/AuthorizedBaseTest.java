package com.app.backend.tests;

import com.app.backend.pages.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import static com.app.backend.utility.BaseUtility.setUtilityDriver;

public class AuthorizedBaseTest {

    protected WebDriver driver;
    protected BasePage basePage;
    protected HomePage homePage;
    protected ProfilePage profilePage;
    protected ProfilePageSupervisor profilePageSupervisor;

    @BeforeClass
    @Parameters({"URL"})
    public void setUp(String url){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(url);
        basePage = new BasePage();
        basePage.setDriver(driver);
        setUtilityDriver();
    }

    @BeforeMethod
    @Parameters({"Username","Password","Role"})
    public void setupMethod(String username,String password,String role){
        homePage = new HomePage();
        if (role.equals("SUPERVISOR")){
            LoginPage loginPage = homePage.showLoginPage();
            profilePageSupervisor = loginPage.loginActionSupervisor(username,password);
        } else if (role.equals("STUDENT")){
            LoginPage loginPage = homePage.showLoginPage();
            profilePage = loginPage.loginAction(username,password);
        } else {
            throw new IllegalStateException("Role parameter has wrong input value: "+role+" is not valid.");
        }
    }

    @AfterMethod
    @Parameters({"Role"})
    public void tearDownMethod(String role){
        if (role.equals("SUPERVISOR")){
            profilePageSupervisor.logoutAction();
        } else if (role.equals("STUDENT")){
            profilePage.logoutAction();
        } else {
            throw new IllegalStateException("Role parameter has wrong input value: "+role+" is not valid.");
        }
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
