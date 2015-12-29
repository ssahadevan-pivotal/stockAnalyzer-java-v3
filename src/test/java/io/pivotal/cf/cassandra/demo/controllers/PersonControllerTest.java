package io.pivotal.cf.cassandra.demo.controllers;

import com.jayway.restassured.http.ContentType;
import io.pivotal.cf.cassandra.demo.controllers.PersonController;
import io.pivotal.cf.cassandra.demo.models.Person;
import io.pivotal.cf.cassandra.demo.repositories.PersonRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static com.jayway.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Matchers.isNotNull;
import static org.mockito.Matchers.notNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PersonControllerTest {

    private PersonRepository mockPersonRepository;
    private PersonController personController;

    @Before
    public void setUp() {
        mockPersonRepository = mock(PersonRepository.class);
        personController = new PersonController(mockPersonRepository);
    }

    @Test
    public void listTest() {
        Person dummyPerson = new Person("1234567890", "Matt", 35);
        List<Person> dummyPeople = Arrays.asList(dummyPerson);
        when(mockPersonRepository.findAll()).thenReturn(dummyPeople);

        given()
                .standaloneSetup(personController)
                .when()
                .get("/people")
                .then()
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

        Person personResponseBody = new Person("ABCDEFG", "Joe", 42);
        when(mockPersonRepository.save(personRequestBody)).thenReturn(personResponseBody);

        given()
                .standaloneSetup(personController)
                .contentType(ContentType.JSON)
                .body(personRequestBody)
                .when()
                .post("/people")
                .then()
                .statusCode(201)
                .assertThat().body("id", equalTo("ABCDEFG"));
    }
}
