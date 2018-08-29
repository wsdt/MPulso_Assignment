package trains.train_pieces.wagons;

import actors.Manufacturer;
import actors.Passenger;
import helper.SerialNumber;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import trains.Item;
import trains.train_pieces.TP_Wagon;

import java.time.Year;
import java.util.Set;

/** Speisewagen */
public class TPW_Diner extends TP_Wagon {

    public TPW_Diner(@NotNull String typeClassification, @NotNull Manufacturer manufacturer, @NotNull Year buildYear, @NotNull SerialNumber serialNumber, double maxCapacityItems, double emptyWeight, double length, double maxCapacityPassengers) {
        super(typeClassification, manufacturer, buildYear, serialNumber, maxCapacityItems, emptyWeight, length, maxCapacityPassengers);
    }

    public TPW_Diner(@Nullable Set<Passenger> passengerSet, @NotNull String typeClassification, @NotNull Manufacturer manufacturer, @NotNull Year buildYear, @NotNull SerialNumber serialNumber, double maxCapacityItems, double emptyWeight, double length, double maxCapacityPassengers) {
        super(passengerSet, typeClassification, manufacturer, buildYear, serialNumber, maxCapacityItems, emptyWeight, length, maxCapacityPassengers);
    }

    public TPW_Diner(@Nullable Set<Passenger> passengerSet, @NotNull Set<Item> itemSet, @NotNull String typeClassification, @NotNull Manufacturer manufacturer, @NotNull Year buildYear, @NotNull SerialNumber serialNumber, double maxCapacityItems, double emptyWeight, double length, double maxCapacityPassengers) {
        super(passengerSet, itemSet, typeClassification, manufacturer, buildYear, serialNumber, maxCapacityItems, emptyWeight, length, maxCapacityPassengers);
    }
}
