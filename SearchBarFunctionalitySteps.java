package Stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import io.cucumber.java.After;
import io.cucumber.java.Before;

import java.time.Duration;

public class SearchBarFunctionalitySteps {
    WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\vaish\\Downloads\\chromedriver-win32\\chromedriver-win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Given("I am on Login page for search bar")
    public void i_am_on_login_page_for_search_bar() {
        driver.get("https://qamoviesapp.ccbp.tech");
    }

    @When("I enter valid username and password")
    public void i_enter_valid_username_and_password() {
        driver.findElement(By.id("usernameInput")).sendKeys("rahul");
        driver.findElement(By.id("passwordInput")).sendKeys("rahul@2021");
    }

    @And("i click on login button")
    public void i_click_on_login_button() {
        driver.findElement(By.className("login-button")).click();
    }

    @Then("I should be redirect to home page")
    public void i_should_be_redirect_to_home_page() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.urlContains("home"));
        Assert.assertTrue(driver.getCurrentUrl().contains("home"),
                "Did not redirect to home page!");
    }

    @Then("I should search the searchbar and click")
    public void i_should_search_the_searchbar_and_click() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement searchBtn = wait.until(
                ExpectedConditions.elementToBeClickable(By.linkText("Search")));
        searchBtn.click();
    }

    @And("I should redirect to searchbar and search movies")
    public void i_should_redirect_to_searchbar_and_search_movies() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains("search"));
        Assert.assertTrue(driver.getCurrentUrl().contains("search"),
                "Did not redirect to search page!");
    }

    @Then("I should be able to search for Avatar and Venom movies")
    public void i_should_be_able_to_search_for_avatar_and_venom_movies() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Search for "Avatar"
        WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("search-input")));
        searchInput.clear();
        searchInput.sendKeys("Avatar");
        searchInput.sendKeys(Keys.ENTER);

        WebElement avatarResult = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("movie-item")));
        Assert.assertTrue(avatarResult.isDisplayed(),
                "Avatar search result not visible!");

        // Search for "Venom"
        searchInput = driver.findElement(By.className("search-input"));
        searchInput.clear();
        searchInput.sendKeys("Venom");
        searchInput.sendKeys(Keys.ENTER);

        WebElement venomResult = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.className("movie-item")));
        Assert.assertTrue(venomResult.isDisplayed(),
                "Venom search result not visible!");
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
