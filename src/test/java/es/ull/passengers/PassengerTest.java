/**
 * @file PassengerTest.java
 * @brief Contiene las pruebas unitarias para la clase Passenger.
 */

package es.ull.passengers;

import es.ull.passengers.Passenger;
import es.ull.flights.Flight;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 * @brief Clase que realiza pruebas unitarias para la clase Passenger.
 */
class PassengerTest {

    private Passenger passenger; /**< Objeto de la clase Passenger para realizar pruebas. */

    /**
     * @brief Configuración inicial para cada prueba.
     */
    @BeforeEach
    void setUp() {
        passenger = new Passenger("ID001", "John Doe", "US");
    }

    /**
     * @brief Prueba para obtener el identificador del pasajero.
     */
    @Test
    void testGetIdentifier() {
        assertEquals("ID001", passenger.getIdentifier());
    }

    /**
     * @brief Prueba para obtener el nombre del pasajero.
     */
    @Test
    void testGetName() {
        assertEquals("John Doe", passenger.getName());
    }

    /**
     * @brief Prueba para obtener el código del país del pasajero.
     */
    @Test
    void testGetCountryCode() {
        assertEquals("US", passenger.getCountryCode());
    }

    /**
     * @brief Prueba para obtener el vuelo al que está asociado el pasajero (debería ser nulo inicialmente).
     */
    @Test
    void testGetFlight() {
        assertNull(passenger.getFlight());
    }

    /**
     * @brief Prueba para unir un pasajero a un vuelo y verificar si la unión es exitosa.
     */
    @Test
    void testJoinFlight() {
        Flight flight = new Flight("AB123", 100);
        passenger.joinFlight(flight);

        assertEquals(flight, passenger.getFlight());
        assertEquals(1, flight.getNumberOfPassengers());
    }

    /**
     * @brief Prueba para dejar un vuelo y verificar si la operación es exitosa.
     */
    @Test
    void testLeaveFlight() {
        Flight flight = new Flight("AB123", 100);
        passenger.joinFlight(flight);
        passenger.joinFlight(null);

        assertNull(passenger.getFlight());
        assertEquals(0, flight.getNumberOfPassengers());
    }

    /**
     * @brief Prueba para manejar un código de país no válido durante la creación de un pasajero.
     */
    @Test
    void testInvalidCountryCode() {
        assertThrows(RuntimeException.class, () -> new Passenger("ID001", "John Doe", "XX"));
    }
}


