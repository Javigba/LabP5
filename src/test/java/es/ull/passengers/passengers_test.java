/**
 * @file PassengerTest.java
 * @brief This file contains the unit tests for the Passenger class.
 */

package es.ull.passengers;
import es.ull.passengers.Passenger;
import es.ull.flights.Flight;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 * @brief Test class for the Passenger class.
 */
class PassengerTest {

    private Passenger passenger; /**< Passenger object for testing. */

    /**
     * @brief Set up the test environment before each test case.
     */
    @BeforeEach
    void setUp() {
        passenger = new Passenger("ID001", "John Doe", "US");
    }

    /**
     * @brief Test for getting the passenger identifier.
     */
    @Test
    void testGetIdentifier() {
        assertEquals("ID001", passenger.getIdentifier());
    }

    /**
     * @brief Test for getting the passenger name.
     */
    @Test
    void testGetName() {
        assertEquals("John Doe", passenger.getName());
    }

    /**
     * @brief Test for getting the passenger country code.
     */
    @Test
    void testGetCountryCode() {
        assertEquals("US", passenger.getCountryCode());
    }

    /**
     * @brief Test for getting the passenger's associated flight.
     */
    @Test
    void testGetFlight() {
        assertNull(passenger.getFlight());
    }

    /**
     * @brief Test for joining a flight.
     */
    @Test
    void testJoinFlight() {
        Flight flight = new Flight("AB123", 100);
        passenger.joinFlight(flight);

        assertEquals(flight, passenger.getFlight());
        assertEquals(1, flight.getNumberOfPassengers());
    }

    /**
     * @brief Test for leaving a flight.
     */
    @Test
    void testLeaveFlight() {
        Flight flight = new Flight("AB123", 100);
        passenger.joinFlight(flight);
        passenger.joinFlight(null);
        passenger.leaveFlight();

        assertNull(passenger.getFlight());
        assertEquals(0, flight.getNumberOfPassengers());
    }

    /**
     * @brief Test for creating a passenger with an invalid country code.
     */
    @Test
    void testInvalidCountryCode() {
        assertThrows(RuntimeException.class, () -> new Passenger("ID001", "John Doe", "XX"));
    }
}

