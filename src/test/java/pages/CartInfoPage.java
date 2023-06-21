package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Allure;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class CartInfoPage {

    SelenideElement
            productName = $(".product-name"),
            contentOrder = $(".order-summary-content .attributes"),
            removeCard = $(".remove-from-cart input"),
            cartButton = $(".update-cart-button"),
            pageTitle = $(".page-title");

    public CartInfoPage openPage() {
        step("Открываем страницу /cart", () ->open("/cart"));
        return this;
    }

    public CartInfoPage checkOrders(String orderItem) {
        step("Проверяем что текст 'Build your own cheap computer' присутствует", () ->
                productName.shouldHave(text("Build your own cheap computer")));
        step("Проверяем что текст 'Processor: Slow' присутствует", () ->
                contentOrder.shouldHave(text(orderItem)));
        return this;
    }

    public CartInfoPage clickCheckbox() {
        step("Нажимаем на чекбокс", () ->
                removeCard.click());
        return this;
    }

    public CartInfoPage clickDeleteButton() {
        step("Нажимаем на кнопку 'Update shopping cart'", () ->
                cartButton.click());
        return this;
    }

    public CartInfoPage checkDeleteItemCard() {
        step("Проверяем что текст 'Shopping cart' присутствует", () ->
                pageTitle.shouldHave(text("Shopping cart")));
        return this;
    }

}
