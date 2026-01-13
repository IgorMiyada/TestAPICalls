package br.com.alura.viacep;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class GenerateFile {

    public static void generateFile(Address address){
        try(BufferedWriter bufferedWriter = new BufferedWriter( new FileWriter(address.cep()))){
            ObjectMapper mapper = new ObjectMapper();
            ObjectWriter writer = mapper.writerWithDefaultPrettyPrinter();
            bufferedWriter.write(writer.writeValueAsString(address));
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
