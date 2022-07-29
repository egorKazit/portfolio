package com.yk.protfolio.springportfolio.services;

import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.stereotype.Service;

@Service
public class HyperlinkServiceImp implements HyperlinkService {

    private static final Pattern HYPERLINK_PATTERN = Pattern.compile("(?:(?:https?|ftp|file):\\/\\/|www\\.|ftp\\.)(?:\\([-A-Z0-9+&@#\\/%=~_|$?!:,.]*\\)|[-A-Z0-9+&@#\\/%=~_|$?!:,.])*(?:\\([-A-Z0-9+&@#\\/%=~_|$?!:,.]*\\)|[A-Z0-9+&@#\\/%=~_|$])",
            Pattern.CASE_INSENSITIVE);

    @Override
    public String replaceWithHyperLink(String originalText) {
        Matcher matcher = HYPERLINK_PATTERN.matcher(originalText);
        AtomicReference<String> originalTextAfterChanges = new AtomicReference<>(originalText);
        matcher.results()
                .forEach(matchResult -> {
                    String href = matchResult.group();
                    originalTextAfterChanges
                            .set(originalTextAfterChanges.get()
                                    .replace(href, String.format("<a href=%s><u>%s</u></a>", href, href)));
                });
        return originalTextAfterChanges.get();
    }
}
