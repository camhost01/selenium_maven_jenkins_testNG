package com.swaglabs.Test;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.swaglabs.BasePage.ConfigBase;
import com.swaglabs.PageObjects.inventory;
import com.swaglabs.PageObjects.login;

public class Inventory extends ConfigBase{
    private login LoginPage;
    private inventory InventorElements;

     @BeforeMethod
    public void setup(){
        loadFile();
        SetupConfigurations();
        LoginPage = new login(driver);
        LoginPage.loginPage();
        InventorElements = new inventory(driver);
    }
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
   
    @BeforeSuite
    public void reportConf(){
        ExtentSparkReporter spark = new ExtentSparkReporter("src\\main\\java\\com\\swaglabs\\report\\InventoryReport.html");
        extent.attachReporter(spark);
    }
    @AfterSuite
    public void finishReport(){
        extent.flush();
    }

    @Test
    public void check_ElementsHome() {
        test = extent.createTest("Validate the elements in landing page","This test evaluate if the elements are present after login the page");
        InventorElements.validate_HomeElements();
    }
    @Test
    public void check_ItemFilter() {
        test = extent.createTest("Validate the filter in the landing page","This test evaluate the filter displayed in the right corner in the landing page");
        InventorElements.check_itemtNameOrder("Ascending");
        InventorElements.check_itemtNameOrder("Descending");
        InventorElements.check_itemtNameOrder("PriceLow");
        InventorElements.check_itemtNameOrder("PriceHigh");
    }

    
    
}
