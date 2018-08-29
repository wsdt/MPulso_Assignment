package actors;

import exceptions.ConstraintViolation;
import org.jetbrains.annotations.NotNull;
import trains.Train;
import trains.train_pieces.TPa_TrainPiece;

import java.util.HashSet;
import java.util.Set;

/** Assuming that every RailroadCompany NEEDS at least one train to be a railroadcompany. */
public class RailroadCompany {
    private Set<Train> ownedTrains;

    public RailroadCompany(@NotNull Set<Train> ownedTrains) {
        this.setOwnedTrains(ownedTrains);
    }

    //SETTER/GETTER +++++++++++++++++++++++++++++++++++++++++
    public Set<Train> getOwnedTrains() {
        return ownedTrains;
    }

    public void setOwnedTrains(@NotNull Set<Train> ownedTrains) {
        this.ownedTrains = ownedTrains;
        Train.semanticValidation(ownedTrains); //after setting!
    }
}
