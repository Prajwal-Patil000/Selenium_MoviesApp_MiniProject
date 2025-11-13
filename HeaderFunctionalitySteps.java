package Stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import java.time.Duration;

public class HeaderFunctionalitySteps {
    WebDriver driver;
    WebDriverWait wait;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver",
                "C:\\Users\\vaish\\Downloads\\chromedriver-win32\\chromedriver-win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://qamoviesapp.ccbp.tech/");
        driver.findElement(By.id("usernameInput")).sendKeys("rahul");
        driver.findElement(By.id("passwordInput")).sendKeys("rahul@2021");
        driver.findElement(By.className("login-button")).click();
    }

    @Given("User is logged in on Home page")
    public void user_is_logged_in_on_home_page() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("home-container")));
    }

    @When("User clicks on the Popular link in header")
    public void user_clicks_on_the_popular_link_in_header() {
        driver.findElement(By.linkText("Popular")).click();
    }

    @Then("User should navigate to Popular page")
    public void user_should_navigate_to_popular_page() {
        wait.until(ExpectedConditions.urlContains("/popular"));
        Assert.assertTrue(driver.getCurrentUrl().contains("popular"));
    }

    @After
    public void teardown() {
        driver.quit();
    }
}
