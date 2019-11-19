package app.hackathon.test.pages;

import app.hackathon.test.helpers.PageDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends PageDriver {

  private By loginFormHeader = By.className("auth-header");
  private By labelElement = By.xpath("//div[@class='form-group']/label");
  private By logoImage = By.xpath("//img[@src='img/logo-big.png']");
  private By usernameLogo = By.xpath("//div[@class='pre-icon os-icon os-icon-user-male-circle']");
  private By passwordLogo = By.xpath("//div[@class='pre-icon os-icon os-icon-fingerprint']");
  private By usernameField = By.id("username");
  private By passwordField = By.id("password");
  private By signInButton = By.id("log-in");
  private By rememberMeCheckBox = By.xpath("//input[@class='form-check-input']");
  private By rememberMeLabel = By.xpath("//label[@class='form-check-label']");
  private By twitterIcon = By.xpath("//img[@src='img/social-icons/twitter.png']");
  private By facebookIcon = By.xpath("//img[@src='img/social-icons/facebook.png']");
  private By linkedInIcon = By.xpath("//img[@src='img/social-icons/linkedin.png']");
  private By warningMessage = By.xpath("//div[contains(@id,'random_id_')]");
  private By loginBox = By.xpath("//div[@class='auth-box-w']");

  public LoginPage(WebDriver webDriver) {
    super(webDriver);
  }

  public void clickLogoImg() {
    scrollInTo(0, 100);
    clickElement(logoImage);
  }

  public String getLoginFormHeader() {
    return getElementText(loginFormHeader);
  }

  public By getLogInBox() {
    return loginBox;
  }

  public String getRememberMeLabelText() {
    return getElementText(rememberMeLabel);
  }

  public String getUsernameLabelText() {
    return getNthElementText(labelElement, 0);
  }

  public String getPasswordLabelText() {
    return getNthElementText(labelElement, 1);
  }

  public Boolean isLogoImageDisplayed() {
    return isElementDisplayed(logoImage);
  }

  public Boolean isUsernameLogoDisplayed() {
    return isElementDisplayed(usernameLogo);
  }

  public Boolean isPasswordLogoDisplayed() {
    return isElementDisplayed(passwordLogo);
  }

  public Boolean isUsernameFieldDisplayed() {
    return isElementDisplayed(usernameField);
  }

  public Boolean isPasswordFieldDisplayed() {
    return isElementDisplayed(passwordField);
  }

  public Boolean isTwitterIconDisplayed() {
    return isElementDisplayed(twitterIcon);
  }

  public Boolean isFacebookIconDisplayed() {
    return isElementDisplayed(facebookIcon);
  }

  public Boolean isLinkedInIconDisplayed() {
    return isElementDisplayed(linkedInIcon);
  }

  public Boolean isRememberMeCheckboxDisplayed() {
    return isElementDisplayed(rememberMeCheckBox);
  }

  public Boolean isSignInDisplayed() {
    return isElementDisplayed(signInButton);
  }

  public void typeUsername(String value) {
    typeValueInField(usernameField, value);
  }

  public void typePassword(String value) {
    typeValueInField(passwordField, value);
  }

  public String getWarningMessage() {
    return getElementText(warningMessage);
  }

  public void clickSignInButton() {
    clickElement(signInButton);
  }

  public void logIn(String username, String password) {
    this.typeUsername(username);
    this.typePassword(password);
    this.clickSignInButton();
  }
}