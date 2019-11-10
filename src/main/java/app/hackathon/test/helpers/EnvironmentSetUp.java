package app.hackathon.test.helpers;

import com.sun.tools.internal.ws.wsdl.document.soap.SOAPUse;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class EnvironmentSetUp {
  private WebDriver webDriver;
  private Properties configProperties;
  private DesiredCapabilities desiredCapabilities;

  public WebDriver getWebDriver() {
    return this.webDriver;
  }

  public EnvironmentSetUp(Properties configProperties){
    this.configProperties = configProperties;
    String environment = this.configProperties.getProperty("execution.environment");
    System.out.println(this.configProperties.getProperty("execution.environment"));
    switch (environment){
      case"local":
        this.setUpLocalEnvironment();
        break;
      case"grid":
        System.out.println("Selenium grid environment - is not implemented");
        break;
      case"browserstack":
        System.out.println("Browser stack environment - is not implemented");
        break;
      case"sauce":
        System.out.println("Sauce Labs environment - is not implemented");
    }
  }

  public void setUpLocalEnvironment(){
    String browser = this.configProperties.getProperty("browser.chrome");
    if(browser.equals("chrome")){
      this.setChromeDriver();
    }else if(browser.equals("firefox")){
      System.out.println("Firefox webdriver is not implemented");
    }else if(browser.equals("ie")){
    System.out.println("IE webdriver is not implemented");
  }
  }

  public void setChromeDriver(){
    System.out.println("Chrome driver is running on local  ");
   /* System.setProperty("webdriver.chrome.driver", System.getenv("webdriver.chrome.driver"));
    webDriver = new ChromeDriver();
    webDriver.get("https://demo.applitools.com/hackathonApp.html");*/
  }

}
