package com.bestbuy.testsuite;

import com.bestbuy.testbase.TestBase;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ProductsAssertionTest extends TestBase {
    /**
     * 11. Verify the if the total is equal to 51957
     * 12. Verify the if the stores of limit is equal to 10
     * 13. Check the single ‘Name’ in the Array list (Duracell - AAA Batteries (4-Pack))
     * 14. Check the multiple ‘Names’ in the ArrayList (Duracell - AA 1.5V CopperTop Batteries (4-
     * Pack), Duracell - AA Batteries (8-Pack), Energizer - MAX Batteries AA (4-Pack))
     * 15. Verify the productId=150115 inside categories of the third name is “Household Batteries”
     * 17. Verify the price = 4.99 of forth product
     * 18. Verify the Product name = Duracell - D Batteries (4-Pack) of 6th product
     * 19. Verify the ProductId = 333179 for the 9th product
     */
    @Test
    public void verifyProductDetails() {
        given()
                .when()
                .get("/products")
                .then()
                .statusCode(200)
                .body("total", equalTo(51957),
                        "limit", equalTo(10),
                        "data.name", hasItems("Duracell - AAA Batteries (4-Pack)"),
                        "data.name", hasItems("Duracell - AA 1.5V CopperTop Batteries (4-Pack)", "Duracell - AA Batteries (8-Pack)", "Energizer - MAX Batteries AA (4-Pack)"),
                        "data[3].categories[2].name", equalTo("Household Batteries"),
                        "data[3].price", equalTo(4.99F),
                        "data[5].name", equalTo("Duracell - D Batteries (4-Pack)"),
                        "data[8].id", equalTo(333179));
    }

    // 16. Verify the categories second name = “Housewares” of productID = 333179
    @Test
    public void verifyProductDetailsAccordingParams() {
        given()
                .pathParam("id", "333179")
                .when()
                .get("/products/{id}")
                .then()
                .statusCode(200)
                .body("categories.name", hasItems("Housewares"));
    }

    // 20. Verify the prodctId = 346575 have 5 categories
    @Test
    public void verifyProductCategoriesAccordingProductId() {
        given()
                .pathParam("id", "346575")
                .when()
                .get("/products/{id}")
                .then()
                .statusCode(200)
                .body("categories", hasSize(5));
    }

}
