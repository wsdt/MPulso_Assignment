package actors;

import exceptions.PhysicsViolation;
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

    /** Validates e.g. whether cyclic order occured or if a wagon is already added to another
     * train etc. (which is physically not possible).
     *
     * To improve: Add semanticValidation also when trains are modified via getter! */
    private void semanticValidation() {
        double originalSize = 0;
        Set<TPa_TrainPiece> allPiecesOfAllTrains = new HashSet<>();
        for (Train train : this.getOwnedTrains()) {
            Set<TPa_TrainPiece> mergedSet =  train.mergeTrainPiecesToSet();
            originalSize +=mergedSet.size();
            allPiecesOfAllTrains.addAll(mergedSet);
        }
        if (allPiecesOfAllTrains.size() != originalSize) {
            throw new PhysicsViolation("A train part cannot exist on two or more locations at the same time.");
        }
    }

    //SETTER/GETTER +++++++++++++++++++++++++++++++++++++++++
    public Set<Train> getOwnedTrains() {
        return ownedTrains;
    }

    public void setOwnedTrains(@NotNull Set<Train> ownedTrains) {
        this.ownedTrains = ownedTrains;
        semanticValidation();
    }
}
