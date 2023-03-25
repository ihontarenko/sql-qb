package pro.javadev.sql.library.parser;

import pro.javadev.sql.library.token.Token;
import pro.javadev.sql.library.tokenizer.Tokenizer;

import java.util.Arrays;
import java.util.List;

import static pro.javadev.sql.library.token.DefaultToken.*;
import static pro.javadev.sql.platform.ansi_sql.SQLToken.*;

@SuppressWarnings({"unused"})
public interface ExpressionRecognizer {

    List<Token> ARITHMETIC_TOKENS = Arrays.asList(T_MINUS, T_PLUS, T_DIVIDE, T_MULTIPLY, T_PERCENT);
    List<Token> STATEMENTS_TOKENS = Arrays.asList(T_SQL_SELECT, T_SQL_UPDATE, T_SQL_DELETE, T_SQL_INSERT);

    default boolean isStatementExpression(Tokenizer tokenizer) {
        return tokenizer.isCurrent(STATEMENTS_TOKENS.toArray(Token[]::new));
    }

    default boolean isExpression(Tokenizer tokenizer) {
        return isFieldPathExpression(tokenizer) || isFunctionExpression(tokenizer)
                || isIdentifier(tokenizer) || isLiteralExpression(tokenizer);
    }

    default boolean isOpenBrace(Tokenizer tokenizer) {
        return tokenizer.isCurrent(T_OPEN_BRACE);
    }

    default boolean isIdentifier(Tokenizer tokenizer) {
        return tokenizer.isCurrent(T_IDENTIFIER);
    }

    default boolean isLiteralExpression(Tokenizer tokenizer) {
        return tokenizer.isCurrent(T_STRING, T_INT, T_FLOAT, T_TRUE, T_FALSE, T_NULL);
    }

    default boolean isArithmeticExpression(Tokenizer tokenizer) {
        boolean result = false;

        if (isLiteralExpression(tokenizer)) {
            result = tokenizer.isNext(ARITHMETIC_TOKENS.toArray(Token[]::new));
        } else if (isFunctionExpression(tokenizer)) {
            result = ARITHMETIC_TOKENS.contains(tokenizer.tokenizer(1).lookOver(T_OPEN_BRACE, T_CLOSE_BRACE).token());
        }

        return result;
    }

    default boolean isArithmeticOperator(Tokenizer tokenizer) {
        return tokenizer.isCurrent(ARITHMETIC_TOKENS.toArray(Token[]::new));
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
