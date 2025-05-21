package com.globalsqa.banking.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byText;

public class CustomerLoginPage {
    public SelenideElement userSelect = $(byId("userSelect"));
    public SelenideElement loginButton = $(byText("Login"));

    public AccountPage loginAsUser(String userName) {
        userSelect.selectOption(userName);
        loginButton.click();
        return new AccountPage();
    }
}

