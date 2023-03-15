package pro.javadev.sql.library;

import java.util.HashMap;
import java.util.Map;

public interface Context {

    <T> T getProperty(String key);

    void setProperty(String key, Object value);

    class DefaultContext implements Context {

        private final Map<String, Object> values = new HashMap<>();

        @Override
        public <T> T getProperty(String key) {
            return (T) values.get(key);
        }

        @Override
        public void setProperty(String key, Object value) {
            values.put(key, value);
        }

    }

}
