import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class MainGoogleBooks {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the name of the book : ");
        String bookName = URLEncoder.encode(sc.nextLine(),StandardCharsets.UTF_8) ;

        System.out.println("Enter the subject of the book (optional) :");
        String subject = URLEncoder.encode(sc.nextLine(),StandardCharsets.UTF_8);

        System.out.println("Enter the book author's name(optional) :");
        String authorName = URLEncoder.encode(sc.nextLine(),StandardCharsets.UTF_8);

        System.out.println("Enter publisher name(optional) :");
        String booksPublisher = URLEncoder.encode(sc.nextLine(),StandardCharsets.UTF_8);

        StringBuilder query = new StringBuilder("https://www.googleapis.com/books/v1/volumes?q=" +
                "intitle:"+bookName);


        if(!subject.trim().isEmpty()){
            query.append("+subject:"+subject);
        }
        if(!authorName.trim().isEmpty()){
            query.append("+inauthor:"+authorName);
        }
        if(!booksPublisher.trim().isEmpty()){
            query.append("+inpublisher:"+booksPublisher);
        }


        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(String.valueOf(query)))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());
    }

}
