package Figures;

public class Bishop extends Figure{
    public Bishop(String name, char color) {
        super(name, color);
    }

    @Override
    public boolean canMove(Figure field[][], int row, int col, int row1, int col1) {
        if (!super.canMove(field, row, col, row1, col1))
            return false;

        if(row == row1 || col == col1)
            return false;

        int rowIndex = row;
        int columnIndex = col;
        int rowDirection = (row1 - row) / Math.abs(row1 - row);
        int colDirection = (col1 - col) / Math.abs(col1 - col);

        while(rowIndex != row1 && columnIndex != col1) {
            rowIndex += rowDirection;
            columnIndex += colDirection;

            if(field[rowIndex][columnIndex] != null)
                return false;
        }

        return true;
    }

    @Override
    public boolean canAttack(Figure field[][], int row, int col, int row1, int col1) {
        return canMove(field, row, col, row1, col1);
    }
}
