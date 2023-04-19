package pro.javadev.sql.platform.ansi_sql.parser;

import pro.javadev.sql.library.SQLDialect;
import pro.javadev.sql.library.ast.ASTNode;
import pro.javadev.sql.library.ast.AliasExpression;
import pro.javadev.sql.library.parser.*;
import pro.javadev.sql.library.tokenizer.Tokenizer;
import pro.javadev.sql.library.ast.ColumnItem;
import pro.javadev.sql.library.ast.SelectStatement;

import java.util.ArrayList;
import java.util.List;

import static pro.javadev.sql.library.token.DefaultToken.T_COMMA;
import static pro.javadev.sql.library.token.DefaultToken.T_IDENTIFIER;
import static pro.javadev.sql.platform.ansi_sql.SQLToken.T_SQL_AS;
import static pro.javadev.sql.platform.ansi_sql.SQLToken.T_SQL_SELECT;

public class SelectStatementParser extends AbstractParser<SelectStatement> {

    @Override
    public SelectStatement parse(SQLDialect dialect, ParserContext context, Tokenizer tokenizer) {
        SelectStatement node = new SelectStatement();

        shift(T_SQL_SELECT, tokenizer);
        parseSelectItems(dialect, context, tokenizer)
                .forEach(node::add);

        return node;
    }

    protected List<ASTNode> parseSelectItems(SQLDialect dialect, ParserContext context, Tokenizer tokenizer) {
        List<ASTNode> selectItems = new ArrayList<>();

        selectItems.add(parseSelectItem(dialect, context, tokenizer));

        while (tokenizer.isCurrent(T_COMMA)) {
            shift(T_COMMA, tokenizer);
            selectItems.add(parseSelectItem(dialect, context, tokenizer));
        }

        return selectItems;
    }

    protected ColumnItem parseSelectItem(SQLDialect dialect, ParserContext context, Tokenizer tokenizer) {
        ColumnItem item = context.getParser(dialect, ColumnItem.class).parse(dialect, context, tokenizer);

        if (tokenizer.isCurrent(T_SQL_AS, T_IDENTIFIER)) {
            item.add(context.getParser(dialect, AliasExpression.class).parse(dialect, context, tokenizer));
        }

        return item;
    }

    @Override
    public boolean matchable(ExpressionRecognizer recognizer, Tokenizer tokenizer) {
        return recognizer.isStatementExpression(tokenizer) && tokenizer.isCurrent(T_SQL_SELECT);
    }

}

