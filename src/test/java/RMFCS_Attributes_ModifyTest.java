import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;

public class RMFCS_Attributes_ModifyTest {



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


//    https://pepco.atlassian.net/browse/NEPTEST-6180
    @Test
    public void updateSupplierRMFCS() throws InterruptedException {
        page.navigate("https://rex.retail.eu-frankfurt-1.ocs.oraclecloud.com/rgbu-rex-pepp-dev3-mfcs/Rms/faces/RmsHome");
//
        FrameLocator iframe = page.frameLocator("iframe[name='trustarc_cm']");
        iframe.getByText("Zaakceptuj wszystkie").click();
        page.getByLabel("PepcoADAzure").click();


//        page.getByLabel("someone@example.com").fill("lbartosik@pepco.eu");
//        page.getByText("Dalej").click();
//        page.locator("[placeholder='Hasło']").fill("X+EnDh6Tzv");
//        page.getByText("Zaloguj").click();
//        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Tak")).click();
//        page.getByTitle("Nawigator Aplikacji").click();
//        context.onPage(newPage -> {
//            System.out.println("New page detected!");
//            newPage.bringToFront(); // Bring the new tab to the front
//            page = newPage; // Switch the page reference to the new tab
//        });
//        page.getByTitle("Merchandising").click();

        Locator tasks = page.locator("[id='pt1:eachSidebarItem:2:l1']").first();
        tasks.click();
//        Thread.sleep(200000);
        page.getByTitle("Folder: Foundation Data").click();
        page.getByTitle("Folder: Supplier and Partner Foundation").click();
        page.getByTitle("Manage Supplier").click();
        Locator supplierID = page.getByLabel("Supplier", new Page.GetByLabelOptions().setExact(true));
        supplierID.fill("11111816");
        supplierID.press("Enter");
//        page.locator("td[id=':_ATp:resId1:0:colSupplier']").first().click();
        page.getByText("Actions").click();
        page.getByText("Edit Supplier", new Page.GetByTextOptions().setExact(true)).click();
        addSuffixByLabel("Supplier Name (Required)");
        addSuffixByHierarchy("Name");
        addSuffixByHierarchy("Phone");
        addSuffixByHierarchy("Email");
        addSuffixByHierarchy("Fax");
        Thread.sleep(200000);

    }
    public void addSuffixByHierarchy(String labelName){
        String suffix = String.valueOf(System.currentTimeMillis());
        Locator InputLocator = page.locator("//tr[td[1]//label[text()='"+labelName+"']]/td[2]//span//input");
        InputLocator.click();
        page.keyboard().press("End");
        InputLocator.type(suffix.substring(0,3));
    }

    public void addSuffixByLabel(String label) {
        String suffix = String.valueOf(System.currentTimeMillis());
        Locator inputSuppName = page.getByLabel(label);
        inputSuppName.click();
        page.keyboard().press("End");
        inputSuppName.type(suffix.substring(0, 3));

    }






}
