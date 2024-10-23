package weekly.w420.C;

import ability.Array;

import java.util.*;
import java.util.function.IntFunction;

import static ability.Prime.euler;

public class Solution {

    // 首先判断这里面所有的质数是否都是非递减的, 因为质数我们无法操作
    // 其次判断这里面所有的合数是否可以操作为比前一个质数更大或相等
    public int minOperations(int[] nums) {
        int max = 0;
        for (var v : nums) max = Math.max(max, v);

        int[] euler = euler(max + 1);
        Set<Integer> isPrime = new HashSet<>();
        List<Integer> primes = new ArrayList<>();
        for (int i = 0; i < euler[0]; i++) {
            isPrime.add(euler[i + 1]);
            primes.add(euler[i + 1]);
        }

        Map<Integer, Integer> memo = new HashMap<>();
        IntFunction<Integer> minFactor = (v) -> {
            if (!memo.containsKey(v)) {
                for (var prime : primes) {
                    if (v % prime == 0) {
                        memo.put(v, prime);
                        break;
                    }
                }
            }
            return memo.get(v);
        };

        int ans = 0, n = nums.length;
        for (int i = 0, p = -1; i <= n; i++) {
            if (i < n && p >= 0 && nums[i] < nums[p]) return -1;
            if (i == n || isPrime.contains(nums[i])) {
                // 枚举质数之前的所有数
                for (int j = Math.min(i - 1, n - 2); j > p; j--) {
                    if (nums[j] > nums[j + 1]) {
                        ans++;
                        nums[j] = minFactor.apply(nums[j]);

                        if (nums[j] > nums[j + 1] || (p >= 0 && nums[j] < nums[p])) return -1;
                    }
                }

                p = i;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().minOperations(new int[]{5, 40, 10}) == -1;
        assert new Solution().minOperations(new int[]{31, 13}) == -1;
        assert new Solution().minOperations(new int[]{20, 10}) == 1;
        assert new Solution().minOperations(Array.make(new int[]{982081,991}, 50000)) == 50000;
        assert new Solution().minOperations(new int[]{982081,991,982081,991,982081}) == 2;
        assert new Solution().minOperations(new int[]{8, 6, 8}) == 1;
        assert new Solution().minOperations(new int[]{75, 117}) == 0;

        assert new Solution().minOperations(new int[]{7,7,6}) == -1;
        assert new Solution().minOperations(new int[]{25,7}) == 1;
        assert new Solution().minOperations(new int[]{1,1,1,1}) == 0;
    }

}
