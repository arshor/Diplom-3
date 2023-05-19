package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePageStellarBurgers {

    private static final By LOGIN_ACCOUNT_BUTTON = By.xpath(".//button[text()='Войти в аккаунт']");
    private static final By PLACE_ORDER_BUTTON = By.xpath(".//button[text()='Оформить заказ']");
    private static final By PERSONAL_ACCOUNT_BUTTON = By.xpath("//*[@id=\"root\"]/div/header/nav/a/p");

    private WebDriver driver;

    public HomePageStellarBurgers(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Клик по кнопке Личный кабинет")
    public void clickPersonalAccount() {
        driver.findElement(PERSONAL_ACCOUNT_BUTTON).click();
    }

    @Step("Клик по кнопке Войти в аккаунт")
    public void clickLoginAccount() {
        driver.findElement(LOGIN_ACCOUNT_BUTTON).click();
    }

    @Step("Проверка загрузки главной страницы после входа в аккаунт")
    public boolean checkLoadHomePageAfterLogin() {
        WebElement placeOrderButton = new WebDriverWait(driver, 5).until(
                ExpectedConditions.visibilityOfElementLocated(PLACE_ORDER_BUTTON));
        return placeOrderButton.isDisplayed();
    }
}
