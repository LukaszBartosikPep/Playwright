package org.example;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.*;

public class INT_Division_Creation {

    static Playwright playwright;
    static Browser browser;

    // New instance for each test method.
    BrowserContext context;
    Page page;

    @BeforeAll
    static void launchBrowser() {
        playwright = Playwright.create();
        BrowserType.LaunchOptions options = new BrowserType.LaunchOptions().setHeadless(false);
        browser = playwright.chromium().launch(options);
    }

//    @AfterAll
//    static void closeBrowser() {
//        playwright.close();
//    }

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
    public void divisionCreationRMFCS() throws InterruptedException {
        page.navigate("https://rex.retail.eu-frankfurt-1.ocs.oraclecloud.com/rgbu-rex-pepp-dev3-mfcs/Rms/faces/RmsHome");
        FrameLocator iframe = page.frameLocator("iframe[name='trustarc_cm']");
        iframe.getByText("Zaakceptuj wszystkie").click();

        page.getByLabel("PepcoADAzure").click();
        Locator tasks = page.locator("[id='pt1:eachSidebarItem:2:l1']").first();
        tasks.click();
        page.getByTitle("Folder: Foundation Data").click();
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Item Foundation").setExact(true)).click();
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Merchandise Hierarchy").setExact(true)).click();
        page.getByTitle("Division").click();
        page.getByTitle("Create").first().click();
    }
}