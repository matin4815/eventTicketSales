package events;


import users.Users;

import java.util.Scanner;

public class EventCreator {
    private static final Scanner scanner = new Scanner(System.in);

    public Event eventCreation(Users user) {
        System.out.println("pls enter the specific event name(try to be as exact as possible)");
        String eventName = scanner.nextLine();
        System.out.println("pls enter how many days do you want to put tickets for sale: ");
        String ticketSalesNumberOfDays = scanner.nextLine();
        System.out.println("pls enter the number of tickets for sale: ");
        String numberOfTickets = scanner.nextLine();
        System.out.println("pls enter the price for one ticket: ");
        String ticketPrice = scanner.nextLine();
        return new Event(eventName, ticketSalesNumberOfDays, Integer.parseInt(numberOfTickets), Integer.parseInt(ticketPrice), user.getUsername());

    }
}
