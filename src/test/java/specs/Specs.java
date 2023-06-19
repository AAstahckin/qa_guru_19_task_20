package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.*;

public class Specs {

    public static RequestSpecification requestSpec = with()
            .log().uri()
            .log().body()
            .log().params()
            .filter(withCustomTemplates())
            .contentType("application/x-www-form-urlencoded; charset=UTF-8");

    public static ResponseSpecification response302Spec = new ResponseSpecBuilder()
            .log(ALL)
            .expectStatusCode(302)
            .build();

    public static ResponseSpecification response200Spec = new ResponseSpecBuilder()
            .log(ALL)
            .expectStatusCode(200)
            .build();

}
