package vrp.exec;

import vrp.model.Solver;
import vrp.model.VrpSolution;

import java.io.File;

public class ConsoleRunner {
    public static void main(String[] args) {
        VrpSolutionFileIO FileIO = new VrpSolutionFileIO();

        VrpSolution problem = FileIO.read(new File("C:/Users/aleksejs/coursera/vrp/data/vrp_5_4_1"));
        VrpSolution solution;

        Solver solver = new Solver();

        solution = solver.solve(problem);


        System.out.println(solution.score);

    }
}
