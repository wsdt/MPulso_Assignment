package trains;

import exceptions.ConstraintViolation;
import sys.SYS_Logger;

/** Güter */
public class Item {
    // Weight in kilograms
    private double weight;

    public Item(double weight) {
        this.setWeight(weight);
    }

    //GETTER/SETTER +++++++++++++++++++++++++++++++++++++
    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        if (weight < 0) {
            throw new ConstraintViolation("An item cannot have a negative weight. Have set it!");
        }
        this.weight = weight;
    }
}
