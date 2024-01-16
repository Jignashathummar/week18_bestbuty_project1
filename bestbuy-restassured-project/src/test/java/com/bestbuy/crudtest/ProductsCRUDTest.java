package com.bestbuy.crudtest;

import com.bestbuy.model.ProductPojo;
import com.bestbuy.testbase.TestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasValue;

public class ProductsCRUDTest extends TestBase {
    static String name = "Samsung-mobile";
    static String type = "Electronics";

    static String upc = "12345678990";

    static String description = "Mobile company";

    static String model = "S24";

    static int productId;

    @Test
    public void test001() {
        ProductPojo productPojo = new ProductPojo();
        productPojo.setName(name);
        productPojo.setType(type);
        productPojo.setUpc(upc);
        productPojo.setDescription(description);
        productPojo.setModel(model);

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .body(productPojo)
                .post("/products");
        response.prettyPrint();
        response.then().statusCode(201);
    }

    @Test
    public void test002() {
        String s1 = "data.findAll{it.name == '";
        String s2 = "'}.get(0)";

        ValidatableResponse response =
                given()
                        .queryParams("name", name)
                        .when()
                        .get("/products")
                        .then().statusCode(200);
        HashMap<String, Object> productMap = response.extract()
                .path(s1 + name + s2);
        response.body(s1 + name + s2, hasValue(name));
        System.out.println(productId);
        productId = (int) productMap.get("id");
    }

    @Test
    public void test003() {
        ProductPojo productPojo = new ProductPojo();
        productPojo.setModel("S78");
        ValidatableResponse response =
                given()
                        .queryParams("id", productId)
                        .contentType(ContentType.JSON)
                        .when()
                        .body(productPojo)
                        .patch("/products").then().statusCode(200);
        response.body("model", equalTo("S78"));
    }

    @Test
    public void test004() {
        System.out.println(productId);
        given()
                .queryParam("id", productId)
                .when()
                .delete("/products")
                .then()
                .statusCode(200);

        given()
                .queryParam("id", productId)
                .when()
                .get("/products")
                .then()
                .statusCode(200);
    }
}
