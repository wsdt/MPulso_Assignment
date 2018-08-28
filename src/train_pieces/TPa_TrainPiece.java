package train_pieces;

import actors.Manufacturer;
import actors.Passenger;
import actors.TicketCollector;
import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import helper.SerialNumber;
import sys.SYS_Logger;

import java.time.Year;
import java.util.HashSet;
import java.util.Set;

public abstract class TPa_TrainPiece {
    //TODO: Set reliable or use another solution
    /** If true this trainPart is already attached to a train.*/
    private boolean alreadyUsed = false;
    /** Use of set as every passenger is unique. But order shouldn't matter so a normal (hash-)Set should be enough. */
    private Set<Passenger> passengerSet;
    /** Leergewicht */
    private double emptyWeight;
    private double length;
    /** Maximales Zuladungsgewicht für Güter (= Items)
     * Measured in KG (not amount) */
    private double maxCapacityItems;
    /** Max. capacity of passengers for this train part.
     * Measured in amount (not kilograms) */
    private double maxCapacityPassengers;
    /** Typenbezeichnung as simple String to be completely flexible */
    private String typeClassification;
    private Manufacturer manufacturer;
    private Year buildYear;
    private SerialNumber serialNumber;
    /** Placed here and not in train, bc. here we can evaluate directly when creating the train-part whether we will need a ticketCollector.
     * Of course, passengers can exit and enter the train all the time, but in our scenario the train doesn't drive.
     *
     * TicketCollectors will be automatically assigned when passengerSet get's set. As we don't need a ticketCollector when we don't have a
     * passenger this should be ok. Additionally, I am assuming here that ticketCollectors don't move to other trainParts and only observe
     * their own trainPart.
     *
     * Moreover, I am assuming that the RailroadCompany will assign Ticketcollectors dynamically and not according to their maximum Capacity
     * as they want to maximize revenue. Therefore, I am assigning ticketCollectors according to CURRENT passengers (which can be only evaluated
     * via empirical data etc.).
     *
     * If you want me to use the maximum capacity, please let me know.
     *
     * Make it as simple as it is, but not simpler. */
    private Set<TicketCollector> ticketCollectorSet;

    public TPa_TrainPiece(@NotNull String typeClassification, @NotNull Manufacturer manufacturer, @NotNull Year buildYear, @NotNull SerialNumber serialNumber, double maxCapacityItems, double emptyWeight, double length, double maxCapacityPassengers) {
        this.setMaxCapacityItems(maxCapacityItems);
        this.setTypeClassification(typeClassification);
        this.setManufacturer(manufacturer);
        this.setBuildYear(buildYear);
        this.setSerialNumber(serialNumber);
        this.setEmptyWeight(emptyWeight);
        this.setLength(length);
        this.setMaxCapacityPassengers(maxCapacityPassengers);
    }

    public TPa_TrainPiece(@Nullable Set<Passenger> passengerSet, @NotNull String typeClassification, @NotNull Manufacturer manufacturer, @NotNull Year buildYear, @NotNull SerialNumber serialNumber, double maxCapacityItems, double emptyWeight, double length, double maxCapacityPassengers) {
        this.setPassengerSet(passengerSet);
        this.setMaxCapacityItems(maxCapacityItems);
        this.setTypeClassification(typeClassification);
        this.setManufacturer(manufacturer);
        this.setBuildYear(buildYear);
        this.setSerialNumber(serialNumber);
        this.setEmptyWeight(emptyWeight);
        this.setLength(length);
        this.setMaxCapacityPassengers(maxCapacityPassengers);
    }

    //GETTER/SETTER +++++++++++++++++++++++++++++++++++++++
    public Set<Passenger> getPassengerSet() {
        return passengerSet;
    }

    /** Set passengers. If more than one is in the trainPart a ticketCollector
     * get's assigned automatically. Every 50 passengers another one will be added. */
    public void setPassengerSet(@Nullable Set<Passenger> passengerSet) {
        if (passengerSet != null && passengerSet.size() > 0) {
            double unobservedPassengers = passengerSet.size();
            Set<TicketCollector> ticketCollectorSet = new HashSet<>();
            while (unobservedPassengers % 50 > 0) {
                ticketCollectorSet.add(new TicketCollector());
                unobservedPassengers -= 50;
            }
            this.setTicketCollectorSet(ticketCollectorSet);
        }
        this.passengerSet = passengerSet;
    }

    public double getMaxCapacityItems() {
        return maxCapacityItems;
    }

    public void setMaxCapacityItems(double maxCapacityItems) {
        if (maxCapacityItems < 0) {
            SYS_Logger.getLogger().warning("MaxCapacity cannot be negative. Have set it anyway!");
        }
        this.maxCapacityItems = maxCapacityItems;
    }

    public String getTypeClassification() {
        return typeClassification;
    }

    public void setTypeClassification(@NotNull String typeClassification) {
        if (typeClassification.isEmpty()) {
            SYS_Logger.getLogger().warning("You should provide a valid type classification for your train part.");
        }
        this.typeClassification = typeClassification;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Year getBuildYear() {
        return buildYear;
    }

    public void setBuildYear(@NotNull Year buildYear) {
        this.buildYear = buildYear;
    }

    public SerialNumber getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(SerialNumber serialNumber) {
        this.serialNumber = serialNumber;
    }

    public double getEmptyWeight() {
        return emptyWeight;
    }

    public void setEmptyWeight(double emptyWeight) {
        this.emptyWeight = emptyWeight;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public Set<TicketCollector> getTicketCollectorSet() {
        return ticketCollectorSet;
    }

    public void setTicketCollectorSet(Set<TicketCollector> ticketCollectorSet) {
        this.ticketCollectorSet = ticketCollectorSet;
    }

    public boolean isAlreadyUsed() {
        return alreadyUsed;
    }

    public void setAlreadyUsed(boolean alreadyUsed) {
        this.alreadyUsed = alreadyUsed;
    }

    public double getMaxCapacityPassengers() {
        return maxCapacityPassengers;
    }

    public void setMaxCapacityPassengers(double maxCapacityPassengers) {
        if (maxCapacityPassengers < 0) {
            SYS_Logger.getLogger().warning("It's logically not possible that a trainpart has a negative capacity for passengers. Have set it!");
        }
        this.maxCapacityPassengers = maxCapacityPassengers;
    }
}
