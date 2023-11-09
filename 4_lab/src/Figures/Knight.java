package Figures;

public class Knight extends Figure{
    public Knight(String name, char color) {
        super(name, color);
    }

    @Override
    public boolean canMove(Figure field[][], int row, int col, int row1, int col1) {
        if (!super.canMove(field, row, col, row1, col1)) {
            return false;
        }

        int[] dx = {2, 2, -2, -2, 1, 1, -1, -1};
        int[] dy = {1, -1, 1, -1, 2, -2, 2, -2};

        for (int i = 0; i < dx.length; i++) {
            int newRow = row + dx[i];
            int newCol = col + dy[i];
            if (newRow >= 0 && newRow < 8 && newCol >= 0 && newCol < 8 && newRow == row1 && newCol == col1) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean canAttack(Figure field[][], int row, int col, int row1, int col1) {
        return canMove(field, row, col, row1, col1);
    }
}
