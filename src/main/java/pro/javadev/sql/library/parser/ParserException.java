package pro.javadev.sql.library.parser;

import pro.javadev.sql.library.tokenizer.Tokenizer;

public class ParserException extends RuntimeException {

    public ParserException(String message) {
        super(message);
    }

    public ParserException(String message, Tokenizer tokenizer) {
        this("%s SEQUENCE: %s".formatted(message, getTokensSequence(tokenizer)));
    }

    public ParserException(String message, Throwable cause) {
        super(message, cause);
    }

    private static String getTokensSequence(Tokenizer tokenizer) {
        Tokenizer     sub     = tokenizer.tokenizer(-5);
        int           limit   = 10;
        int           count   = 0;
        StringBuilder builder = new StringBuilder();

        while (count++ < limit && sub.hasNext()) {
            if (count - 1 == limit / 2) {
                builder.append("[* ").append(sub.next().value()).append(" *] ");
            } else {
                builder.append(sub.next().value()).append(" ");
            }
        }

        return builder.toString();
    }

}
