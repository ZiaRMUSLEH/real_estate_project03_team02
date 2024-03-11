package com.project.real_estate_project03_team02.service.helper;

import com.project.real_estate_project03_team02.payload.messages.ErrorMessages;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SlugGeneratorTest {

    @Test
    void generateSlugShouldGenerateValidSlug() {
        SlugGenerator slugGenerator = new SlugGenerator();
        String title = "This is a test title";
        String generatedSlug = slugGenerator.generateSlug(title);
        assertNotNull(generatedSlug);
        assertTrue(generatedSlug.matches("^[a-zA-Z0-9-._~:/?#\\[\\]@!$&'()*+,;=%]+$"), "Generated slug should be URL-encoded");
        assertTrue(generatedSlug.length() <= 200, "Generated slug should be truncated to 200 characters");
    }

}
