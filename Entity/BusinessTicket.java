package Entity;

public class BusinessTicket extends Ticket {

    public BusinessTicket(String n, String s, String d, String td, String dt, String st, String fn, String m, String sv, String r) {
        super(n, s, d, td, dt, st, fn, m, sv, r);
    }

    @Override
    public Object[] showDetails() {
        return new Object[]{ 
            "VIP " + passengerName, 
            flightNumber,
            source, 
            destination, 
            travelDate, 
            "BUSINESS (PRIORITY)",
            meal,
            services,
            remarks
        };
    }
}