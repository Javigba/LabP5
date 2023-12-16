/**
 * @file FlightTest.java
 * @brief This file contains the unit tests for the Flight class.
 */

package es.ull.flights;

import es.ull.passengers.Passenger;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 * @brief Test class for the Flight class.
 */
class FlightTest {

    private Flight flight; /**< Flight object for testing. */

    /**
     * @brief Set up the test environment before each test case.
     */
    @BeforeEach
    void setUp() {
        flight = new Flight("AB123", 100);
    }

    /**
     * @brief Test for getting the flight number.
     */
    @Test
    void testGetFlightNumber() {
        assertEquals("AB123", flight.getFlightNumber());
    }

    /**
     * @brief Test for getting the number of passengers.
     */
    @Test
    void testGetNumberOfPassengers() {
        assertEquals(0, flight.getNumberOfPassengers());
    }

    /**
     * @brief Test for adding a passenger to the flight.
     */
    @Test
    void testAddPassenger() {
        Passenger passenger = new Passenger("ID001", "John Doe", "US");
        assertTrue(flight.addPassenger(passenger));
        assertEquals(1, flight.getNumberOfPassengers());
        assertEquals(flight, passenger.getFlight());
    }

    /**
     * @brief Test for attempting to add more passengers than the available seats.
     */
    @Test
    void testAddPassengerExceedingSeats() {
        Flight smallFlight = new Flight("XY456", 1);
        Passenger passenger1 = new Passenger("ID001", "John Doe", "US");
        Passenger passenger2 = new Passenger("ID002", "Jane Doe", "CA");

        assertTrue(smallFlight.addPassenger(passenger1));
        assertThrows(RuntimeException.class, () -> smallFlight.addPassenger(passenger2));
    }

    /**
     * @brief Test for removing a passenger from the flight.
     */
    @Test
    void testRemovePassenger() {
        Passenger passenger = new Passenger("ID001", "John Doe", "US");
        flight.addPassenger(passenger);

        assertTrue(flight.removePassenger(passenger));
        assertEquals(0, flight.getNumberOfPassengers());
        assertNull(passenger.getFlight());
    }

    /**
     * @brief Test for creating a flight with an invalid flight number.
     */
    @Test
    void testInvalidFlightNumber() {
        assertThrows(RuntimeException.class, () -> new Flight("invalid", 100));
    }
}

