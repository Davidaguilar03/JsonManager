package co.edu.uptc.services;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import co.edu.uptc.models.*;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JsonConvertorService {

    public void personToJson(List<Person> persons, String outputFile) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        try (FileWriter fileWriter = new FileWriter(outputFile)) {
            objectMapper.writeValue(fileWriter, persons);
        }
    }

    public ArrayList<Person> jsonToPerson(String inputFile) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(inputFile);
        return objectMapper.readValue(file, new TypeReference<ArrayList<Person>>() {
        });
    }

}
