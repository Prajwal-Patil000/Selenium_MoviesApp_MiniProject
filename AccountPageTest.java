import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.AccountPage;

import java.time.Duration;

public class AccountPageTest {

    public WebDriver driver;
    public AccountPage accountPageTest;
    public WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\vaish\\Downloads\\chromedriver-win32\\chromedriver-win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        driver.get("https://qamoviesapp.ccbp.tech");
        accountPageTest = new AccountPage(driver);
    }

    @Test
    public void accountPageUiTest() {
        // Login
        accountPageTest.LoginToApplication("rahul", "rahul@2021");

        // Navigate to account page
        driver.findElement(By.className("avatar-button")).click();

        // Verify correct page
        Assert.assertEquals(driver.getCurrentUrl(), "https://qamoviesapp.ccbp.tech/account", "Not navigated to Account page");

        // Check all 8 UI elements
        Assert.assertTrue(accountPageTest.isHeadingDisplayed(), "Heading not displayed");
        Assert.assertTrue(accountPageTest.isMembershipContainerDisplayed(), "Membership container not displayed");
        Assert.assertTrue(accountPageTest.isMembershipHeadingDisplayed(), "Membership heading not displayed");
        Assert.assertTrue(accountPageTest.isMembershipUsernameDisplayed(), "Membership username not displayed");
        Assert.assertTrue(accountPageTest.isMembershipPasswordDisplayed(), "Membership password not displayed");
        Assert.assertTrue(accountPageTest.isPlanContainerDisplayed(), "Plan container not displayed");
        Assert.assertTrue(accountPageTest.isPlanParagraphDisplayed(), "Plan paragraph not displayed");
        Assert.assertTrue(accountPageTest.isLogoutButtonDisplayed(), "Logout button not displayed");
    }

    // ✅ New Test: Verify Logout button functionality
    @Test
    public void logoutButtonFunctionalityTest() {
        // Login
        accountPageTest.LoginToApplication("rahul", "rahul@2021");

        // Navigate to account page
        driver.findElement(By.className("avatar-button")).click();
        wait.until(ExpectedConditions.urlContains("/account"));

        // Click Logout button
        driver.findElement(By.className("logout-button")).click();

        // Wait for Login page
        wait.until(ExpectedConditions.urlToBe("https://qamoviesapp.ccbp.tech/login"));

        // Verify navigated to Login page
        Assert.assertEquals(driver.getCurrentUrl(), "https://qamoviesapp.ccbp.tech/login",
                "Logout did not navigate to Login page");

        // Optional: verify username input displayed
        Assert.assertTrue(driver.findElement(By.id("usernameInput")).isDisplayed(),
                "Username input not visible after logout");
    }


    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
