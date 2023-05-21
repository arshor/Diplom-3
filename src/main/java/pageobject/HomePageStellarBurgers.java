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
    private static final By BUNS_LINK = By.xpath("//*[@id=\"root\"]/div/main/section[1]/div[1]/div[1]");
    private static final By SAUCES_LINK = By.xpath("//*[@id=\"root\"]/div/main/section[1]/div[1]/div[2]");
    private static final By FILLINGS_LINK = By.xpath("//*[@id=\"root\"]/div/main/section[1]/div[1]/div[3]");
    private static final By BUNS_SECTION = By.xpath("//*[@id=\"root\"]/div/main/section[1]/div[2]/h2[1]");
    private static final By SAUCES_SECTION = By.xpath(".//h2[text()='Соусы']");
    private static final By FILLINGS_SECTION = By.xpath("//*[@id=\"root\"]/div/main/section[1]/div[2]/h2[3]");
    private static final By ACTIVE_SECTION = By.xpath(
            ".//section[@class='BurgerIngredients_ingredients__1N8v2']/div/div[@class='tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']/span");

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

    @Step("Клик по по разделу Булки")
    public void clickBuns() {
        driver.findElement(BUNS_LINK).click();
    }

    @Step("Клик по по разделу Соусы")
    public void clickSauces() {
        driver.findElement(SAUCES_LINK).click();
    }

    @Step("Клик по по разделу Начинки")
    public void clickFillings() {
        driver.findElement(FILLINGS_LINK).click();
    }

    @Step("Получить название активного раздела")
    public String getActiveSectionName() {
        return driver.findElement(ACTIVE_SECTION).getText();
    }


}
