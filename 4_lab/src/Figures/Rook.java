package Figures;

public class Rook extends Figure{
    public Rook(String name, char color) {
        super(name, color);
    }

    @Override
    public boolean canMove(Figure field[][], int row, int col, int row1, int col1) {
        if (!super.canMove(field, row, col, row1, col1)) {
            return false;
        }

        if (row == row1 && col != col1) {
            int start = Math.min(col, col1) + 1;
            int end = Math.max(col, col1);
            for (int i = start; i < end; i++) {
                if (field[row][i] != null) {
                    return false;
                }
            }
            return true;
        }

        if (row != row1 && col == col1) {
            int start = Math.min(row, row1) + 1;
            int end = Math.max(row, row1);
            for (int i = start; i < end; i++) {
                if (field[i][col] != null) {
                    return false;
                }
            }
            return true;
        }

        return false;
    }

    @Override
    public boolean canAttack(Figure field[][], int row, int col, int row1, int col1) {
        return canMove(field, row, col, row1, col1);
    }
}
