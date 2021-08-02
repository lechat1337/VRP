package vrp.model;

import org.optaplanner.core.api.domain.solution.*;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.hardsoftbigdecimal.HardSoftBigDecimalScore;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@PlanningSolution
public class VRPSolution {

    @PlanningEntityCollectionProperty
    @ValueRangeProvider(id = "customerRange")
    public List<Customer> customerList;


    public List<Vehicle> vehicleList;

    @ProblemFactCollectionProperty
    public List<Location> locationList;
    @ProblemFactProperty
    public Depot depot;

    @PlanningScore
    public HardSoftBigDecimalScore score;



    public VRPSolution(){
        this.customerList = new ArrayList<Customer>();
        this.vehicleList = new ArrayList<Vehicle>();
        this.locationList = new ArrayList<Location>();
    }

    public VRPSolution(List<Customer> customerList, List<Vehicle> vehicleList, Depot depot){
        this.customerList = customerList;
        this.vehicleList = vehicleList;
        this.depot = depot;
        this.locationList = new ArrayList<Location>();
        this.locationList.add(depot.location);
        for (Customer customer:
             customerList) {
            this.locationList.add(customer.getLocation());
        }

    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }
    @PlanningEntityCollectionProperty
    @ValueRangeProvider(id = "vehicleRange")
    public List<Vehicle> getVehicleList() {
        return vehicleList;
    }

    public void setVehicleList(List<Vehicle> vehicleList) {
        this.vehicleList = vehicleList;
    }

    public List<Location> getLocationList() {
        return this.locationList;
    }

    public void setLocationList(List<Location> locationList) {
        this.locationList = locationList;
    }

    public HardSoftBigDecimalScore getScore() {
        return score;
    }

    public void setScore(HardSoftBigDecimalScore score) {
        this.score = score;
    }
}
