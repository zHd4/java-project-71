import static org.junit.jupiter.api.Assertions.assertEquals;

import hexlet.code.Differ;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class DifferTest {
    private static final Map<String, String> JSON_FILES_PATHS = Map.of(
            "file1", "./src/test/resources/file1.json",
            "file2", "./src/test/resources/file2.json"
    );

    private static final Map<String, String> YAML_FILES_PATHS = Map.of(
            "file1", "./src/test/resources/file1.yml",
            "file2", "./src/test/resources/file2.yml"
    );

    private static String stylishExpected;
    private static String plainExpected;
    private static String jsonExpected;

    @BeforeAll
    public static void loadResources() throws IOException {
        stylishExpected = readResource("stylish_expected.txt");
        plainExpected = readResource("plain_expected.txt");
        jsonExpected = readResource("json_expected.json");
    }

    private static String readResource(String filename) throws IOException {
        return Files.readString(Path.of("src/test/resources/" + filename));
    }

    @Test
    void testJsonStylishDiff() throws IOException {
        String actual = Differ.generate(JSON_FILES_PATHS.get("file1"),
                JSON_FILES_PATHS.get("file2"));
        assertEquals(stylishExpected, actual);
    }

    @Test
    void testYamlStylishDiff() throws IOException {
        String actual = Differ.generate(YAML_FILES_PATHS.get("file1"),
                YAML_FILES_PATHS.get("file2"));
        assertEquals(stylishExpected, actual);
    }

    @Test
    void testJsonPlainDiff() throws IOException {
        String actual = Differ.generate(JSON_FILES_PATHS.get("file1"),
                JSON_FILES_PATHS.get("file2"),
                "plain");
        assertEquals(plainExpected, actual);
    }

    @Test
    void testYamlPlainDiff() throws IOException {
        String actual = Differ.generate(YAML_FILES_PATHS.get("file1"),
                YAML_FILES_PATHS.get("file2"),
                "plain");
        assertEquals(plainExpected, actual);
    }

    @Test
    void testJsonFormatDiff() throws IOException {
        String actual = Differ.generate(JSON_FILES_PATHS.get("file1"),
                JSON_FILES_PATHS.get("file2"),
                "json");
        assertEquals(jsonExpected, actual);
    }

    @Test
    void testYamlJsonFormatDiff() throws IOException {
        String actual = Differ.generate(YAML_FILES_PATHS.get("file1"),
                YAML_FILES_PATHS.get("file2"),
                "json");
        assertEquals(jsonExpected, actual);
    }
}
