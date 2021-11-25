import events.Event;
import events.EventFile;
import exceptions.InvalidPassword;
import exceptions.InvalidUsername;
import exceptions.PasswordTooShort;
import exceptions.UsernameAlreadyExists;
import sales.TicketSales;
import users.UserFileManagement;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static UserFileManagement userFileManagement = new UserFileManagement();
    private static EventFile eventFile = new EventFile();
    private static TicketSales ticketSales = new TicketSales();

    static Logger logger = Logger.getLogger("MyLog");
    static FileHandler fh;

    public static void main(String[] args) throws UsernameAlreadyExists, PasswordTooShort, InvalidUsername, InvalidPassword, IOException, ParseException {
        try {

            fh = new FileHandler("/home/matin/Desktop/MyLogFile.log");
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        printAtStart();
        boolean isManager = false;
        boolean isCostumer = false;
        String startAction = scanner.nextLine();
        userFileManagement.getDataFromTXT();
        userFileManagement.cleanTxtFile();
        eventFile.getDataFromTXT();
        eventFile.cleanTxtFile();
        switch (startAction){
            case "0":
                userFileManagement.listDataInToFile();
                eventFile.listDataInToFile();
                System.out.println("Come back again, goodbye");
                break;
            case "1":
                logger.info("login attempt by manager");
                userFileManagement.login();
                isManager = true;
                logger.info("login complete");

                break;
            case "2":
                userFileManagement.login();
                isCostumer = true;
                break;
            case "3":
                logger.info("creating a new costumer");
                userFileManagement.signUp();
                isCostumer = true;
                logger.info("new costumer created");
                break;
            case "4":
                printAtStart();
                break;
            default:
                System.out.println("Invalid input");
        }
        while(isManager) {
            printManagerAction();
            String managerAction = scanner.nextLine();
            switch (managerAction) {
                case "0":
                    userFileManagement.listDataInToFile();
                    eventFile.listDataInToFile();
                    System.out.println("Come back again, goodbye");
                    isManager = false;
                    break;
                case "1":
                    userFileManagement.signUp();
                    break;
                case "2":
                    logger.info("an event is being created");
                    eventFile.eventCreator();
                    logger.info("event creation complete");
                    break;
                case "3":
                    System.out.println("pls enter your manager username");
                    String username = scanner.nextLine();
                    eventFile.printAManagersEvents(username);
                    break;
                default:
                    System.out.println("Invalid input");
                    break;
            }
        }

        while(isCostumer) {
            printCostumerAction();
            String customerAction = scanner.nextLine();
            switch (customerAction) {
                case "0":
                    userFileManagement.listDataInToFile();
                    eventFile.listDataInToFile();
                    System.out.println("Come back again, goodbye");
                    isCostumer = false;
                    break;
                case "1":
                    ticketSales.displayTickets();
                    break;
                case "2":
                    logger.info("Ticket sales at proccess");
                    ticketSales.buyTickets();
                    logger.info("ticket sales over");
                    break;
                default:
                    System.out.println("Invalid input");
                    break;
            }
        }
        userFileManagement.listDataInToFile();
        eventFile.listDataInToFile();

    }

    private static void printAtStart(){
        System.out.println("Available actions ");
        System.out.println("0 -- to get out of the shop\n" +
                "1 -- to get in as manager\n"+
                "2 -- to get in as a costumer\n"+
                "3 -- to sign up as a costumer\n"+
                "4 -- to print a list of available actions.");
        System.out.println("choose your action: ");

    }


    private static void printManagerAction(){
        System.out.println("Available actions ");
        System.out.println("0 -- to get out of the shop\n" +
                "1 -- to create a new manager\n"+
                "2 -- to create a new event\n"+
                "3 -- to see your current events\n");
        System.out.println("choose your action: ");

    }

    private static void printCostumerAction(){
        System.out.println("Available actions ");
        System.out.println("0 -- to get out of the shop\n" +
                "1 -- to see the events\n"+
                "2 -- to buy the ticket");
        System.out.println("choose your action: ");

    }
}
