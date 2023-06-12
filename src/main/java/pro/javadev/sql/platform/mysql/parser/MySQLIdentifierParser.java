package pro.javadev.sql.platform.mysql.parser;

import pro.javadev.sql.internal.SQLDialect;
import pro.javadev.sql.internal.ast.IdentifierNode;
import pro.javadev.sql.internal.parser.ParserContext;
import pro.javadev.sql.internal.tokenizer.Tokenizer;
import pro.javadev.sql.platform.ansi_sql.parser.IdentifierParser;

import java.util.Locale;

public class MySQLIdentifierParser extends IdentifierParser {

    @Override
    public IdentifierNode parse(SQLDialect dialect, ParserContext context, Tokenizer tokenizer) {
        IdentifierNode identifier = super.parse(dialect, context, tokenizer);

        identifier.setIdentifier(identifier.getIdentifier().toUpperCase(Locale.ROOT));

        return identifier;
    }

}
