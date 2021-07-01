package vrp.exec;

import vrp.model.Customer;
import vrp.model.Solver;
import vrp.model.Vehicle;
import vrp.model.VrpSolution;

import java.io.File;

public class ConsoleRunner {
    public static void main(String[] args) {
        VrpSolutionFileIO FileIO = new VrpSolutionFileIO();

        VrpSolution problem = FileIO.read(new File("C:/Users/aleksejs/coursera/vrp/data/vrp_16_3_1"));
        VrpSolution solution;

        Solver solver = new Solver();

        solution = solver.solve(problem);

        FileIO.write(solution, new File("C:\\Users\\aleksejs\\just_python\\vis_data\\vrp_solution.txt"));
        StringBuilder solutionStr = new StringBuilder();
        for (Vehicle v:
             solution.getVehicleList()) {
            if(v.nextTask != null){
                Customer c = v.nextTask;
                solutionStr.append(v.toString()).append("\n");
                while (c != null){
                    solutionStr.append(c.toString()).append("--->");
                    c = c.nextTask;
                }
                solutionStr.append("\n");
            }else {
                solutionStr.append(v.toString()).append(" is not used\n");
            }

        }
        System.out.println(solutionStr.toString());

        System.out.println(solution.score);

    }
}
