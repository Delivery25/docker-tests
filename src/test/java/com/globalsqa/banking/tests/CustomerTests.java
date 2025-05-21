package com.globalsqa.banking.tests;

import com.globalsqa.banking.pages.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.cssClass;
import static org.testng.Assert.assertNotEquals;

public class CustomerTests extends BaseTest {

    private MainPage mainPage;
    private CustomerLoginPage customerLoginPage;
    private AccountPage accountPage;

    @BeforeMethod
    public void customerLoginSetup() {
        mainPage = new MainPage();
        customerLoginPage = mainPage.goToCustomerLogin();
        accountPage = customerLoginPage.loginAsUser("Hermoine Granger");
    }

    @Test(description = "Customer can login and view account details")
    public void customerShouldBeAbleToLoginAndSeeAccountDetails() {
        accountPage.welcomeMessage.shouldBe(visible).shouldHave(text("Hermoine Granger"));
        assertNotEquals(accountPage.getAccountNumber(), "", "Account number should not be empty");
        assertNotEquals(accountPage.getBalance(), "", "Balance should not be empty");
        assertNotEquals(accountPage.getCurrency(), "", "Currency should not be empty");
    }

    @Test(description = "Customer can make a deposit")
    public void customerShouldBeAbleToMakeDeposit() {
        String depositAmount = "100";
        accountPage.makeDeposit(depositAmount);
        accountPage.depositSuccessMessage.shouldHave(text("Deposit Successful"));
    }

    @Test(description = "Customer can view transactions")
    public void customerShouldBeAbleToViewTransactions() {
        String depositAmount = "20";
        accountPage.makeDeposit(depositAmount);
        accountPage.depositSuccessMessage.shouldHave(text("Deposit Successful"));

        accountPage.goToTransactions();
        accountPage.transactionsTable.shouldBe(visible);
        accountPage.backButton.click();
    }

    @Test(description = "Customer can logout")
    public void customerShouldBeAbleToLogout() {
        accountPage.logout();
        customerLoginPage.userSelect.shouldBe(visible);
        customerLoginPage.loginButton.should(exist).shouldHave(cssClass("ng-hide"));
    }
}

