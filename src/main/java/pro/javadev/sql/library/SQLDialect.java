package pro.javadev.sql.library;

public enum SQLDialect {

    MYSQL("MySQL", '`'),
    MSSQL("Microsoft SQL Server", '['),
    ORACLE("Oracle", '"'),
    ANSI_SQL("ANSI SQL", '`');

    private final String name;
    private final char   escapeCharacter;

    SQLDialect(String name, char escapeCharacter) {
        this.name = name;
        this.escapeCharacter = escapeCharacter;
    }

    public String getName() {
        return name;
    }

    public char getEscapeCharacter() {
        return escapeCharacter;
    }

    @Override
    public String toString() {
        return name;
    }

}
