package weekly.biweekly52.p4;

import java.util.Arrays;

public class Solution {

    public int sumOfFlooredPairs(int[] nums) {
        int rs = 0, sz = nums.length, mod = 1000000007, len = 0;
        if (sz == 1) {
            return 1;
        }

        Arrays.sort(nums);
        int[] ns = new int[sz], cs = new int[sz]; ns[0] = nums[0];
        for (int i = 0; i < sz; i++) {
            if (ns[len] == nums[i]) {
                cs[len]++;
            } else {
                ns[++len] = nums[i];
                cs[len] = 1;
            }
        }

        len++;
        for (int i = 0; i < len; i++) {
            rs = (rs + cs[i] * cs[i]) % mod;
        }

        for (int i = 1; i < len; i++) {
            for (int j = 0; j < i; j++) {
                rs = (rs + (ns[i] / ns[j] * cs[i] * cs[j])) % mod;
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        assert new Solution().sumOfFlooredPairs(new int[]{2,5,9}) == 10;
        assert new Solution().sumOfFlooredPairs(new int[]{7,7,7,7,7,7,7}) == 49;
    }

}
