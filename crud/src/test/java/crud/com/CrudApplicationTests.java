package crud.com;

import crud.com.util.FileUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestPropertySource("/application-test.properties")
class CrudApplicationTests {

    private final String CATEGORIA_PATH = "/crudapi/v1/categorias";

    @LocalServerPort
    private int PORT = 8082;

    @Order(1)
    @Test
    void deveSalvarCategoria() throws IOException {
        RestAssured.given()
                .basePath(CATEGORIA_PATH)
                .port(PORT)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(FileUtils.readFileContent("categoria_post.json"))
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Order(2)
    @Test
    void deveListarCategorias() throws IOException {
        RestAssured.given()
                .basePath(CATEGORIA_PATH)
                .port(PORT)
                .accept(ContentType.JSON)
                .when()
                .get()
                .then()
                .body("descricao", Matchers.hasItems("leve"));
    }

    @Order(3)
    @Test
    void deveBuscarCategoriaPorId() {
        RestAssured.given()
                .basePath(CATEGORIA_PATH)
                .port(PORT)
                .pathParam("id", 1)
                .accept(ContentType.JSON)
                .when()
                .get("/{id}")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("descricao", Matchers.equalTo("leve"));
    }

    @Order(4)
    @Test
    void deveAtualizarCategoriaPorId() throws IOException {
        RestAssured.given()
                .basePath(CATEGORIA_PATH)
                .port(PORT)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("id", 1)
                .body(FileUtils.readFileContent("categoria_put.json"))
                .when()
                .put("/{id}")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("descricao", Matchers.equalTo("leve novo"));
    }

    @Order(5)
    @Test
    void deveDeletarCategoriaPorId() {
        RestAssured.given()
                .basePath(CATEGORIA_PATH)
                .port(PORT)
                .pathParam("id", 1)
                .when()
                .delete("/{id}")
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }


}
