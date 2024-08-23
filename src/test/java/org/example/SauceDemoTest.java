package org.example;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SauceDemoTest {
    // Shared between all tests in this class.
    static Playwright playwright;
    static Browser browser;

    // New instance for each test method.
    BrowserContext context;
    Page page;

    @BeforeAll
    static void launchBrowser() {
        playwright = Playwright.create();
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
    void LoginIntoSauceDemo() {
        page.navigate("https://www.saucedemo.com/");
        Locator user = page.getByPlaceholder("Username");
        Locator password = page.getByPlaceholder("Password");
//        Locator button = page.getByText("Login");

        Locator saveButton = page.getByRole(AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Login"));
//        assertThat(start).containsText("Swag Labs");
//        String text=start.textContent();

        user.fill("standard_user");
        password.fill("secret_sauce");
        saveButton.click();
        System.out.println(page.title());
//        assertEquals("Swag Labs", text);
    }

    @Test
    void addProductsToCart() throws InterruptedException {
        LoginIntoSauceDemo();
        Locator firstButton = page.getByText("Add to cart").locator("nth=0");
        Locator secButton = page.getByText("Add to cart").locator("nth=1");
        Locator shopButton = page.locator("data-test=shopping-cart-link");
        firstButton.click();
        shopButton.click();

    }
    @Test
    void checkout() throws InterruptedException {
        addProductsToCart();
        Locator checkoutButton=page.getByText("Checkout");
        checkoutButton.click();
        Thread.sleep(20000);

    }
//    @Test
//    void shouldSearchWiki() {
//        page.navigate("https://www.wikipedia.org/");
//        page.locator("input[name=\"search\"]").click();
//        page.locator("input[name=\"search\"]").fill("playwright");
//        page.locator("input[name=\"search\"]").press("Enter");
//        assertEquals("https://en.wikipedia.org/wiki/Playwright", page.url());
//    }
}
