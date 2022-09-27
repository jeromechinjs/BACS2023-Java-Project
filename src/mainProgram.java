import java.util.Scanner;

public class mainProgram {
    public static void main(String[] args) {

        //// --MOVIES MODULE--////
        Movie movie = new Movie();
        Movie movies[] = {
                new Movie("Star Wars", "English", "Sci-Fi", "3:00PM", "13+", 2),
                new Movie("Your Name", "Japanese", "Fiction", "5:00PM", "13+", 5),
                new Movie("Deadpool", "English", "Comics", "5:00PM", "18+", 1),
                new Movie("Among Us", "English", "Sci-Fi", "8:00PM", "13+", 3),
                new Movie("Imposter", "English", "History", "9:00PM", "18+", 4)
        };

        // Display Header and Available Movies
        System.out.printf("\nWelcome to XXXX Movie Ticketing System" +
                "\n======================================\n");
        System.out.println("\nAvailable Movies for today: ");
        movie.movieTableHeader();
        for (int i = 0; i < 5; i++) {
            System.out.printf("|%-3d", i + 1);
            movies[i].printMovieDetails();
        }
        printLine();

        // Input movie Number
        int movieChose = movie.inputValidation(movies);

        // Display movie Chosen
        System.out.println("\nMovie Chosen: ");
        movie.movieTableHeader();
        System.out.printf("|%-3d", movieChose);
        movies[movieChose - 1].printMovieDetails();
        printLine();
        //// --------------------////

        //// --TICKETING MODULE--////
        Customer customers = new Customer();
        int totalQuantity,
                adultQuantity,
                childQuantity,
                studentQuantity;
        // Input Total Quantity of Ticket
        do {
            totalQuantity = customers.inputValidation(); // input and validation

            // Input Quantity of Adult tickets
            Adult adult = new Adult();
            adultQuantity = adult.input(totalQuantity);
            totalQuantity -= adultQuantity;

            customers.ticketsLeft(totalQuantity); // Print total tickets (specified by user prior) left

            // Input Quantity of Children tickets
            Children children = new Children();
            childQuantity = children.input(totalQuantity);
            totalQuantity -= childQuantity;

            customers.ticketsLeft(totalQuantity); // Print total tickets (specified by user prior) left

            // Input Quantity of Student tickets
            Student student = new Student();
            studentQuantity = student.input(totalQuantity);
            totalQuantity -= studentQuantity;

            if (totalQuantity != 0) {
                System.out.println(
                        "Total ticket count does not match with total quantity of tickets needed, Please try again!");
            }
        } while (totalQuantity != 0);

        // to pass value into customer class and those sub-classes for claculations
        // purpose
        Customer adultTickets = new Adult(adultQuantity, childQuantity, studentQuantity);
        Customer childTickets = new Children(adultQuantity, childQuantity, studentQuantity);
        Customer studentTickets = new Student(adultQuantity, childQuantity, studentQuantity);
        //// --------------------////

        //// --SEAT SELECTION MODULE--////
        // Create theatre objects
        Theatre[] halls = {
                new Theatre(1),
                new Theatre(2),
                new Theatre(3),
                new Theatre(4),
                new Theatre(5),
        };

        // Display seat diagram (based on hall number)
        int hallNumber = movies[movieChose - 1].movieHallNumber;
        Theatre theatre = halls[hallNumber - 1];
        theatre.displaySeats(hallNumber);

        // Select seats for each customer
        int seatNumber, validatedSeatNumber;
        int totalCustomer = adultQuantity + childQuantity + studentQuantity;
        for (int i = 0; i < totalCustomer; i++) {
            seatNumber = validatedSeatNumber = theatre.inputValidation(i); // validate seat number input
            theatre.removeSeat(validatedSeatNumber); // remove seat number (mark as occupied)
            theatre.takenSeats.add(new Seat(seatNumber, hallNumber)); // store seat info in takenSeats[]
        }
        //// --------------------////

        //// --TICKET PRINTING MODULE--////
        // Displaying Bill for Payment
        System.out.println("\n");
        System.out.println("Ticket Payment:");
        printLine();
        System.out.println("Category\t|Ticket Quantity\t|Pricing(RM Per Unit)\t|Total Pricing(RM)\t|");
        printLine();
        System.out.print("Adult\t\t|" + adultQuantity + "\t\t\t|" + adultTickets + "\t\t\t|");
        System.out.print("\nChild\t\t|" + childQuantity + "\t\t\t|" + childTickets + "\t\t\t|");
        System.out.print("\nStudent\t\t|" + studentQuantity + "\t\t\t|" + studentTickets + "\t\t\t|\n");
        printLine();
        System.out.print("Total(RM) : \t\t\t\t\t\t\t");
        System.out.printf("%40s", totalPayable());

        // Daniel, you can access the takenSeats ArrayList and print the seat numbers
        // out accordingly👍
        // Kindly refer to here
        // https://www.freecodecamp.org/news/java-array-methods-how-to-print-an-array-in-java/
        //// --------------------////
    }

    public static void printLine() {
        System.out.println("-----------------------------------------------------------------------------------------");
    }

    public double totalPayable(double a, double b, double c) {
        return a + b + c;
    }
}