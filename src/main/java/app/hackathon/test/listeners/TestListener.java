package app.hackathon.test.listeners;

import app.hackathon.test.helpers.Base;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener extends Base implements ITestListener {

  private static String getTestMethodName(ITestResult iTestResult) {
    return iTestResult.getMethod().getConstructorOrMethod().getName();
  }

  @Attachment(value = "Page screenshot", type = "image/png")
  public byte[] saveScreenshotPNG(WebDriver driver) {
    return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
  }

  @Attachment(value = "{0}", type = "text/plain")
  public static String saveTextLog(String message) {
    return message;
  }

  @Attachment(value = "{0}", type = "text/html")
  public static String attachHtml(String html) {
    return html;
  }

  @Override
  public void onStart(ITestContext iTestContext) {
    System.out.println("onStart method " + iTestContext.getName());
    iTestContext.setAttribute("WebDriver", Base.getWebDriver());
  }

  @Override
  public void onFinish(ITestContext iTestContext) {
    System.out.println("onFinish method " + iTestContext.getName());
  }

  @Override
  public void onTestStart(ITestResult iTestResult) {
    System.out.println("onTestStart method " + getTestMethodName(iTestResult) + " start");
  }

  @Override
  public void onTestSuccess(ITestResult iTestResult) {
    System.out.println("onTestSuccess method " + getTestMethodName(iTestResult) + " succeed");
  }

  @Override
  public void onTestFailure(ITestResult iTestResult) {
    System.out.println("onTestFailure method " + getTestMethodName(iTestResult) + " failed");
    Object testClass = iTestResult.getInstance();
    WebDriver driver = Base.getWebDriver();
    if (driver instanceof WebDriver) {
      System.out.println("Screenshot captured for test case:" + getTestMethodName(iTestResult));
      saveScreenshotPNG(driver);
    }
    saveTextLog(getTestMethodName(iTestResult) + " failed and screenshot taken!");
  }

  @Override
  public void onTestSkipped(ITestResult iTestResult) {
    System.out.println("onTestSkipped method " + getTestMethodName(iTestResult) + " skipped");
  }

  @Override
  public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
    System.out.println("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
  }
}