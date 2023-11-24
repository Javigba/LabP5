package es.ull.flights;
import es.ull.passengers.Passenger;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;


class FlightTest {

    private Flight flight;

    @BeforeEach
    void setUp() {
        flight = new Flight("AB123", 100);
    }

    @Test
    void testGetFlightNumber() {
        assertEquals("AB123", flight.getFlightNumber());
    }

    @Test
    void testGetNumberOfPassengers() {
        assertEquals(0, flight.getNumberOfPassengers());
    }

    @Test
    void testAddPassenger() {
        Passenger passenger = new Passenger("ID001", "John Doe", "US");
        assertTrue(flight.addPassenger(passenger));
        assertEquals(1, flight.getNumberOfPassengers());
        assertEquals(flight, passenger.getFlight());
    }

    @Test
    void testAddPassengerExceedingSeats() {
        Flight smallFlight = new Flight("XY456", 1);
        Passenger passenger1 = new Passenger("ID001", "John Doe", "US");
        Passenger passenger2 = new Passenger("ID002", "Jane Doe", "CA");

        assertTrue(smallFlight.addPassenger(passenger1));
        assertThrows(RuntimeException.class, () -> smallFlight.addPassenger(passenger2));
    }

    @Test
    void testRemovePassenger() {
        Passenger passenger = new Passenger("ID001", "John Doe", "US");
        flight.addPassenger(passenger);

        assertTrue(flight.removePassenger(passenger));
        assertEquals(0, flight.getNumberOfPassengers());
        assertNull(passenger.getFlight());
    }

    @Test
    void testInvalidFlightNumber() {
        assertThrows(RuntimeException.class, () -> new Flight("invalid", 100));
    }
}
