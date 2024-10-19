package com.app.backend.tests;

import com.app.backend.pages.BasePage;
import com.app.backend.pages.HomePage;
import com.app.backend.pages.LoginPage;
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

    @BeforeClass
    public void setUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @BeforeMethod
    @Parameters({"URL"})
    public void setMethod(String url){
        driver.get(url);
        basePage = new BasePage();
        basePage.setDriver(driver);
        setUtilityDriver();
        homePage = new HomePage();
        setUtilityDriver();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
