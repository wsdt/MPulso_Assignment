package trains.train_pieces;

import actors.Manufacturer;
import actors.Passenger;
import actors.TicketCollector;
import exceptions.ConstraintViolation;
import helper.SerialNumber;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sys.SYS_Logger;
import trains.Item;

import java.time.Year;
import java.util.HashSet;
import java.util.Set;

public abstract class TPa_TrainPiece {
    /** Use of set as every passenger is unique. But order shouldn't matter so a normal (hash-)Set should be enough.
     * Which and how many passengers are currently on the trainpiece? */
    private Set<Passenger> passengerSet;
    /** How many items are currently on the trainPiece? */
    private Set<Item> itemSet;
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
        this.setMaxCapacityItems(maxCapacityItems);
        this.setTypeClassification(typeClassification);
        this.setManufacturer(manufacturer);
        this.setBuildYear(buildYear);
        this.setSerialNumber(serialNumber);
        this.setEmptyWeight(emptyWeight);
        this.setLength(length);
        this.setMaxCapacityPassengers(maxCapacityPassengers);
        this.setPassengerSet(passengerSet); //should be last
    }

    public TPa_TrainPiece(@Nullable Set<Passenger> passengerSet, @NotNull Set<Item> itemSet, @NotNull String typeClassification, @NotNull Manufacturer manufacturer, @NotNull Year buildYear, @NotNull SerialNumber serialNumber, double maxCapacityItems, double emptyWeight, double length, double maxCapacityPassengers) {
        this.setMaxCapacityItems(maxCapacityItems);
        this.setTypeClassification(typeClassification);
        this.setManufacturer(manufacturer);
        this.setBuildYear(buildYear);
        this.setSerialNumber(serialNumber);
        this.setEmptyWeight(emptyWeight);
        this.setLength(length);
        this.setMaxCapacityPassengers(maxCapacityPassengers);
        this.setItemSet(itemSet); //should be after maxCapacityItems
        this.setPassengerSet(passengerSet); //should be after maxPassengerSetter
    }

    /** Validates whether item weights are over the train part limit. */
    public void isItemLoadOk() {
        // in kg
        double sum = 0;
        for (Item i : this.getItemSet()) {
            sum += i.getWeight();
        }
        if (sum > this.getMaxCapacityItems()) {
            throw new ConstraintViolation("Your train piece carries too much weight in form of items. Limit:"+this.getMaxCapacityItems()+", onBoard:"+sum);
        }
    }

    public void isPassengerLoadOk() {
        if (passengerSet != null) {
            if (this.getPassengerSet().size() > this.getMaxCapacityPassengers()) {
                throw new ConstraintViolation("Your train piece has too much passengers on board. Limit:"+this.getMaxCapacityPassengers()+", onBoard:"+this.getPassengerSet().size());
            }
        }
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
        isPassengerLoadOk();
    }

    public double getMaxCapacityItems() {
        return maxCapacityItems;
    }

    public void setMaxCapacityItems(double maxCapacityItems) {
        if (maxCapacityItems < 0) {
            throw new ConstraintViolation("MaxCapacity cannot be negative.");
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
        if (emptyWeight < 0) {
            throw new ConstraintViolation("Weight cannot be negative!");
        }
        this.emptyWeight = emptyWeight;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        if (length < 0) {
            throw new ConstraintViolation("It's logically not possible that the length is negative.");
        }
        this.length = length;
    }

    public Set<TicketCollector> getTicketCollectorSet() {
        return ticketCollectorSet;
    }

    public void setTicketCollectorSet(Set<TicketCollector> ticketCollectorSet) {
        this.ticketCollectorSet = ticketCollectorSet;
    }

    public double getMaxCapacityPassengers() {
        return maxCapacityPassengers;
    }

    public void setMaxCapacityPassengers(double maxCapacityPassengers) {
        if (maxCapacityPassengers < 0) {
            throw new ConstraintViolation("It's logically not possible that a trainpart has a negative capacity for passengers.");
        }
        this.maxCapacityPassengers = maxCapacityPassengers;
    }

    public Set<Item> getItemSet() {
        return itemSet;
    }

    public void setItemSet(@NotNull Set<Item> itemSet) {
        this.itemSet = itemSet;
        isItemLoadOk();
    }
}
