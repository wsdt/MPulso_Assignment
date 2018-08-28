package trains;

import exceptions.ConstraintViolation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sys.SYS_Logger;
import trains.train_pieces.TP_Locomotive;
import trains.train_pieces.TP_Wagon;
import trains.train_pieces.TPa_TrainPiece;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class Train {
    /**
     * Made an enum to remove appr. 3/4 other methods and combine them into one.
     */
    enum QUERY_MODE {
        MAX_CAPACITY_ITEMS(0), EMPTY_WEIGHT(1), TRAIN_LENGTH(2),
        TICKET_COLLECTORS(3), MAX_CAPACITY_PASSENGERS(4), MAX_CAPACITY_OVERALL(5),
        MAX_TRAIN_WEIGHT(6);

        private int flag;

        QUERY_MODE(int flag) {
            this.flag = flag;
        }

        public int getFlag() {
            return flag;
        }
    }

    /**
     * Bc. of Sets no cycles should be possible as every wagon/locomotive is unique.
     * Used LinkedHashSets to persist order as it matters here.
     *
     * Separated both, so possible independent queries could be done in future.
     */
    private LinkedHashSet<TP_Locomotive> locomotiveSet;
    private LinkedHashSet<TP_Wagon> wagonSet;

    public Train(@NotNull LinkedHashSet<TP_Locomotive> locomotiveSet) {
        this.setLocomotiveSet(locomotiveSet);
    }

    public Train(@NotNull LinkedHashSet<TP_Locomotive> locomotiveSet, @Nullable LinkedHashSet<TP_Wagon> wagonSet) {
        this.setLocomotiveSet(locomotiveSet);
        this.setWagonSet(wagonSet);
    }

    /**
     * @return sum of desired query_mode (e.g. train length in total, etc.)
     */
    public double getCumulatedData(@NotNull QUERY_MODE query_mode) {
        double overall = 0;
        switch (query_mode) {
            case MAX_CAPACITY_ITEMS:
                for (TPa_TrainPiece trainPiece : mergeTrainPiecesToSet()) {
                    overall += trainPiece.getMaxCapacityItems();
                }
                break;
            case EMPTY_WEIGHT:
                for (TPa_TrainPiece trainPiece : mergeTrainPiecesToSet()) {
                    overall += trainPiece.getEmptyWeight();
                }
                break;
            case TRAIN_LENGTH:
                for (TPa_TrainPiece trainPiece : mergeTrainPiecesToSet()) {
                    overall += trainPiece.getLength();
                }
                break;
            case TICKET_COLLECTORS:
                for (TPa_TrainPiece trainPiece : mergeTrainPiecesToSet()) {
                    overall += (trainPiece.getTicketCollectorSet() == null) ? 0 : trainPiece.getTicketCollectorSet().size();
                }
                break;
            case MAX_CAPACITY_PASSENGERS:
                for (TPa_TrainPiece trainPiece : mergeTrainPiecesToSet()) {
                    overall += trainPiece.getMaxCapacityPassengers();
                }
                break;
            case MAX_CAPACITY_OVERALL:
                // *75kg, as we assume that a passenger weights 75kg in average.
                overall = getCumulatedData(QUERY_MODE.MAX_CAPACITY_ITEMS)+(getCumulatedData(QUERY_MODE.MAX_CAPACITY_PASSENGERS)*75);
                break;
            case MAX_TRAIN_WEIGHT:
                overall = getCumulatedData(QUERY_MODE.MAX_CAPACITY_OVERALL)+getCumulatedData(QUERY_MODE.EMPTY_WEIGHT);
                break;
            default:
                SYS_Logger.getLogger().severe("Could not accumulate/summarize desired data, as query mode is not supported -> " + query_mode);
        }
        return overall;
    }

    /**
     * Small helper method to make code cleaner
     *
     * POSSIBLE IMPROVEMENT: If we assume that trainPieces don't change (or we implement an observer or similar) --> then we could add an additional Set with all merged.
     * or/and we know that we don't need (or at least not usually) locomotive/wagon sets separated
     * I would HIGHLY RECOMMEND to remove this method as it is not a fast operation and add ONE HashSet (e.g. LinkedHashSet<Tpa_TrainPiece>).
     *
     * But with the given constraints is I think a safe bet (except I add some further validations which would be better).
     */
    public Set<TPa_TrainPiece> mergeTrainPiecesToSet() {
        Set<TPa_TrainPiece> trainPieces = new HashSet<>(this.getWagonSet());
        trainPieces.addAll(this.getLocomotiveSet());
        return trainPieces;
    }

    //GETTER/SETTER ---------------------
    public LinkedHashSet<TP_Locomotive> getLocomotiveSet() {
        return locomotiveSet;
    }

    public void setLocomotiveSet(@NotNull LinkedHashSet<TP_Locomotive> locomotiveSet) {
        if (locomotiveSet.size() <= 0) {
            throw new ConstraintViolation("A train needs at least one locomotive!");
        }
        this.locomotiveSet = locomotiveSet;
    }

    public LinkedHashSet<TP_Wagon> getWagonSet() {
        return wagonSet;
    }

    public void setWagonSet(@Nullable LinkedHashSet<TP_Wagon> wagonSet) {
        this.wagonSet = wagonSet;
    }
}
