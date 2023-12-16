/**
 * @file FlightTest.java
 * @brief Este archivo contiene las pruebas unitarias para la clase Flight.
 */

package es.ull.flights;

import es.ull.passengers.Passenger;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 * @brief Clase de prueba para la clase Flight.
 */
class FlightTest {

    private Flight flight; /**< Objeto Flight para las pruebas. */

    /**
     * @brief Configura el entorno de prueba antes de cada caso de prueba.
     */
    @BeforeEach
    void setUp() {
        flight = new Flight("AB123", 100);
    }

    /**
     * @brief Prueba para obtener el número de vuelo.
     */
    @Test
    void testGetFlightNumber() {
        assertEquals("AB123", flight.getFlightNumber());
    }

    /**
     * @brief Prueba para obtener el número de pasajeros.
     */
    @Test
    void testGetNumberOfPassengers() {
        assertEquals(0, flight.getNumberOfPassengers());
    }

    /**
     * @brief Prueba para agregar un pasajero al vuelo.
     */
    @Test
    void testAddPassenger() {
        Passenger passenger = new Passenger("ID001", "John Doe", "US");
        assertTrue(flight.addPassenger(passenger));
        assertEquals(1, flight.getNumberOfPassengers());
        assertEquals(flight, passenger.getFlight());
    }

    /**
     * @brief Prueba para intentar agregar más pasajeros que los asientos disponibles.
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
     * @brief Prueba para eliminar un pasajero del vuelo.
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
     * @brief Prueba para crear un vuelo con un número de vuelo inválido.
     */
    @Test
    void testInvalidFlightNumber() {
        assertThrows(RuntimeException.class, () -> new Flight("invalid", 100));
    }
}
