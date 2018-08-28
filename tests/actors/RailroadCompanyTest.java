package actors;

import exceptions.ConstraintViolation;
import helper.SerialNumber;
import org.junit.jupiter.api.Test;
import propulsion_methods.PM_Diesel;
import trains.Train;
import trains.train_pieces.TP_Locomotive;

import java.time.Year;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.LinkedHashSet;

import static org.junit.jupiter.api.Assertions.*;

class RailroadCompanyTest {


    @Test
    void semanticValidation_noTrains() {
        new RailroadCompany(new HashSet<>());
    }

    @Test
    void semanticValidation_noLocomotives() {
        assertThrows(ConstraintViolation.class, () -> {
            new Train(new LinkedHashSet<>());
        });
    }

    @Test
    void semanticValidation_someTrains() {
        LinkedHashSet<TP_Locomotive> locomotives = new LinkedHashSet<>();
        locomotives.add(new TP_Locomotive("h",new Manufacturer(), Year.now(),new SerialNumber("123-123542-1234"),5d,5d,5d,5d,5d,new PM_Diesel()));
        Train t = new Train(locomotives);
        HashSet<Train> ts = new HashSet<>();
        ts.add(t);
        assertThrows(ConstraintViolation.class, () -> {
            new RailroadCompany(ts);
        });
    }
}