package com.globalsqa.banking.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selectors.byXpath;

public class AddCustomerPage {
    public SelenideElement firstNameInput = $(byXpath("//input[@placeholder='First Name']"));
    public SelenideElement lastNameInput = $(byXpath("//input[@placeholder='Last Name']"));
    public SelenideElement postCodeInput = $(byXpath("//input[@placeholder='Post Code']"));
    public SelenideElement addCustomerSubmitButton = $(byXpath("//button[@type='submit'][normalize-space()='Add Customer']"));

    public BankManagerPage addCustomer(String firstName, String lastName, String postCode) {
        firstNameInput.setValue(firstName);
        lastNameInput.setValue(lastName);
        postCodeInput.setValue(postCode);
        addCustomerSubmitButton.click();

        return new BankManagerPage();
    }
}

