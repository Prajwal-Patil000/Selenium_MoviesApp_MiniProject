package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MovieDetailsPage {

    WebDriver driver;
    WebDriverWait wait;

    // ✅ FIXED: The background is usually on the main container in this app
    @FindBy(className = "movie-details-container")
    WebElement backgroundImage;

    // ✅ FIXED: Specific class for title
    @FindBy(className = "movie-title")
    WebElement movieTitle;

    // ✅ FIXED: Specific class for overview/description
    @FindBy(className = "movie-overview")
    WebElement movieDescription;

    // ✅ FIXED: Locating images inside the similar movies list
    @FindBy(xpath = "//ul[contains(@class, 'similar-movies-list-container')]//img")
    List<WebElement> similarMovies;

    // ✅ NEW: Added Elements as per instruction
    @FindBy(className = "movie-review-container")
    WebElement movieReviewContainer;

    @FindBy(className = "detailed-movie-categories-container")
    WebElement detailedMovieCategoriesContainer;

    public MovieDetailsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public boolean isBackgroundPosterDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(backgroundImage));
            return backgroundImage.isDisplayed();
        } catch (Exception e) {
            System.out.println("Debug: Background Element not found or invisible.");
            return false;
        }
    }

    public boolean isTitleDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(movieTitle));
            return movieTitle.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isDescriptionDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(movieDescription));
            return movieDescription.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean areSimilarMoviesDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfAllElements(similarMovies));
            return !similarMovies.isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isMovieReviewContainerDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(movieReviewContainer));
            return movieReviewContainer.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isDetailedMovieCategoriesDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(detailedMovieCategoriesContainer));
            return detailedMovieCategoriesContainer.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
