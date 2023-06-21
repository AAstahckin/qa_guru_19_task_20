package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Allure;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class LoginPage {

    SelenideElement
            inputEmail = $("input#Email"),
            inputPassword = $("input#Password"),
            pageTitle = $(".page-title"),
            titleText = $(".returning-wrapper .title"),
            loginButton = $("input.login-button");

    public LoginPage openPage() {
        Allure.step("Открываем страницу /login");
        open("/login");
        step("Проверяем что текст 'Welcome, Please Sign In!' присутствует", () ->
                pageTitle.shouldHave(Condition.text("Welcome, Please Sign In!")));
        step("Проверяем что текст 'Returning Customer' присутствует", () ->
                titleText.shouldHave(Condition.text("Returning Customer")));
        return this;
    }

    public LoginPage setEmail(String email) {
        Allure.step("Вводим логин");
        inputEmail.setValue(email);
        return this;
    }

    public LoginPage setPassword(String password) {
        Allure.step("Вводим пароль");
        inputPassword.setValue(password);
        return this;
    }

    public LoginPage login() {
        Allure.step("Нажимаем на кнопку 'Log in'");
        loginButton.click();
        return this;
    }

}
