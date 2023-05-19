package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterPageStellarBurgers {

    private static final By REGISTER_BUTTON = By.xpath(".//button[text()='Зарегистрироваться']");
    private static final By NAME_FIELD = By.xpath("//*[@id=\"root\"]/div/main/div/form/fieldset[1]/div/div/input");
    private static final By EMAIL_FIELD = By.xpath("//*[@id=\"root\"]/div/main/div/form/fieldset[2]/div/div/input");
    private static final By PASSWORD_FIELD = By.xpath(".//input[@name='Пароль']");
    private static final By INVALID_PASSWORD_LABEL = By.xpath(".//p[@class='input__error text_type_main-default']");
    private static final By ENTER_LINK = By.xpath(".//a[@href='/login']");

    private WebDriver driver;

    public RegisterPageStellarBurgers(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Ввод имени при регистрации")
    public void enterName(String name){
        driver.findElement(NAME_FIELD).sendKeys(name);
    }

    @Step("Ввод email при регистрации")
    public void enterEmail(String email){
        driver.findElement(EMAIL_FIELD).sendKeys(email);
    }

    @Step("Ввод пароля при регистрации")
    public void enterPassword(String password){
        driver.findElement(PASSWORD_FIELD).sendKeys(password);
    }

    @Step("Нажать на кнопку Зарегистрироваться")
    public void clickRegister(){
        driver.findElement(REGISTER_BUTTON).click();
    }

    @Step("Нажать на ссылку Войти")
    public void clickEnter(){
        driver.findElement(ENTER_LINK).click();
    }

    @Step("Проверка правильности введенного пароля")
    public boolean showsInvalidPasswordLabel(){
        return driver.findElement(INVALID_PASSWORD_LABEL).isDisplayed();
    }

}
