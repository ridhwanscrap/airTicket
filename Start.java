import Frame.*;
import Entity.*;

public class Start {
    public static void main(String[] args) {

        Flight[] flights = new Flight[5];
        flights[0] = new Flight("BG101", "SkyExpress 101");
        flights[1] = new Flight("BG102", "SkyExpress 102");
        flights[2] = new Flight("BG103", "SkyExpress 103");
        flights[3] = new Flight("BG104", "SkyExpress 104");
        flights[4] = new Flight("BG104", "Us bangla 11");
       
        new AirlineTicketSystem(flights);
    }
}