package vrp.exec;

import org.optaplanner.persistence.common.api.domain.solution.SolutionFileIO;
import vrp.model.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class VrpSolutionFileIO implements SolutionFileIO<VrpSolution> {

    @Override
    public String getInputFileExtension() {
        return "";
    }

    @Override
    public VrpSolution read(File inputSolutionFile) {
        VrpSolution solution = new VrpSolution();
        try {
            Scanner sc = new Scanner(inputSolutionFile).useLocale(Locale.US);
            int nCustomers = sc.nextInt();
            int nVehicles = sc.nextInt();
            long capasity = sc.nextLong();
            List<Customer> customerList = new ArrayList<>(nCustomers - 1);
            List<Vehicle> vehicleList = new ArrayList<>(nVehicles);
            long  tDemand = sc.nextLong();
            Location location = new Location(sc.nextBigDecimal(), sc.nextBigDecimal());
            Depot depot = new Depot(location);
            for(int i = 0; i < nVehicles; i++){

                vehicleList.add(new Vehicle(nCustomers + i, depot, capasity));
            }
            for(int i = 1; i < nCustomers; i++){
                tDemand = sc.nextLong();
                location = new Location(sc.nextBigDecimal(), sc.nextBigDecimal());
                customerList.add(new Customer(i, tDemand, location));
            }
            solution = new VrpSolution(customerList, vehicleList, depot);
            Matrix.setMatrix(solution.getLocationList());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return solution;
    }

    @Override
    public void write(VrpSolution vrpSolution, File outputSolutionFile) {

    }
}
