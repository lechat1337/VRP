package vrp.model;

import org.optaplanner.core.impl.heuristic.selector.common.nearby.NearbyDistanceMeter;

public class CustomerNearbyDistanceMeter implements NearbyDistanceMeter<Customer, Task> {

public double getNearbyDistance(Customer origin, Task destination) {
            return Matrix.getDist(origin.getLocationId(), destination.getLocationId()).doubleValue();
        }
}
