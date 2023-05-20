import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageobject.HomePageStellarBurgers;

import static org.junit.Assert.assertTrue;

public class SectionsHomePageTests {

    private WebDriver driver;
    public static final String HOME_PAGE_URL = "https://stellarburgers.nomoreparties.site/";

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        //System.setProperty("webdriver.chrome.driver", "src/main/resources/yandexdriver.exe");
        driver = new ChromeDriver();
        driver.get(HOME_PAGE_URL);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    @DisplayName("Тест перехода к разделу Начинки")
    public void gotoFillingsSectionShowsOk() {

        HomePageStellarBurgers objHomePageStellarBurgers = new HomePageStellarBurgers(driver);
        objHomePageStellarBurgers.clickFillings();
        boolean isFillingsSectionDisplayed = objHomePageStellarBurgers.checkDisplayedFillingsSection();

        assertTrue(isFillingsSectionDisplayed);
    }

    @Test
    @DisplayName("Тест перехода к разделу Булки")
    public void gotoBunsSectionShowsOk() {

        HomePageStellarBurgers objHomePageStellarBurgers = new HomePageStellarBurgers(driver);
        objHomePageStellarBurgers.clickFillings();
        objHomePageStellarBurgers.clickBuns();
        boolean isBunsSectionDisplayed = objHomePageStellarBurgers.checkDisplayedBunsSection();

        assertTrue(isBunsSectionDisplayed);
    }

    @Test
    @DisplayName("Тест перехода к разделу Соусы")
    public void gotoSaucesSectionShowsOk() {

        HomePageStellarBurgers objHomePageStellarBurgers = new HomePageStellarBurgers(driver);
        objHomePageStellarBurgers.clickFillings();
        objHomePageStellarBurgers.clickSauces();
        boolean isSaucesSectionDisplayed = objHomePageStellarBurgers.checkDisplayedSaucesSection();

        assertTrue(isSaucesSectionDisplayed);
    }
}
