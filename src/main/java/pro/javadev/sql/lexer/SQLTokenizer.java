package pro.javadev.sql.lexer;

import pro.javadev.sql.common.AbstractTokenizer;
import pro.javadev.sql.common.Token;

import java.util.List;

public class SQLTokenizer extends AbstractTokenizer {

    public SQLTokenizer(List<Token.Entry> entries) {
        super(entries);
    }

}
