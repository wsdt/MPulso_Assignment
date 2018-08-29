import actors.Manufacturer;
import actors.Passenger;
import actors.RailroadCompany;
import actors.TicketCollector;
import exceptions.ConstraintViolation;
import helper.SerialNumber;
import org.junit.jupiter.api.BeforeAll;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CalcTest {
    private static Train train;

    @BeforeAll
    static void initializeTrain() {
        Set<Passenger> passengers = new HashSet<>();
        passengers.add(new Passenger());
        passengers.add(new Passenger());

        LinkedHashSet<TP_Locomotive> locomotives = new LinkedHashSet<>();
        locomotives.add(new TP_Locomotive(passengers,"h",new Manufacturer(),Year.now(),new SerialNumber("123-123456-1274"),5d,5d,5d,5d,5d, new PM_Diesel()));

        LinkedHashSet<TP_Wagon> wagons = new LinkedHashSet<>();
        wagons.add(new TPW_Carrier("h",new Manufacturer(),Year.now(),new SerialNumber("123-123456-1284"),5d,5d,5d,5d));
        wagons.add(new TPW_Coach("h",new Manufacturer(),Year.now(),new SerialNumber("123-123456-1294"),5d,5d,5d,5d));
        wagons.add(new TPW_Diner("h",new Manufacturer(),Year.now(),new SerialNumber("123-123456-1034"),5d,5d,5d,5d));
        wagons.add(new TPW_Sleeper("h",new Manufacturer(),Year.now(),new SerialNumber("123-123456-0234"),5d,5d,5d,5d));

        CalcTest.train = new Train(locomotives, wagons);
    }

    @Test
    void cumulData_maxCapitems() {
        assertEquals(25d,CalcTest.train.getCumulatedData(Train.QUERY_MODE.MAX_CAPACITY_ITEMS));
    }

    @Test
    void cumulData_emptyWeight() {
        assertEquals(25d, CalcTest.train.getCumulatedData(Train.QUERY_MODE.EMPTY_WEIGHT));
    }

    @Test
    void cumulData_trainLength() {
        assertEquals(25d, CalcTest.train.getCumulatedData(Train.QUERY_MODE.TRAIN_LENGTH));
    }

    @Test
    void cumulData_ticketCollectors() {
        //For each 50 passengers 1 ticket collector
        assertEquals(1,CalcTest.train.getCumulatedData(Train.QUERY_MODE.TICKET_COLLECTORS));
    }

    @Test
    void cumulData_maxCapPassengers() {
        assertEquals(25d, CalcTest.train.getCumulatedData(Train.QUERY_MODE.MAX_CAPACITY_PASSENGERS));
    }

    @Test
    void cumulData_maxCapOverall() {
        assertEquals(3750d, CalcTest.train.getCumulatedData(Train.QUERY_MODE.MAX_CAPACITY_OVERALL));
    }

    @Test
    void cumulData_maxTrainWeight() {
        assertEquals(3775d, CalcTest.train.getCumulatedData(Train.QUERY_MODE.MAX_TRAIN_WEIGHT));
    }
}