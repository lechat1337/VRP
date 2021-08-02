package vrp.exec;

import org.optaplanner.persistence.common.api.domain.solution.SolutionFileIO;
import vrp.model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class VrpSolutionFileIO implements SolutionFileIO<VRPSolution> {

    @Override
    public String getInputFileExtension() {
        return "";
    }

    @Override
    public VRPSolution read(File inputSolutionFile) {
        VRPSolution solution = new VRPSolution();
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
            solution = new VRPSolution(customerList, vehicleList, depot);
            Matrix.setMatrix(solution.getLocationList());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return solution;
    }

    @Override
    public void write(VRPSolution vrpSolution, File outputSolutionFile) {
        StringBuilder solutionStr = new StringBuilder();
        solutionStr.append(vrpSolution.getScore().getSoftScore().abs()).append(" 0\n");
        for (Vehicle v:
                vrpSolution.getVehicleList()) {
            if(v.nextTask != null){
                Customer c = v.nextTask;
                solutionStr.append("0 ");
                while (c != null){
                    solutionStr.append(c.id).append(" ");
                    c = c.nextTask;
                }
                solutionStr.append("0\n");
            }else {
                solutionStr.append("0 0\n");
            }
        }
        System.out.println(solutionStr.toString());
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(outputSolutionFile.getAbsolutePath(), false));
            writer.write(solutionStr.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
