package pro.javadev.sql.v1.matcher;

import pro.javadev.sql.v1.common.Token;

import java.util.Arrays;
import java.util.List;

public class EnumTokenMatcher implements TokenMatcher<String, Token> {

    private final List<Enum<? extends Token>> enums;

    public EnumTokenMatcher(Enum<? extends Token>... enums) {
        this.enums = Arrays.asList(enums);
    }

    @Override
    public Token match(String value) {
        return null;
    }

}
