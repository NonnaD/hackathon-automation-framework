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


  public CustomerPage(WebDriver webDriver) {
    super(webDriver);
  }

  public String getUserRole() {
    return getElementText(userRole);
  }

  public void clickTransactionAmount() {
    clickElement(transactionAmount);
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
    Collections.reverse(debitTransactions);
    Collections.reverse(creditTransactions);
    debitTransactions.forEach((transaction) -> {
      allTransactions.add(Double.parseDouble(transaction.getText().replaceAll(",","").substring(2, transaction.getText().length() - 4)));
    });
    creditTransactions.forEach((transaction) -> {
      allTransactions.add((Double.parseDouble(transaction.getText().replaceAll(",","").replaceAll(" ", "").substring(0, transaction.getText().length() - 5))));
    });
    allTransactions.forEach(System.out::println);
    return allTransactions;
  }
}
