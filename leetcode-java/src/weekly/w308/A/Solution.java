package weekly.w308.A;

import java.util.Arrays;

public class Solution {

    public int[] answerQueries(int[] nums, int[] queries) {
        Arrays.sort(nums);

        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int j = 0, sum = 0;
            while (j < nums.length && sum <= queries[i]) sum += nums[j++];
            ans[i] = sum <= queries[i] ? j : j - 1;
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
