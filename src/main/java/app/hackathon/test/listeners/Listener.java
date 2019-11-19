package app.hackathon.test.listeners;

import app.hackathon.test.helpers.Base;
import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listener implements ITestListener {

  private WebDriver webDriver;
  Base baseFunctions = new Base();

  @Override
  public void onTestStart(ITestResult result) {
  }

  @Override
  public void onTestSuccess(ITestResult result) {
  }

  @Override
  public void onTestFailure(ITestResult result) {
    String screenName = result.getName();
    try {
      System.out.println("Test case failed :" + result.getName());
      baseFunctions.getScreenshot(screenName);
      System.out.println("Screenshots has been taken");
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  @Override
  public void onTestSkipped(ITestResult result) {
  }

  @Override
  public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    String screenName = result.getName();

    try {
      baseFunctions.getScreenshot(screenName);
      System.out.println("screen taken");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void onStart(ITestContext context) {
  }

  @Override
  public void onFinish(ITestContext context) {
  }
}