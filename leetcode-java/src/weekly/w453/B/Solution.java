package weekly.w453.B;

/**
 * Q2. Count the Number of Computer Unlocking Permutations
 *
 * https://leetcode.cn/contest/weekly-contest-453/problems/count-the-number-of-computer-unlocking-permutations
 *
 * You are given an array complexity of length n.
 *
 * There are n locked computers in a room with labels from 0 to n - 1, each with its own unique password.
 * The password of the computer i has a complexity[i].
 *
 * The password for the computer labeled 0 is already decrypted and serves as the root.
 * All other computers must be unlocked using it or another previously unlocked computer, following this information:
 *
 * You can decrypt the password for the computer i using the password for computer j, where j is any integer
 * less than i with a lower complexity. (i.e. j < i and complexity[j] < complexity[i])
 *
 * To decrypt the password for computer i, you must have already unlocked a computer j such
 * that j < i and complexity[j] < complexity[i].
 *
 * Find the number of permutations of [0, 1, 2, ..., (n - 1)] that represent a valid order
 * in which the computers can be unlocked, starting from computer 0 as the only initially unlocked one.
 *
 * Since the answer may be large, return it modulo 109 + 7.
 *
 * Note that the password for the computer with label 0 is decrypted, and not the computer
 * with the first position in the permutation.
 */

public class Solution {

    public int countPermutations(int[] complexity) {
        // 满足 a < b && complexity[a] < complexity[b], 才能使用 a 解锁 b
        //  - 有多少种 [0, 1, 2, ..., n-1] (表示解锁的有效顺序) 的排列方式
        //
        // 当解锁 i 时, 所有 j > i 且 complexity[j] > complexity[i] 的都可以解锁
        //  - 也就是解锁 i 时, 后面就有 j_i 种可能的填法, 也就是 j_i!
        //  - 当解锁 i 之后再解锁 j0, 后面就会有 j_i - 1 + j_j0 种填法

        // 我们有根密码可以解锁 0 的计算机, 如果后面有任何小于等于 complexity[0] 的计算机, 则我们无法解锁所有计算机
        for (int j = 1; j < complexity.length; j++) if (complexity[j] <= complexity[0]) return 0;

        // 剩下的也就是都可以使用 0 来解锁, 也就是求 (n - 1)!
        long ans = 1;
        for (int i = 2; i < complexity.length; i++) ans = (ans * i) % 1_000_000_007;
        return (int) ans;
    }

    public static void main(String[] args) {
        assert new Solution().countPermutations(new int[]{38,223,100,123,406,234,256,93,222,259,233,69,139,245,45,98,214}) == 789741546;
    }

}
