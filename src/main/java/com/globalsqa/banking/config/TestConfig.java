package com.globalsqa.banking.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TestConfig {
    private static final Properties properties = new Properties();
    private static final String CONFIG_FILE = "config.properties";
    private static TestConfig instance;

    private TestConfig() {
        loadProperties();
        setPropertiesFromEnv();
    }

    public static TestConfig getInstance() {
        if (instance == null) {
            instance = new TestConfig();
        }
        return instance;
    }

    private void loadProperties() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            if (input == null) {
                System.err.println("Не удалось найти " + CONFIG_FILE);
                return;
            }
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void setPropertiesFromEnv() {
        String envBrowser = System.getenv("BROWSER_TYPE");
        if (envBrowser != null && !envBrowser.isEmpty()) {
            properties.setProperty("browser", envBrowser);
        }

        String remoteUrl = System.getenv("SELENIDE_REMOTE");
        if (remoteUrl != null && !remoteUrl.isEmpty()) {
            properties.setProperty("remote", "true");
            properties.setProperty("gridUrl", remoteUrl);
        }

        String headless = System.getenv("SELENIDE_HEADLESS");
        if (headless != null && !headless.isEmpty()) {
            properties.setProperty("headless", headless.toLowerCase());
        }

        System.setProperty("selenide.browser", getBrowser());
        if (isRemote()) {
            System.setProperty("selenide.remote", getGridUrl());
        }
        if (isHeadless()) {
            System.setProperty("selenide.headless", "true");
        }
        System.setProperty("selenide.baseUrl", getBaseUrl());
        System.setProperty("selenide.timeout", String.valueOf(getTimeout()));
    }

    public String getBaseUrl() {
        return properties.getProperty("baseUrl", "https://www.globalsqa.com/angularJs-protractor/BankingProject");
    }

    public String getBrowser() {
        return properties.getProperty("browser", "chrome");
    }

    public long getTimeout() {
        return Long.parseLong(properties.getProperty("timeout", "30000"));
    }

    public boolean isRemote() {
        return Boolean.parseBoolean(properties.getProperty("remote", "false"));
    }

    public String getGridUrl() {
        return properties.getProperty("gridUrl", "http://selenium-hub:4444/wd/hub");
    }

    public boolean isHeadless() {
        return Boolean.parseBoolean(properties.getProperty("headless", "false"));
    }
}
