package com.swaglabs.PageObjects;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.swaglabs.BasePage.ConfigBase;
import com.swaglabs.Utilities.utilities;

public class login extends ConfigBase {


    utilities Utils;
    public login(WebDriver driver) {
        loadFile();
        PageFactory.initElements(driver, this);

    }

    @FindBy(id = "user-name")
    private WebElement usernameField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(css = "input[value='Login']")
    private WebElement loginButton;

    @FindBy(css = "h3[data-test='error']")
    private WebElement errorLogin;

    @FindBy(className = "app_logo")
    private WebElement titleLanding;

    public void validate_CorrectCredentials() {
        try {
            addUserPass("ok");
            test.log(Status.PASS, "User and pass added correctly");
            loginButton.click();
            utilities.WaitElement(titleLanding);
            Assert.assertEquals(properties.getProperty("urlLogExpected"), driver.getCurrentUrl());
            test.log(Status.PASS, "Url expected same as obtained");
        } catch (AssertionError e) {
            System.out.println(e);
            test.log(Status.FAIL, "Fail correct login user and pass");
            throw e;
        }

    }

    public void validate_BadCredentials() {
        try {
            addUserPass("wrong");
            test.log(Status.PASS, "User and pass added correctly");
            loginButton.click();
            utilities.WaitElement(errorLogin);
            Assert.assertEquals(properties.getProperty("msgWrongCredentials"), errorLogin.getText());
            test.log(Status.PASS, "Message for wrong credentials is according to expected: "+ properties.getProperty("msgWrongCredentials"));
        } catch (AssertionError e) {
            test.log(Status.FAIL, "Fail validating bad credentials");
            throw e;
        }
    }

    public void validate_EmptyFields(){
        loginButton.click();
        try {
            utilities.WaitElement(errorLogin);
            Assert.assertEquals(properties.getProperty("msgEmptyUserName"), errorLogin.getText());
            test.log(Status.PASS, "Message for empty username is according to expected: "+ properties.getProperty("msgEmptyUserName"));
            usernameField.sendKeys(properties.getProperty("okUser"));
            loginButton.click();
            utilities.WaitElement(errorLogin);
            Assert.assertEquals(properties.getProperty("msgEmptyPass"), errorLogin.getText());
            test.log(Status.PASS, "Message for empty password is according to expected: "+ properties.getProperty("msgEmptyPass"));
        } catch (AssertionError e) {
            throw e;
        }
    }

    public void loginPage(){
        addUserPass("ok");
        loginButton.click();
        utilities.WaitElement(titleLanding);
        Assert.assertEquals(properties.getProperty("urlLogExpected"), driver.getCurrentUrl());
    }
    
    protected void addUserPass(String type) {
        switch (type) {
            case "ok":
                usernameField.sendKeys(properties.getProperty("okUser"));
                passwordField.sendKeys(properties.getProperty("okPass"));
                break;
            case "wrong":
                usernameField.sendKeys(properties.getProperty("badUser"));
                passwordField.sendKeys(properties.getProperty("badPass"));
                break;
            default:
                break;
        }
    }
}
