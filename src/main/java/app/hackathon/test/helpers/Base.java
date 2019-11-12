package app.hackathon.test.helpers;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class Base {

  private static final Logger logger = LogManager.getLogger(Base.class.getName());
  private static WebDriver webDriver;
  private EnvironmentSetUp setEnvironment;
  private Properties configProperties;

  @BeforeSuite(alwaysRun = true)
  public void initAll() {
    this.configProperties = this.initProperties();
    setEnvironment = new EnvironmentSetUp(configProperties);
  }

  public void startUp() {
    webDriver = setEnvironment.getWebDriver();
    webDriver.get(this.configProperties.getProperty("app.url"));
    logger.info(String.format("Navigate to %s", this.configProperties.getProperty("app.url")));
  }

  public void tearDown() {
    String appType = this.configProperties.getProperty("application.type", "");
    try {
      if (appType.equals("web") && webDriver != null) {
        webDriver.quit();
        logger.info("WebDriver successfully terminated");
      } else if (appType.equals("mobile")) {
        //mobile is not implemented
      }
    } catch (WebDriverException webDriverExc) {
      logger.info("WebDriver exception : " + webDriverExc.getMessage());
    }
  }

  public Properties initProperties() {
    Properties properties = new Properties();
    FileInputStream dataPropInput;
    try {
      dataPropInput = new FileInputStream("./src/main/resources/config.properties");
      properties.load(dataPropInput);
    } catch (IOException propertiesInit) {
      logger.info("IO exception : " + propertiesInit.getMessage());
    }
    return properties;
  }

  public static WebDriver getWebDriver(){
    return webDriver;
  }
}
