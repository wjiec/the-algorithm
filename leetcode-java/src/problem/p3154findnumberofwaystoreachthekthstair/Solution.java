package problem.p3154findnumberofwaystoreachthekthstair;

import java.util.HashMap;
import java.util.Map;

public class Solution {

    public int waysToReachStair(int k) {
        return reach(1, 0, true, k);
    }

    private final Map<Long, Integer> memo = new HashMap<>();

    private int reach(int curr, int jump, boolean canSubtract, int k) {
        if (curr > k + 1) return 0;

        long key = ((long) curr << 32) | ((long) jump << 16) | (canSubtract ? 1 : 0);
        if (memo.containsKey(key)) return memo.get(key);

        int ans = curr == k ? 1 : 0;
        ans += reach(curr + (1 << jump), jump + 1, true, k);
        if (canSubtract && curr > 0) ans += reach(curr - 1, jump, false, k);

        memo.put(key, ans);
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().waysToReachStair(0) == 2;
        assert new Solution().waysToReachStair(1) == 4;
    }

}
