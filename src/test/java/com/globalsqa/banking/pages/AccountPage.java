package com.globalsqa.banking.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selectors.byCssSelector;

public class AccountPage {
    public SelenideElement accountInfo = $(".borderM.padT20");
    public SelenideElement transactionsButton = $(byXpath("//button[contains(text(),'Transactions')]"));
    public SelenideElement depositButton = $(byXpath("//button[contains(text(),'Deposit')]"));
    public SelenideElement logoutButton = $(byText("Logout"));
    public SelenideElement welcomeMessage = $("strong > span.fontBig.ng-binding");

    public SelenideElement amountInput = $(byXpath("//input[@placeholder='amount']"));
    public SelenideElement depositSubmitButton = $(byXpath("//button[@type='submit'][text()='Deposit']"));
    public SelenideElement depositSuccessMessage = $(byCssSelector("span.error.ng-binding[ng-show='message']"));

    public SelenideElement transactionsTable = $("table.table.table-bordered.table-striped");
    public SelenideElement backButton = $(byText("Back"));


    public String getAccountNumber() { return accountInfo.$$("strong").get(1).getText(); }

    public String getBalance() {
        return accountInfo.$$("strong").get(2).getText();
    }

    public String getCurrency() {
        return accountInfo.$$("strong").get(3).getText();
    }

    public AccountPage goToTransactions() {
        transactionsButton.click();
        return this;
    }

    public AccountPage goToDeposit() {
        depositButton.click();
        return this;
    }

    public AccountPage makeDeposit(String amount) {
        goToDeposit();
        amountInput.setValue(amount);
        depositSubmitButton.click();
        return this;
    }

    public CustomerLoginPage logout() {
        logoutButton.click();
        return new CustomerLoginPage();
    }
}

