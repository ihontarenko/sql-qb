package pro.javadev.sql.library.parser;

import pro.javadev.sql.library.SQLDialect;
import pro.javadev.sql.library.node.Node;
import pro.javadev.sql.library.token.Token;
import pro.javadev.sql.library.tokenizer.Tokenizer;

public interface Parser<N extends Node> extends Node {

    N parse(SQLDialect dialect, ParserContext context, Tokenizer tokenizer);

    boolean matchable(ExpressionRecognizer recognizer, Tokenizer tokenizer);

    default ParserException parseErrorException(Token.Entry current, Token expected) {
        return new ParserException("EXPECTED TOKEN: [" + expected + "] BUT GOTTEN: [" + current + "]");
    }

}
