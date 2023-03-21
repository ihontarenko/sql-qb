package pro.javadev.sql.platform.ansi_sql.parser;

import pro.javadev.sql.library.SQLDialect;
import pro.javadev.sql.library.ast.LiteralNode;
import pro.javadev.sql.library.parser.AbstractParser;
import pro.javadev.sql.library.parser.ParserContext;
import pro.javadev.sql.library.tokenizer.Tokenizer;

public class LiteralParser extends AbstractParser<LiteralNode> {

    @Override
    public LiteralNode parse(SQLDialect dialect, ParserContext context, Tokenizer tokenizer) {
        return new LiteralNode(tokenizer.next().value());
    }

}

