package br.com.alura.viacep;

import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchCep {

    public static Address search(String cep) {
        validaCep(cep);
        String apiUrl =  "https://viacep.com.br/ws/"+cep+"/json/";

        try(HttpClient client = HttpClient.newHttpClient()){
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(apiUrl))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(response.body(),Address.class);
        }catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public static boolean validaCep(String cep){
        Pattern pattern = Pattern.compile("\\D");
        Matcher matcher = pattern.matcher(cep);
        if(matcher.find()){
            throw new IllegalArgumentException("The CEP input accepts only numbers");
        }else if(cep.length()>8){
            throw new IllegalArgumentException("The CEP must contain exactly 8 numbers");
        }
        return true;
    }


}
