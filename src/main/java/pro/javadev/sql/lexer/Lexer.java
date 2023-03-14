package pro.javadev.sql.lexer;

import pro.javadev.sql.SQLDialect;
import pro.javadev.sql.common.Tokenizer;

import java.util.List;

public interface Lexer {

    Tokenizer tokenize(SQLDialect dialect, LexerContext context, String sql);

}

