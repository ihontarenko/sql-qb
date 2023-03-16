package pro.javadev.sql.platform.oracle.ast;

import pro.javadev.sql.library.ast.ASTNode;

public class RowNumNode extends ASTNode {

    private final int rowNumber;

    public RowNumNode(int rowNumber) {
        this.rowNumber = rowNumber;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    @Override
    public String toString() {
        return "ROW_NUM_NODE{rowNumber=%d}".formatted(rowNumber);
    }

}
