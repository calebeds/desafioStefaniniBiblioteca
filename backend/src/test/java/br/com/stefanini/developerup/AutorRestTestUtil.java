package br.com.stefanini.developerup;

import io.vertx.core.json.JsonObject;

public class AutorRestTestUtil {

    //Métodos Auxiliares

    public String createBodyReq(long isni, String nome, 
        String email, String dataDeNascimento, String biografia){
        JsonObject bodyJson = new JsonObject();
        bodyJson.put("isni", isni);
        bodyJson.put("nome",nome);
        bodyJson.put("email", email);
        bodyJson.put("dataDeNascimento", dataDeNascimento);
        bodyJson.put("biografia", biografia);
        
        return bodyJson.toString();
    }
}
