package daily.d220504p1823findthewinnerofthecirculargame;

/**
 * 1823. Find the Winner of the Circular Game
 *
 * https://leetcode-cn.com/problems/find-the-winner-of-the-circular-game/
 *
 * There are n friends that are playing a game. The friends are sitting in a circle and are numbered
 * from 1 to n in clockwise order. More formally, moving clockwise from the ith friend brings
 * you to the (i+1)th friend for 1 <= i < n, and moving clockwise from
 * the nth friend brings you to the 1st friend.
 *
 * The rules of the game are as follows:
 *
 * Start at the 1st friend.
 * Count the next k friends in the clockwise direction including the friend you started at.
 * The counting wraps around the circle and may count some friends more than once.
 *
 * The last friend you counted leaves the circle and loses the game.
 * If there is still more than one friend in the circle, go back to step 2 starting from the friend
 * immediately clockwise of the friend who just lost and repeat.
 * Else, the last friend in the circle wins the game.
 * Given the number of friends, n, and an integer k, return the winner of the game.
 */

public class Solution {

    public int findTheWinner(int n, int k) {
        int ans = 0;
        for (int i = 2; i <= n; i++) {
            ans = (ans + k) % i;
        }
        return ans + 1;
    }

    public int findTheWinnerRecursive(int n, int k) {
        // 1, 2, 3, 4, 5, ..., n-1, n
        // 从这里面选择第 k 个数删除后，我们可以得到一个新的序列
        // k+1, k+2, ..., n-1, n, 1, 2, 3, 4, 5, ..., k-2, k-1
        // k + ((1, 2, 3, 4, 5, ..., n-2, n-1) % (n - 1))
        // 接着我们就可以根据这个进行递推
        // k + (k + (1, 2, 3, 4, 5, ...) % (n - 2)) % (n - 1)
        // ...
        if (n == 1) return 1;
        return (findTheWinnerRecursive(n - 1, k) + k - 1) % n + 1;
    }

    public static void main(String[] args) {
        assert new Solution().findTheWinner(5, 2) == 3;
        assert new Solution().findTheWinner(6, 5) == 1;

        assert new Solution().findTheWinnerRecursive(5, 2) == 3;
        assert new Solution().findTheWinnerRecursive(6, 5) == 1;
    }

}
