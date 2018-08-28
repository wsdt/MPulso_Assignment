package trains.train_pieces.wagons;

import actors.Manufacturer;
import actors.Passenger;
import helper.SerialNumber;
import trains.train_pieces.TP_Wagon;

import java.time.Year;
import java.util.Set;

/** Schlafwagen */
public class TPW_Sleeper extends TP_Wagon {

    public TPW_Sleeper(String typeClassification, Manufacturer manufacturer, Year buildYear, SerialNumber serialNumber, double maxCapacityItems, double emptyWeight, double length, double maxCapacityPassengers) {
        super(typeClassification, manufacturer, buildYear, serialNumber, maxCapacityItems, emptyWeight, length, maxCapacityPassengers);
    }

    public TPW_Sleeper(Set<Passenger> passengerSet, String typeClassification, Manufacturer manufacturer, Year buildYear, SerialNumber serialNumber, double maxCapacityItems, double emptyWeight, double length, double maxCapacityPassengers) {
        super(passengerSet, typeClassification, manufacturer, buildYear, serialNumber, maxCapacityItems, emptyWeight, length, maxCapacityPassengers);
    }
}
