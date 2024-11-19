package weekly.w424.C;

public class Solution {

    public int minZeroArray(int[] nums, int[][] queries) {
        int l = 0, r = queries.length + 1, ans = -1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (canZeroArray(nums, queries, mid)) {
                ans = mid; r = mid;
            } else l = mid + 1;
        }
        return ans;
    }

    private boolean canZeroArray(int[] nums, int[][] queries, int n) {
        long[] diff = new long[nums.length + 1];
        for (int i = 0; i < n; i++) {
            diff[queries[i][0]] += queries[i][2];
            diff[queries[i][1] + 1] -= queries[i][2];
        }

        for (int i = 0; i < nums.length; i++) {
            if (i > 0) diff[i] += diff[i - 1];
            if (diff[i] < nums[i]) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        assert new Solution().minZeroArray(new int[]{2,0,2}, new int[][]{{0,2,1},{0,2,1},{1,1,3}}) == 2;
        assert new Solution().minZeroArray(new int[]{4,3,2,1}, new int[][]{{1,3,2},{0,2,1}}) == -1;
    }

}
