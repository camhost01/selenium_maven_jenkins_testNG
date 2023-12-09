package com.swaglabs.Utilities;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.swaglabs.BasePage.ConfigBase;

public class utilities extends ConfigBase{

   public static void WaitElement(WebElement element){
      ExpectedConditions.visibilityOf(element);
   }

}
