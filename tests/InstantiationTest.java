import actors.Manufacturer;
import actors.Passenger;
import actors.RailroadCompany;
import actors.TicketCollector;
import exceptions.ConstraintViolation;
import exceptions.SerialNumberMalformed;
import helper.SerialNumber;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import propulsion_methods.PM_Diesel;
import propulsion_methods.PM_Electric;
import propulsion_methods.PM_Steam;
import trains.Item;
import trains.Train;
import trains.train_pieces.TP_Locomotive;
import trains.train_pieces.TP_Wagon;
import trains.train_pieces.wagons.TPW_Carrier;
import trains.train_pieces.wagons.TPW_Coach;
import trains.train_pieces.wagons.TPW_Diner;
import trains.train_pieces.wagons.TPW_Sleeper;

import java.time.Year;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertThrows;

class InstantiationTest {
    @BeforeAll
    static void resetSerialNumbers() {
        SerialNumber.setUniqueSerialNumbers(new HashSet<>());
    }

    @Test
    void trainPiece_negativeValues() {
        assertThrows(ConstraintViolation.class, ()->{new TP_Locomotive("h",new Manufacturer(),Year.now(),new SerialNumber("100-000100-0000"),-1,1,1,1,1, new PM_Electric());});
        assertThrows(ConstraintViolation.class, ()->{new TP_Locomotive("h",new Manufacturer(),Year.now(),new SerialNumber("110-000800-0000"),1,-1,1,1,1, new PM_Electric());});
        assertThrows(ConstraintViolation.class, ()->{new TP_Locomotive("h",new Manufacturer(),Year.now(),new SerialNumber("111-000900-0000"),1,1,-1,1,1, new PM_Electric());});
        assertThrows(ConstraintViolation.class, ()->{new TP_Locomotive("h",new Manufacturer(),Year.now(),new SerialNumber("001-001000-0000"),1,1,1,-1,1, new PM_Electric());});
        assertThrows(ConstraintViolation.class, ()->{new Item(-1);});
    }

    @Test
    void trainPiece_itemOverload() {
        Set<Item> items = new HashSet<>();
        items.add(new Item(15));
        items.add(new Item(20));

        //Set item overload
        assertThrows(ConstraintViolation.class,()->{new TP_Locomotive(null,items,"h",new Manufacturer(),Year.now(),new SerialNumber("100-000000-0000"),1,1,1,1,1, new PM_Electric());});
        //now increment -> should work (capacity of items is in kg!)
        new TP_Locomotive(null,items,"h",new Manufacturer(),Year.now(),new SerialNumber("100-000000-0001"),35,1,1,1,1, new PM_Electric());
    }

    @Test
    void trainPiece_passengerOverload() {
        Set<Passenger> passengers = new HashSet<>();
        passengers.add(new Passenger());
        passengers.add(new Passenger());

        //Set passenger overload
        assertThrows(ConstraintViolation.class,()->{new TP_Locomotive(passengers,"h",new Manufacturer(),Year.now(),new SerialNumber("000-000000-0000"),1,1,1,1,1, new PM_Electric());});
        //now increment -> should work
        new TP_Locomotive(passengers,"h",new Manufacturer(),Year.now(),new SerialNumber("000-000000-0001"),1,1,1,2,1, new PM_Electric());
    }

    /** Can a trainpart exist multiple times? */
    @Test
    void railroadCompany_withTrains_integrityCheck() {
        LinkedHashSet<TP_Locomotive> locomotives = new LinkedHashSet<>();
        locomotives.add(new TP_Locomotive("h",new Manufacturer(),Year.now(),new SerialNumber("123-123456-1274"),5d,5d,5d,5d,5d, new PM_Diesel()));

        LinkedHashSet<TP_Wagon> wagons = new LinkedHashSet<>();
        wagons.add(new TPW_Carrier("h",new Manufacturer(),Year.now(),new SerialNumber("123-123456-1284"),5d,5d,5d,5d));
        wagons.add(new TPW_Coach("h",new Manufacturer(),Year.now(),new SerialNumber("123-123456-1294"),5d,5d,5d,5d));
        wagons.add(new TPW_Diner("h",new Manufacturer(),Year.now(),new SerialNumber("123-123456-1034"),5d,5d,5d,5d));
        wagons.add(new TPW_Sleeper("h",new Manufacturer(),Year.now(),new SerialNumber("123-123456-0234"),5d,5d,5d,5d));

        Set<Train> trains = new HashSet<>();
        trains.add(new Train(locomotives));
        trains.add(new Train(locomotives,wagons));

        assertThrows(ConstraintViolation.class, ()->{new RailroadCompany(trains);}); //as the same locomotive get's added twice to a train which is not possible
    }

    @Test
    void railroadCompany_noTrains() {
        new RailroadCompany(new HashSet<>());
    }

    @Test
    void train_noLocomotives() {
        assertThrows(ConstraintViolation.class, () -> {
            new Train(new LinkedHashSet<>());
        });
    }

    /**Testing default constructors of other classes*/
    @Test
    void defaultConstructors() {
        new Item(15);
        new Manufacturer();
        new Passenger();
        new TicketCollector();

        //Propulsion methods
        new PM_Diesel();
        new PM_Electric();
        new PM_Steam();
    }
}