package responseassertions;

import models.AddingItemResponse;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

public class AssertAddProductResponse {

    public static void assertionsAddProductResponse(AddingItemResponse response, int countOfItems, int quantity) {
        step("Проверяем что параметр success = true", () ->
                assertThat(response.getSuccess()).isEqualTo("true"));
        step("Проверяем что параметр message заполнен", () ->
                assertThat(response.getMessage()).isEqualTo("The product has been added to your <a href=\"/cart\">shopping cart</a>"));
        step("Проверяем что параметр updatetopcartsectionhtml заполнен " + countOfItems + " " + quantity, () ->
                assertThat(response.getUpdatetopcartsectionhtml()).isEqualTo("(" + (countOfItems + quantity) + ")"));
        step("Проверяем что параметр updateflyoutcartsectionhtml присутствует в ответе " + countOfItems + " " + quantity, () ->
                assertThat(response.getUpdateflyoutcartsectionhtml()).isNotEmpty());
    }

}
