package org.example;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;


public class MediaExpertTest {



    static Playwright playwright;
    static Browser browser;

    // New instance for each test method.
    BrowserContext context;
    Page page;

    @BeforeAll
    static void launchBrowser() {
        playwright = Playwright.create();
        BrowserType.LaunchOptions options=new BrowserType.LaunchOptions().setHeadless(true);
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
    void mediaExpertChooseProduct() throws InterruptedException {
        page.navigate("https://www.mediaexpert.pl/");
        page.getByText("ZAAKCEPTUJ WSZYSTKIE").locator("nth=1").click();

//        Locator productSection=page.locator("a[href='/komputery-i-tablety']").
//                nth(1);
//        productSection.click();
//        Locator macParent=page.locator("a").filter(new Locator.FilterOptions().setHasText(" MacBooki ")).nth(1);
////        Locator macDescendant=macParent.getByAltText("MacBooki");
////        page.locator("a:has(img[alt='MacBooki'])").click();
//        macParent.click();
//        page.getByText("30 produktów").first().click();
//        page.getByText("5 produktów", new Page.GetByTextOptions().setExact(true)).click();
////        Thread.sleep(20000);
//
//        //Search for product, and click on corresponding add button
//        Locator rows=page.locator("a").filter(new Locator.FilterOptions().setHasText(" Laptop"));
//
//        Locator content=page.locator("div").locator("h2").locator("a[href*='/komputery-i-tablety/laptopy-i-ultrabooki/laptopy/']").filter(new Locator.FilterOptions().setHasText(" Laptop"));
//        int count= content.count();
//        int indexOfProduct=0;
//        for(int i=0;i<count;++i){
//            if ((content.nth(i).textContent()).contains("Gwiezdna szarość")){
//                System.out.println("yes");
//                indexOfProduct=i;
//            }
//
//        }System.out.println(indexOfProduct);
//
//        page.getByText("Do koszyka").nth(indexOfProduct).click();
//        Locator closePopUp=page.locator("div[data-v-7d43fb33]").locator("div[data-v-7d43fb33]").locator("i[data-v-7d43fb33]");
//        closePopUp.click();
//
//        Thread.sleep(2000000);

    }
}
