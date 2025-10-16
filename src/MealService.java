import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class MealService {

    public String url(int menuOption){
        Scanner sc = new Scanner(System.in);
        StringBuilder url = new StringBuilder("https://www.themealdb.com/api/json/v1/1");
        switch(menuOption){
            case 1 :
                System.out.println("Enter the recip's name : ");
                String recipsName  = URLEncoder.encode(sc.nextLine(), StandardCharsets.UTF_8);
                url.append("/search.php?s=").append(recipsName);
                break;
            case 2:
                url.append("/random.php");
                break;
            case 3:
                System.out.println("Enter the main ingredient : ");
                String mainIngredient = URLEncoder.encode(sc.nextLine(), StandardCharsets.UTF_8);
                url.append("/filter.php?i=").append(mainIngredient);
        }
        return url.toString();
    }

    public void envioApi(String url){
        try(HttpClient client = HttpClient.newHttpClient();){
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();
            HttpResponse<String> response = client.send(request,HttpResponse.BodyHandlers.ofString());

            ObjectMapper mapper = new ObjectMapper();
            Object json = mapper.readValue(response.body(), Object.class);
            ObjectWriter writer = mapper.writerWithDefaultPrettyPrinter();
            System.out.println(writer.writeValueAsString(json));

        }catch (InterruptedException | IOException error){
            throw new RuntimeException(error);
        }
    }
}
