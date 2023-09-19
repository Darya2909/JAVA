import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        problem1();
        problem2();
        problem3();
        problem4();
        problem5();

    }

    public static void problem1() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int count = 0;
        while (n != 1) {
            if (n % 2 == 0) {
                n /= 2;
            }
            else {
                n = 3*n + 1;
            }
            count++;
        }
        System.out.println(count);
        scanner.close();
    }
    public static void problem2() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int count = 0;
        int sum = 0;
        while (n > 0) {
            int k = scanner.nextInt();
            if (count % 2 == 0) {
                sum = sum + k;
            }
            else {
                sum = sum - k;
            }
            n--;
            count++;
        }
        System.out.println(sum);
        scanner.close();
    }

    public static void problem3() {
        int x = 0;
        int y = 0;

        Scanner scanner = new Scanner(System.in);
        int s_x = scanner.nextInt();

        int s_y = scanner.nextInt();

        int min_steps = 0;

        while(true) {
            String direction = scanner.next();

            if(direction.equals("стоп"))
                break;

            if (x != s_x && y != s_y)
                min_steps++;

            int steps = scanner.nextInt();

            switch(direction) {
                case "север":
                    y += steps;
                    break;
                case "запад":
                    x -= steps;
                    break;
                case "восток":
                    x += steps;
                    break;
                case "юг":
                    y -= steps;
                    break;
                default:
                    System.out.print("Неправильно введено направление");
                    break;
            }

        }

        System.out.println(min_steps + 1);
        scanner.close();
    }

    public static void problem4() {

        Scanner scanner = new Scanner(System.in);
        int roads = scanner.nextInt();
        int max = 0;
        int count = 0;
        int roadnumber = 0;

        while (roads != 0) {
            count++;
            int tunnels = scanner.nextInt();
            int min = Integer.MAX_VALUE;
            while (tunnels != 0) {
                int high = scanner.nextInt();
                if (high < min)
                    min = high;
                tunnels--;
            }
            if(min > max) {
                max = min;
                roadnumber = count;
            }
            roads--;
        }

        System.out.println(roadnumber + " " + max);
        scanner.close();
    }

    public static void problem5() {
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();

        if (number < 100 || number > 999) {
            System.out.println("Неподходящее число");
            return;
        }

        int sum = 0;
        int multiplication = 1;

        while (number > 0) {
            multiplication *= number % 10;
            sum += number % 10;

            number = number / 10;
        }

        if(sum % 2 == 0 && multiplication % 2 == 0)
            System.out.println("Число является дважды чётным");
        else
            System.out.println("Число не является дважды чётным");

        scanner.close();
    }
}