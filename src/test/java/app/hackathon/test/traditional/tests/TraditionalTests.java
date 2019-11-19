package app.hackathon.test.traditional.tests;

import app.hackathon.test.data.Constants;
import app.hackathon.test.helpers.Base;
import app.hackathon.test.pages.CustomerPage;
import app.hackathon.test.pages.LoginPage;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Listeners({app.hackathon.test.listeners.TestListener.class})

public class TraditionalTests extends Base {

  private WebDriver webDriver;
  private LoginPage loginPage;
  private CustomerPage customerPage;
  private Constants constants;
  private SoftAssert softAssert;

  @BeforeMethod
  public void initializeAll() {
    initAll();
    startUp();
    webDriver = getWebDriver();
    loginPage = new LoginPage(webDriver);
    customerPage = new CustomerPage(webDriver);
    constants = new Constants();
    softAssert = new SoftAssert();
  }

  @AfterMethod(alwaysRun = true)
  public void closeSession() {
    tearDown();
  }

  @DataProvider
  public Object[][] userInfoData() {
    Object[][] data = new Object[4][4];
    data[0][0] = "tester";
    data[0][1] = "";
    data[0][2] = "Password must be present";
    data[0][3] = "Correct username and empty password";
    data[1][0] = "";
    data[1][1] = "automation";
    data[1][2] = "Username must be present";
    data[1][3] = "Correct password and empty username";
    data[2][0] = "";
    data[2][1] = "";
    data[2][2] = "Both Username and Password must be present";
    data[2][3] = "Empty password and empty username";
    data[3][0] = "tester";
    data[3][1] = "automation";
    data[3][2] = "";
    data[3][3] = "Correct password and correct username";
    return data;
  }

  @Test(description = "Login Page UI Elements Test")
  public void validateLoginPageUIElements() {
    softAssert.assertEquals(loginPage.getLoginFormHeader(), constants.getLoginFormHeader(),
        "Login form header:");
    softAssert.assertEquals(loginPage.getUsernameLabelText(), constants.getUsernameLabel(),
        "Username label text:");
    softAssert.assertEquals(loginPage.getPasswordLabelText(), constants.getPasswordLabel(),
        "Password label text:");
    softAssert.assertEquals(loginPage.getRememberMeLabelText(), constants.getRememberMeLabelText(),
        "Remember me label text:");
    softAssert.assertTrue(loginPage.isLogoImageDisplayed(), "Logo image is:");
    softAssert.assertTrue(loginPage.isUsernameLogoDisplayed(), "Username logo is:");
    softAssert.assertTrue(loginPage.isPasswordLogoDisplayed(), "Password logo is:");
    softAssert.assertTrue(loginPage.isUsernameFieldDisplayed(), "Username field is:");
    softAssert.assertTrue(loginPage.isPasswordFieldDisplayed(), "Password filed is:");
    softAssert.assertTrue(loginPage.isTwitterIconDisplayed(), "Twitter icon is:");
    softAssert.assertTrue(loginPage.isFacebookIconDisplayed(), "Facebook icon is:");
    softAssert.assertTrue(loginPage.isLinkedInIconDisplayed(), "Linked in icon is:");
    softAssert.assertTrue(loginPage.isSignInDisplayed(), "Sign in is:");
    softAssert.assertTrue(loginPage.isRememberMeCheckboxDisplayed(), "Remember me checkbox is:");
    softAssert.assertAll();
  }

  @Test(description = "Login Page functional testing", dataProvider = "userInfoData")
  public void verifyLogInFunctionalityTest(String username, String password, String message,
      String testName) {
    loginPage.typeUsername(username);
    loginPage.typePassword(password);
    loginPage.clickSignInButton();
    if (!username.equals("") && !password.equals("")) {
      Assert.assertEquals(customerPage.getUserRole(), constants.getCustomer(), testName);
    } else {
      Assert.assertEquals(loginPage.getWarningMessage(), message, testName);
    }
  }

  @Test(description = "Verify table sort functionality")
  public void verifyTableSortFunctionalityTest() {
    loginPage.logIn("user", "password");
    Assert.assertEquals(customerPage.getUserRole(), constants.getCustomer());
    customerPage.clickTransactionAmount();
    List<Double> actualTransactions = customerPage.getAllTransactions();
    List<Double> sortedTransactions = customerPage.getAllTransactions();
    Collections.sort(sortedTransactions);
    Assert.assertEquals(sortedTransactions, actualTransactions);
  }

  @Test(description = "Validate the bar chart and representing that data")
  public void canvasChartTest() {
    loginPage.logIn("user", "password");
    Assert.assertEquals(customerPage.getUserRole(), constants.getCustomer());
    customerPage.clickCompareExpenses();
    Assert.assertTrue(customerPage.isChartDisplayed());
    softAssert.assertFalse(customerPage.isChar2017_2018Changed(), "2017-2018 data");
    customerPage.clickDataForNextYear();
    Assert.assertTrue(customerPage.isChartDisplayed());
    softAssert.assertTrue(customerPage.is2019DataAdded(), "2019 data added");
    softAssert.assertAll();
  }

  @Test(description = "Dynamic advertisement content test")
  public void dynamicAddContentTest() {
    appendShowAddParam();
    loginPage.logIn("user", "password");
    softAssert.assertEquals(customerPage.getUserRole(), constants.getCustomer());
    softAssert.assertTrue(customerPage.isFirstAddPresent(), "First add is present:");
    softAssert.assertFalse(customerPage.isFirstAddChanged(), "First add is changed:");
    softAssert.assertTrue(customerPage.isSecondAddPresent(), "Second add is present:");
    softAssert.assertFalse(customerPage.isSecondAddChanged(), "Second add is changed:");
    softAssert.assertAll();
  }
}
