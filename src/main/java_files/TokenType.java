package pro.javadev.sql;

public enum TokenType {
    // Keywords
    SELECT,
    FROM,
    WHERE,
    GROUP,
    BY,
    ORDER,
    ASC,
    DESC,
    LIMIT,
    OFFSET,
    JOIN,
    INNER,
    OUTER,
    LEFT,
    RIGHT,
    FULL,
    ON,

    // Identifiers
    IDENTIFIER,

    // Literals
    INTEGER,
    FLOAT,
    STRING,

    // Operators
    PLUS,
    MINUS,
    MULTIPLY,
    DIVIDE,
    MOD,
    CONCATENATE,
    EQUALS,
    NOT_EQUALS,
    GREATER_THAN,
    LESS_THAN,
    GREATER_THAN_OR_EQUALS,
    LESS_THAN_OR_EQUALS,
    AND,
    OR,
    NOT,
    LIKE,
    IS,
    NULL,

    // Punctuation
    COMMA,
    PERIOD,
    LPAREN,
    RPAREN,
    SEMICOLON,

    // Miscellaneous
    WHITESPACE,
    COMMENT,
    EOF
}
