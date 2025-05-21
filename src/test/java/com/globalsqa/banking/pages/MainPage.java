package com.globalsqa.banking.pages;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.Selectors;

import static com.codeborne.selenide.Selenide.$;

public class MainPage {
    public SelenideElement customerLoginButton = $(Selectors.byText("Customer Login"));
    public SelenideElement bankManagerLoginButton = $(Selectors.byText("Bank Manager Login"));

    public CustomerLoginPage goToCustomerLogin() {
        customerLoginButton.click();
        return new CustomerLoginPage();
    }

    public BankManagerPage goToBankManagerLogin() {
        bankManagerLoginButton.click();
        return new BankManagerPage();
    }
}

