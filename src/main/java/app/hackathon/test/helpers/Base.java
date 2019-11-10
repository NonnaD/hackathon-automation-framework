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
  //private Properties configProperties;

  public Properties initProperties(){
    Properties properties = new Properties();
    try {
      FileInputStream dataPropInput = new FileInputStream("./src/main/resources/config.properties");
    }catch (IOException propertiesInit){
      LOGGER.info("IO exception : " + propertiesInit.getMessage());
    }
    properties.load(dataPropInput);
    return properties;
  }

  @BeforeSuite(alwaysRun = true)
  public void startUp() {
    Properties configProperties = this.initProperties();
    System.out.println(configProperties.getProperty("browser"));
  //String testExecutionEnvironment = configProperties.getProperty("execution.environment");
  //String appType = configProperties.getProperty("application.type");
    setEnvironment = new EnvironmentSetUp(this.configProperties);

  }

  @AfterSuite(alwaysRun = true)
  public void tearDown(){
    String appType = configProperties.getProperty("application.type", "");

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
