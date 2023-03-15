package pro.javadev.sql.library.parser;

import pro.javadev.sql.library.SQLDialect;
import pro.javadev.sql.library.node.Node;
import pro.javadev.sql.library.tokenizer.Tokenizer;

public interface Parser<N extends Node> {
    N parse(SQLDialect dialect, ParserContext context, Tokenizer tokenizer);
}
