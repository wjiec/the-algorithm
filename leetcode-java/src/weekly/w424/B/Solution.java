package weekly.w424.B;

public class Solution {

    public boolean isZeroArray(int[] nums, int[][] queries) {
        // 在 queries[i][0] 到 queries[i][1] 中选择任意子集减少1
        int[] diff = new int[nums.length + 1];
        for (var query : queries) {
            diff[query[0]]++;
            diff[query[1] + 1]--;
        }

        for (int i = 0; i < nums.length; i++) {
            if (i > 0) diff[i] += diff[i - 1];
            if (diff[i] < nums[i]) return false;
        }

        return true;
    }

    public static void main(String[] args) {
    }

}
