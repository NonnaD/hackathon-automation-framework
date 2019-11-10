package app.hackathon.test.traditional.tests;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import app.hackathon.test.helpers.Base;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TraditionalTests extends Base {

  @BeforeMethod
  public void initSession(){
    startUp();
  }

  @AfterMethod(alwaysRun = true)
  public void closeSession(){
    tearDown();
  }

  @Test
  public void tet1() {
  assertTrue(true);
  }
}
