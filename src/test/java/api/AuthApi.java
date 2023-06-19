package api;

import static io.restassured.RestAssured.given;
import static specs.Specs.requestSpec;
import static specs.Specs.response302Spec;

public class AuthApi {

    public static String authCookieKey = "NOPCOMMERCE.AUTH";

    public static String getAuthCookie(String login, String password) {
        return given(requestSpec)
                .formParam("Email", login)
                .formParam("Password", password)
                .when()
                .post("/login")
                .then().spec(response302Spec)
                .extract()
                .cookie(authCookieKey);
    }

}
