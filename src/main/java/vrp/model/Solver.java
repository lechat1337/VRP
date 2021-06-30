package vrp.model;

import org.optaplanner.core.api.score.ScoreManager;
import org.optaplanner.core.api.score.buildin.hardmediumsoftbigdecimal.HardMediumSoftBigDecimalScore;
import org.optaplanner.core.api.solver.SolverFactory;
import org.optaplanner.core.api.solver.event.BestSolutionChangedEvent;
import org.optaplanner.core.api.solver.event.SolverEventListener;

public class Solver {

    private static class ProgressWatcher implements SolverEventListener<VrpSolution>
    {
        private org.optaplanner.core.api.solver.Solver<VrpSolution> solver;

        ProgressWatcher(org.optaplanner.core.api.solver.Solver<VrpSolution> solver)
        {
            this.solver = solver;
        }

        @Override
        public void bestSolutionChanged(BestSolutionChangedEvent<VrpSolution> event) {

        }
    }

    private static final String SOLVER_CONFIG = "solverConfig.xml";

    public static VrpSolution solve(VrpSolution problem)
    {
        SolverFactory<VrpSolution> solverFactory = SolverFactory.createFromXmlResource(SOLVER_CONFIG);
        org.optaplanner.core.api.solver.Solver<VrpSolution> solver = solverFactory.buildSolver();

        solver.addEventListener(new ProgressWatcher(solver));
        VrpSolution solution = solver.solve(problem);
        //ScoreManager<VrpSolution, HardMediumSoftBigDecimalScore> scoreManager = ScoreManager.create(solverFactory);
        //System.out.println(scoreManager.explainScore(solution));
        return solution;
    }
}