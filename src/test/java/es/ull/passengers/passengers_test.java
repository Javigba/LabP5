package es.ull.passengers;
import es.ull.passengers.Passenger;
import es.ull.flights.Flight;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;



class PassengerTest {

    private Passenger passenger;

    @BeforeEach
    void setUp() {
        passenger = new Passenger("ID001", "John Doe", "US");
    }

    @Test
    void testGetIdentifier() {
        assertEquals("ID001", passenger.getIdentifier());
    }

    @Test
    void testGetName() {
        assertEquals("John Doe", passenger.getName());
    }

    @Test
    void testGetCountryCode() {
        assertEquals("US", passenger.getCountryCode());
    }

    @Test
    void testGetFlight() {
        assertNull(passenger.getFlight());
    }

    @Test
    void testJoinFlight() {
        Flight flight = new Flight("AB123", 100);
        passenger.joinFlight(flight);

        assertEquals(flight, passenger.getFlight());
        assertEquals(1, flight.getNumberOfPassengers());
    }

    @Test
    void testLeaveFlight() {
        Flight flight = new Flight("AB123", 100);
        passenger.joinFlight(flight);
        passenger.joinFlight(null);

        assertNull(passenger.getFlight());
        assertEquals(0, flight.getNumberOfPassengers());
    }

    @Test
    void testInvalidCountryCode() {
        assertThrows(RuntimeException.class, () -> new Passenger("ID001", "John Doe", "XX"));
    }
}

