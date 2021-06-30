package vrp.model;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.InverseRelationShadowVariable;

@PlanningEntity
public class Vehicle implements Task{
    public int id;
    public Depot depot;
    public long capasity;
    //@InverseRelationShadowVariable(sourceVariableName = "previousTask")
    public Customer nextTask;

    public Vehicle(int id, Depot depot, long capasity) {
        this.id = id;
        this.depot = depot;
        this.capasity = capasity;
    }

    public Vehicle() {
    }

    @Override
    public Location getLocation() {
        return this.depot.location;
    }

    @Override
    public int getLocationId() {
        return 0;
    }

    @Override
    public Vehicle getVehicle() {
        return this;
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
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Depot getDepot() {
        return depot;
    }

    public void setDepot(Depot depot) {
        this.depot = depot;
    }

    public long getCapasity() {
        return capasity;
    }

    public void setCapasity(long capasity) {
        this.capasity = capasity;
    }
}
