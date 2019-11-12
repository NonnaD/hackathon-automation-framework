package app.hackathon.test.traditional.tests;

import app.hackathon.test.data.Constants;
import app.hackathon.test.helpers.Base;
import app.hackathon.test.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TraditionalTests extends Base {

  private WebDriver webDriver;
  private LoginPage loginPage;
  private Constants constants;

  @BeforeMethod
  public void initializeAll() {
    startUp();
    webDriver = getWebDriver();
    loginPage = new LoginPage(webDriver);
    constants = new Constants();
  }

  @AfterMethod(alwaysRun = true)
  public void closeSession() {
    tearDown();
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
}
