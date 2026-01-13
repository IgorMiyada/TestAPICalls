package br.com.alura.viacep;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try{
            System.out.println("Enter your cep : ");
            String cep = scanner.nextLine();
            Address address = SearchCep.search(cep);
            GenerateFile.generateFile(address);
        }  catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
}
