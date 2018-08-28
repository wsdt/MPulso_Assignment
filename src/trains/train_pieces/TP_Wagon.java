package trains.train_pieces;

import actors.Manufacturer;
import actors.Passenger;
import helper.SerialNumber;

import java.time.Year;
import java.util.Set;

public abstract class TP_Wagon extends TPa_TrainPiece {

    public TP_Wagon(String typeClassification, Manufacturer manufacturer, Year buildYear, SerialNumber serialNumber, double maxCapacityItems, double emptyWeight, double length, double maxCapacityPassengers) {
        super(typeClassification, manufacturer, buildYear, serialNumber, maxCapacityItems, emptyWeight, length, maxCapacityPassengers);
    }

    public TP_Wagon(Set<Passenger> passengerSet, String typeClassification, Manufacturer manufacturer, Year buildYear, SerialNumber serialNumber, double maxCapacityItems, double emptyWeight, double length, double maxCapacityPassengers) {
        super(passengerSet, typeClassification, manufacturer, buildYear, serialNumber, maxCapacityItems, emptyWeight, length, maxCapacityPassengers);
    }
}
