package Stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class HeaderFunctionalitySteps {
    WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver",
                "C:\\Users\\vaish\\Downloads\\chromedriver-win32\\chromedriver-win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Given("I am on Login page for Header")
    public void i_am_on_login_page_for_header() {
        driver.get("https://qamoviesapp.ccbp.tech/");
    }

    @When("I enter valid username and password for Account for Header")
    public void i_enter_valid_username_and_password_for_header() {
        driver.findElement(By.id("usernameInput")).sendKeys("rahul");
        driver.findElement(By.id("passwordInput")).sendKeys("rahul@2021");
    }

    @And("I click on login button")
    public void i_click_on_login_button() {
        driver.findElement(By.className("login-button")).click();
    }

    @Then("I should be redirected to home page")
    public void i_should_be_redirected_to_home_page() {
        String expectedUrl = "https://qamoviesapp.ccbp.tech/";
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe(expectedUrl));
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, expectedUrl, "URLs do not match");
    }

    @And("I should see the Navbar elements")
    public void i_should_see_the_navbar_elements() {
        Assert.assertTrue(driver.findElement(By.linkText("Home")).isDisplayed(), "Home link not visible");
        Assert.assertTrue(driver.findElement(By.linkText("Popular")).isDisplayed(), "Popular link not visible");
        Assert.assertTrue(driver.findElement(By.className("search-empty-button")).isDisplayed(), "Search button not visible");
        Assert.assertTrue(driver.findElement(By.className("avatar-button")).isDisplayed(), "Account icon not visible");
    }

    @Then("I should be navigated to homepage")
    public void i_should_be_navigated_to_homepage() {
        WebElement homeLink = driver.findElement(By.linkText("Home"));
        homeLink.click();
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.urlToBe("https://qamoviesapp.ccbp.tech/"));
        Assert.assertEquals(driver.getCurrentUrl(), "https://qamoviesapp.ccbp.tech/");
    }

    @Then("I should be navigate to popular page")
    public void i_should_be_navigate_to_popular_page() {
        WebElement popularLink = driver.findElement(By.linkText("Popular"));
        popularLink.click();
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.urlToBe("https://qamoviesapp.ccbp.tech/popular"));
        Assert.assertTrue(driver.getCurrentUrl().contains("/popular"), "Did not navigate to Popular page");
    }

    @When("I should be navigate to search page")
    public void i_should_be_navigate_to_search_page() {
        WebElement searchBtn = driver.findElement(By.className("search-empty-button"));
        searchBtn.click();
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.urlContains("/search"));
        Assert.assertTrue(driver.getCurrentUrl().contains("/search"), "Did not navigate to Search page");
    }

    @Then("I should be Navigate to Account Page Through elements Successfully in header section")
    public void i_should_be_navigate_to_account_page_through_elements_successfully_in_header_section() {
        WebElement accountBtn = driver.findElement(By.className("avatar-button"));
        accountBtn.click();
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.urlContains("/account"));
        Assert.assertTrue(driver.getCurrentUrl().contains("/account"), "Did not navigate to Account page");
    }

    @After
    public void tearDown() {
            driver.quit();

    }
}
