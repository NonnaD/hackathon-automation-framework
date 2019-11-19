package app.hackathon.test.helpers;


import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.MatchLevel;
import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.selenium.Eyes;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.maven.surefire.shade.common.org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.testng.annotations.BeforeClass;

public class Base {

  private static final Logger logger = LogManager.getLogger(Base.class.getName());
  private static WebDriver webDriver;
  private EnvironmentSetUp setEnvironment;
  private Properties configProperties;
  private Eyes eyes;
  private static BatchInfo batchInfo;


  public void initAll() {
    this.configProperties = this.initProperties();
    setEnvironment = new EnvironmentSetUp(configProperties);
  }

  public void startUp() {
    webDriver = setEnvironment.getWebDriver();
    webDriver.get(this.configProperties.getProperty("app.url"));
    logger.info(String.format("Navigate to %s", this.configProperties.getProperty("app.url")));
    webDriver.manage().window().maximize();
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

  public static WebDriver getWebDriver() {
    return webDriver;
  }

  public void appendShowAddParam() {
    webDriver.navigate().to(String
        .format("%s%s", webDriver.getCurrentUrl(), configProperties.getProperty("show.adds")));
  }

  public void getScreenshot(String screenName) throws IOException {
    File src = ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.FILE);
    FileUtils
        .copyFile(src, new File("./screenshots/" + screenName + ".png"));
  }

  public void initEyes() {
    eyes = new Eyes();
    eyes.setApiKey(configProperties.getProperty("applitools.api.key"));
  }

  public void validateWindow(MatchLevel matchLevel, Boolean forceFullScreen, String tag) {
    eyes.open(getWebDriver(), "Visual AI Tests",
        Thread.currentThread().getStackTrace()[2].getMethodName()/*, new RectangleSize(1440, 677)*/);
    eyes.setMatchLevel(matchLevel);
    eyes.setForceFullPageScreenshot(forceFullScreen);
    eyes.checkWindow();
    eyes.close();
  }

  public void validateElement(MatchLevel matchLevel, By element, Boolean forceFullScreen,
      String tag) {
    eyes.open(getWebDriver(), "Visual AI Tests", Thread.currentThread().getStackTrace()[2]
        .getMethodName()/*, new RectangleSize(1440, 677)*/);
    eyes.setMatchLevel(matchLevel);
    eyes.checkElement(element, tag);
    eyes.setForceFullPageScreenshot(forceFullScreen);
    eyes.close();
  }

  public void validateElement(MatchLevel matchLevel, String testName, By element,
      Boolean forceFullScreen, String tag) {
    eyes.open(getWebDriver(), "Visual AI Tests", testName/*, new RectangleSize(1440, 677)*/);
    eyes.setMatchLevel(matchLevel);
    eyes.checkElement(element, tag);
    eyes.setForceFullPageScreenshot(forceFullScreen);
    eyes.close();
  }

  public void validateRegion(MatchLevel matchLevel, By element, Boolean forceFullScreen,
      String tag) {
    eyes.open(getWebDriver(), "Visual AI Tests", Thread.currentThread().getStackTrace()[2]
        .getMethodName()/*, new RectangleSize(1440, 677)*/);
    eyes.setMatchLevel(matchLevel);
    eyes.checkRegion(element, tag);
    eyes.setForceFullPageScreenshot(forceFullScreen);
    eyes.close();
  }

  public void validateFrame(MatchLevel matchLevel, Integer frameIndex, Boolean forceFullScreen,
      String tag) {
    eyes.open(getWebDriver(), "Visual AI Tests", Thread.currentThread().getStackTrace()[2]
        .getMethodName()/*, new RectangleSize(1440, 677)*/);
    eyes.setMatchLevel(matchLevel);
    eyes.checkFrame(frameIndex, tag);
    eyes.setForceFullPageScreenshot(forceFullScreen);
    eyes.close();
  }

  public void eyesTearDown() {
    eyes.abortIfNotClosed();
  }

  public void eyesSetBatch() {
    eyes.setBatch(batchInfo);
  }

  @BeforeClass
  public static void setBatchInfo() {
    batchInfo = new BatchInfo("Hackathon");
  }
}
