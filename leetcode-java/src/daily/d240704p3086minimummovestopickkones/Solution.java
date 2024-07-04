package daily.d240704p3086minimummovestopickkones;

import common.Tag;

import java.util.ArrayList;
import java.util.List;

/**
 * 3086. Minimum Moves to Pick K Ones
 *
 * https://leetcode.cn/problems/minimum-moves-to-pick-k-ones/
 *
 * You are given a binary array nums of length n, a positive integer k and a non-negative integer maxChanges.
 *
 * Alice plays a game, where the goal is for Alice to pick up k ones from nums using the minimum number of moves.
 * When the game starts, Alice picks up any index aliceIndex in the range [0, n - 1] and stands there.
 * If nums[aliceIndex] == 1 , Alice picks up the one and nums[aliceIndex] becomes 0(this does not count as a move).
 * After this, Alice can make any number of moves (including zero) where in each move Alice must perform exactly
 * one of the following actions:
 *
 * Select any index j != aliceIndex such that nums[j] == 0 and set nums[j] = 1. This action
 * can be performed at most maxChanges times.
 *
 * Select any two adjacent indices x and y (|x - y| == 1) such that nums[x] == 1, nums[y] == 0, then
 * swap their values (set nums[y] = 1 and nums[x] = 0). If y == aliceIndex, Alice picks up the one
 * after this move and nums[y] becomes 0.
 *
 * Return the minimum number of moves required by Alice to pick exactly k ones.
 */

public class Solution {

    @Tag({"两边往中间移动"})
    public long minimumMoves(int[] nums, int k, int maxChanges) {
        int n = nums.length, serial = 0;
        List<Integer> oneIndex = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) continue;

            oneIndex.add(i);
            serial = Math.max(serial, nums[i] + (i > 0 ? nums[i - 1] : 0) + (i + 1 < n ? nums[i + 1] : 0));
        }

        // 是否可以用连续的1配合maxChanges完成目标
        serial = Math.min(serial, k);
        if (serial + maxChanges >= k) {
            return Math.max(serial - 1, 0) + 2L * (k - serial);
        }

        long[] sum = new long[oneIndex.size() + 1];
        for (int i = 0; i < oneIndex.size(); i++) {
            sum[i + 1] = sum[i] + oneIndex.get(i);
        }

        long ans = Long.MAX_VALUE;
        // 现在我们必须移动其他位置上的1来满足要求
        int width = k - maxChanges;
        for (int r = width; r <= oneIndex.size(); r++) {
            int l = r - width, mid = l + width / 2;
            long index = oneIndex.get(mid);

            long leftSum = index * (mid - l) - (sum[mid] - sum[l]);
            long rightSum = (sum[r] - sum[mid]) - index * (r - mid);

            ans = Math.min(ans, leftSum + rightSum);
        }

        return ans + maxChanges * 2L;
    }

    public static void main(String[] args) {
        assert new Solution().minimumMoves(new int[]{1,1}, 1, 2) == 0;
        assert new Solution().minimumMoves(new int[]{0,1,0,0,0,1}, 3, 1) == 6;
        assert new Solution().minimumMoves(new int[]{0,0,1}, 2, 1) == 2;
        assert new Solution().minimumMoves(new int[]{1,0,1,0,1}, 3, 0) == 4;

        assert new Solution().minimumMoves(new int[]{1,1,0,0,0,1,1,0,0,1}, 3, 1) == 3;
        assert new Solution().minimumMoves(new int[]{0,0,0,0}, 2, 3) == 4;
    }

}
