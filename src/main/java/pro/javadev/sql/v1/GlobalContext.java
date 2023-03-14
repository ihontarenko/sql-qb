package pro.javadev.sql.v1;

import pro.javadev.sql.v1.lexer.LexerContext;
import pro.javadev.sql.v1.render.RendererContext;

public interface GlobalContext extends Context {

    RendererContext getRendererContext();

    void setRendererContext(RendererContext context);

    LexerContext getLexerContext();

    void setLexerContext(LexerContext context);

    class DefaultGlobalContext extends Context.DefaultContext implements GlobalContext {

        private static final String LEXER_CONTEXT    = "LEXER_CONTEXT";
        private static final String RENDERER_CONTEXT = "RENDERER_CONTEXT";

        @Override
        public RendererContext getRendererContext() {
            return getProperty(RENDERER_CONTEXT);
        }

        @Override
        public void setRendererContext(RendererContext context) {
            setProperty(RENDERER_CONTEXT, context);
        }

        @Override
        public LexerContext getLexerContext() {
            return getProperty(LEXER_CONTEXT);
        }

        @Override
        public void setLexerContext(LexerContext context) {
            setProperty(LEXER_CONTEXT, context);
        }

    }

}
