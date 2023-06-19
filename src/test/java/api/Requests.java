package api;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.ResponseSpecification;

import java.util.Map;

import static api.AuthApi.authCookieKey;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static specs.Specs.requestSpec;

public class Requests {

    public static ValidatableResponse sendPostAddressEditRequest(
            String endpoint,
            String cookieValue,
            ResponseSpecification responseSpecification,
            Map<String, String> data) {
        return  step("Выполняется POST запрос на " + endpoint , () -> given(requestSpec)
                .cookie(authCookieKey, cookieValue)
                .formParams(data)
                .when()
                .post(endpoint)
                .then().spec(responseSpecification));
    }

    public static String sendGetAddressRequest(
            String endpoint,
            String cookieValue,
            ResponseSpecification responseSpecification) {
        return step("Выполняется GET запрос на " + endpoint , () -> given(requestSpec)
                .cookie(authCookieKey, cookieValue)
                .when()
                .post(endpoint)
                .then().spec(responseSpecification)
                .extract().asString());
    }

    public static String sendGetMyAccountRequest(
            String endpoint,
            String cookieValue,
            ResponseSpecification responseSpecification) {
        return step("Выполняется GET запрос на " + endpoint , () -> given(requestSpec)
                .cookie(authCookieKey, cookieValue)
                .when()
                .post(endpoint)
                .then().spec(responseSpecification)
                .extract().asString());
    }

    public static <T> T sendPostAddItemToCartRequest(String endpoint, String cookieValue, String body, Class<T> responseClass, ResponseSpecification responseSpecification) {
        Response response = step("Выполняется POST запрос на " + endpoint , () -> given(requestSpec)
                .cookie(authCookieKey, cookieValue)
                .body(body)
                .when().post(endpoint).then().spec(responseSpecification).extract().response());
        return response.as(responseClass);
    }


}
