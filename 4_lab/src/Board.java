import Figures.Bishop;
import Figures.Figure;
import Figures.King;
import Figures.Knight;
import Figures.Pawn;
import Figures.Queen;
import Figures.Rook;

import java.util.ArrayList;

public class Board {
//TODO: Список фигур и начальное положение всех фигур
    private Figure fields[][] = new Figure[8][8];
    private ArrayList<String> takeWhite = new ArrayList(16);
    private ArrayList<String> takeBlack = new ArrayList(16);

    public char getColorGaming() {
        return colorGaming;
    }

    public void setColorGaming(char colorGaming) {
        this.colorGaming = colorGaming;
    }

    private char colorGaming;

    public void init(){
        this.fields[0] = new Figure[]{
                new Rook("R", 'w'), new Knight("N", 'w'),
                new Bishop("B", 'w'), new Queen("Q", 'w'),
                new King("K", 'w'), new Bishop("B", 'w'),
                new Knight("N", 'w'),new Rook("R", 'w')
        };
        this.fields[1] = new Figure[]{
                new Pawn("P", 'w'),    new Pawn("P", 'w'),
                new Pawn("P", 'w'),    new Pawn("P", 'w'),
                new Pawn("P", 'w'),    new Pawn("P", 'w'),
                new Pawn("P", 'w'),    new Pawn("P", 'w'),
        };

        this.fields[7] = new Figure[]{
                new Rook("R", 'b'), new Knight("N", 'b'),
                new Bishop("B", 'b'), new Queen("Q", 'b'),
                new King("K", 'b'), new Bishop("B", 'b'),
                new Knight("N", 'b'),new Rook("R", 'b')
        };
        this.fields[6] = new Figure[]{
                new Pawn("P", 'b'),    new Pawn("P", 'b'),
                new Pawn("P", 'b'),    new Pawn("P", 'b'),
                new Pawn("P", 'b'),    new Pawn("P", 'b'),
                new Pawn("P", 'b'),    new Pawn("P", 'b'),
        };
    }

    public String getCell(int row, int col){
        Figure figure = this.fields[row][col];
        if (figure == null){
            return "    ";
        }
        return " "+figure.getColor()+figure.getName()+" ";
    }

    public ArrayList<String> getTakeWhite() {
        return takeWhite;
    }

    public ArrayList<String> getTakeBlack() {
        return takeBlack;
    }

    public boolean move_figure(int row1, int col1, int row2, int col2 ){
        if(!((row1 >=0 && row1 < 8 ) && (col1 >=0 && col1 < 8) && (row2 >=0 && row2 < 8) && (col2 >=0 && col2 < 8)))
            return false;

        Figure figure =  this.fields[row1][col1];
        if(figure == null)
            return false;

        if(this.colorGaming != figure.getColor())
            return false;

        if (figure.canMove(this.fields, row1, col1, row2, col2) && this.fields[row2][col2]==null){
            System.out.println("move");
            this.fields[row2][col2] = figure;
            this.fields[row1][col1] = null;
            return true;
        }

        if (figure.canAttack(this.fields, row1, col1, row2, col2)
            && this.fields[row2][col2] != null
            && this.fields[row2][col2].getColor() != figure.getColor())
        {
            System.out.println("attack");
            switch (this.fields[row2][col2].getColor()) {
                case 'w' -> this.takeWhite.add(this.fields[row2][col2].getColor() + this.fields[row2][col2].getName());
                case 'b' -> this.takeBlack.add(this.fields[row2][col2].getColor() + this.fields[row2][col2].getName());
            }
            this.fields[row2][col2] = figure;
            this.fields[row1][col1] = null;
            return true;
        }

        return false;
    }

    public void print_board(){
        System.out.println(" +----+----+----+----+----+----+----+----+");
        for(int row = 7; row > -1; row--){
            System.out.print(row);
            for(int col = 0; col< 8; col++){
                System.out.print("|"+getCell(row, col));
             }
            System.out.println("|");
            System.out.println(" +----+----+----+----+----+----+----+----+");
        }

        for(int col = 0; col < 8; col++)
            System.out.print("    " + col);
    }

    public boolean kingStatus() {
        char currentColor = this.getColorGaming();
        int KingX = 0;
        int KingY = 0;

        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                if(this.fields[i][j] == null)
                    continue;

                Figure king = this.fields[i][j];
                if(!(king instanceof King) || king.getColor() != currentColor)
                    continue;

                KingX = j;
                KingY = i;
                break;
            }
        }

        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                Figure figure = this.fields[i][j];
                if(figure == null || figure.getColor() == currentColor)
                    continue;

                if(figure.canAttack(this.fields, i, j, KingY, KingX)){
                    boolean isCheck = isCheckAfterMove(figure, i, j, KingY, KingX);
                    if(isCheck) {
                        System.out.println("Шах и мат!");
                        return false;
                    }

                    System.out.println("Шах!");
                    return true;
                }
            }
        }

        return true;
    }

    private boolean isCheckAfterMove(Figure figure, int sourceX, int sourceY, int targetX, int targetY) {
        Figure originalFigure = this.fields[targetY][targetX];
        this.fields[targetY][targetX] = figure;
        this.fields[sourceY][sourceX] = null;

        char currentColor = this.getColorGaming();
        int kingX = -1;
        int kingY = -1;

        outerLoop:
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Figure piece = this.fields[i][j];
                if (piece instanceof King && piece.getColor() == currentColor) {
                    kingX = j;
                    kingY = i;
                    break outerLoop;
                }
            }
        }

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Figure piece = this.fields[i][j];
                if (piece != null && piece.getColor() != currentColor && piece.canAttack(this.fields, i, j, kingY, kingX)) {
                    this.fields[targetY][targetX] = originalFigure;
                    this.fields[sourceY][sourceX] = figure;
                    return true;
                }
            }
        }

        this.fields[targetY][targetX] = originalFigure;
        this.fields[sourceY][sourceX] = figure;
        return false;
    }
}
