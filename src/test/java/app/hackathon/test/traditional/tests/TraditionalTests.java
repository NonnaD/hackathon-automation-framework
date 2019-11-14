package app.hackathon.test.traditional.tests;

import app.hackathon.test.data.Constants;
import app.hackathon.test.helpers.Base;
import app.hackathon.test.pages.CustomerPage;
import app.hackathon.test.pages.LoginPage;
import java.util.Collections;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TraditionalTests extends Base {

  private WebDriver webDriver;
  private LoginPage loginPage;
  private CustomerPage customerPage;
  private Constants constants;

  @BeforeMethod
  public void initializeAll() {
    startUp();
    webDriver = getWebDriver();
    loginPage = new LoginPage(webDriver);
    customerPage = new CustomerPage(webDriver);
    constants = new Constants();
  }

  @AfterTest(alwaysRun = true)
  public void closeSession() {
    tearDown();
  }

  @DataProvider
  public Object[][] userInfoData() {
    Object[][] data = new Object[4][3];
    data[0][0] = "tester";
    data[0][1] = "";
    data[0][2] = "Password must be present";
    data[1][0] = "";
    data[1][1] = "automation";
    data[1][2] = "Username must be present";
    data[2][0] = "";
    data[2][1] = "";
    data[2][2] = "Both Username and Password must be present";
    data[3][0] = "tester";
    data[3][1] = "automation";
    data[3][2] = "";
    return data;
  }

  @Test(description = "Login Page UI Elements Test")
  public void validateLoginPageUIElements() {
    Assert.assertEquals(loginPage.getLoginFormHeader(), constants.getLoginFormHeader());
    Assert.assertEquals(loginPage.getUsernameLabelText(), constants.getUsernameLabel());
    Assert.assertEquals(loginPage.getPasswordLabelText(), constants.getPasswordLabel());
    Assert.assertEquals(loginPage.getRememberMeLabelText(), constants.getRememberMeLabelText());
    Assert.assertTrue(loginPage.isLogoImageDisplayed());
    Assert.assertTrue(loginPage.isUsernameLogoDisplayed());
    Assert.assertTrue(loginPage.isPasswordLogoDisplayed());
    Assert.assertTrue(loginPage.isUsernameFieldDisplayed());
    Assert.assertTrue(loginPage.isPasswordFieldDisplayed());
    Assert.assertTrue(loginPage.isTwitterIconDisplayed());
    Assert.assertTrue(loginPage.isFacebookIconDisplayed());
    Assert.assertTrue(loginPage.isLinkedInIconDisplayed());
    Assert.assertTrue(loginPage.isSignInDisplayed());
    Assert.assertTrue(loginPage.isRememberMeCheckboxDisplayed());
  }

  @Test(description = "Login Page functional testing", dataProvider = "userInfoData")
  public void verifyLogInFunctionality(String username, String password, String message) {
    loginPage.typeUsername(username);
    loginPage.typePassword(password);
    loginPage.clickSignInButton();
    if (!username.equals("") && !password.equals("")) {
      Assert.assertEquals(customerPage.getUserRole(), constants.getCustomer());
    } else {
      Assert.assertEquals(loginPage.getWarningMessage(), message);
    }
  }

  @Test(description = "Verify table sort functionality")
  public void verifyTableSortFunctionality() {
    loginPage.logIn("user","password");
    Assert.assertEquals(customerPage.getUserRole(), constants.getCustomer());
    customerPage.clickTransactionAmount();
    List<Double> actualTransactions = customerPage.getAllTransactions();
    List<Double> sortedTransactions = customerPage.getAllTransactions();
    Collections.sort(sortedTransactions);
    Assert.assertEquals(sortedTransactions, actualTransactions);
  }

  @Test(description = "Validate the bar chart and representing that data")
  public void canvasChartTest() {
    loginPage.logIn("user","password");
    Assert.assertEquals(customerPage.getUserRole(), constants.getCustomer());
    customerPage.clickCompareExpenses();
    customerPage.isChartDisplayed();
    //spent 2 hour to find any solution
    //unable to automate canvas element using traditional approach
  }
}
