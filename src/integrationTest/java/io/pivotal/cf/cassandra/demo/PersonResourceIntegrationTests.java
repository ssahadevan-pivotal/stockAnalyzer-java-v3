package io.pivotal.cf.cassandra.demo;

import com.jayway.restassured.http.ContentType;
import io.pivotal.cf.cassandra.demo.models.Person;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class PersonResourceIntegrationTests {

    @Autowired
    CassandraOperations cassandraTemplate;

    @Before
    public void setUp() {
        String cql = "create table if not exists person (id text, name text, age int, primary key(id))";
        cassandraTemplate.execute(cql);

        cql = "insert into person (id,name,age) values ('1234567890','Matt',35)";
        cassandraTemplate.execute(cql);
    }

    @Test
    public void findAll() {
        given()
                .when()
                .get("/people")

                .then().log().all()
                .statusCode(200)

                .body("id", hasItems("1234567890"))
                .body("name", hasItems("Matt"))
                .body("age", hasItems(35));
    }

    @Test
    public void createTest() {
        Person personRequestBody = new Person();
        personRequestBody.setName("Joe");
        personRequestBody.setAge(42);

        given()
                .contentType(ContentType.JSON)
                .body(personRequestBody)

                .when()
                .post("/people")

                .then().log().all()
                .statusCode(201)

                .assertThat().body("id", is(notNullValue()));

        given()
                .when()
                .get("/people")

                .then().log().all()
                .statusCode(200)

                .body("name", hasItems("Joe"))
                .body("age", hasItems(42));
    }

    @After
    public void tearDown() {
        String cql = "truncate person";
        cassandraTemplate.execute(cql);
    }

}
