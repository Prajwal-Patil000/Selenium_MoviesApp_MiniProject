package Stepdefinitions;

// Cucumber imports
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

// Selenium imports
// TestNG imports
// Java imports

public class AccountFunctionalitySteps {
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

    @Given("User navigates to Account page")
    public void user_navigates_to_account_page() {
        wait.until(ExpectedConditions.elementToBeClickable(By.id("accountLink"))).click();
    }

    @Then("User details and logout option should be visible")
    public void user_details_and_logout_option_should_be_visible() {
        Assert.assertTrue(driver.findElement(By.id("logoutButton")).isDisplayed(), "Logout button missing");
    }

    @When("User logs out from account")
    public void user_logs_out_from_account() {
        driver.findElement(By.id("logoutButton")).click();
    }

    @Then("User should be redirected to Login page again")
    public void user_should_be_redirected_to_login_page_again() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("usernameInput")));
        Assert.assertTrue(driver.getCurrentUrl().contains("qamoviesapp.ccbp.tech"));
    }

    @After
    public void teardown() {
        driver.quit();
    }
}
