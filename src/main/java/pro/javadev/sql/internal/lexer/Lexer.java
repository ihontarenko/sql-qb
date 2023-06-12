package pro.javadev.sql.internal.lexer;

import pro.javadev.sql.internal.SQLDialect;
import pro.javadev.sql.internal.tokenizer.Tokenizer;

public interface Lexer {
    Tokenizer tokenize(SQLDialect dialect, LexerContext context, String sql);
}

