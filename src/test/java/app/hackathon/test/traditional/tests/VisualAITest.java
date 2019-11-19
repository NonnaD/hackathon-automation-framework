package app.hackathon.test.traditional.tests;

import app.hackathon.test.data.Constants;
import app.hackathon.test.helpers.Base;
import app.hackathon.test.pages.CustomerPage;
import app.hackathon.test.pages.LoginPage;
import com.applitools.eyes.MatchLevel;
import com.applitools.eyes.selenium.Eyes;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Listeners({app.hackathon.test.listeners.TestListener.class})

public class VisualAITest extends Base {

  private WebDriver webDriver;
  private LoginPage loginPage;
  private CustomerPage customerPage;
  private Constants constants;
  private SoftAssert softAssert;
  private Eyes eyes;

  @BeforeMethod
  public void initializeAll() {
    initAll();
    initEyes();
    eyesSetBatch();
    startUp();
    webDriver = getWebDriver();
    loginPage = new LoginPage(webDriver);
    customerPage = new CustomerPage(webDriver);
    constants = new Constants();
    softAssert = new SoftAssert();
    eyes = new Eyes();
  }

  @AfterMethod(alwaysRun = true)
  public void closeSession() {
    eyesTearDown();
    tearDown();
  }

  @DataProvider
  public Object[][] userInfoData() {
    Object[][] data = new Object[4][3];
    data[0][0] = "tester";
    data[0][1] = "";
    data[0][2] = "Login test - correct username and empty password";
    data[1][0] = "";
    data[1][1] = "automation";
    data[1][2] = "Login test - empty username and correct password";
    data[2][0] = "";
    data[2][1] = "";
    data[2][2] = "Login test - empty username and empty password";
    data[3][0] = "tester";
    data[3][1] = "automation";
    data[3][2] = "Login test - correct username and correct password";
    return data;
  }

  @Test(description = "Login Page UI Elements Test")
  public void validateLoginPageUIElements() {
    validateWindow(MatchLevel.STRICT, true, "Login module elements");
    loginPage.clickLogoImg();
    validateWindow(MatchLevel.STRICT, true, "Login page bacground");
  }

  @Test(description = "Login Page functional testing", dataProvider = "userInfoData")
  public void verifyLogInFunctionalityTest(String username, String password, String testName) {
    loginPage.typeUsername(username);
    loginPage.typePassword(password);
    loginPage.clickSignInButton();
    if (!username.equals("") && !password.equals("")) {
      validateWindow(MatchLevel.LAYOUT, false, "Login page module");
    } else {
      validateElement(MatchLevel.CONTENT, testName, loginPage.getLogInBox(), true,
          "Login page module");
    }
  }

  @Test(description = "Verify table sort functionality")
  public void verifyTableSortFunctionalityTest() {
    loginPage.logIn("user", "password");
    Assert.assertEquals(customerPage.getUserRole(), constants.getCustomer());
    customerPage.clickTransactionAmount();
    validateWindow(MatchLevel.STRICT, true, "Amount module ");
  }

  @Test(description = "Validate the bar chart and representing that data")
  public void canvasChartTest() {
    loginPage.logIn("user", "password");
    Assert.assertEquals(customerPage.getUserRole(), constants.getCustomer());
    customerPage.clickCompareExpenses();
    validateWindow(MatchLevel.CONTENT,true, "Chart 2017-1018");
    customerPage.clickDataForNextYear();
    validateWindow(MatchLevel.CONTENT, true, "Chart 2019");
  }

  @Test(description = "Dynamic advertisement content test")
  public void dynamicAddContentTest() {
    appendShowAddParam();
    loginPage.logIn("user", "password");
    softAssert.assertEquals(customerPage.getUserRole(), constants.getCustomer());
    validateRegion(MatchLevel.LAYOUT, customerPage.getFinancialOverview(), true,
        "Dynamic advertisement area");
  }
}