package Stepdefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class PopularFunctionalitySteps {

    WebDriver driver;
    WebDriverWait wait;

    @Given("I am on the Movies App homepage")
    public void i_am_on_the_movies_app_homepage() {
        // ✅ FIXED: Path matches your working TestNG code exactly
        System.setProperty("webdriver.chrome.driver",
                "C:\\Users\\ADMIN\\Downloads\\chromedriver-win32\\chromedriver-win32\\chromedriver.exe");

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        driver.get("https://qamoviesapp.ccbp.tech/");

        // Login Logic
        driver.findElement(By.id("usernameInput")).sendKeys("rahul");
        driver.findElement(By.id("passwordInput")).sendKeys("rahul@2021");
        driver.findElement(By.className("login-button")).click();

        // Wait for successful login (URL change or Logo presence)
        wait.until(ExpectedConditions.urlContains("/"));
    }

    @When("I click on the Popular button")
    public void i_click_on_the_popular_button() {
        // ✅ FIXED: Use a more robust locator (Link Text) and wait for it to be clickable
        WebElement popularBtn = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Popular")));
        popularBtn.click();
    }

    @Then("I should be navigated to the Popular page")
    public void i_should_be_navigated_to_the_popular_page() {
        // ✅ FIXED: Increased wait and checking specific URL endpoint
        wait.until(ExpectedConditions.urlContains("popular"));
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("popular"), "Navigation to Popular page failed!");
    }

    @And("available movies should be displayed")
    public void available_movies_should_be_displayed() {
        // ✅ FIXED: CRITICAL STEP - Wait for the movie elements to actually appear before counting them
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("movie-icon-item")));

        List<WebElement> movies = driver.findElements(By.className("movie-icon-item"));
        Assert.assertTrue(movies.size() > 0, "No movies are displayed on Popular page!");
    }

    @Then("I close the Popular page browser")
    public void i_close_the_popular_page_browser() {
        if (driver != null) {
            driver.quit();
        }
    }
}
