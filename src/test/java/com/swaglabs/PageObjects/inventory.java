package com.swaglabs.PageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.swaglabs.BasePage.ConfigBase;
import com.swaglabs.Utilities.utilities;

public class inventory extends ConfigBase {

    private String[] elementAscending = { "Sauce Labs Backpack", "Sauce Labs Bike Light", "Sauce Labs Bolt T-Shirt",
            "Sauce Labs Fleece Jacket", "Sauce Labs Onesie", "Test.allTheThings() T-Shirt (Red)" };
    private String[] elementDescending = { "Test.allTheThings() T-Shirt (Red)", "Sauce Labs Onesie",
            "Sauce Labs Fleece Jacket",
            "Sauce Labs Bolt T-Shirt", "Sauce Labs Bike Light", "Sauce Labs Backpack" };
    private String[] priceAscending = { "$7.99", "$9.99", "$15.99", "$15.99", "$29.99", "$49.99" };
    private String[] priceDescending = { "$49.99", "$29.99", "$15.99", "$15.99", "$9.99", "$7.99" };

    public inventory(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "app_logo")
    private WebElement titleLanding;

    @FindBy(className = "shopping_cart_link")
    private WebElement shopCartIcon;

    @FindBy(className = "product_sort_container")
    private WebElement filterProducts;

    @FindBy(id = "react-burger-menu-btn")
    private WebElement burgerMenu;

    @FindBy(id = "inventory_sidebar_link")
    private WebElement burgerAllItem;

    @FindBy(id = "about_sidebar_link")
    private WebElement burgerAbout;

    @FindBy(id = "logout_sidebar_link")
    private WebElement burgerLogout;

    @FindBy(id = "reset_sidebar_link")
    private WebElement burgerReset;

    @FindBy(id = "react-burger-cross-btn")
    private WebElement burgerClose;

    @FindBy(className = "inventory_list")
    private WebElement inventory;

    private By itemListName = By.className("inventory_item_name");

    private By itemListPrice = By.className("inventory_item_price");

    public void validate_HomeElements() {
        try {
            utilities.WaitElement(titleLanding);
            test.log(Status.PASS, "Title present");
            utilities.WaitElement(shopCartIcon);
            test.log(Status.PASS, "CartIcon present");
            utilities.WaitElement(burgerMenu);
            test.log(Status.PASS, "Burger menu present");
            utilities.WaitElement(inventory);
            test.log(Status.PASS, "Inventory present");
        } catch (Exception e) {
            test.log(Status.FAIL, "Error with elements present in the Landing page");
            throw e;
        }

    }

    public void check_itemtNameOrder(String filterApplied) {
        List<String> listToCompare = new ArrayList<>();
        Select optionFilter = new Select(filterProducts);
        try {
            validate_HomeElements();
            switch (filterApplied) {
                case "Ascending":
                    optionFilter.selectByIndex(0);
                    listofElements = driver.findElements(itemListName);
                    test.log(Status.PASS, "Filtered by " + filterApplied);
                    for (WebElement elementinList : listofElements) {
                        listToCompare.add(elementinList.getText());
                    }
                    Assert.assertEquals(elementAscending, listToCompare.toArray());
                    break;
                case "Descending":
                    optionFilter.selectByIndex(1);
                    listofElements = driver.findElements(itemListName);
                    test.log(Status.PASS, "Filtered by " + filterApplied);
                    for (WebElement elementinList : listofElements) {
                        listToCompare.add(elementinList.getText());
                    }
                    Assert.assertEquals(elementDescending, listToCompare.toArray());
                    break;
                case "PriceLow":
                    optionFilter.selectByIndex(2);
                    listofElements = driver.findElements(itemListPrice);
                    test.log(Status.PASS, "Filtered by " + filterApplied);
                    for (WebElement elementinList : listofElements) {
                        listToCompare.add(elementinList.getText());
                    }
                    Assert.assertEquals(priceAscending, listToCompare.toArray());
                    break;
                case "PriceHigh":
                    optionFilter.selectByIndex(3);
                    listofElements = driver.findElements(itemListPrice);
                    test.log(Status.PASS, "Filtered by " + filterApplied);
                    for (WebElement elementinList : listofElements) {
                        listToCompare.add(elementinList.getText());
                    }
                    Assert.assertEquals(priceDescending, listToCompare.toArray());
                    break;
                default:
                    break;
            }
            test.log(Status.PASS, "Item filtered as expected: " + filterApplied);
        } catch (AssertionError e) {
            test.log(Status.FAIL, "Error validating the filter Item  :" + filterApplied);
            throw e;
        }
    }

}
