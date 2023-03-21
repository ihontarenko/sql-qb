package pro.javadev.sql.platform.ansi_sql.parser;

import pro.javadev.sql.library.SQLDialect;
import pro.javadev.sql.library.parser.AbstractParser;
import pro.javadev.sql.library.parser.ParserContext;
import pro.javadev.sql.library.tokenizer.Tokenizer;
import pro.javadev.sql.library.ast.LiteralNode;

public class LiteralParser extends AbstractParser<LiteralNode> {

    @Override
    public LiteralNode parse(SQLDialect dialect, ParserContext context, Tokenizer tokenizer) {
        LiteralNode node = new LiteralNode(null);

        return node;
    }

}

