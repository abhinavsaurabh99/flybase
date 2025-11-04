package in.abhinavsaurabh.flybase.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;

import in.abhinavsaurabh.flybase.model.Schema;

public class YamlSchemaParser {
	
    public static Schema parse(File file) {

        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try {
            return mapper.readValue(file, Schema.class);
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse Yaml: " + e.getMessage(), e);
        }
    }
}
