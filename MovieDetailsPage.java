package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class MovieDetailsPage {
    public WebDriver driver;

    By LoginButton = By.className("login-button");
    By USERNAME = By.id("usernameInput");
    By PASSWORD = By.id("passwordInput");

    public MovieDetailsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isMovieHeadingDisplayed() {
        WebElement heading = driver.findElement(By.className("movie-title"));
        return heading.isDisplayed();
    }

    public boolean isMovieReviewDisplayed() {
        WebElement review = driver.findElement(By.className("movie-review-container"));
        return review.isDisplayed();
    }

    public boolean isMovieOverviewDisplayed() {
        WebElement overview = driver.findElement(By.className("movie-overview"));
        return overview.isDisplayed();
    }

    public boolean isMoviePlaybuttonDisplayed() {
        WebElement playBtn = driver.findElement(By.className("play-button"));
        return playBtn.isDisplayed();
    }

    public boolean isMovieCategoriesDisplayed() {
        WebElement categories = driver.findElement(By.className("detailed-movie-categories-container"));
        return categories.isDisplayed();
    }

    public void LoginToApplication(String username, String password) {
        driver.findElement(USERNAME).sendKeys(username);
        driver.findElement(PASSWORD).sendKeys(password);
        driver.findElement(LoginButton).click();
    }
}
