package pro.javadev.sql.v2;

public class Examples {

    public abstract class SQLLexerFactory {
        public abstract SQLLexer createLexer(String input);

        public static SQLLexerFactory getFactory(SQLDialect dialect, String version) {
            switch (dialect) {
                case MYSQL:
                    return MySQLLexerFactory.getFactory(version);
                case POSTGRESQL:
                    return PostgreSQLLexerFactory.getFactory(version);
                default:
                    throw new IllegalArgumentException("Unsupported SQL dialect: " + dialect);
            }
        }
    }

    public class MySQLLexerFactory extends SQLLexerFactory {
        private final String version;

        private MySQLLexerFactory(String version) {
            this.version = version;
        }

        public static MySQLLexerFactory getFactory(String version) {
            return new MySQLLexerFactory(version);
        }

        @Override
        public SQLLexer createLexer(String input) {
            switch (version) {
                case "5.7":
                    return new MySQL57Lexer(input);
                case "8.0":
                    return new MySQL80Lexer(input);
                default:
                    throw new IllegalArgumentException("Unsupported MySQL version: " + version);
            }
        }
    }

    public class PostgreSQLLexerFactory extends SQLLexerFactory {
        private final String version;

        private PostgreSQLLexerFactory(String version) {
            this.version = version;
        }

        public static PostgreSQLLexerFactory getFactory(String version) {
            return new PostgreSQLLexerFactory(version);
        }

        @Override
        public SQLLexer createLexer(String input) {
            switch (version) {
                case "9.5":
                    return new PostgreSQL95Lexer(input);
                case "10":
                    return new PostgreSQL10Lexer(input);
                default:
                    throw new IllegalArgumentException("Unsupported PostgreSQL version: "


            }
            return new PostgreSQL13Lexer(input);
            default:
            throw new IllegalArgumentException("Unsupported PostgreSQL version: " + version);
        }
    }
}

public enum SQLDialect {
    MYSQL,
    POSTGRESQL
}
