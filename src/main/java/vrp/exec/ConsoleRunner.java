package vrp.exec;

import org.optaplanner.benchmark.api.PlannerBenchmark;
import org.optaplanner.benchmark.api.PlannerBenchmarkFactory;
import vrp.model.Solver;
import vrp.model.VRPSolution;

import java.io.File;

public class ConsoleRunner {
    public static void main(String[] args) {
//        VrpSolutionFileIO FileIO = new VrpSolutionFileIO();
//        String[] problems = {"vrp_16_3_1"};//, "vrp_200_16_1", "vrp_421_41_1"}; //"vrp_26_8_1", "vrp_51_5_1", "vrp_101_10_1",
//        String pathPr = "C:/Users/aleksejs/coursera/vrp/data/";
//        String pathSol = "C:/Users/aleksejs/just_python/vis_data/";
//        for (String problemName:
//             problems) {
//            Solver solver = new Solver();
//            VRPSolution problem = FileIO.read( new File(pathPr + problemName));
//            VRPSolution solution;
//            solution = solver.solve(problem);
//            FileIO.write(solution, new File(pathSol + problemName));
//            System.out.println(solution.score);
//        }

        PlannerBenchmarkFactory benchmarkFactory = PlannerBenchmarkFactory.createFromXmlResource("VRPBenchmarkConfig.xml");
        PlannerBenchmark benchmark = benchmarkFactory.buildPlannerBenchmark();
        benchmark.benchmarkAndShowReportInBrowser();

//        for (Vehicle v:
//             solution.getVehicleList()) {
//            if(v.nextTask != null){
//                Customer c = v.nextTask;
//                solutionStr.append(v.toString()).append("\n");
//                while (c != null){
//                    solutionStr.append(c.toString()).append("--->");
//                    c = c.nextTask;
//                }
//                solutionStr.append("\n");
//            }else {
//                solutionStr.append(v.toString()).append(" is not used\n");
//            }
//
//        }
//        System.out.println(solutionStr.toString());

    }
}
