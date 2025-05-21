package com.globalsqa.banking.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byXpath;

public class CustomersPage {
    public SelenideElement searchCustomerInput = $(byXpath("//input[@placeholder=\'Search Customer\']"));
    public ElementsCollection customerRows = $$("table > tbody > tr");

    public CustomersPage searchCustomer(String searchTerm) {
        searchCustomerInput.setValue(searchTerm);
        return this;
    }

    public SelenideElement getCustomerRow(String firstName) {
        return customerRows.filterBy(text(firstName)).first();
    }

    public BankManagerPage deleteCustomer(String firstName) {
        SelenideElement row = getCustomerRow(firstName);
        row.$(byXpath(".//button[text()=\'Delete\']")).click();
        return new BankManagerPage();
    }
}

