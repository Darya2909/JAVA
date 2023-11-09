package Figures;

public class King extends Figure{
    public King(String name, char color) {
        super(name, color);
    }

    @Override
    public boolean canMove(Figure field[][], int row, int col, int row1, int col1) {
        if (!super.canMove(field, row, col, row1, col1))
            return false;

        int diffRow = Math.abs(row1 - row);
        int diffCol = Math.abs(col1 - col);
        return (diffRow == 1 && diffCol == 2) || (diffRow == 2 && diffCol == 1);
    }

    @Override
    public boolean canAttack(Figure field[][], int row, int col, int row1, int col1) {
        return Math.abs(row - row1) == 1 && Math.abs(col - col1) == 1;
    }
}
