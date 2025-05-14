package nl.wjglerum;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
@TestHTTPEndpoint(AuthenticatedResource.class)
class AuthenticatedResourceTest {

    @Test
    @TestSecurity(user = "bob")
    void testAuthenticated() {
        given()
                .when()
                .get()
                .then()
                .statusCode(200)
                .body(is("bob"));
    }

    @Test
    @TestSecurity(user = "alice", roles = {"admin"})
    void testAdmin() {
        given()
                .when()
                .get("admin")
                .then()
                .statusCode(200)
                .body(containsString("admin"));
    }
}
