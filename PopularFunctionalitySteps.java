package Stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import io.cucumber.java.After;
import io.cucumber.java.Before;

import java.time.Duration;

public class PopularFunctionalitySteps {
    WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\vaish\\Downloads\\chromedriver-win32\\chromedriver-win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Given("I am on login page for Popular page")
    public void i_am_on_login_page_for_popular_page() {
        driver.get("https://qamoviesapp.ccbp.tech");
    }

    @When("I enter valid username and password for popular movies page")
    public void i_enter_valid_username_and_password_for_popular_movies_page() {
        driver.findElement(By.id("usernameInput")).sendKeys("rahul");
        driver.findElement(By.id("passwordInput")).sendKeys("rahul@2021");
    }

    @And("i click on login button for popular movies")
    public void i_click_on_login_button_for_popular_movies() {
        driver.findElement(By.className("login-button")).click();
    }

    @And("I navigate to the popular page and click on a movies")
    public void i_navigate_to_the_popular_page_and_click_on_a_movie() {
        driver.findElement(By.linkText("Popular")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("popular"));

        // Click on the first movie in the list
        driver.findElement(By.cssSelector("a[href*='/movies/']")).click();

        wait.until(ExpectedConditions.urlContains("movies"));
    }

    @Then("I should see the Popular page loaded Successfully")
    public void i_should_see_popular_page_loaded_successfully() {
        Assert.assertTrue(driver.getCurrentUrl().contains("movies"),
                "Did not navigate to movie details page from popular section!");
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
