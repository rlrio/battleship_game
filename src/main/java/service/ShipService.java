package service;

import model.Cell;
import model.CellStatus;
import model.Ship;

public class ShipService {
    private final Ship ship;

    public ShipService(Ship ship) {
        this.ship = ship;
    }

    public boolean isHit(Cell cell) {
        if (ship.getCellsList().contains(cell)) {
            ship.getCellsList().get(ship.getCellsList().indexOf(cell)).setCellStatus(CellStatus.HIT);
            return true;
        }
        return false;
    }

    public boolean isNotAlive() {
        return ship.getCellsList().stream().allMatch(c -> c.getCellStatus().equals(CellStatus.HIT));
    }

}
