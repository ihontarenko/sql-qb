package pro.javadev.sql.internal.parser;

import pro.javadev.sql.internal.SQLDialect;
import pro.javadev.sql.internal.node.Node;
import pro.javadev.sql.internal.token.Token;
import pro.javadev.sql.internal.tokenizer.Tokenizer;

public interface Parser<N extends Node> extends Node {

    N parse(SQLDialect dialect, ParserContext context, Tokenizer tokenizer);

    boolean matchable(ExpressionRecognizer recognizer, Tokenizer tokenizer);

    default ParserException parseErrorException(Token.Entry current, Token expected) {
        return new ParserException("EXPECTED TOKEN: [" + expected + "] BUT GOTTEN: [" + current + "]");
    }

}
