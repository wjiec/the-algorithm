package weekly.w435.D;

/**
 * 3445. Maximum Difference Between Even and Odd Frequency II
 *
 * https://leetcode.cn/contest/weekly-contest-435/problems/maximum-difference-between-even-and-odd-frequency-ii/
 *
 * You are given a string s and an integer k. Your task is to find the maximum difference
 * between the frequency of two characters, freq[a] - freq[b], in a substring subs of s, such that:
 *
 * subs has a size of at least k.
 * Character a has an odd frequency in subs.
 * Character b has an even frequency in subs.
 * Return the maximum difference.
 *
 * Note that subs can contain more than 2 distinct characters.
 */

public class Solution {

    private final static int INF = 1_000_000_007;

    // s 中只有 0, 1, 2, 3, 4
    public int maxDifference(String s, int k) {
        char[] chars = s.toCharArray();
        // 可以枚举奇数数字是 a, 偶数数字是 b, 求长度至少为 k 的子字符串中 a - b 的最大值
        int ans = -INF;
        for (char a = 0; a <= 4; a++) {
            for (char b = 0; b <= 4; b++) {
                if (a == b) continue;
                ans = Math.max(ans, maxDifference(chars, k, a, b));
            }
        }
        return ans;
    }

    // 求 chars 中长度至少为 k 的子序列中, a 出现奇数次且 b 出现偶数次时 freq[a] - freq[b] 的最大值
    private int maxDifference(char[] chars, int k, int a, int b) {
        // 要想 freq[a] - freq[b] 则 freq[a] 需要尽可能大, 同时 freq[b] 尽可能小
        //  - 可以在序列中将 a 变为 1, b 变为 -1, 使用前缀和计算差值, 计算区间为 [l, r), 要满足
        //      - r - l >= k
        //      - 差值为: (pre_(r,a) - pre_(l,a)) - (pre_(r,b) - pre_(l,b))
        //             = pre_(r,a) - pre_(l,a) - pre_(r,b) - -pre_(l,b)
        //             = (pre_(r,a) - pre_(r,b)) - (pre_(l,a) - pre_(l,b))
        // 我们可以枚举右端点 r, 记录满足 r - l >= k 的所有 l 的最小值
        //  - 但是这样就丢失了 a 的数量要为奇数, 且 b 的数量要为偶数的信息
        // 所以我们使用 min[p][q] 表示 a 的奇偶性为 p, b 的奇偶性为 q 的最小值
        int[] currFreq = new int[5];
        int[] prevFreq = new int[5];
        int[][] min = new int[][]{{INF, INF}, {INF, INF}};

        int ans = -INF;
        for (int l = 0, r = 0; r < chars.length; r++) {
            // 统计频率, 也就是前缀和
            currFreq[chars[r] - '0']++;
            // 移动左边到最大可以到达的位置, 同时需要保证
            while (r - l + 1 >= k && currFreq[a] > prevFreq[a] && currFreq[b] > prevFreq[b]) {
                int p = prevFreq[a] & 1, q = prevFreq[b] & 1;
                min[p][q] = Math.min(min[p][q], prevFreq[a] - prevFreq[b]);
                prevFreq[chars[l++] - '0']++;
            }
            // 当前位置 a 的数量为 currFreq[a], 要使得 a 的数量为奇数, 则需要取与当前 a 的频率奇偶性相反的位置
            // b 同理, 因为要使得 b 的数量为偶数, 所以需要取与当前 b 的频率奇偶性相同的位置
            ans = Math.max(ans, (currFreq[a] - currFreq[b]) - min[(currFreq[a] & 1) ^ 1][currFreq[b] & 1]);
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().maxDifference("12233", 4) == -1;
        assert new Solution().maxDifference("1122211", 3) == 1;
        assert new Solution().maxDifference("110", 3) == -1;
    }

}
