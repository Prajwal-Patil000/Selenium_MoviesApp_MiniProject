
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;

import java.time.Duration;

public class HeaderPageTest {
    public WebDriver driver;
    public HomePage homePage;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver",
                "C:\\Users\\vaish\\Downloads\\chromedriver-win32\\chromedriver-win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://qamoviesapp.ccbp.tech/");
        homePage = new HomePage(driver);
        homePage.LoginToApplication("rahul", "rahul@2021");
    }

    @Test(priority = 1)
    public void checkTheNavBars() {
        WebElement homeNav = driver.findElement(By.xpath("//a[text()='Home']"));
        WebElement popularNav = driver.findElement(By.xpath("//a[text()='Popular']"));
        WebElement searchIcon = driver.findElement(By.className("search-empty-button"));
        WebElement avatarButton = driver.findElement(By.className("avatar-button"));
        WebElement websiteLogo = driver.findElement(By.className("website-logo"));

        Assert.assertTrue(websiteLogo.isDisplayed(), "Website logo not displayed!");
        Assert.assertTrue(homeNav.isDisplayed(), "Home navbar not displayed!");
        Assert.assertTrue(popularNav.isDisplayed(), "Popular navbar not displayed!");
        Assert.assertTrue(searchIcon.isDisplayed(), "Search icon not displayed!");
        Assert.assertTrue(avatarButton.isDisplayed(), "Avatar button not displayed!");
    }

    @Test(priority = 2)
    public void checkNavigationFunctionality() {
        WebElement popularLink = driver.findElement(By.xpath("//a[text()='Popular']"));
        popularLink.click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://qamoviesapp.ccbp.tech/popular",
                "Not navigated to Popular page!");

        WebElement avatarButton = driver.findElement(By.className("avatar-button"));
        avatarButton.click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://qamoviesapp.ccbp.tech/account",
                "Not navigated to Account page!");

        WebElement homeLink = driver.findElement(By.xpath("//a[text()='Home']"));
        homeLink.click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://qamoviesapp.ccbp.tech/",
                "Not navigated to Home page!");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
