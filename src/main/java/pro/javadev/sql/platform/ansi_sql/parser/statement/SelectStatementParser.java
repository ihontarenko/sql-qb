package pro.javadev.sql.platform.ansi_sql.parser.statement;

import pro.javadev.sql.library.SQLDialect;
import pro.javadev.sql.library.ast.ASTNode;
import pro.javadev.sql.library.ast.statement.AliasExpression;
import pro.javadev.sql.library.parser.AbstractParser;
import pro.javadev.sql.library.parser.ParserContext;
import pro.javadev.sql.library.token.DefaultToken;
import pro.javadev.sql.library.tokenizer.Tokenizer;
import pro.javadev.sql.platform.ansi_sql.SQLToken;
import pro.javadev.sql.library.ast.statement.ColumnItem;
import pro.javadev.sql.library.ast.statement.SelectStatement;

import java.util.ArrayList;
import java.util.List;

import static pro.javadev.sql.platform.ansi_sql.SQLToken.T_SQL_AS;

public class SelectStatementParser extends AbstractParser<SelectStatement> {

    @Override
    public SelectStatement parse(SQLDialect dialect, ParserContext context, Tokenizer tokenizer) {
        SelectStatement node = new SelectStatement();

        consumeToken(SQLToken.T_SQL_SELECT, tokenizer);

        parseSelectItems(dialect, context, tokenizer).forEach(node::add);

        return node;
    }

    protected List<ASTNode> parseSelectItems(SQLDialect dialect, ParserContext context, Tokenizer tokenizer) {
        List<ASTNode> selectItems = new ArrayList<>();

        selectItems.add(parseSelectItem(dialect, context, tokenizer));

        while (tokenizer.isCurrent(DefaultToken.T_COMMA)) {
            consumeToken(DefaultToken.T_COMMA, tokenizer);
            selectItems.add(parseSelectItem(dialect, context, tokenizer));
        }

        return selectItems;
    }

    protected ColumnItem parseSelectItem(SQLDialect dialect, ParserContext context, Tokenizer tokenizer) {
        ColumnItem item = context.getParser(dialect, ColumnItem.class).parse(dialect, context, tokenizer);

        if (isCurrent(T_SQL_AS, tokenizer)) {
            item.add(context.getParser(dialect, AliasExpression.class).parse(dialect, context, tokenizer));
        }

        return item;
    }

}

