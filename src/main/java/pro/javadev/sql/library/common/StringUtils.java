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

    public static String underscored(final String value){
        return underscored(value, false);
    }

    public static String underscored(final String value,final boolean toUpperCase) {
        String result = value;

        if (value != null && !value.isBlank()) {
            StringBuilder builder  = new StringBuilder();
            char          previous = ' ';

            for (char current : value.toCharArray()) {
                char newCharacter = toUpperCase ? toUpperCase(current) : toLowerCase(current);

                if (isUpperCase(current) && isLetter(previous) && !isUpperCase(previous)) {
                    builder.append("_").append(newCharacter);
                } else {
                    builder.append(newCharacter);
                }

                previous = current;
            }

            result = builder.toString();
        }

        return result;
    }

}
