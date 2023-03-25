package pro.javadev.sql.library.common;

import static java.lang.Character.*;

abstract public class StringUtils {

    public static String uncapitalize(final String value) {
        String result = value;

        if (value != null && value.length() > 0) {
            char[] chars = value.toCharArray();
            chars[0] = toLowerCase(chars[0]);
            result = new String(chars);
        }

        return result;
    }

    public static String underscored(final String value) {
        return underscored(value, false);
    }

    public static String underscored(final String value, final boolean toUpperCase) {
        String result = value;

        if (value != null && !value.isBlank()) {
            StringBuilder builder  = new StringBuilder();
            char          previous = ' ';
            char[]        chars    = value.toCharArray();
            int           counter  = 0;
            int           size     = chars.length;
            char          next;

            for (char current : chars) {
                char newCharacter = toUpperCase ? toUpperCase(current) : toLowerCase(current);
                int  nextIndex    = counter++ + 1;

                next = (nextIndex < size) ? chars[nextIndex] : ' ';

                if (isUpperCase(current) && isLetter(previous) && (isLowerCase(previous) || isLowerCase(next))) {
                    builder.append("_");
                }

                builder.append(newCharacter);

                previous = current;
            }

            result = builder.toString();
        }

        return result;
    }

}
