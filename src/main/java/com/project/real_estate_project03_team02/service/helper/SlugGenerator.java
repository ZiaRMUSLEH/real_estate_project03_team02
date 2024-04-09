package com.project.real_estate_project03_team02.service.helper;
import com.project.real_estate_project03_team02.exception.BadRequestException;
import com.project.real_estate_project03_team02.payload.messages.ErrorMessages;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
@Component
@RequiredArgsConstructor
public class SlugGenerator {

    public  String generateSlug(String title) {
        String encodedTitle = urlEncode(title);
        return truncateSlug(encodedTitle, 200);
    }

    private  String urlEncode(String value) {
        try {
            return URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            throw new BadRequestException(ErrorMessages.ERROR_ENCODING_SLUG);
        }
    }

    private  String truncateSlug(String slug, int maxLength) {
        if (slug.length() > maxLength) {
            return slug.substring(0, maxLength);
        }
        return slug;
    }
}
