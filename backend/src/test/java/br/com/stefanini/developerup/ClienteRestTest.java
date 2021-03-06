package br.com.stefanini.developerup;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;


/**
 * @author Danilo Dorgam
 * email danilodorgam@gmail.com
 * created 30/03/2022
 * @version 0.1.0
 */
@QuarkusTest
public class ClienteRestTest {
    private final static String URL = "/api/cliente";
    ClienteRestTestUtil ct = new ClienteRestTestUtil();

    @Test
    public void testarListarBlocoIndicadorComSucesso(){
        given()
                .contentType(ContentType.JSON)
                .when()
                .get(URL)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body(matchesJsonSchemaInClasspath("schemas/JsonSchemaLista.json"));
    }

    @Test
    public void listarUmClienteTest(){
        given()
                .contentType(ContentType.JSON)
                .when()
                .get(URL+"/maratona@stefanini.com")
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void inserirUmClienteTest(){
         //Preparação do teste | Montando o corpo da requisição
        String bodyInserir = ct.createBodyReq("Fulano Cliente", 
            "emailcliente@gmail.com", "(21) 2222-2222", 0);
        ////

        System.out.println(bodyInserir);

        given()
                .contentType(ContentType.JSON)
                .body(bodyInserir)
                .when()
                .post(URL)
                .then()
                .statusCode(HttpStatus.SC_CREATED);
    }

    @Test
    public void editarUmClienteTest(){
         //Preparação do teste | Montando o corpo da requisição de editar
        String bodyEditar = ct.createBodyReq("Usuário Editado", 
            "maratona@stefanini.com", "(21) 2222-2222", 0);
        ////



        System.out.println(bodyEditar);

        given()
                .contentType(ContentType.JSON)
                .body(bodyEditar)
                .when()
                .put(URL + "/maratona@stefanini.com")
                .then()
                .statusCode(HttpStatus.SC_CREATED);
    }

    @Test
    public void excluirUmClienteTest(){

        given()
                .contentType(ContentType.JSON)
                .when()
                .delete(URL + "/calebe@stefanini.com")
                .then()
                .statusCode(HttpStatus.SC_OK);
    }



}
