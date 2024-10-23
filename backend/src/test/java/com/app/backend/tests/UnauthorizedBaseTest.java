package com.app.backend.tests;

import com.app.backend.pages.BasePage;
import com.app.backend.pages.HomePage;
import com.app.backend.utility.BaseUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import static com.app.backend.utility.BaseUtility.setUtilityDriver;

public class UnauthorizedBaseTest {

    protected WebDriver driver;
    protected BasePage basePage;
    protected HomePage homePage;


    @BeforeClass
    @Parameters({"URL"})
    public void setup(String url){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        basePage = new BasePage();
        basePage.setDriver(driver);
        setUtilityDriver();
        homePage = new HomePage();
        driver.get(url);
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}
