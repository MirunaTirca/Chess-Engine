public class Move {

    final Spot currentLocation;
    final Spot destinationLocation;
    final Piece movedPiece;

    @Override
    public String toString() {
        return "Move{" +
                "currentLocation=" + currentLocation +
                ", destinationLocation=" + destinationLocation +
                ", movedPiece=" + movedPiece +
                '}';
    }


    public Move(final Spot currentLocation,
                final Spot destinationLocation,
                final Piece moved) {
        this.currentLocation = currentLocation;
        this.destinationLocation = destinationLocation;
        this.movedPiece = moved;
    }
    public Spot getDestinationLocation() {
        return this.destinationLocation;
    }

    public Spot getCurrentLocation() {
        return this.currentLocation;
    }

    public Piece getMovedPiece() {
        return this.movedPiece;
    }

}
