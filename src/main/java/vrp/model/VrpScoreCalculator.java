package vrp.model;

import org.optaplanner.core.api.score.buildin.hardsoftbigdecimal.HardSoftBigDecimalScore;
import org.optaplanner.core.api.score.calculator.EasyScoreCalculator;

import java.math.BigDecimal;

public class VrpScoreCalculator implements EasyScoreCalculator<VrpSolution, HardSoftBigDecimalScore> {

    @Override
    public HardSoftBigDecimalScore calculateScore(VrpSolution vrpSolution) {
        long hard = 0;
        BigDecimal soft = BigDecimal.ZERO;

        for (Vehicle vehicle: vrpSolution.getVehicleList()) {
            if(vehicle.nextTask != null){
                int sumDemand = 0;
                Customer c = vehicle.nextTask;
                while(c != null){
                    sumDemand += c.demand;
                    c = c.nextTask;
                }
                hard += -Math.min(0, sumDemand - vehicle.capasity);
            }
        }

        for (Vehicle vehicle: vrpSolution.getVehicleList()) {
            if(vehicle.nextTask != null){
                BigDecimal tripDist = BigDecimal.ZERO;
                Customer c = vehicle.nextTask;
                do{
                    tripDist = tripDist.add(c.distToPrevious());
                    c = c.nextTask;
                }while(c != null);
                soft = soft.add(tripDist);
                soft = soft.add(c.distToDepot());
            }
        }



        return HardSoftBigDecimalScore.of(BigDecimal.valueOf(hard), soft);
    }

}
