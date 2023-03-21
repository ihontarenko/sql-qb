package pro.javadev.sql.library;

public enum SQLDialect {

    MYSQL("MySQL", '`', '`'),
    MSSQL("Microsoft SQL Server", '[', ']'),
    ORACLE("Oracle", '"', '"'),
    ANSI_SQL("ANSI SQL", '`', '`');

    private final String name;
    private final char   closeEscapeCharacter;
    private final char   openEscapeCharacter;

    SQLDialect(String name, char openEscapeCharacter, char closeEscapeCharacter) {
        this.name = name;
        this.closeEscapeCharacter = closeEscapeCharacter;
        this.openEscapeCharacter = openEscapeCharacter;
    }

    public String getName() {
        return name;
    }

    public char getOpenEscapeCharacter() {
        return openEscapeCharacter;
    }

    public char getCloseEscapeCharacter() {
        return closeEscapeCharacter;
    }

    @Override
    public String toString() {
        return name;
    }

}
