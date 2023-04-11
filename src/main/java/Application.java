import com.google.inject.Guice;
import com.google.inject.Injector;
import module.AppModule;
import solver.Solver;
import solver.impl.FindNoOfIslandsSolver;
import solver.impl.FloodFillSolver;

import java.util.Arrays;
import java.util.List;

public class Application {

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new AppModule());
        runSolvers(Arrays.asList(
                injector.getInstance(FloodFillSolver.class),
                injector.getInstance(FindNoOfIslandsSolver.class)
        ));
    }

    private static void runSolvers(List<Solver> solvers) {
        for(Solver solver: solvers) {
            solver.solve();
        }
    }
}
