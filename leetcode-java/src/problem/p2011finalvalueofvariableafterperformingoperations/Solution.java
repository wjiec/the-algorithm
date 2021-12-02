package problem.p2011finalvalueofvariableafterperformingoperations;

/**
 * 2011. Final Value of Variable After Performing Operations
 *
 * https://leetcode-cn.com/problems/final-value-of-variable-after-performing-operations/
 *
 * There is a programming language with only four operations and one variable X:
 *
 * ++X and X++ increments the value of the variable X by 1.
 * --X and X-- decrements the value of the variable X by 1.
 * Initially, the value of X is 0.
 *
 * Given an array of strings operations containing a list of operations,
 * return the final value of X after performing all the operations.
 */

public class Solution {

    public int finalValueAfterOperations(String[] operations) {
        int ans = 0;
        for (var op : operations) {
            if (op.charAt(0) == '+' || op.charAt(2) == '+') ans++;
            else ans--;
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().finalValueAfterOperations(new String[]{"--X","X++","X++"}) == 1;
        assert new Solution().finalValueAfterOperations(new String[]{"++X","++X","X++"}) == 3;
        assert new Solution().finalValueAfterOperations(new String[]{"X++","++X","--X","X--"}) == 0;
    }

}
