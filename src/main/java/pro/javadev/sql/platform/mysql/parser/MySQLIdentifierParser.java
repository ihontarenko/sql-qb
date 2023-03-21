package pro.javadev.sql.platform.mysql.parser;

import pro.javadev.sql.library.SQLDialect;
import pro.javadev.sql.library.ast.IdentifierNode;
import pro.javadev.sql.library.parser.ParserContext;
import pro.javadev.sql.library.tokenizer.Tokenizer;
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
