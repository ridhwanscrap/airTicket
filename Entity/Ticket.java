package Entity;

public class Ticket extends TicketThing {

    // Changed to protected so BusinessTicket can access them directly
    protected String passengerName;
    protected String source;
    protected String destination;
    protected String travelDate;
    protected String departureTime;
    protected String seatType;
    protected String flightNumber;
    protected String meal;
    protected String services;
    protected String remarks;

    // Updated Constructor
    public Ticket(String passengerName, String source, String destination, String travelDate, String departureTime, String seatType, String flightNumber, String meal, String services, String remarks) {
        this.passengerName = passengerName;
        this.source = source;
        this.destination = destination;
        this.travelDate = travelDate;
        this.departureTime = departureTime;
        this.seatType = seatType;
        this.flightNumber = flightNumber;
        this.meal = meal;
        this.services = services;
        this.remarks = remarks;
    }

    public String getPassengerName() { 
        return passengerName; 
    }

    // Updated toTxt to save new fields (10 items total)
    public String toTxt() {
        return passengerName + "," + source + "," + destination + "," + travelDate + "," + departureTime + "," + seatType + "," + flightNumber + "," + meal + "," + services + "," + remarks;
    }

    @Override
    public Object[] showDetails() {
        return new Object[]{ passengerName, flightNumber, source, destination, travelDate, seatType, meal, services, remarks };
    }
}