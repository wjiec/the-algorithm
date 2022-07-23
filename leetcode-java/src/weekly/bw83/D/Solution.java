package weekly.bw83.D;

import java.util.HashSet;
import java.util.Set;

public class Solution {

    public int shortestSequence(int[] rolls, int k) {
        int ans = 1;
        Set<Integer> set = new HashSet<>();
        for (int roll : rolls) {
            set.add(roll);
            if (set.size() == k) {
                set.clear(); ans++;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().shortestSequence(new int[]{4,2,1,2,3,3,2,4,1}, 4) == 3;
        assert new Solution().shortestSequence(new int[]{1,1,2,2}, 2) == 2;
        assert new Solution().shortestSequence(new int[]{1,1,3,2,2,2,3,3}, 4) == 1;
    }

}
