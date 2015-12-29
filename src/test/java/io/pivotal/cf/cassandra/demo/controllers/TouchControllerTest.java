package io.pivotal.cf.cassandra.demo.controllers;

import org.junit.Test;

import static com.jayway.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class TouchControllerTest {

    @Test
    public void touchTest() {
        given()
                .standaloneSetup(new TouchController())
                .when()
                .get("/touch")
                .then()
                .statusCode(200)
                .body(equalTo("Goodbye World!"));
    }
}
