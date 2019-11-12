package app.hackathon.test.helpers;

import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EnvironmentSetUp {

  private WebDriver webDriver;
  private Properties configProperties;
  private static final Logger logger = LogManager.getLogger(EnvironmentSetUp.class.getName());

  public EnvironmentSetUp(Properties configProperties) {
    this.configProperties = configProperties;
    String environment = this.configProperties.getProperty("execution.environment");
    switch (environment) {
      case "local":
        this.setUpLocalEnvironment();
        break;
      case "grid":
        logger.info("Selenium grid environment - is not implemented");
        break;
      case "browserstack":
        logger.info("Browser stack environment - is not implemented");
        break;
      case "sauce":
        logger.info("Sauce Labs environment - is not implemented");
    }
  }

  public void setUpLocalEnvironment() {
    String browser = this.configProperties.getProperty("browser");
    if (browser.equals("chrome")) {
      this.setChromeDriver();
    } else if (browser.equals("firefox")) {
      logger.info("Firefox webdriver is not implemented");
    } else if (browser.equals("ie")) {
      logger.info("IE webdriver is not implemented");
    }
  }

  public void setChromeDriver() {
    System.setProperty("webdriver.chrome.driver", "/Users/nonna/Desktop/MyJavaMac/chromedriver");
    webDriver = new ChromeDriver();
    logger.info("Chrome driver is running on local machine");
  }

  public WebDriver getWebDriver() {
    return this.webDriver;
  }
}
