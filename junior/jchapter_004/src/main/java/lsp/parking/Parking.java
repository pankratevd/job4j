package lsp.parking;

public interface Parking {
    /**
     * Check if Auto can parking
     *
     * @param auto
     * @return true in case Auto can parking
     */
    boolean accept(Auto auto);

    /**
     * Take place by Auto
     *
     * @param auto
     */
    String takePlace(Auto auto);

    /**
     * Release place by Auto
     *
     * @param auto
     */
    void releasePlace(Auto auto);

    /**
     * Get information about free places for Auto
     *
     * @param auto
     * @return number
     */
    Integer getFreePlaces(Auto auto);

}
