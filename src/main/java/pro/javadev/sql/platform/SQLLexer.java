package pro.javadev.sql.platform;

import pro.javadev.sql.library.SQLDialect;
import pro.javadev.sql.library.common.PriorityComparator;
import pro.javadev.sql.library.lexer.Lexer;
import pro.javadev.sql.library.lexer.LexerContext;
import pro.javadev.sql.library.lexer.LexerException;
import pro.javadev.sql.library.lexer.TokenPattern;
import pro.javadev.sql.library.token.Token.Entry;
import pro.javadev.sql.library.tokenizer.Tokenizer;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SQLLexer implements Lexer {

    private int position;

    public SQLLexer() {
        this.position = 0;
    }

    @Override
    public Tokenizer tokenize(SQLDialect dialect, LexerContext context, String sql) {
        int                ordinal  = 0;
        List<TokenPattern> patterns = getTokenPatterns(dialect, context);
        List<Entry>        entries  = new ArrayList<>();

        while (position < sql.length() - 1) {
            Entry entry = null;

            skipWhitespace(sql);

            for (TokenPattern token : patterns) {
                Pattern pattern = token.getPattern();
                Matcher matcher = pattern.matcher(sql).region(position, sql.length());

                if (matcher.lookingAt()) {
                    entry = Entry.of(token.getToken(), sql.substring(position, matcher.end()), position, ordinal++);
                    this.position += matcher.end() - position;
                    break;
                }
            }

            if (entry == null) {
                throw unexpectedCharacterException(dialect, sql);
            }

            entries.add(entry);
        }

        this.position = 0;

        return new SQLTokenizer(entries);
    }

    private void skipWhitespace(String sql) {
        while (position < sql.length() && Character.isWhitespace(sql.charAt(position))) {
            position++;
        }
    }

    private List<TokenPattern> getTokenPatterns(SQLDialect dialect, LexerContext context) {
        List<TokenPattern> patterns = context.getTokenPatterns(dialect);

        patterns.addAll(context.getTokenPatterns(SQLDialect.ANSI_SQL));
        patterns.sort(new PriorityComparator<>());

        return patterns;
    }

    private RuntimeException unexpectedCharacterException(SQLDialect dialect, String sql) {
        String template  = "[DIALECT:%s] UNEXPECTED CHARACTER [%s]:%d SQL: (%s...)";
        String subSQL    = sql.substring(position, Math.min(position + 10, sql.length())).replaceAll("[\n\t]+", " ");
        char   character = sql.charAt(position);
        String message   = String.format(template, dialect.toString(), character, position, subSQL);

        return new LexerException(message);
    }

}
