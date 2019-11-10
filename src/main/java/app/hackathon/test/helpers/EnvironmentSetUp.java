package app.hackathon.test.helpers;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class EnvironmentSetUp {
  private WebDriver webDriver;
  private Properties configProperties;

  public WebDriver getWebDriver() {
    return this.webDriver;
  }

  public EnvironmentSetUp(Properties configProperties){
    this.configProperties = configProperties;
    String environment = this.configProperties.getProperty("execution.environment");
    switch (environment){
      case"local":
        this.setUpLocalEnvironment();
        break;
      case"grid":
        LOGGER.info("Selenium grid environment - is not implemented");
        break;
      case"browserstack":
        LOGGER.info("Browser stack environment - is not implemented");
        break;
      case"sauce":
        LOGGER.info("Sauce Labs environment - is not implemented");
    }
  }

  public void setUpLocalEnvironment(){
    String browser = this.configProperties.getProperty("browser");
    if(browser.equals("chrome")){
      this.setChromeDriver();
    }else if(browser.equals("firefox")){
      LOGGER.info("Firefox webdriver is not implemented");
    }else if(browser.equals("ie")){
      LOGGER.info("IE webdriver is not implemented");
  }
  }

  public void setChromeDriver(){
    System.setProperty("webdriver.chrome.driver", "/Users/nonna/Desktop/MyJavaMac/chromedriver");
    webDriver = new ChromeDriver();
    LOGGER.info("Chrome driver is running on local");
  }

}
