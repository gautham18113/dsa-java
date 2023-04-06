package common;

import core.ProblemInterface;
import core.Solution;
import core.TestCase;
import core.datastructure.BST;
import core.io.Input;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;

import org.assertj.core.api.Assertions;

public class TestUtil {

    @SuppressWarnings("all")
    public static void runAssertions(ProblemInterface problem, Solution solution) {
        for (TestCase test : problem.getProblem().getTestCases()) {
            Input input = test.getInputs().get(0);

            Object actual = solution.solve(input).getOutput();
            Object expected = test.getOutput().getOutput();

            if (test.getCompare().isPresent()) {
                BiFunction fn = test.getCompare().get();
                Boolean result = (Boolean) fn.apply(expected, actual);
                assert(result);
            } else if (actual instanceof BST && expected instanceof BST) {
                Assertions.assertThat(actual.equals(expected));
            } else if (actual instanceof List && expected instanceof  List) {
                assert(((List<?>) actual).containsAll((List<?>) expected));
            } else {
                Assertions.assertThat(actual).isEqualTo(expected);
            }
        }
    }

    public static void runAssertionsMultipleParams(ProblemInterface problem, Solution solution) {
        for (TestCase test : problem.getProblem().getTestCases()) {
            Assertions.assertThat(solution.solve(new Input<>(test.getInputs())).getOutput())
                    .isEqualTo(test.getOutput().getOutput());
        }
    }
}
