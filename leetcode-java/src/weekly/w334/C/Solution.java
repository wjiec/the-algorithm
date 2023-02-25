package weekly.w334.C;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution {

    private record Number(int idx, int val) {
        @Override
        public String toString() {
            return idx + "." + val;
        }
    }

    public int maxNumOfMarkedIndices(int[] nums) {
        PriorityQueue<Number> nq = new PriorityQueue<>(Comparator.comparingInt(v -> v.val));
        PriorityQueue<Number> dq = new PriorityQueue<>(Comparator.comparingInt(v -> v.val));
        for (int i = 0; i < nums.length; i++) {
            nq.add(new Number(i, nums[i]));
            dq.add(new Number(i, nums[i] * 2));
        }

        int ans = 0;
        boolean[] mark = new boolean[nums.length];
        while (!nq.isEmpty() && !dq.isEmpty()) {
            int ni = nq.peek().idx;
            int di = dq.peek().idx;
            if (dq.peek().val <= nq.peek().val) {
                if (!mark[ni] && !mark[di]) {
                    ans += 2;
                    mark[ni] = true;
                    mark[di] = true;
                }
                if (mark[ni]) nq.remove();
                if (mark[di]) dq.remove();
            } else nq.remove();
        }
        System.out.println(ans);
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().maxNumOfMarkedIndices(new int[]{
            1,78,27,48,14,86,79,68,77,20,57,21,18,67,5,51,70,85,47,
            56,22,79,41,8,39,81,59,74,14,45,49,15,10,28,16,77,22,65,
            8,36,79,94,44,80,72,8,96,78,39,92,69,55,9,44,26,76,40,77,
            16,69,40,64,12,48,66,7,59,10
        }) == 64;

        assert new Solution().maxNumOfMarkedIndices(new int[]{3,5,2,4}) == 2;
        assert new Solution().maxNumOfMarkedIndices(new int[]{9,2,5,4}) == 4;
        assert new Solution().maxNumOfMarkedIndices(new int[]{7,6,8}) == 0;
    }

}
