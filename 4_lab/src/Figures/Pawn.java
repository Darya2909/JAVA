package Figures;

public class Pawn extends Figure {

    private boolean first = true;

    public Pawn(String name, char color) {
        super(name, color);
    }

    @Override
    public boolean canMove(Figure field[][], int row, int col, int row1, int col1) {
        if (!super.canMove(field, row, col, row1, col1))
            return false;

        if (this.first) {
            boolean validWhiteMove = (row1 == row + 1) && (col == col1) && (this.getColor() == 'w');
            boolean validBlackMove = (row1 == row - 1) && (col == col1) && (this.getColor() == 'b');
            if (validWhiteMove || validBlackMove) {
                this.first = false;
                return true;
            }
        } else {
            boolean validWhiteMove = (row1 == row + 1) && (col == col1) && (this.getColor() == 'w');
            boolean validBlackMove = (row1 == row - 1) && (col == col1) && (this.getColor() == 'b');
            return validWhiteMove || validBlackMove;
        }

        return false;
    }

    @Override
    public boolean canAttack(Figure field[][], int row, int col, int row1, int col1) {
        return Math.abs(row - row1) == 1 && Math.abs(col - col1) == 1;
    }
}
