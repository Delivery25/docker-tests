package com.globalsqa.banking.pages;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.Selectors;
import static com.codeborne.selenide.Selenide.$;

public class BankManagerPage {
    public SelenideElement addCustomerButton = $(Selectors.byText("Add Customer"));
    public SelenideElement openAccountButton = $(Selectors.byText("Open Account"));
    public SelenideElement customersButton = $(Selectors.byText("Customers"));

    public AddCustomerPage goToAddCustomer() {
        addCustomerButton.click();
        return new AddCustomerPage();
    }

    public OpenAccountPage goToOpenAccount() {
        openAccountButton.click();
        return new OpenAccountPage();
    }

    public CustomersPage goToCustomers() {
        customersButton.click();
        return new CustomersPage();
    }
}

