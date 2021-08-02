package vrp.model;

import org.optaplanner.core.api.solver.SolverFactory;
import org.optaplanner.core.api.solver.event.BestSolutionChangedEvent;
import org.optaplanner.core.api.solver.event.SolverEventListener;

public class Solver {

    private static class ProgressWatcher implements SolverEventListener<VRPSolution>
    {
        private org.optaplanner.core.api.solver.Solver<VRPSolution> solver;

        ProgressWatcher(org.optaplanner.core.api.solver.Solver<VRPSolution> solver)
        {
            this.solver = solver;
        }

        @Override
        public void bestSolutionChanged(BestSolutionChangedEvent<VRPSolution> event) {

        }
    }

    private static final String SOLVER_CONFIG = "solverConfig.xml";

    public static VRPSolution solve(VRPSolution problem)
    {
        SolverFactory<VRPSolution> solverFactory = SolverFactory.createFromXmlResource(SOLVER_CONFIG);
        org.optaplanner.core.api.solver.Solver<VRPSolution> solver = solverFactory.buildSolver();

        solver.addEventListener(new ProgressWatcher(solver));
        VRPSolution solution = solver.solve(problem);
        //ScoreManager<VrpSolution, HardMediumSoftBigDecimalScore> scoreManager = ScoreManager.create(solverFactory);
        //System.out.println(scoreManager.explainScore(solution));
        return solution;
    }
}