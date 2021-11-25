package sales;

import events.Event;
import events.EventFile;
import users.UserFileManagement;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

public class TicketSales {
    private static EventFile eventFile = new EventFile();
    private static UserFileManagement userFileManagement = new UserFileManagement();
    private static Scanner scanner = new Scanner(System.in);

    public void displayTickets() throws IOException, ParseException {
        eventFile.getDataFromTXT();
        eventFile.displayEvents();
        System.out.println("*****************************");
        System.out.println("*****************************");
        System.out.println("*****************************");
    }

    public void buyTickets() throws IOException, ParseException {
        userFileManagement.getDataFromTXT();
        eventFile.getDataFromTXT();
        eventFile.cleanTxtFile();
        System.out.println("pls enter the name of the event that you would like to attend to: ");
        String event = scanner.nextLine();
        System.out.println("pls enter the number of tickets that you want to buy: ");
        int ticketCount = scanner.nextInt();
        Event event1 = eventFile.getEvent(event);
        event1.calculateRemainingTickets(ticketCount);
        int totalPrice = event1.calculatePrice(ticketCount);
        System.out.println("your total fee is " + totalPrice + " and thanks for shopping");
        eventFile.listDataInToFile();
    }
}
