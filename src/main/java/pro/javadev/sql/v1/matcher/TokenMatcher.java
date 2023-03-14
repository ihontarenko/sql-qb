package pro.javadev.sql.v1.matcher;

public interface TokenMatcher<V, T> {

    T match(V value);

}
