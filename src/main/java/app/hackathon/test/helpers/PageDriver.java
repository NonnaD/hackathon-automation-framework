package app.hackathon.test.helpers;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PageDriver {

  private WebDriver webDriver;

  public PageDriver(WebDriver webDriver) {
    this.webDriver = webDriver;
  }

  public WebDriver getWebDriver() {
    return this.webDriver;
  }

  public void browserMaximize() {
    webDriver.manage().window().maximize();
  }

  public String getElementText(By by) {
    return webDriver.findElement(by).getText();
  }

  public boolean isElementDisplayed(By by) {
    boolean isElementDisplayed;
    if (webDriver.findElements(by).size() > 0) {
      isElementDisplayed = webDriver.findElement(by).isDisplayed();
    } else {
      isElementDisplayed = false;
    }
    return isElementDisplayed;
  }

  public boolean isElementPresent(By by) {
    return webDriver.findElements(by).size() > 0;
  }

  public String getNthElementText(By by, Integer elementNumber) {
    return webDriver.findElements(by).get(elementNumber).getText();
  }

  public String getElementAttribute(By by, String attribute) {
    return webDriver.findElement(by).getAttribute(attribute);
  }

  public void typeValueInField(By by, String value) {
    webDriver.findElement(by).sendKeys(value);
  }

  public void clickElement(By by) {
    webDriver.findElement(by).click();
  }

  public List<WebElement> getWebElementsList(By by) {
    return webDriver.findElements(by);
  }

  public void scrollInTo(int x, int y) {
    JavascriptExecutor js = (JavascriptExecutor) webDriver;
    js.executeScript("window.scrollBy(" + x + "," + y + ")");
  }

  public void hardPause(int timeOut) {
    try {
      Thread.sleep(timeOut);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
