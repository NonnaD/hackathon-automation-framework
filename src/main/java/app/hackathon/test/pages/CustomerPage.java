package app.hackathon.test.pages;

import app.hackathon.test.helpers.PageDriver;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CustomerPage extends PageDriver {

  private By userRole = By.className("logged-user-role");
  private By transactionAmount = By.id("amount");
  private By debitTransaction = By.className("text-success");
  private By creditTransaction = By.className("text-danger");
  private By compareExpenses = By.id("showExpensesChart");
  private By chart = By.id("canvas");
  private By firstAdd = By.xpath("//div[@id='flashSale']/img");
  private By secondAdd = By.xpath("//div[@id='flashSale2']/img");

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
    if(isSecondAddPresent()) {
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
   * This function retrieves all transactions from the Amounts column
   * in order they present on the page
   *
   * @return List<Double> - list of all transactions amounts
   */
  public List<Double> getAllTransactions(){
    List<Double> allTransactions = new ArrayList<>();
    List<WebElement> debitTransactions = new ArrayList<>(getWebElementsList(debitTransaction));
    List<WebElement> creditTransactions = new ArrayList<>(getWebElementsList(creditTransaction));
    creditTransactions.forEach((transaction) -> {
      allTransactions.add((Double.parseDouble(transaction.getText().replaceAll(",","").replaceAll(" ", "").substring(0, transaction.getText().length() - 5))));
    });
    debitTransactions.forEach((transaction) -> {
      allTransactions.add(Double.parseDouble(transaction.getText().replaceAll(",","").substring(2, transaction.getText().length() - 4)));
    });
    allTransactions.forEach(System.out::println);
    return allTransactions;
  }
}
