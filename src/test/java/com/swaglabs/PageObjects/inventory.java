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
        loadFile();
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

    @FindBy(className = "shopping_cart_badge")
    private WebElement cartNotification;

    @FindBy(xpath = "//*[contains(text(),'Add to cart')]")
    private WebElement addCartbutton;

    @FindBy(id = "back-to-products")
    private WebElement backToProductsBtn;

    @FindBy(id = "item_0_title_link")
    private WebElement titleProduct0;

    @FindBy(id = "item_1_title_link")
    private WebElement titleProduct1;

    @FindBy(id = "item_2_title_link")
    private WebElement titleProduct2;

    @FindBy(id = "item_3_title_link")
    private WebElement titleProduct3;

    @FindBy(id = "item_4_title_link")
    private WebElement titleProduct4;

    @FindBy(id = "item_5_title_link")
    private WebElement titleProduct5;

    @FindBy(css = ".inventory_details_name")
    private WebElement detailTitleProduct;

    @FindBy(css = ".inventory_details_desc")
    private WebElement detailDescProduct;

    private By itemListName = By.className("inventory_item_name");

    private By itemListPrice = By.className("inventory_item_price");

    private By addCartBtn = By.xpath("//*[contains(text(),'Add to cart')]");

    private By btnProducts = By.cssSelector(".btn_inventory");

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

    public void check_iconNotification(String details) {
        try {
            switch (details) {
                case "Landing":
                    listofElements = driver.findElements(addCartBtn);
                    for (WebElement button : listofElements) {
                        button.click();
                        test.log(Status.PASS, "Click in Add to Cart button");
                    }
                    break;
                case "Details":
                    listofElements = driver.findElements(btnProducts);
                    break;
                default:
                    break;
            }
            int value = listofElements.size();
            String compare = String.valueOf(value);
            Assert.assertEquals(cartNotification.getText(), compare);
            test.log(Status.PASS, "Icon cart notification: " + cartNotification.getText()
                    + " correspond to items added: " + compare);
        } catch (AssertionError e) {
            test.log(Status.FAIL, "Error in test case icon cart notification");
            throw e;
        }

    }

    public void check_productDescription() {
        String detTitle, detDescription;
        try {
            for (int x = 1; x <= 6; x++) {
                if (x == 1)
                    titleProduct0.click();
                if (x == 2)
                    titleProduct1.click();
                if (x == 3)
                    titleProduct2.click();
                if (x == 4)
                    titleProduct3.click();
                if (x == 5)
                    titleProduct4.click();
                if (x == 6)
                    titleProduct5.click();
                utilities.WaitElement(addCartbutton);
                test.log(Status.PASS, "Product detail");
                detTitle = detailTitleProduct.getText();
                detDescription = detailDescProduct.getText();
                switch (detTitle) {
                    case "Sauce Labs Backpack":
                        Assert.assertEquals(detDescription, properties.getProperty("Sauce_Labs_Backpack"));
                        break;
                    case "Sauce Labs Bike Light":
                        Assert.assertEquals(detDescription, properties.getProperty("Sauce_Labs_Bike_Light"));
                        break;
                    case "Sauce Labs Bolt T-Shirt":
                        Assert.assertEquals(detDescription, properties.getProperty("Sauce_Labs_Bolt_T-Shirt"));
                        break;
                    case "Sauce Labs Fleece Jacket":
                        Assert.assertEquals(detDescription, properties.getProperty("Sauce_Labs_Fleece_Jacket"));
                        break;
                    case "Sauce Labs Onesie":
                        Assert.assertEquals(detDescription, properties.getProperty("Sauce_Labs_Onesie"));
                        break;
                    case "Test.allTheThings() T-Shirt (Red)":
                        Assert.assertEquals(detDescription,
                                properties.getProperty("Test.allTheThings()_T-Shirt_(Red)"));
                        break;
                    default:
                        break;
                }
                addCartbutton.click();
                test.log(Status.PASS, "Product: " + detTitle + " added in cart from detail page");
                backToProductsBtn.click();
                utilities.WaitElement(burgerMenu);
            }
        } catch (AssertionError e) {
            test.log(Status.FAIL, "Error checking the product description");
            throw e;
        }
    }
}
