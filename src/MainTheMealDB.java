import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class MainTheMealDB {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        MenuTheMeal menu = new MenuTheMeal();
        MealService mealService = new MealService();

        System.out.println(menu.toString());
        System.out.println("Select the desired option : ");
        int desiredOption = sc.nextInt();
        String url = mealService.url(desiredOption);
        mealService.envioApi(url);


    }
}
