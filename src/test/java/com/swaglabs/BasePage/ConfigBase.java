package com.swaglabs.BasePage;

import java.io.FileInputStream;
import java.io.InputStream;
import java.time.Duration;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class ConfigBase {
    protected static WebDriver driver;
    protected Properties properties = new Properties();
    protected WebDriverWait waait;
    protected ExtentReports extent = new ExtentReports();
    protected static ExtentTest test; 
    protected List<WebElement> listofElements;

    public void SetupConfigurations() {
            // Configurar el sistema para usar el controlador de Chrome
            switch ("Chrome") {
                case "Chrome":
                    System.setProperty("wedriver.chrome.driver",
                    "src\\main\\java\\com\\orangehr\\drivers\\chromedriver.exe");
                    ChromeOptions chromeOptions = new ChromeOptions();
                    if ("false".equals("true")) {
                        chromeOptions.addArguments("--headless=new");
                        driver = new ChromeDriver(chromeOptions);
                    } else {
                        driver = new ChromeDriver();
                    }
                    break;
                case "Edge":
                    System.setProperty("webdriver.edge.driver", "src\\main\\java\\com\\swaglabs\\drivers\\msedgedriver.exe");
                    EdgeOptions options = new EdgeOptions();
                    options.addArguments("--headless");
                    if ("true".equals("true")) {
                        driver = new EdgeDriver(options);
                    } else {
                        driver = new EdgeDriver();
                    }
                    break;
                default:
                    break;
            }
            switch ("Desktop") {
                case "Desktop":
                    driver.manage().window().maximize();
                    break;
                case "Iphone-13":
                    driver.manage().window().setSize(new Dimension(390, 844));
                    break;
                case "Ipad-air":
                    driver.manage().window().setSize(new Dimension(768, 1024));
                    break;
                case "SamsungS21":
                    driver.manage().window().setSize(new Dimension(360, 800));
                    break;
                default:
                    break;
            }
            driver.get("https://www.saucedemo.com/");
            waait = new WebDriverWait(driver, Duration.ofSeconds(22));
    }

    public void loadFile() {
        try {
            InputStream input = new FileInputStream("src\\main\\java\\com\\swaglabs\\data\\data.txt");
            properties.load(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
