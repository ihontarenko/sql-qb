package pro.javadev.sql.library.ast;

public class OffsetNode extends ASTNode {

    private int offset;

    public OffsetNode(int offset) {
        this.offset = offset;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    @Override
    public String toString() {
        return "OFFSET_NODE[offset=%d]".formatted(offset);
    }

}
