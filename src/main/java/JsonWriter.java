import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;


public class JsonWriter {

    ObjectMapper objectMapper;
    File file;

    public JsonWriter(String outputFile) {
        objectMapper = new ObjectMapper();
        try {
            file = new File(outputFile);
            file.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void write(Email email) {

        try {
            objectMapper.writeValue(file, email);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
