package br.com.stefanini.developerup;

import io.vertx.core.json.JsonObject;

public class ClienteRestTestUtil {

    //Métodos Auxiliares

    public String createBodyReq(String nome, String email, 
        String contato, Integer qtdDeEmprestimos){
        JsonObject bodyJson = new JsonObject();

        bodyJson.put("nome",nome);
        bodyJson.put("email", email);
        bodyJson.put("contato",contato);
        bodyJson.put("qtdEmprestimos", qtdDeEmprestimos);
        
        return bodyJson.toString();
    }
}
