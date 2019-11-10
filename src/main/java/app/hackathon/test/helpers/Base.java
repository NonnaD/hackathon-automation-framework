package app.hackathon.test.helpers;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class Base {
  private static ThreadLocal<WebDriver> webDriver = new ThreadLocal();
  private EnvironmentSetUp setEnvironment;
  private Properties configProperties;

  public Properties initProperties(){
    Properties properties = new Properties();
    FileInputStream dataPropInput;
    try {
      dataPropInput = new FileInputStream("./src/main/resources/config.properties");
      properties.load(dataPropInput);
    }catch (IOException propertiesInit){
      LOGGER.info("IO exception : " + propertiesInit.getMessage());
    }
    return properties;
  }

  @BeforeSuite(alwaysRun = true)
  public void startUp() {
    this.configProperties = this.initProperties();
    setEnvironment = new EnvironmentSetUp(configProperties);
    webDriver.set(setEnvironment.getWebDriver());
    getWebDriver().get(this.configProperties.getProperty("app.url"));
  }

  @AfterSuite(alwaysRun = true)
  public void tearDown(){
    String appType = this.configProperties.getProperty("application.type", "");
    try {
      if (appType.equals("WEB") && getWebDriver() != null) {
        getWebDriver().quit();
      }else if (appType.equals("MOBILE")){
        //mobile is not implemented
      }
    }catch (WebDriverException webDriverExc){
      LOGGER.info("WebDriver exception : " + webDriverExc.getMessage());
    }

  }

  public static WebDriver getWebDriver() {
    return webDriver.get();
  }
}
