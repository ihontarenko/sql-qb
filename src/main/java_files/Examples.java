package pro.javadev.sql;

public class Examples {

    public static void main(String[] args) {
        SQLDialect dialect = SQLDialect.MYSQL;
        LexerConfigurator lexerConfigurator = new LexerConfigurator(dialect);
        Lexer lexer = lexerConfigurator.getLexer();

        TokenizerConfigurator tokenizerConfigurator = new TokenizerConfigurator(dialect);
        Tokenizer tokenizer = tokenizerConfigurator.getTokenizer();

        QueryBuilderConfigurator queryBuilderConfigurator = new QueryBuilderConfigurator(dialect);
        QueryBuilder queryBuilder = queryBuilderConfigurator.getQueryBuilder();

        RendererFactoryConfigurator rendererFactoryConfigurator = new RendererFactoryConfigurator(dialect);
        RendererFactory rendererFactory = rendererFactoryConfigurator.getRendererFactory();


        Lexer lexer = new Lexer();
        lexer.addTokenPattern(TokenType.KEYWORD, "\\bSELECT\\b", "");
        lexer.addTokenPattern(TokenType.KEYWORD, "\\bFROM\\b", "");
        lexer.addTokenPattern(TokenType.KEYWORD, "\\bWHERE\\b", "");
        lexer.addTokenPattern(TokenType.KEYWORD, "\\bIN\\b", " ");

        String input = "SELECT column1 FROM table1 WHERE column2 IN (1, 2, 3)";
        List<Token> tokens = lexer.tokenize(input);


        Lexer lexer = new Lexer();

// Додати шаблон для розпізнавання ідентифікаторів
        lexer.addTokenPattern("[a-zA-Z_][a-zA-Z0-9_]*", TokenType.IDENTIFIER);

// Додати шаблон для розпізнавання числових літералів
        lexer.addTokenPattern("[0-9]+", TokenType.LITERAL);

        // Створюємо об'єкт лексера для MySQL
        Lexer lexer = new Lexer(SQLDialect.MYSQL);

// Розбираємо SQL-запит за допомогою лексера, токенайзера та парсера
        List<Token> tokens = lexer.parse(sql);
        Tokenizer tokenizer = new Tokenizer(SQLDialect.MYSQL);
        ASTNode rootNode = tokenizer.tokenize(tokens);

// Перетворюємо AST-дерево на об'єкт SelectASTNode
        SelectASTNode selectNode = (SelectASTNode) rootNode.getChildren(SelectASTNode.class);


    }

}
