package app.hackathon.test.pages;

import app.hackathon.test.helpers.PageDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CustomerPage extends PageDriver {

  private By userRole = By.className("logged-user-role");

  public CustomerPage(WebDriver webDriver) {
    super(webDriver);
  }

  public String getUserRole() {
    return getElementText(userRole);
  }
}
