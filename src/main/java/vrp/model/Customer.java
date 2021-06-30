package vrp.model;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.AnchorShadowVariable;
import org.optaplanner.core.api.domain.variable.InverseRelationShadowVariable;
import org.optaplanner.core.api.domain.variable.PlanningVariable;
import org.optaplanner.core.api.domain.variable.PlanningVariableGraphType;

import java.math.BigDecimal;

@PlanningEntity
public class Customer implements Task{
    public int id;
    public long demand;
    public Location location;
    @InverseRelationShadowVariable(sourceVariableName = "previousTask")
    public Customer nextTask;
    @PlanningVariable(graphType = PlanningVariableGraphType.CHAINED, valueRangeProviderRefs = {"customerRange", "vehicleRange"})
    public Task previousTask;
    @AnchorShadowVariable(sourceVariableName = "previousTask")
    public Vehicle vehicle;

    public Customer(){}

    public Customer(int id, long demand, Location location) {
        this.id = id;
        this.demand = demand;
        this.location = location;
    }

    @Override
    public Location getLocation() {
        return this.location;
    }

    @Override
    public int getLocationId() {
        return this.id;
    }

    @Override
    public Vehicle getVehicle() {
        return previousTask.getVehicle();
    }

    @Override
    public Customer getNextTask() {
        return this.nextTask;
    }

    @Override
    public void setNextTask(Customer nextCustomer) {
        this.nextTask = nextCustomer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getDemand() {
        return demand;
    }

    public void setDemand(long demand) {
        this.demand = demand;
    }

    public Task getPreviousTask() {
        return previousTask;
    }

    public void setPreviousTask(Task previousTask) {
        this.previousTask = previousTask;
    }

    public BigDecimal distToDepot() {
        return Matrix.getDist(0, this.id);
    }

    public BigDecimal distToPrevious() {
        return Matrix.getDist(this.id, this.previousTask.getLocationId());
    }
}
