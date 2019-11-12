package app.hackathon.test.helpers;

import app.hackathon.test.pages.LoginPage;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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

  public List<WebElement> getWebElementsList(By by){
    return webDriver.findElements(by);
  }
}
