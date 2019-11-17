package app.hackathon.test.pages;

import app.hackathon.test.helpers.PageDriver;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import sun.misc.BASE64Decoder;

public class CustomerPage extends PageDriver {

  private By userRole = By.className("logged-user-role");
  private By transactionAmount = By.id("amount");
  private By debitTransaction = By.className("text-success");
  private By creditTransaction = By.className("text-danger");
  private By compareExpenses = By.id("showExpensesChart");
  private By chart = By.id("canvas");
  private By firstAdd = By.xpath("//div[@id='flashSale']/img");
  private By secondAdd = By.xpath("//div[@id='flashSale2']/img");
  private By dataForNextYear = By.id("addDataset");

  public CustomerPage(WebDriver webDriver) {
    super(webDriver);
  }

  public String getUserRole() {
    return getElementText(userRole);
  }

  public void clickTransactionAmount() {
    clickElement(transactionAmount);
  }

  public void clickCompareExpenses() {
    clickElement(compareExpenses);
  }

  public void clickDataForNextYear() {
    clickElement(dataForNextYear);
  }

  public BufferedImage getCanvasAsImage(String filePath) {
    browserMaximize();
    BufferedImage image = null;
    byte[] imageByte;
    JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
    WebElement canvas = getWebDriver().findElement(chart);
    Object base64 = js
        .executeScript("return arguments[0].toDataURL('image/png').substring(22);", canvas);
    String imgBase64 = base64.toString();
    try {
      BASE64Decoder decoder = new BASE64Decoder();
      imageByte = decoder.decodeBuffer(imgBase64);
      ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
      image = ImageIO.read(bis);
      bis.close();
      File fileImg = new File(filePath);
      ImageIO.write(image, "png", fileImg);
      System.out.println(image.createGraphics());
    } catch (Exception e) {
      e.printStackTrace();
    }

    return image;
  }

  public Boolean isChar2017_2018Changed(){
    this.getCanvasAsImage("./canvasImg/newChart2017-2018.png");
    String file1 = "./canvasImg/baseline2017-2018.png";
    String file2 = "./canvasImg/newChart2017-2018.png";
    return !isCharChanged(file1, file2);
  }

  public Boolean is2019DataAdded(){
    this.getCanvasAsImage("./canvasImg/newChart2019.png");
    String file1 = "./canvasImg/baseline2017-2018.png";
    String file2 = "./canvasImg/newChart2019.png";
    return !isCharChanged(file1, file2);
  }

  public Boolean isCharChanged(String img1, String img2) {
    Boolean isImagesChanged = null;
    String file1 = img1;
    String file2 = img2;
    Image image1 = Toolkit.getDefaultToolkit().getImage(file1);
    Image image2 = Toolkit.getDefaultToolkit().getImage(file2);

    try {
      PixelGrabber grabImage1Pixels = new PixelGrabber(image1, 0, 0, -1,
          -1, false);
      PixelGrabber grabImage2Pixels = new PixelGrabber(image2, 0, 0, -1,
          -1, false);
      int[] image1Data = null;
      if (grabImage1Pixels.grabPixels()) {
        int width = grabImage1Pixels.getWidth();
        int height = grabImage1Pixels.getHeight();
        image1Data = new int[width * height];
        image1Data = (int[]) grabImage1Pixels.getPixels();
      }

      int[] image2Data = null;
      if (grabImage2Pixels.grabPixels()) {
        int width = grabImage2Pixels.getWidth();
        int height = grabImage2Pixels.getHeight();
        image2Data = new int[width * height];
        image2Data = (int[]) grabImage2Pixels.getPixels();
      }
      isImagesChanged = java.util.Arrays.equals(image1Data, image2Data);
      System.out.println("Pixels equal: "
          + java.util.Arrays.equals(image1Data, image2Data));

    } catch (InterruptedException e1) {
      e1.printStackTrace();
    }
    return isImagesChanged;
  }

  public Boolean isChartDisplayed() {
    return isElementDisplayed(chart);
  }

  public Boolean isFirstAddPresent() {
    return isElementDisplayed(firstAdd);
  }

  public Boolean isSecondAddPresent() {
    return isElementDisplayed(secondAdd);
  }

  public Boolean isFirstAddChanged() {
    boolean isFirstAddChanged = true;
    if (isFirstAddPresent()) {
      String srcAttributeValue = getElementAttribute(firstAdd, "src");
      String styleAttributeValue = getElementAttribute(firstAdd, "style");
      System.out.println("First add src attribute value equals: " + srcAttributeValue);
      System.out.println("First add style attribute value equals: " + styleAttributeValue);
      isFirstAddChanged = !srcAttributeValue.contains("img/flashSale.gif") || !styleAttributeValue
          .equals("width: 129px; height: 100px;");
    }
    return isFirstAddChanged;
  }

  public Boolean isSecondAddChanged() {
    boolean isFirstAddChanged = true;
    if (isSecondAddPresent()) {
      String srcAttributeValue = getElementAttribute(secondAdd, "src");
      String styleAttributeValue = getElementAttribute(secondAdd, "style");
      System.out.println("Second add src attribute value equals: " + srcAttributeValue);
      System.out.println("Second add style attribute value equals: " + styleAttributeValue);
      isFirstAddChanged = !srcAttributeValue.contains("img/flashSale2.gif") || !styleAttributeValue
          .equals("width: 140px; height: 100px;");
    }
    return isFirstAddChanged;
  }

  /**
   * This function retrieves all transactions from the Amounts column in order they present on the
   * page
   *
   * @return List<Double> - list of all transactions amounts
   */
  public List<Double> getAllTransactions() {
    List<Double> allTransactions = new ArrayList<>();
    List<WebElement> debitTransactions = new ArrayList<>(getWebElementsList(debitTransaction));
    List<WebElement> creditTransactions = new ArrayList<>(getWebElementsList(creditTransaction));
    creditTransactions.forEach((transaction) -> {
      allTransactions.add((Double.parseDouble(
          transaction.getText().replaceAll(",", "").replaceAll(" ", "")
              .substring(0, transaction.getText().length() - 5))));
    });
    debitTransactions.forEach((transaction) -> {
      allTransactions.add(Double.parseDouble(transaction.getText().replaceAll(",", "")
          .substring(2, transaction.getText().length() - 4)));
    });
    allTransactions.forEach(System.out::println);
    return allTransactions;
  }
}
