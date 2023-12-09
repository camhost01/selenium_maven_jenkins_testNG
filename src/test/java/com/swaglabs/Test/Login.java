package com.swaglabs.Test;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.swaglabs.BasePage.ConfigBase;
import com.swaglabs.PageObjects.login;

public class Login extends ConfigBase{

    private login LoginPage;
    
    @BeforeMethod
    public void setup(){
        loadFile();
        SetupConfigurations();
        LoginPage = new login(driver);
    }
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    
    @BeforeSuite
    public void reportConf(){
        ExtentSparkReporter spark = new ExtentSparkReporter("src\\main\\java\\com\\swaglabs\\report\\LoginReport.html");
        extent.attachReporter(spark);
    }
    @AfterSuite
    public void finishReport(){
        extent.flush();
    }

    @Test
    public void check_CorrectLogin() {
        test = extent.createTest("Validate Login - correct credentials","This test evaluate the response if the user add a correct user and pass");
        LoginPage.validate_CorrectCredentials();
    }
    @Test
    public void check_IncorrectLogin() {
        test = extent.createTest("Validate Login - incorrect credentials","This test evaluate the response if the user add an incorrect user and pass");
        LoginPage.validate_BadCredentials();
    }

    @Test
    public void check_emptyFields() {
        test = extent.createTest("Validate Login - empty fields","This test evaluate the response if the user not add user and pass");
        LoginPage.validate_EmptyFields();
    }
}
