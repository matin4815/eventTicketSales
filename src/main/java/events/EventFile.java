package events;

import users.UserFileManagement;
import users.Users;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class EventFile {
    private static final Scanner scanner = new Scanner(System.in);
    private static Map<String, Event> eventMap = new HashMap<>();
    String fileAddress = "/home/matin/Desktop/events.txt";
    String line = "";

    public void getDataFromTXT() throws IOException, ParseException {
        BufferedReader br = new BufferedReader(new FileReader(fileAddress));

        while((line = br.readLine()) != null){
            String[] values = line.split(",");
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:ss", Locale.ENGLISH);
            String date = values[4];
            Event event = new Event(values[0], values[1] , Integer.parseInt(values[2]), Integer.parseInt(values[3]), values[6]);
//            event.setTicketSalesStartDate(date);
            event.setRemainingTicket(Integer.parseInt(values[5]));
            eventMap.put(event.getEventName(), event);
        }
    }

    public void cleanTxtFile() throws IOException {
        BufferedWriter bw1 = new BufferedWriter(new FileWriter(fileAddress));
        bw1.close();
    }

    public void listDataInToFile() throws IOException{
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileAddress, true));
        for(String eventName : eventMap.keySet()){
            line = eventMap.get(eventName).getEventName() +","+eventMap.get(eventName).getTicketSalesFinalDay()+","+eventMap.get(eventName).getNumberOfTickets()
                    +","+eventMap.get(eventName).getTicketPrice()+","+eventMap.get(eventName).getTicketSalesStartDate()+","+eventMap.get(eventName).getRemainingTicket()
            +","+eventMap.get(eventName).getEventManager();
            bw.write(line+"\n");
        }
        bw.close();
    }
//            event.setTicketSalesStartDate(date);

    public void eventCreator() throws IOException {
        System.out.println("Only admins can create and event so pls enter your username and password");
        System.out.println("username: ");
        String username = scanner.nextLine();
        System.out.println("password: ");
        String password = scanner.nextLine();
        Users user = getUser(username, password);
        user.setLoggedIn(true);
        if(user.isLoggedIn()){
            if(user.isAdmin()){
                EventCreator eventCreator = new EventCreator();
                Event event = eventCreator.eventCreation(user);
                if(checkForEvent(event.getEventName())){
                    System.out.println("this event is already defined");
                }else{
                    eventMap.put(event.getEventName(), event);
                }
            }else{
                System.out.println("you are not a manager!!!");
            }
        }else{
            System.out.println("you are not logged in, pls first log in");
        }

    }

    public Users getUser(String username, String password) throws IOException {
        UserFileManagement userFileManagement = new UserFileManagement();
        userFileManagement.getDataFromTXT();
        return userFileManagement.findUser(username, password);
    }

    private boolean checkForEvent(String eventName) {
        for(String EventName : eventMap.keySet()) {
            if (EventName.equals(eventName)) {
                return true;
            }
        }
        return false;
    }

    public void displayEvents(){
        for(String eventName : eventMap.keySet()){
            System.out.println(eventName +" will be held at " + eventMap.get(eventName).formatAndCalculateDate() + " and the number of remaining tickets are" + eventMap.get(eventName).getRemainingTicket());
        }
    }

    public Event getEvent(String eventname){
        return eventMap.get(eventname);

    }

    public void printAManagersEvents(String managerName){
        for(String eventName : eventMap.keySet()){
            if(eventMap.get(eventName).getEventManager().equals(managerName)){
                System.out.println(eventName + " will be held at " + eventMap.get(eventName).formatAndCalculateDate() + " and tickets remaining are " + eventMap.get(eventName).getRemainingTicket());
            }
        }
    }
}
