package pro.javadev.sql.v1.lexer;

public interface Lexer {

    void tokenize(LexerContext context, String sql);

}

