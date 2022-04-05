package br.com.stefanini.developerup;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;


/**
 * @author Calebe Oliveira
 * email calebe.dso@gmail.com
 * created 05/03/2022
 * @version 0.1.0
 */
@QuarkusTest
public class AutorRestTest {
    private final static String URL = "/api/autor";
    AutorRestTestUtil at = new AutorRestTestUtil();

    @Test
    public void listarAutoresTest(){
        given()
                .contentType(ContentType.JSON)
                .when()
                .get(URL)
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void listarUmAutorTest(){
        given()
                .contentType(ContentType.JSON)
                .when()
                .get(URL+"/2423429993642345")
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void inserirUmAutorTest(){
         //Preparação do teste | Montando o corpo da requisição
        String bodyInserir = at.createBodyReq( 3333333333333333l , 
            "Autor Bom", "autor@emailll.com", "2021-02-09", "Uma biografia muito boa");
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
    public void editarUmAutorTest(){
         //Preparação do teste | Montando o corpo da requisição de editar
         String bodyEditar = at.createBodyReq( 3333333333333333l , 
            "Autor Mau", "autorfeio@emailll.com", "2021-02-09", "Uma biografia muito ruim");
        ////

        given()
                .contentType(ContentType.JSON)
                .body(bodyEditar)
                .when()
                .put(URL + "/3333333333333333")
                .then()
                .statusCode(HttpStatus.SC_CREATED);
    }

    @Test
    public void excluirUmAutorTest(){

        given()
                .contentType(ContentType.JSON)
                .when()
                .delete(URL + "/3333333333333333")
                .then()
                .statusCode(HttpStatus.SC_OK);
    }



}
