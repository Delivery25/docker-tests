package com.globalsqa.banking.tests;

import com.codeborne.selenide.Selenide;
import com.globalsqa.banking.pages.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class BankManagerTests extends BaseTest {

    private BankManagerPage bankManagerPage;

    @BeforeMethod
    public void pageSetup() {
        MainPage mainPage = new MainPage();
        bankManagerPage = mainPage.goToBankManagerLogin();
    }

    private String getUniqueId() {
        return String.valueOf(System.currentTimeMillis()).substring(7);
    }

    @Test(description = "Bank Manager can add a new customer")
    public void bankManagerShouldBeAbleToAddCustomer() {
        AddCustomerPage addCustomerPage = bankManagerPage.goToAddCustomer();
        String uniqueId = getUniqueId();
        String firstName = "TestF" + uniqueId;
        String lastName = "TestL" + uniqueId;
        String postCode = "P" + uniqueId;

        addCustomerPage.addCustomer(firstName, lastName, postCode);
        String alertText = Selenide.switchTo().alert().getText();
        Selenide.switchTo().alert().accept();
        assertTrue(alertText.contains("Customer added successfully with customegr id"), "Alert message for add customer is incorrect.");

        CustomersPage customersPage = bankManagerPage.goToCustomers();
        customersPage.searchCustomer(firstName);
        customersPage.getCustomerRow(firstName).shouldBe(visible).shouldHave(text(lastName)).shouldHave(text(postCode));
    }

    @Test(description = "Bank Manager can open an account for an existing customer")
    public void bankManagerShouldBeAbleToOpenAccount() {
        AddCustomerPage addCustomerPage = bankManagerPage.goToAddCustomer();
        String uniqueId = getUniqueId();
        String firstName = "OpenF" + uniqueId;
        String lastName = "OpenL" + uniqueId;
        String postCode = "PO" + uniqueId;
        String customerFullName = firstName + " " + lastName;

        addCustomerPage.addCustomer(firstName, lastName, postCode);
        Selenide.switchTo().alert().accept();

        OpenAccountPage openAccountPage = bankManagerPage.goToOpenAccount();
        String currency = "Dollar";
        openAccountPage.openAccount(customerFullName, currency);

        String alertText = Selenide.switchTo().alert().getText();
        Selenide.switchTo().alert().accept();
        assertTrue(alertText.contains("Account created successfully with account Number"), "Alert message for open account is incorrect.");
    }

    @Test(description = "Bank Manager can delete a customer")
    public void bankManagerShouldBeAbleToDeleteCustomer() {
        AddCustomerPage addCustomerPage = bankManagerPage.goToAddCustomer();
        String uniqueId = getUniqueId();
        String firstName = "DelF" + uniqueId;
        String lastName = "DelL" + uniqueId;
        String postCode = "PD" + uniqueId;

        addCustomerPage.addCustomer(firstName, lastName, postCode);
        Selenide.switchTo().alert().accept();

        CustomersPage customersPage = bankManagerPage.goToCustomers();
        customersPage.searchCustomer(firstName);
        customersPage.getCustomerRow(firstName).shouldBe(visible);

        customersPage.deleteCustomer(firstName);

        bankManagerPage.goToCustomers();
        customersPage.searchCustomer(firstName);
        int rowsFound = customersPage.customerRows.filterBy(text(firstName)).size();
        assertEquals(rowsFound, 0, "Customer " + firstName + " should have been deleted.");
    }
}

