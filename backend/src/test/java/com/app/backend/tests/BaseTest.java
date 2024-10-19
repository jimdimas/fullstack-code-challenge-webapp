package com.app.backend.tests;

import com.app.backend.pages.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import static com.app.backend.utility.BaseUtility.setUtilityDriver;

public class BaseTest {

    protected WebDriver driver;
    protected BasePage basePage;
    protected HomePage homePage;
    protected ProfilePage profilePage;
    protected ProfilePageSupervisor profilePageSupervisor;

    @BeforeClass
    @Parameters({"URL","Username","Password","Role"})
    public void setUp(String url,String username,String password,String role){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(url);
        basePage = new BasePage();
        basePage.setDriver(driver);
        setUtilityDriver();
        homePage = new HomePage();
        setUtilityDriver();

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

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
