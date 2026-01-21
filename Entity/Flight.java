package Entity;

public class Flight {
    private String flightNumber;
    private String flightName;

    public Flight(String flightNumber, String flightName) {
        this.flightNumber = flightNumber;
        this.flightName = flightName;
    }

    public void setFlightNumber(String flightNumber){
        this.flightNumber = flightNumber;
    }

    public void setFlightName(String flightName){
        this.flightName = flightName;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getFlightName() {
        return flightName;
    }

    @Override
    public String toString() {
        return flightName + " " + flightNumber ;
    }
}
