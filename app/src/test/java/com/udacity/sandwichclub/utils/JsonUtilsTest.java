package com.udacity.sandwichclub.utils;

import static org.junit.Assert.*;

import com.udacity.sandwichclub.model.Sandwich;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.hamcrest.core.Is;

public class JsonUtilsTest {

    @org.junit.Test
    public void parseSandwichJson() {
        String hamCheeseSandwichJson = "ham_and_cheese_sandwich.json";
        URL sandwichUrl = getClass().getResource("/" + hamCheeseSandwichJson);
        try {
            String testInput =
                    new String(Files.readAllBytes(Paths.get(sandwichUrl.toURI())), "UTF-8");
            Sandwich s = JsonUtils.parseSandwichJson(testInput);
            assertEquals("Ham and cheese sandwich", s.getMainName());

            List<String> expected = new ArrayList<>();
            expected.add("Sliced bread");
            expected.add("Cheese");
            expected.add("Ham");
            assertThat(s.getIngredients(), Is.is(expected));

        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
            fail("Failed to load the mock json (" + hamCheeseSandwichJson + ")");
        }
    }
}
