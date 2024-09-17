package org.example;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PepcoNET {

    static Playwright playwright;
    static Browser browser;

    // New instance for each test method.
    BrowserContext context;
    Page page;

    @BeforeAll
    static void launchBrowser() {
        playwright = com.microsoft.playwright.Playwright.create();
        BrowserType.LaunchOptions options=new BrowserType.LaunchOptions().setHeadless(false);
        browser = playwright.chromium().launch(options);
    }

    @AfterAll
    static void closeBrowser() {
        playwright.close();
    }

    @BeforeEach
    void createContextAndPage() {
        context = browser.newContext();
        page = context.newPage();
    }

    @AfterEach
    void closeContext() {
        context.close();
    }

    @Test
    void pepcoNetTest() throws InterruptedException {
        page.navigate("https://net.pepco.eu/");
        Thread.sleep(2222);
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Search")).click();

    }
}
