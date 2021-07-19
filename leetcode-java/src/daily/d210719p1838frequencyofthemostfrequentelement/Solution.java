package daily.d210719p1838frequencyofthemostfrequentelement;

import java.util.*;

/**
 * 1838. Frequency of the Most Frequent Element
 *
 * https://leetcode-cn.com/problems/frequency-of-the-most-frequent-element/
 *
 * The frequency of an element is the number of times it occurs in an array.
 *
 * You are given an integer array nums and an integer k. In one operation,
 * you can choose an index of nums and increment the element at that index by 1.
 *
 * Return the maximum possible frequency of an element after performing at most k operations.
 */

public class Solution {

    public int maxFrequency(int[] nums, int k) {
        Arrays.sort(nums);

        long n = nums.length, total = 0, l = 0, ans = 0;
        for (int r = 1; r < n; r++) {
            total += (nums[r] - nums[r - 1]) * (r - l);
            while (total > k) {
                total -= nums[r] - nums[(int) l];
                ++l;
            }
            ans = Math.max(ans, r - l + 1);
        }
        return (int) ans;
    }

    public int maxFrequencySimple(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (var n : nums) map.merge(n, 1, Integer::sum);

        List<Map.Entry<Integer, Integer>> pairs = new ArrayList<>(map.entrySet());
        pairs.sort((l, r) -> r.getKey() - l.getKey());

        int[] backSum = new int[pairs.size()];
        for (int i = pairs.size() - 1, s = 0; i >= 0; i--) {
            s += pairs.get(i).getValue();
            backSum[i] = s;
        }

        int ans = pairs.get(0).getValue();
        for (int i = 0, l = pairs.size(); i < l; i++) {
            ans = Math.max(ans, pairs.get(i).getValue() + doIncrement(pairs, pairs.get(i).getKey(), i + 1, k));
            if (ans >= backSum[i]) break;
        }

        return ans;
    }

    private int doIncrement(List<Map.Entry<Integer, Integer>> entries, int value, int start, int k) {
        int count = 0, l = entries.size();
        for (int i = start; i < l; i++) {
            int diff = value - entries.get(i).getKey();
            if (k < diff) break;

            int cnt = Math.min(k / diff, entries.get(i).getValue());
            count += cnt;
            k -= cnt * diff;
        }
        return count;
    }

    public static void main(String[] args) {
        assert new Solution().maxFrequency(new int[]{
            9940,9995,9944,9937,9941,9952,9907,9952,
            9987,9964,9940,9914,9941,9933,9912,9934,
            9980,9907,9980,9944,9910,9997
        }, 7925) == 22;

        assert new Solution().maxFrequency(new int[]{1,2,4}, 5) == 3;
        assert new Solution().maxFrequency(new int[]{1,4,8,13}, 5) == 2;
        assert new Solution().maxFrequency(new int[]{3,9,6}, 2) == 1;


        assert new Solution().maxFrequencySimple(new int[]{
            9940,9995,9944,9937,9941,9952,9907,9952,
            9987,9964,9940,9914,9941,9933,9912,9934,
            9980,9907,9980,9944,9910,9997
        }, 7925) == 22;

        assert new Solution().maxFrequencySimple(new int[]{1,2,4}, 5) == 3;
        assert new Solution().maxFrequencySimple(new int[]{1,4,8,13}, 5) == 2;
        assert new Solution().maxFrequencySimple(new int[]{3,9,6}, 2) == 1;
    }

}
