/**
 * @file Passenger.java
 * @brief Contiene la implementación de la clase Passenger.
 */

/*
 * ========================================================================
 *
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * ========================================================================
 */

package es.ull.passengers;

import java.util.Arrays;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import es.ull.flights.Flight;

/**
 * @brief Clase que representa a un pasajero y gestiona la información asociada.
 */
public class Passenger {

    private String identifier; /**< Identificador único del pasajero. */
    private String name; /**< Nombre del pasajero. */
    private String countryCode; /**< Código del país del pasajero. */
    private Flight flight; /**< Vuelo al que está asociado el pasajero, puede ser nulo. */

    /**
     * @brief Constructor de la clase Passenger.
     * @param identifier Identificador único del pasajero.
     * @param name Nombre del pasajero.
     * @param countryCode Código del país del pasajero.
     * @throws RuntimeException Si el código de país no es válido.
     */
    public Passenger(String identifier, String name, String countryCode) {
        if (!Arrays.asList(Locale.getISOCountries()).contains(countryCode)) {
            throw new RuntimeException("Invalid country code");
        }

        this.identifier = identifier;
        this.name = name;
        this.countryCode = countryCode;
    }

    /**
     * @brief Obtiene el identificador único del pasajero.
     * @return Identificador único del pasajero.
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * @brief Obtiene el nombre del pasajero.
     * @return Nombre del pasajero.
     */
    public String getName() {
        return name;
    }

    /**
     * @brief Obtiene el código del país del pasajero.
     * @return Código del país del pasajero.
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * @brief Obtiene el vuelo al que está asociado el pasajero.
     * @return Vuelo al que está asociado el pasajero, puede ser nulo.
     */
    public Flight getFlight() {
        return flight;
    }

    /**
     * @brief Asocia al pasajero con un vuelo y actualiza la información.
     * @param flight Vuelo al que se une el pasajero.
     */
    public void joinFlight(Flight flight) {
        Flight previousFlight = this.flight;
        if (null != previousFlight) {
            if (!previousFlight.removePassenger(this)) {
                throw new RuntimeException("Cannot remove passenger");
            }
        }
        setFlight(flight);
        if (null != flight) {
            if (!flight.addPassenger(this)) {
                throw new RuntimeException("Cannot add passenger");
            }
        }
    }

    /**
     * @brief Establece el vuelo asociado al pasajero.
     * @param flight Nuevo vuelo asociado al pasajero.
     */
    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    /**
     * @brief Representación en cadena del objeto Passenger.
     * @return Representación en cadena del objeto Passenger.
     */
    @Override
    public String toString() {
        return "Passenger " + getName() + " with identifier: " + getIdentifier() + " from " + getCountryCode();
    }
}

