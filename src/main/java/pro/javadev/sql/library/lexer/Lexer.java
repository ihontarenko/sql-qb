package pro.javadev.sql.library.lexer;

import pro.javadev.sql.library.SQLDialect;
import pro.javadev.sql.library.tokenizer.Tokenizer;

public interface Lexer {

    Tokenizer tokenize(SQLDialect dialect, LexerContext context, String sql);

}

