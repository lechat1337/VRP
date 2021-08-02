package vrp.model;

import org.optaplanner.core.api.score.buildin.hardsoftbigdecimal.HardSoftBigDecimalScore;
import org.optaplanner.core.api.score.stream.Constraint;
import org.optaplanner.core.api.score.stream.ConstraintFactory;
import org.optaplanner.core.api.score.stream.ConstraintProvider;
import static org.optaplanner.core.api.score.stream.ConstraintCollectors.sumLong;
public class VRPConstratProvider implements ConstraintProvider {
    
    @Override
    public Constraint[] defineConstraints(ConstraintFactory constraintFactory) {
        return new Constraint[] {
                penalizeCapasity(constraintFactory),
                penalizeDistanceToPrevious(constraintFactory),
                penalizeDistanceToDepot(constraintFactory)
        };
    }

    private Constraint penalizeDistanceToDepot(ConstraintFactory constraintFactory) {
        return constraintFactory.from(Customer.class)
                .filter(customer -> customer.nextTask == null)
                .penalizeBigDecimal("distToDepot",
                        HardSoftBigDecimalScore.ONE_SOFT,
                        Customer::distToDepot);
    }

    private Constraint penalizeDistanceToPrevious(ConstraintFactory constraintFactory) {
        return constraintFactory.from(Customer.class)
                .penalizeBigDecimal("distToPrevious",
                        HardSoftBigDecimalScore.ONE_SOFT,
                        Customer::distToPrevious);

    }

    private Constraint penalizeCapasity(ConstraintFactory constraintFactory) {
        return constraintFactory.from(Customer.class)
                .groupBy(Customer::getVehicle, sumLong(Customer::getDemand))
                .filter((vehicle, totalDemand) -> totalDemand > vehicle.getCapasity())
                .penalize("capasity",
                        HardSoftBigDecimalScore.ONE_HARD,
                        (vehicle, totalDemand) -> (int) (totalDemand - vehicle.getCapasity()));
    }
}
