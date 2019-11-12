package app.hackathon.test.helpers;

import app.hackathon.test.pages.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PageDriver {

  private WebDriver webDriver;

  public PageDriver(WebDriver webDriver) {
    this.webDriver = webDriver;
  }

  public void browserMaximize () {
    webDriver.manage().window().maximize();
  }

  public String getElementText(By by) {
    return webDriver.findElement(by).getText();
  }

  public boolean isElementDisplayed(By by) {
    return webDriver.findElement(by).isDisplayed();
  }

  public String getNthElementText(By by, Integer elementNumber){
    return webDriver.findElements(by).get(elementNumber).getText();
  }

  public void typeValueInField(By by, String value){
    webDriver.findElement(by).sendKeys(value);
  }

  public void clickElement(By by){
    webDriver.findElement(by).click();
  }

}
