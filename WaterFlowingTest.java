public class WaterFlowingTest {
    static int passed = 0;
    static int failed = 0;

    public static void assertEquals(String name, boolean expected, boolean actual) {
        if (expected == actual) {
            System.out.println("[PASS] " + name);
            passed++;
        } else {
            System.out.println("[FAIL] " + name + " - expected: " + expected + ", got: " + actual);
            failed++;
        }
    }

    public static void main(String[] args) {
        // Test 1: original map from the exercise - expected false for (3,2)
        int[][] map1 = {
            {5,5,5,5,5},
            {5,1,1,1,5},
            {5,1,5,1,5},
            {5,1,1,1,5},
            {5,5,5,5,5}
        };
        assertEquals("Original map (3,2) => false", false, waterFlowing.canFlowOff(map1, 3, 2));

        // Test 2: border cell should return true
        int[][] map2 = {
            {1,2,3},
            {4,5,6},
            {7,8,9}
        };
        assertEquals("Border cell (0,0) => true", true, waterFlowing.canFlowOff(map2, 0, 0));

        // Test 3: interior cell with descending path to border should return true
        int[][] map3 = {
            {2,1,0},
            {3,2,1},
            {4,3,2}
        };
        // start at (1,1) value 2 -> up (0,1)=1 which is less and on border -> should be true
        assertEquals("Interior flowable (1,1) => true", true, waterFlowing.canFlowOff(map3, 1, 1));

        // Test 4: single-cell map (border) should be true
        int[][] map4 = {{42}};
        assertEquals("Single-cell map (0,0) => true", true, waterFlowing.canFlowOff(map4, 0, 0));

        // Summary
        System.out.println();
        System.out.println("Tests passed: " + passed + ", failed: " + failed);
        if (failed > 0) {
            System.exit(1);
        } else {
            System.exit(0);
        }
    }
}
