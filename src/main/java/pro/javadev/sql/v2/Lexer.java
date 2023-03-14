package pro.javadev.sql.v2;

import java.util.ArrayList;
import java.util.List;

public class Lexer {

    private final CharSequence input;
    private       char         current;
    private       int          position;
    private       int          line;

    public Lexer(CharSequence input) {
        this.position = 0;
        this.line = 0;
        this.input = input;
        this.current = input.charAt(position);
    }

    private void advance() {
        position++;
        if (position > input.length() - 1) {
            current = '\0';
        } else {
            current = input.charAt(position);
        }
    }

    private void skipWhitespace() {
        while (current != '\0' && Character.isWhitespace(current)) {
            if (current == '\n') {
                line++;
            }
            advance();
        }
    }

    private String getWord() {
        StringBuilder result = new StringBuilder();

        while (current != '\0' && Character.isLetterOrDigit(current)) {
            result.append(current);
            advance();
        }

        return result.toString();
    }

    private String getString(char quote) {
        StringBuilder result = new StringBuilder();

        advance();

        while (current != '\0' && current != quote) {
            if (current == '\\') {
                advance();
                switch (current) {
                    case 'n' -> result.append('\n');
                    case 'r' -> result.append('\r');
                    case 't' -> result.append('\t');
                    default -> result.append(current);
                }
            } else {
                result.append(current);
            }
            advance();
        }

        if (current == quote) {
            advance();
        }

        return result.toString();
    }

    private String getSymbol() {
        StringBuilder result = new StringBuilder();

        while (current != '\0' && !Character.isLetterOrDigit(current) && !Character.isWhitespace(current)) {
            result.append(current);
            advance();
        }

        return result.toString();
    }

    public List<String> tokenize() {
        List<String> tokens = new ArrayList<>();

        while (position < input.length()) {
            char current = input.charAt(position);

            if (Character.isWhitespace(current)) {
                skipWhitespace();
            }

            if (Character.isLetterOrDigit(current)) {
                String word = getWord();
                tokens.add(word.toUpperCase());
            } else if (current == '\'' || current == '\"') {
                tokens.add(getString(current));
            } else {
                String symbol = getSymbol();
                tokens.add(symbol.toUpperCase());
            }
        }

        return tokens;
    }
}

