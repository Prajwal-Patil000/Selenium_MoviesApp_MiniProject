package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SearchPage {

    WebDriver driver;
    WebDriverWait wait;

    public SearchPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // ---------- Locators ----------
    @FindBy(id = "usernameInput")
    WebElement usernameField;

    @FindBy(id = "passwordInput")
    WebElement passwordField;

    @FindBy(className = "login-button")
    WebElement loginButton;

    @FindBy(className = "search-empty-button")  // ✅ fixed missing quote and parenthesis
            WebElement searchIcon;

    @FindBy(id = "search")
    WebElement searchInput;

    @FindBy(className = "search-button")
    WebElement searchButton;

    @FindBy(className = "movie-image")
    List<WebElement> movieResults;

    @FindBy(className = "not-found-search-image")
    WebElement failureImage;

    @FindBy(className = "not-found-search-paragraph")
    WebElement failureText;

    // ---------- Actions ----------
    public void login(String username, String password) {
        wait.until(ExpectedConditions.visibilityOf(usernameField)).sendKeys(username);
        passwordField.sendKeys(password);
        loginButton.click();
    }

    public void openSearchPage() {
        wait.until(ExpectedConditions.elementToBeClickable(searchIcon)).click();
    }

    public void searchMovie(String movieName) {
        wait.until(ExpectedConditions.visibilityOf(searchInput));
        searchInput.clear();
        searchInput.sendKeys(movieName);
        searchButton.click();
    }

    public int getSearchResultsCount() {
        try {
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.visibilityOfAllElements(movieResults),
                    ExpectedConditions.visibilityOf(failureImage)
            ));
        } catch (Exception ignored) {
        }
        return movieResults.size();
    }

    public boolean isFailureDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(failureImage));
            wait.until(ExpectedConditions.visibilityOf(failureText));
            return failureImage.isDisplayed() && failureText.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
