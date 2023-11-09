import java.util.Scanner;

public class Main {
    public static Board board = new Board();
    public static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        board.setColorGaming('w');
        board.init();

        while (true) {
            boolean curKingStatus = board.kingStatus();
            if(!curKingStatus){
                System.out.println("Игра окончена.");

                if(board.getColorGaming() == 'w')
                    System.out.println("Белые выйграли");
                else
                    System.out.println("Черные выйграли.");

                in.close();
                break;
            }

            board.print_board();
            System.out.println();
            System.out.println("Команды: ");
            System.out.println("exit - Выход из игры (Сдаться)");
            System.out.println("y1 x1 y2 x2 - Ход фигуры из клетки x1, y1 в клетку x2, y2");

            System.out.println("Взятые Белые:" + board.getTakeWhite().toString());
            System.out.println("Взятые Черные:" + board.getTakeBlack().toString());

            switch (board.getColorGaming()) {
                case 'w' -> System.out.println("Ход Белых:");
                case 'b' -> System.out.println("Ход Черных:");
            }

            String inputLine = in.nextLine();
            if (inputLine.equals("exit")){
                System.out.println("Игра завершена.");
                in.close();
                break;
            }

            int x1, y1, x2, y2;
            try {
                String[] coords = inputLine.split(" ");
                y1 = Integer.parseInt(coords[0]);
                x1 = Integer.parseInt(coords[1]);
                y2 = Integer.parseInt(coords[2]);
                x2 = Integer.parseInt(coords[3]);
            } catch (Exception ex) {
                System.out.println("Ошибка хода, повторите ввод хода!");
                continue;
            }

            if(board.move_figure(y1, x1, y2, x2))
                switch (board.getColorGaming()) {
                    case 'w' -> board.setColorGaming('b');
                    case 'b' -> board.setColorGaming('w');
                }
            else
                System.out.println("Ошибка хода, повторите ввод хода!");
        }
    }
}