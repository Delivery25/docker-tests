package com.globalsqa.banking.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byText;

public class OpenAccountPage {
    public SelenideElement customerSelect = $(byId("userSelect"));
    public SelenideElement currencySelect = $(byId("currency"));
    public SelenideElement processButton = $(byText("Process"));

    public BankManagerPage openAccount(String customerName, String currency) {
        customerSelect.selectOption(customerName);
        currencySelect.selectOption(currency);
        processButton.click();

        return new BankManagerPage();
    }
}

