package pro.javadev.sql.library.parser;

import pro.javadev.sql.library.token.Token;
import pro.javadev.sql.library.tokenizer.Tokenizer;

import static pro.javadev.sql.library.token.DefaultToken.*;

@SuppressWarnings({"unused"})
public interface ExpressionRecognizer {

    default boolean isIdentifier(Tokenizer tokenizer) {
        return tokenizer.isCurrent(T_IDENTIFIER);
    }

    default boolean isLiteralExpression(Tokenizer tokenizer) {
        return tokenizer.isCurrent(T_STRING, T_INT, T_FLOAT, T_TRUE, T_FALSE);
    }

    default boolean isArithmeticExpression(Tokenizer tokenizer) {
        return false;
    }

    default boolean isFunctionExpression(Tokenizer tokenizer) {
        return tokenizer.sequence(T_IDENTIFIER, T_OPEN_BRACE);
    }

    default boolean isFieldPathExpression(Tokenizer tokenizer) {
        return tokenizer.isCurrent(T_FIELD_PATH);
    }

    default boolean isSubSelectExpression(Tokenizer tokenizer) {
        return false;
    }

}
