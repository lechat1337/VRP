package vrp.model;

import org.optaplanner.core.api.score.buildin.hardsoftbigdecimal.HardSoftBigDecimalScore;
import org.optaplanner.core.api.score.calculator.EasyScoreCalculator;

import java.math.BigDecimal;

public class VRPScoreCalculator implements EasyScoreCalculator<VRPSolution, HardSoftBigDecimalScore> {

    @Override
    public HardSoftBigDecimalScore calculateScore(VRPSolution vrpSolution) {
        long hard = 0;
        BigDecimal soft = BigDecimal.ZERO;

        for (Vehicle vehicle: vrpSolution.getVehicleList()) {
            if(vehicle.nextTask != null){
                int sumDemand = 0;
                Customer c = vehicle.nextTask;
                while(c != null) {
                    sumDemand += c.demand;
                    c = c.nextTask;
                }
                hard -= (sumDemand > vehicle.capasity) ? sumDemand - vehicle.capasity : 0;
            }
        }

        for (Vehicle vehicle: vrpSolution.getVehicleList()) {
            if(vehicle.nextTask != null){
                BigDecimal tripDist = BigDecimal.ZERO;
                Customer c = vehicle.nextTask;
                while(c.nextTask != null){
                    tripDist = tripDist.add(c.distToPrevious());
                    c = c.nextTask;
                }
                tripDist = tripDist.add(c.distToPrevious());
                soft = soft.add(tripDist);
                soft = soft.add(c.distToDepot());
            }
        }

        soft = soft.negate();

        return HardSoftBigDecimalScore.of(BigDecimal.valueOf(hard), soft);
    }

}
