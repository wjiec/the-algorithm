package problem.p2086minimumnumberofbucketsrequiredtocollectrainwaterfromhouses;

/**
 * 2086. Minimum Number of Buckets Required to Collect Rainwater from Houses
 *
 * https://leetcode.cn/problems/minimum-number-of-buckets-required-to-collect-rainwater-from-houses/
 *
 * You are given a 0-indexed string street. Each character in street is either 'H' representing
 * a house or '.' representing an empty space.
 *
 * You can place buckets on the empty spaces to collect rainwater that falls from the adjacent houses.
 * The rainwater from a house at index i is collected if a bucket is placed at index i - 1 and/or index i + 1.
 * A single bucket, if placed adjacent to two houses, can collect the rainwater from both houses.
 *
 * Return the minimum number of buckets needed so that for every house, there is at least one bucket
 * collecting rainwater from it, or -1 if it is impossible.
 */

public class Solution {

    public int minimumBuckets(String street) {
        int n = street.length(), ans = 0;
        char[] chars = street.toCharArray();
        for (int i = 0; i < n; i++) {
            if (chars[i] == 'H') {
                ans++;
                boolean left = i != 0 && chars[i - 1] == '.';
                boolean right = i != n - 1 && chars[i + 1] == '.';
                if (!left && !right) return -1;
            }
        }

        for (int i = 1; i < n - 1; i++) {
            if (chars[i] == '.' && chars[i - 1] == 'H' && chars[i + 1] == 'H') {
                ans--; i += 2; // 已经复用的房子就不能再使用了，直接跳过
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().minimumBuckets(".HH.H.H.H..") == 3;

        assert new Solution().minimumBuckets("H..H") == 2;
        assert new Solution().minimumBuckets(".H.H.") == 1;
        assert new Solution().minimumBuckets(".HHH.") == -1;
        assert new Solution().minimumBuckets("H") == -1;
        assert new Solution().minimumBuckets(".") == 0;
    }

}
