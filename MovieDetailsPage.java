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

    @FindBy(css = "div[class*='details'], div[class*='background']")
    WebElement backgroundImage;

    @FindBy(css = "h1[class*='title'], h2[class*='heading']")
    WebElement movieTitle;

    @FindBy(css = "p[class*='description']")
    WebElement movieDescription;

    @FindBy(css = "div[class*='similar'] img")
    List<WebElement> similarMovies;

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
}
