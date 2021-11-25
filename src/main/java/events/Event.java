package events;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Event {

    private final String eventName;
    private Date ticketSalesStartDate = new Date();
    private String ticketSalesFinalDay;
    private final int numberOfTickets;
    private int remainingTicket;
    private int ticketPrice;
    private String eventManager;

    public Event(String eventName, String ticketSalesNumberOfDays, int numberOfTickets, int ticketPrice, String eventManager) {
        this.eventName = eventName;
        this.ticketSalesFinalDay = ticketSalesNumberOfDays;
        this.numberOfTickets = numberOfTickets;
        this.remainingTicket = numberOfTickets;
        this.ticketPrice = ticketPrice;
        this.eventManager = eventManager;
    }

    public String getEventName() {
        return eventName;
    }

    public String getEventManager() {
        return eventManager;
    }

    public void setEventManager(String eventManager) {
        this.eventManager = eventManager;
    }

    public Date getTicketSalesStartDate() {
        return ticketSalesStartDate;
    }

    public String getTicketSalesFinalDay() {
        return ticketSalesFinalDay;
    }

    public int getNumberOfTickets() {
        return numberOfTickets;
    }

    public int getRemainingTicket() {
        return remainingTicket;
    }

    public int getTicketPrice() {
        return ticketPrice;
    }

    public void setRemainingTicket(int remainingTicket) {
        this.remainingTicket = remainingTicket;
    }

    public void setTicketSalesStartDate(Date ticketSalesStartDate) {
        this.ticketSalesStartDate = ticketSalesStartDate;
    }

    public void setTicketSalesFinalDay(String ticketSalesFinalDay) {
        this.ticketSalesFinalDay = ticketSalesFinalDay;
    }

    public void setTicketPrice(int ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public String formatAndCalculateDate(){
        final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.ticketSalesStartDate);
        calendar.add(Calendar.DATE, Integer.parseInt(this.ticketSalesFinalDay));
        return dateFormat.format(calendar.getTime());

    }

    public int calculatePrice(int ticketNumbers){
        return this.ticketPrice*ticketNumbers;
    }

    public void calculateRemainingTickets(int soldTickets){
        this.remainingTicket -= soldTickets;
    }

}
