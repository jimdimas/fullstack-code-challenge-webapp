package com.app.backend.part1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginFailTest {

    WebDriver driver;

    @BeforeClass
    public void setUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost:3000/login");
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void testLoggingIn() throws InterruptedException {
        Thread.sleep(2000);
        WebElement username = driver.findElement(By.id("username"));
        WebElement password = driver.findElement(By.id("password"));

        username.sendKeys("jimdim");
        password.sendKeys("12345");
        driver.findElement(By.id("submit")).click();
        Thread.sleep(1000);
        Assert.assertEquals(driver.getCurrentUrl().contains("profile"),true);
    }
}
