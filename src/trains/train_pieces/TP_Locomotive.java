package trains.train_pieces;

import actors.Manufacturer;
import actors.Passenger;
import helper.SerialNumber;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import propulsion_methods.PMa_PropulsionMethod;
import trains.Item;


import java.time.Year;
import java.util.Set;

public class TP_Locomotive extends TPa_TrainPiece {
    /**
     * Zugkraft, how much weight can the locomotive pull (additional
     * to emptyWeight)
     */
    private double tractionForce;
    /**
     * E.g. Diesel, Steam, Electric etc.
     */
    private PMa_PropulsionMethod propulsionMethod;

    public TP_Locomotive(@NotNull String typeClassification, @NotNull Manufacturer manufacturer, @NotNull Year buildYear, @NotNull SerialNumber serialNumber,
                         double maxCapacityItems, double emptyWeight, double length, double maxCapacityPassengers, double tractionForce, @NotNull PMa_PropulsionMethod propulsionMethod) {
        super(typeClassification, manufacturer, buildYear, serialNumber, maxCapacityItems, emptyWeight, length, maxCapacityPassengers);
        this.setTractionForce(tractionForce);
        this.setPropulsionMethod(propulsionMethod);
    }

    public TP_Locomotive(@Nullable Set<Passenger> passengerSet, @NotNull String typeClassification, @NotNull Manufacturer manufacturer, @NotNull Year buildYear, @NotNull SerialNumber serialNumber,
                         double maxCapacityItems, double emptyWeight, double length, double maxCapacityPassengers, double tractionForce, @NotNull PMa_PropulsionMethod propulsionMethod) {
        super(passengerSet, typeClassification, manufacturer, buildYear, serialNumber, maxCapacityItems, emptyWeight, length, maxCapacityPassengers);
        this.setTractionForce(tractionForce);
        this.setPropulsionMethod(propulsionMethod);
    }

    public TP_Locomotive(@Nullable Set<Passenger> passengerSet, @NotNull Set<Item> itemSet, @NotNull String typeClassification, @NotNull Manufacturer manufacturer, @NotNull Year buildYear, @NotNull SerialNumber serialNumber,
                         double maxCapacityItems, double emptyWeight, double length, double maxCapacityPassengers, double tractionForce, @NotNull PMa_PropulsionMethod propulsionMethod) {
        super(passengerSet, itemSet, typeClassification, manufacturer, buildYear, serialNumber, maxCapacityItems, emptyWeight, length, maxCapacityPassengers);
        this.setTractionForce(tractionForce);
        this.setPropulsionMethod(propulsionMethod);
    }


    //GETTER/SETTER +++++++++++++++++++++++++++++++++++++++++
    public double getTractionForce() {
        return tractionForce;
    }

    public void setTractionForce(double tractionForce) {
        this.tractionForce = tractionForce;
    }

    public PMa_PropulsionMethod getPropulsionMethod() {
        return propulsionMethod;
    }

    public void setPropulsionMethod(@NotNull PMa_PropulsionMethod propulsionMethod) {
        this.propulsionMethod = propulsionMethod;
    }
}
