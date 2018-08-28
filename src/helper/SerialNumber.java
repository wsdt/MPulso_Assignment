package helper;

import exceptions.SerialNumberMalformed;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public class SerialNumber {
    private static final Pattern CONSTRAINTS = Pattern.compile("^([0-9]{3})-([0-9]{6})-([0-9]{4})$");
    /**
     * HashSet to ensure that all serialNumbers are unique.
     * NOTICE: Do not put SerialNumber.class here, bc. set would not recognize
     * different strings as serialNumber would be only a member (different memory-lcoation)
     */
    private static Set<String> uniqueSerialNumbers = new HashSet<>();
    private String serialNumber;

    public SerialNumber(@NotNull String serialNumber) {
        if (CONSTRAINTS.matcher(serialNumber).matches()) {
            addNewSerialNumber(serialNumber);
            this.setSerialNumber(serialNumber);
        } else {
            throw new SerialNumberMalformed("Your serial number does not meet the required constraints -> " + serialNumber);
        }
    }

    /**
     * Evaluates whether serialNumber is unique and returns false & throws exception if not.
     */
    private static void addNewSerialNumber(@NotNull String serialNumber) {
        int amount_serial_numbers = uniqueSerialNumbers.size();
        uniqueSerialNumbers.add(serialNumber);
        if (amount_serial_numbers >= uniqueSerialNumbers.size()) {
            throw new SerialNumberMalformed("Your serial number is not unique!");
        }
    }

    //GETTER/SETTER ++++++++++++++++++++++++++++++++++
    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public static Set<String> getUniqueSerialNumbers() {
        return uniqueSerialNumbers;
    }

    public static void setUniqueSerialNumbers(Set<String> uniqueSerialNumbers) {
        SerialNumber.uniqueSerialNumbers = uniqueSerialNumbers;
    }
}
