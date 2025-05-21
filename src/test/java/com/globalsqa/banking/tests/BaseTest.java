package com.globalsqa.banking.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.globalsqa.banking.config.TestConfig;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.*;

public class BaseTest {

    @BeforeClass
    @Parameters(value = {"browser"})
    public void setUpClass(@Optional String browser) {
        TestConfig config = TestConfig.getInstance();

        if (browser != null && !browser.isEmpty()) {
            Configuration.browser = browser;
        } else {
            Configuration.browser = config.getBrowser();
        }

        Configuration.screenshots = true;
        Configuration.savePageSource = true;
        Configuration.reportsFolder = "target/selenide-reports";

        setupBrowserOptions(config);
    }

    private void setupBrowserOptions(TestConfig config) {
        switch (Configuration.browser.toLowerCase()) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();

                if (config.isHeadless()) {
                    chromeOptions.addArguments("--headless");
                }

                Configuration.browserCapabilities = chromeOptions;
                break;
            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();

                if (config.isHeadless()) {
                    firefoxOptions.addArguments("-headless");
                }

                Configuration.browserCapabilities = firefoxOptions;
                break;
            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();

                if (config.isHeadless()) {
                    edgeOptions.addArguments("--headless");
                }

                Configuration.browserCapabilities = edgeOptions;
                break;
        }
    }

    @BeforeMethod
    public void openBrowser() {
        Selenide.open("/");
    }

    @AfterMethod
    public void closeBrowser() {
        Selenide.closeWebDriver();
    }
}

