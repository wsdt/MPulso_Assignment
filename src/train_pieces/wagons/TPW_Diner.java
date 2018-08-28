package train_pieces.wagons;

import actors.Manufacturer;
import actors.Passenger;
import helper.SerialNumber;
import train_pieces.TP_Wagon;

import java.time.Year;
import java.util.Set;

/** Speisewagen */
public class TPW_Diner extends TP_Wagon {

    public TPW_Diner(String typeClassification, Manufacturer manufacturer, Year buildYear, SerialNumber serialNumber, double maxCapacityItems, double emptyWeight, double length, double maxCapacityPassengers) {
        super(typeClassification, manufacturer, buildYear, serialNumber, maxCapacityItems, emptyWeight, length, maxCapacityPassengers);
    }

    public TPW_Diner(Set<Passenger> passengerSet, String typeClassification, Manufacturer manufacturer, Year buildYear, SerialNumber serialNumber, double maxCapacityItems, double emptyWeight, double length, double maxCapacityPassengers) {
        super(passengerSet, typeClassification, manufacturer, buildYear, serialNumber, maxCapacityItems, emptyWeight, length, maxCapacityPassengers);
    }
}
