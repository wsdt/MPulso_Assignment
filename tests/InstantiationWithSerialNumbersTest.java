import actors.Manufacturer;
import actors.Passenger;
import actors.RailroadCompany;
import actors.TicketCollector;
import exceptions.ConstraintViolation;
import exceptions.SerialNumberMalformed;
import helper.SerialNumber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import propulsion_methods.PM_Diesel;
import propulsion_methods.PM_Electric;
import propulsion_methods.PM_Steam;
import trains.Train;
import trains.train_pieces.TP_Locomotive;
import trains.train_pieces.wagons.TPW_Carrier;

import java.time.Year;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class InstantiationWithSerialNumbersTest {
    @BeforeEach
    void resetSerialNumbers() {
        SerialNumber.setUniqueSerialNumbers(new HashSet<>());
    }

    @Test
    void serialNumber_wrongFormatted() {
        assertThrows(SerialNumberMalformed.class, () -> {
            new SerialNumber("123:123456-1234");
        });
        assertThrows(SerialNumberMalformed.class, () -> {
            new SerialNumber("123-123456_1234");
        });
        assertThrows(SerialNumberMalformed.class, () -> {
            new SerialNumber("123-123456-123");
        });
        assertThrows(SerialNumberMalformed.class, () -> {
            new SerialNumber("123-12356-1234");
        });
        assertThrows(SerialNumberMalformed.class, () -> {
            new SerialNumber("12-123456-1234");
        });
        assertThrows(SerialNumberMalformed.class, () -> {
            new SerialNumber("1231234561234");
        });
        assertThrows(SerialNumberMalformed.class, () -> {
            new SerialNumber("");
        });
        assertThrows(SerialNumberMalformed.class, () -> {
            new SerialNumber("123-123456-12345");
        });
    }

    @Test
    void serialNumber_notUnique() {
        new SerialNumber("123-123456-1234"); //should work
        assertThrows(SerialNumberMalformed.class, () -> {
            new SerialNumber("123-123456-1234");
        }); //not unique
    }

    @Test
    void locomotive_noPassengers() {
        new TP_Locomotive("hoh", new Manufacturer(), Year.now(), new SerialNumber("123-123455-1234"), 5d, 5d, 5d, 5d, 5d, new PM_Diesel());
    }

    @Test
    void locomotive_nullPassengers() {
        new TP_Locomotive(null, "hoh", new Manufacturer(), Year.now(), new SerialNumber("123-123455-1234"), 5d, 5d, 5d, 5d, 5d, new PM_Diesel());
    }

    @Test
    void locomotive_emptyPassengers() {
        new TP_Locomotive(new HashSet<>(), "hoh", new Manufacturer(), Year.now(), new SerialNumber("123-123455-1234"), 5d, 5d, 5d, 5d, 5d, new PM_Diesel());
    }

    @Test
    void locomotive_withPassengers() {
        Set<Passenger> passengers = new HashSet<>();
        passengers.add(new Passenger());
        new TP_Locomotive(passengers, "hoh", new Manufacturer(), Year.now(), new SerialNumber("123-123455-1234"), 5d, 5d, 5d, 5d, 5d, new PM_Diesel());
    }
}