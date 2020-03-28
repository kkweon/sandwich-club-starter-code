package com.udacity.sandwichclub.utils;

import java.util.List;

public class StringUtils {

    // It's the same as String.join which is not supported in this Android SDK.
    public static String join(String s, List<String> alsoKnownAs) {
        StringBuilder sb = new StringBuilder();

        String separator = "";

        // <sep="">A<sep>B<sep>C
        for (String a : alsoKnownAs) {
            sb.append(separator);
            sb.append(a);

            if (separator.isEmpty()) {
                separator = s;
            }
        }

        return sb.toString();
    }
}
