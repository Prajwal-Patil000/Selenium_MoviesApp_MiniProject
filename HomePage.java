package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;


public class HomePage {

    public WebDriver driver;

    // ✅ Login Page Locators
    private final By USERNAME_INPUT = By.id("usernameInput");
    private final By PASSWORD_INPUT = By.id("passwordInput");
    private final By LOGIN_BUTTON = By.className("login-button");

    // ✅ Header / Navbar Locators
    private final By WEBSITE_LOGO = By.className("website-logo");
    private final By NAV_HOME = By.xpath("//a[text()='Home']");
    private final By NAV_POPULAR = By.xpath("//a[text()='Popular']");
    private final By NAV_SEARCH = By.className("search-empty-button");
    private final By NAV_ACCOUNT = By.className("avatar-button");

    // ✅ Home Page Elements
    private final By PLAY_BUTTON = By.className("home-movie-play-button");
    private final By TRENDING_NOW_HEADING = By.xpath("//h1[text()='Trending Now']");
    private final By ORIGINALS_HEADING = By.xpath("//h1[text()='Originals']");
    private final By TRENDING_MOVIES = By.xpath("//h1[text()='Trending Now']/following-sibling::div//img");
    private final By ORIGINALS_MOVIES = By.xpath("//h1[text()='Originals']/following-sibling::div//img");
    private final By SLICK_NEXT_BUTTON = By.xpath("(//button[contains(@class,'slick-next')])[1]");

    // ✅ Footer Elements
    private final By CONTACT_US_TEXT = By.className("contact-us-paragraph");
    private final By GOOGLE_ICON = By.className("google-icon");
    private final By TWITTER_ICON = By.className("twitter-icon");
    private final By INSTAGRAM_ICON = By.className("instagram-icon");
    private final By YOUTUBE_ICON = By.className("youtube-icon");

    // ✅ Constructor
    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // ✅ Login Method
    public void LoginToApplication(String username, String password) {
        driver.findElement(USERNAME_INPUT).sendKeys(username);
        driver.findElement(PASSWORD_INPUT).sendKeys(password);
        driver.findElement(LOGIN_BUTTON).click();
    }

    // ✅ Logo Test
    public boolean isLogoDisplayed() {
        return driver.findElement(WEBSITE_LOGO).isDisplayed();
    }

    // ✅ Navbar Tests
    public boolean isHomeNavbarDisplayed() {
        return driver.findElement(NAV_HOME).isDisplayed();
    }

    public boolean isPopularNavbarDisplayed() {
        return driver.findElement(NAV_POPULAR).isDisplayed();
    }

    public boolean isSearchNavbarDisplayed() {
        return driver.findElement(NAV_SEARCH).isDisplayed();
    }

    public boolean isAccountNavbarDisplayed() {
        return driver.findElement(NAV_ACCOUNT).isDisplayed();
    }

    // ✅ Play Button
    public boolean isPlayButtonDisplayed() {
        return driver.findElement(PLAY_BUTTON).isDisplayed();
    }

    // ✅ Section Headings
    public boolean isTrendingNowHeadingDisplayed() {
        return driver.findElement(TRENDING_NOW_HEADING).isDisplayed();
    }

    public boolean isOriginalsHeadingDisplayed() {
        return driver.findElement(ORIGINALS_HEADING).isDisplayed();
    }

    // ✅ Movies in Sections
    public boolean areTrendingMoviesDisplayed() {
        return driver.findElements(TRENDING_MOVIES).size() > 0;
    }

    public boolean areOriginalsMoviesDisplayed() {
        return driver.findElements(ORIGINALS_MOVIES).size() > 0;
    }


    // Check "Next" Button Functionality in Slick Carousel
    public String getCurrentMoviePoster() {
        WebElement currentPoster = driver.findElement(By.cssSelector(".slick-active img"));
        return currentPoster.getAttribute("src");
    }

    public void clickNextInSlick() {
        WebElement nextButton = driver.findElement(By.cssSelector(".slick-next"));
        nextButton.click();
    }

    public boolean checkSlickNextFunctionality() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Actions actions = new Actions(driver);

        List<WebElement> before = driver.findElements(TRENDING_MOVIES);
        if (before.isEmpty()) return false;

        String firstBefore = before.get(0).getAttribute("src");
        WebElement nextButton = driver.findElement(SLICK_NEXT_BUTTON);

        // Scroll into view
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", nextButton);
        actions.moveToElement(nextButton).pause(Duration.ofMillis(500)).click().perform();

        // Wait for next image to change
        boolean imageChanged = wait.until(driver -> {
            List<WebElement> after = driver.findElements(TRENDING_MOVIES);
            if (after.isEmpty()) return false;
            String firstAfter = after.get(0).getAttribute("src");
            return !firstAfter.equals(firstBefore);
        });

        // Debugging log (optional)
        System.out.println("Before image: " + firstBefore);
        List<WebElement> after = driver.findElements(TRENDING_MOVIES);
        if (!after.isEmpty())
            System.out.println("After image: " + after.get(0).getAttribute("src"));

        return imageChanged;
    }

    // ✅ Contact Us Section
    public boolean isContactUsSectionDisplayed() {
        boolean iconsVisible = driver.findElement(GOOGLE_ICON).isDisplayed()
                && driver.findElement(TWITTER_ICON).isDisplayed()
                && driver.findElement(INSTAGRAM_ICON).isDisplayed()
                && driver.findElement(YOUTUBE_ICON).isDisplayed();
        boolean textVisible = driver.findElement(CONTACT_US_TEXT).isDisplayed();
        return iconsVisible && textVisible;
    }
}
