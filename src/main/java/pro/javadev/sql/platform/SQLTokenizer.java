package pro.javadev.sql.platform;

import pro.javadev.sql.internal.token.Token;
import pro.javadev.sql.internal.tokenizer.AbstractTokenizer;

import java.util.List;

public class SQLTokenizer extends AbstractTokenizer {

    public SQLTokenizer(List<Token.Entry> entries) {
        super(entries);
    }

}
