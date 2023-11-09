package Figures;

public class Queen extends Figure{
    public Queen(String name, char color) {
        super(name, color);
    }

    @Override
    public boolean canMove(Figure field[][], int row, int col, int row1, int col1) {
        if (!super.canMove(field, row, col, row1, col1)) {
            return false;
        }

        int rowDiff = Math.abs(row - row1);
        int colDiff = Math.abs(col - col1);

        if (rowDiff == 0 || colDiff == 0) {
            return true;
        } else if (rowDiff == colDiff) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean canAttack(Figure field[][], int row, int col, int row1, int col1) {
        return canMove(field, row, col, row1, col1);
    }
}
