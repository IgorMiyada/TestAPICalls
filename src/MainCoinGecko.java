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

public class MainCoinGecko {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the crypto coin you want to search : ");
        String coin = URLEncoder.encode(sc.nextLine(), StandardCharsets.UTF_8);
        String url = "https://api.coingecko.com/api/v3/simple/price?vs_currencies=usd&ids="+coin+
                "&x_cg_demo_api_key=CG-f4eyUMDJ7WvoKkr4Re9ooLy4";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response = client.send(request,HttpResponse.BodyHandlers.ofString());

        ObjectMapper mapper = new ObjectMapper();
        Object json = mapper.readValue(response.body(), Object.class);
        ObjectWriter writer = mapper.writerWithDefaultPrettyPrinter();
        System.out.println(writer.writeValueAsString(json));
    }
}
