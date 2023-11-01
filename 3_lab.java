import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;



class Cinema {
    private String name;
    private Map<String,  List<Theater>> theatersByMovie;
    private Map<String,  Integer> MovieByDuration;

    private Map<String, String> MovieByTime;

    public Cinema(String name) {
        this.name = name;
        this.theatersByMovie = new HashMap<>();
        this.MovieByDuration = new HashMap<>();
        this.MovieByTime = new HashMap<>();

    }

    public String getName() {
        return name;
    }

    public void addTheater(String movie, Theater theater) {
        if (!theatersByMovie.containsKey(movie)) {
            theatersByMovie.put(movie, new ArrayList<>());
        }
        theatersByMovie.get(movie).add(theater);
    }

    public void addDuration(String movie, int duration) {

        MovieByDuration.put(movie, duration);
    }

    public void addTime(String movie, String time) {

        MovieByTime.put(movie, time);
    }

    public List<Theater> getTheatersForMovie(String movie) {
        return theatersByMovie.getOrDefault(movie, new ArrayList<>());
    }
}

class Theater {
    private String name;
    private int capacity;
    private boolean[] seats;

    public Theater(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.seats = new boolean[capacity];
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean[] getSeats() {
        return seats;
    }

    public boolean isSeatAvailable(int seatNumber) {
        return !seats[seatNumber - 1];
    }

    public void bookSeat(int seatNumber) {
        seats[seatNumber - 1] = true;
    }
}

class TicketingSystem {
    private Map<String, Cinema> cinemasByName;

    public TicketingSystem() {
        this.cinemasByName = new HashMap<>();
    }

    public void addCinema(String name) {
        cinemasByName.put(name, new Cinema(name));
    }

    public void addTheater(String cinemaName, String movie, Theater theater, int duration, String time) {
        Cinema cinema = cinemasByName.get(cinemaName);
        if (cinema != null) {
            cinema.addTheater(movie, theater);
            cinema.addDuration(movie,duration);
            cinema.addTime(movie, time);
        } else {
            System.out.println("Cinema not found.");
        }
    }

    public void bookTicket(String cinemaName, String movie, String theaterName, int seatNumber) {
        Cinema cinema = cinemasByName.get(cinemaName);
        if (cinema != null) {
            List<Theater> theaters = cinema.getTheatersForMovie(movie);
            if (theaters != null) {
                Theater theater = theaters.stream().filter(t -> t.getName().equals(theaterName)).findFirst().orElse(null);
                if (theater != null) {
                    boolean[] seats = theater.getSeats();
                    if (seatNumber >= 1 && seatNumber <= seats.length) {
                        if (theater.isSeatAvailable(seatNumber)) {
                            theater.bookSeat(seatNumber);
                            System.out.println("Ticket booked successfully.");
                        } else {
                            System.out.println("Seat is already booked.");
                        }
                    } else {
                        System.out.println("Invalid seat number.");
                    }
                } else {
                    System.out.println("Theater not found for the movie.");
                }
            } else {
                System.out.println("No theaters found for the movie.");
            }
        } else {
            System.out.println("Cinema not found.");
        }
    }

    public void printSeatPlan(String cinemaName, String movie, String theaterName) {
        Cinema cinema = cinemasByName.get(cinemaName);
        if (cinema != null) {
            List<Theater> theaters = cinema.getTheatersForMovie(movie);
            if (theaters != null) {
                Theater theater = theaters.stream().filter(t -> t.getName().equals(theaterName)).findFirst().orElse(null);
                if (theater != null) {
                    boolean[] seats = theater.getSeats();
                    System.out.println("Seat Plan for " + cinemaName + " - " + movie + " - " + theaterName);
                    for (int i = 0; i < seats.length; i++) {
                        if (seats[i]) {
                            System.out.println("Seat " + (i + 1) + ": Booked");
                        } else {
                            System.out.println("Seat " + (i + 1) + ": Available");
                        }
                    }
                } else {
                    System.out.println("Theater not found for the movie.");
                }
            } else {
                System.out.println("No theaters found for the movie.");
            }
        } else {
            System.out.println("Cinema not found.");
        }
    }
}


public class Main {
    public static void main(String[] args) {
        TicketingSystem ticketingSystem = new TicketingSystem();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Admin or user? Write '1' or '2'");
        Integer reply = scanner.nextInt();
        if (reply == 1) {
            while (true) {
                System.out.println("1. Add Cinema");
                System.out.println("2. Add Theater");
                System.out.println("3. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1:
                        System.out.print("Enter cinema name: ");
                        String cinemaName = scanner.nextLine();
                        ticketingSystem.addCinema(cinemaName);
                        break;
                    case 2:
                        System.out.print("Enter cinema name: ");
                        cinemaName = scanner.nextLine();
                        System.out.print("Enter movie name: ");
                        String movieName = scanner.nextLine();
                        System.out.print("Enter movie start time: ");
                        String time = scanner.nextLine();
                        System.out.print("Enter theater name: ");
                        String theaterName = scanner.nextLine();
                        System.out.print("Enter movie duration in minutes: ");
                        int duration = scanner.nextInt();
                        System.out.print("Enter theater capacity: ");
                        int capacity = scanner.nextInt();
                        scanner.nextLine();
                        ticketingSystem.addTheater(cinemaName, movieName, new Theater(theaterName, capacity), duration, time);
                        break;
                    case 3:
                        System.exit(0);
                }
            }
        }
        if (reply == 2) {
            while (true) {
                System.out.println("1. Book Ticket");
                System.out.println("2. Print Seat Plan");
                System.out.println("3. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1:
                        System.out.print("Enter cinema name: ");
                        String cinemaName = scanner.nextLine();
                        System.out.print("Enter movie name: ");
                        String movieName = scanner.nextLine();
                        System.out.print("Enter theater name: ");
                        String theaterName = scanner.nextLine();
                        System.out.print("Enter seat number: ");
                        int seatNumber = scanner.nextInt();
                        scanner.nextLine();
                        ticketingSystem.bookTicket(cinemaName, movieName, theaterName, seatNumber);
                        break;
                    case 2:
                        System.out.print("Enter cinema name: ");
                        cinemaName = scanner.nextLine();
                        System.out.print("Enter movie name: ");
                        movieName = scanner.nextLine();
                        System.out.print("Enter theater name: ");
                        theaterName = scanner.nextLine();
                        ticketingSystem.printSeatPlan(cinemaName, movieName, theaterName);
                        break;
                    case 3:
                        System.exit(0);
                }
            }
        }
        else
        {
            System.out.print("Wrong Reply ");
        }
    }
}