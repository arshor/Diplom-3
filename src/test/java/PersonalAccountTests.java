import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageobject.HomePageStellarBurgers;
import pageobject.LoginPageStellarBurgers;
import pageobject.PersonalAccountPageStellarBurgers;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.Assert.assertTrue;

public class PersonalAccountTests {

    private User user;
    private UserClient userClient;
    private WebDriver driver;
    private ValidatableResponse response;
    private String accessToken;
    public static final String HOME_PAGE_URL = "https://stellarburgers.nomoreparties.site/";

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(HOME_PAGE_URL);

        user = UserGenerator.getRandom();
        userClient = new UserClient();
        response = userClient.createUser(user);
    }

    @After
    public void tearDown() {
        driver.quit();;
        if (response.extract().statusCode() == SC_OK) {
            accessToken = response.extract().path("accessToken");
            userClient.deleteUser(accessToken.split(" ")[1]);
        }
    }

    @Test
    @DisplayName("Тест перехода в Личный кабинет")
    public void gotoPersonalAccountShowsOk() {

        HomePageStellarBurgers objHomePageStellarBurgers = new HomePageStellarBurgers(driver);
        objHomePageStellarBurgers.clickLoginAccount();

        LoginPageStellarBurgers objLoginPageStellarBurgers = new LoginPageStellarBurgers(driver);
        objLoginPageStellarBurgers.enterEmail(user.getEmail());
        objLoginPageStellarBurgers.enterPassword(user.getPassword());
        objLoginPageStellarBurgers.clickEnter();
        objHomePageStellarBurgers.clickPersonalAccount();

        PersonalAccountPageStellarBurgers objPersonalAccountPageStellarBurgers = new PersonalAccountPageStellarBurgers(driver);
        boolean isSaveButtonDisplayed = objPersonalAccountPageStellarBurgers.checkLoadPersonalAccountPage();

        assertTrue(isSaveButtonDisplayed);
    }

    @Test
    @DisplayName("Тест перехода из личного кабинета в конструктор по клику на Конструктор")
    public void gotoConstructorByClickConstructorFromPersonalAccountShowsOk() {

        HomePageStellarBurgers objHomePageStellarBurgers = new HomePageStellarBurgers(driver);
        objHomePageStellarBurgers.clickLoginAccount();

        LoginPageStellarBurgers objLoginPageStellarBurgers = new LoginPageStellarBurgers(driver);
        objLoginPageStellarBurgers.enterEmail(user.getEmail());
        objLoginPageStellarBurgers.enterPassword(user.getPassword());
        objLoginPageStellarBurgers.clickEnter();
        objHomePageStellarBurgers.clickPersonalAccount();

        PersonalAccountPageStellarBurgers objPersonalAccountPageStellarBurgers = new PersonalAccountPageStellarBurgers(driver);
        objPersonalAccountPageStellarBurgers.clickConstructor();
        boolean isHomePageDisplayed = objHomePageStellarBurgers.checkLoadHomePageAfterLogin();

        assertTrue(isHomePageDisplayed);
    }

    @Test
    @DisplayName("Тест перехода из личного кабинета в конструктор по клику на логотип Stellar Burgers")
    public void gotoConstructorByClickLogoFromPersonalAccountShowsOk() {

        HomePageStellarBurgers objHomePageStellarBurgers = new HomePageStellarBurgers(driver);
        objHomePageStellarBurgers.clickLoginAccount();

        LoginPageStellarBurgers objLoginPageStellarBurgers = new LoginPageStellarBurgers(driver);
        objLoginPageStellarBurgers.enterEmail(user.getEmail());
        objLoginPageStellarBurgers.enterPassword(user.getPassword());
        objLoginPageStellarBurgers.clickEnter();
        objHomePageStellarBurgers.clickPersonalAccount();

        PersonalAccountPageStellarBurgers objPersonalAccountPageStellarBurgers = new PersonalAccountPageStellarBurgers(driver);
        objPersonalAccountPageStellarBurgers.clickLogo();
        boolean isHomePageDisplayed = objHomePageStellarBurgers.checkLoadHomePageAfterLogin();

        assertTrue(isHomePageDisplayed);
    }

    @Test
    @DisplayName("Тест выхода по кнопке Выйти в личном кабинете")
    public void quitFromAccountByClickQuitFromPersonalAccountShowsOk() {

        HomePageStellarBurgers objHomePageStellarBurgers = new HomePageStellarBurgers(driver);
        objHomePageStellarBurgers.clickLoginAccount();

        LoginPageStellarBurgers objLoginPageStellarBurgers = new LoginPageStellarBurgers(driver);
        objLoginPageStellarBurgers.enterEmail(user.getEmail());
        objLoginPageStellarBurgers.enterPassword(user.getPassword());
        objLoginPageStellarBurgers.clickEnter();
        objHomePageStellarBurgers.clickPersonalAccount();

        PersonalAccountPageStellarBurgers objPersonalAccountPageStellarBurgers = new PersonalAccountPageStellarBurgers(driver);
        objPersonalAccountPageStellarBurgers.clickQuit();
        boolean isLoginPageDisplayed = objLoginPageStellarBurgers.checkLoadLoginPage();

        assertTrue(isLoginPageDisplayed);
    }
}
