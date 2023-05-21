import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageobject.ForgotPasswordPageStellarBurgers;
import pageobject.HomePageStellarBurgers;
import pageobject.LoginPageStellarBurgers;
import pageobject.RegisterPageStellarBurgers;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.Assert.assertTrue;

public class LoginUserTests {

    private User user;
    private UserClient userClient;
    private WebDriver driver;
    private ValidatableResponse response;
    public static final String HOME_PAGE_URL = "https://stellarburgers.nomoreparties.site/";

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        //System.setProperty("webdriver.chrome.driver", "src/main/resources/yandexdriver.exe");
        driver = new ChromeDriver();
        driver.get(HOME_PAGE_URL);

        user = UserGenerator.getRandom();
        userClient = new UserClient();
        response = userClient.createUser(user);
    }

    @After
    public void tearDown() {
        driver.quit();
        if (response.extract().statusCode() == SC_OK) {
            String accessToken = response.extract().path("accessToken");
            userClient.deleteUser(accessToken.split(" ")[1]);
        }
    }

    @Test
    @DisplayName("Тест входа по кнопке «Войти в аккаунт» на главной странице")
    public void loginUserFromLoginInAccountHomePageShowsOk() {

        HomePageStellarBurgers objHomePageStellarBurgers = new HomePageStellarBurgers(driver);
        objHomePageStellarBurgers.clickLoginAccount();

        LoginPageStellarBurgers objLoginPageStellarBurgers = new LoginPageStellarBurgers(driver);
        objLoginPageStellarBurgers.enterEmail(user.getEmail());
        objLoginPageStellarBurgers.enterPassword(user.getPassword());
        objLoginPageStellarBurgers.clickEnter();
        boolean isPlaceOrderButton = objHomePageStellarBurgers.checkLoadHomePageAfterLogin();

        assertTrue(isPlaceOrderButton);
    }

    @Test
    @DisplayName("Тест входа через кнопку «Личный кабинет» на главной странице")
    public void loginUserFromPersonalAccountHomePageShowsOk() {

        HomePageStellarBurgers objHomePageStellarBurgers = new HomePageStellarBurgers(driver);
        objHomePageStellarBurgers.clickPersonalAccount();

        LoginPageStellarBurgers objLoginPageStellarBurgers = new LoginPageStellarBurgers(driver);
        objLoginPageStellarBurgers.enterEmail(user.getEmail());
        objLoginPageStellarBurgers.enterPassword(user.getPassword());
        objLoginPageStellarBurgers.clickEnter();
        boolean isPlaceOrderButton = objHomePageStellarBurgers.checkLoadHomePageAfterLogin();

        assertTrue(isPlaceOrderButton);
    }

    @Test
    @DisplayName("Тест входа через кнопку в форме регистрации")
    public void loginUserFromRegisterPageShowsOk() {

        HomePageStellarBurgers objHomePageStellarBurgers = new HomePageStellarBurgers(driver);
        objHomePageStellarBurgers.clickLoginAccount();

        LoginPageStellarBurgers objLoginPageStellarBurgers = new LoginPageStellarBurgers(driver);
        objLoginPageStellarBurgers.clickRegister();

        RegisterPageStellarBurgers objRegisterPageStellarBurgers = new RegisterPageStellarBurgers(driver);
        objRegisterPageStellarBurgers.clickEnter();

        objLoginPageStellarBurgers.enterEmail(user.getEmail());
        objLoginPageStellarBurgers.enterPassword(user.getPassword());
        objLoginPageStellarBurgers.clickEnter();
        boolean isPlaceOrderButton = objHomePageStellarBurgers.checkLoadHomePageAfterLogin();

        assertTrue(isPlaceOrderButton);
    }

    @Test
    @DisplayName("Тест входа через кнопку в форме восстановления пароля")
    public void loginUserFromForgotPasswordPageShowsOk() {

        HomePageStellarBurgers objHomePageStellarBurgers = new HomePageStellarBurgers(driver);
        objHomePageStellarBurgers.clickLoginAccount();

        LoginPageStellarBurgers objLoginPageStellarBurgers = new LoginPageStellarBurgers(driver);
        objLoginPageStellarBurgers.clickForgotPassword();

        ForgotPasswordPageStellarBurgers objForgotPasswordPageStellarBurgers = new ForgotPasswordPageStellarBurgers(driver);
        objForgotPasswordPageStellarBurgers.clickEnter();

        objLoginPageStellarBurgers.enterEmail(user.getEmail());
        objLoginPageStellarBurgers.enterPassword(user.getPassword());
        objLoginPageStellarBurgers.clickEnter();
        boolean isPlaceOrderButton = objHomePageStellarBurgers.checkLoadHomePageAfterLogin();

        assertTrue(isPlaceOrderButton);
    }

}
