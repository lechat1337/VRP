package vrp.model;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.InverseRelationShadowVariable;

@PlanningEntity
public interface Task {

    Location getLocation();

    int getLocationId();

    Vehicle getVehicle();

    @InverseRelationShadowVariable(sourceVariableName = "previousTask")
    Customer getNextTask();

    void setNextTask(Customer nextCustomer);
}
