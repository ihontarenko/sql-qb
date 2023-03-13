package pro.javadev.sql;

public class MathExpressionTokenizer implements Tokenizer<MathExpressionASTNode> {

    private static final TokenPattern<MathExpressionToken> PATTERN =
            TokenPattern.builder(MathExpressionToken.class)
                    .addPattern(TokenType.NUMBER, "\\d+(\\.\\d+)?")
                    .addPattern(TokenType.PLUS, "\\+")
                    .addPattern(TokenType.MINUS, "-")
                    .addPattern(TokenType.MULTIPLY, "\\*")
                    .addPattern(TokenType.DIVIDE, "/")
                    .addPattern(TokenType.LPAREN, "\\(")
                    .addPattern(TokenType.RPAREN, "\\)")
                    .build();

    private final TokenizerFactory tokenizerFactory;
    private final TokenizerContext context;

    public MathExpressionTokenizer(TokenizerFactory tokenizerFactory, TokenizerContext context) {
        this.tokenizerFactory = tokenizerFactory;
        this.context = context;
    }

    @Override
    public List<Token<MathExpressionToken>> tokenize(String input) {
        Lexer lexer = new Lexer(PATTERN, input);
        return lexer.tokenize().stream()
                .map(token -> {
                    TokenType type = token.getType();
                    String value = token.getValue();
                    switch (type) {
                        case NUMBER:
                            return new Token<>(type, new BigDecimal(value));
                        default:
                            return new Token<>(type);
                    }
                })
                .collect(Collectors.toList());
    }

    @Override
    public MathExpressionASTNode parse(List<Token<MathExpressionToken>> tokens) {
        return new MathExpressionASTNode(tokens.stream().map(Token::getValue).collect(Collectors.toList()));
    }

    @Override
    public TokenizerContext getContext() {
        return context;
    }

    public static class MathExpressionTokenizerFactory implements TokenizerFactory {
        @Override
        public <T extends ASTNode> Tokenizer<T> getTokenizer(Class<T> nodeClass) {
            if (MathExpressionASTNode.class.isAssignableFrom(nodeClass)) {
                return (Tokenizer<T>) new MathExpressionTokenizer(this, TokenizerContext.DEFAULT);
            }
            return null;
        }
    }
}
