package weekly.biweekly52.p1;

import common.Checker;

public class Solution {

    public int[] memLeak(int memory1, int memory2) {
        if (memory1 == 0 && memory2 == 0) {
            return new int[]{1, 0, 0};
        }

        int i = 1;
        for (int max = Math.max(memory1, memory2); i <= max; i++, max = Math.max(memory1, memory2)) {
            if (memory1 >= memory2) {
                memory1 -= i;
            } else {
                memory2 -= i;
            }
        }

        return new int[]{i, memory1, memory2};
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().memLeak(2, 2), new int[]{3, 1, 0});
        assert Checker.check(new Solution().memLeak(8, 11), new int[]{6, 0, 4});
    }

}
