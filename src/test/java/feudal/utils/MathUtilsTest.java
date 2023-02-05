package feudal.utils;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MathUtilsTest {
    /**
     * Method under test: {@link MathUtils#getRandInt(int, int)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetRandInt() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IllegalArgumentException: bound must be greater than origin
        //       at java.util.Random.ints(Random.java:726)
        //       at feudal.utils.MathUtils.getRandInt(MathUtils.java:8)
        //   See https://diff.blue/R013 to resolve this issue.

        MathUtils.getRandInt(1, 1);
    }

    /**
     * Method under test: {@link MathUtils#getRandInt(int, int)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetRandInt2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IllegalArgumentException: bound must be greater than origin
        //       at java.util.Random.ints(Random.java:726)
        //       at feudal.utils.MathUtils.getRandInt(MathUtils.java:8)
        //   See https://diff.blue/R013 to resolve this issue.

        MathUtils.getRandInt(3, 3);
    }

    /**
     * Method under test: {@link MathUtils#getRandInt(int, int)}
     */
    @Test
    void testGetRandInt3() {
        assertEquals(1, MathUtils.getRandInt(0, 3));
    }
}

