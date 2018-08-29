package trains.train_pieces;

import actors.Manufacturer;
import actors.Passenger;
import helper.SerialNumber;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import trains.Item;

import java.time.Year;
import java.util.Set;

public abstract class TP_Wagon extends TPa_TrainPiece {
    public TP_Wagon(@NotNull String typeClassification, @NotNull Manufacturer manufacturer, @NotNull Year buildYear, @NotNull SerialNumber serialNumber, double maxCapacityItems, double emptyWeight, double length, double maxCapacityPassengers) {
        super(typeClassification, manufacturer, buildYear, serialNumber, maxCapacityItems, emptyWeight, length, maxCapacityPassengers);
    }

    public TP_Wagon(@Nullable Set<Passenger> passengerSet, @NotNull String typeClassification, @NotNull Manufacturer manufacturer, @NotNull Year buildYear, @NotNull SerialNumber serialNumber, double maxCapacityItems, double emptyWeight, double length, double maxCapacityPassengers) {
        super(passengerSet, typeClassification, manufacturer, buildYear, serialNumber, maxCapacityItems, emptyWeight, length, maxCapacityPassengers);
    }

    public TP_Wagon(@Nullable Set<Passenger> passengerSet, @NotNull Set<Item> itemSet, @NotNull String typeClassification, @NotNull Manufacturer manufacturer, @NotNull Year buildYear, @NotNull SerialNumber serialNumber, double maxCapacityItems, double emptyWeight, double length, double maxCapacityPassengers) {
        super(passengerSet, itemSet, typeClassification, manufacturer, buildYear, serialNumber, maxCapacityItems, emptyWeight, length, maxCapacityPassengers);
    }
}
