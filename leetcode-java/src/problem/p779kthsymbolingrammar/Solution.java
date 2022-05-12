package problem.p779kthsymbolingrammar;

/**
 * 779. K-th Symbol in Grammar
 *
 * https://leetcode.cn/problems/k-th-symbol-in-grammar/
 *
 * We build a table of n rows (1-indexed). We start by writing 0 in the 1st row. Now in every subsequent row,
 * we look at the previous row and replace each occurrence of 0 with 01, and each occurrence of 1 with 10.
 *
 * For example, for n = 3, the 1st row is 0, the 2nd row is 01, and the 3rd row is 0110.
 * Given two integer n and k, return the kth (1-indexed) symbol in the nth row of a table of n rows.
 */

public class Solution {

    public int kthGrammar(int n, int k) {
        if (k == 1) return 0;
        if (k == 2) return 1;

        if (kthGrammar(n, (k + 1) / 2) == 0) { // 0 -> 01
            return k % 2 == 0 ? 1 : 0;
        }
        return k % 2 == 0 ? 0 : 1; // 1 -> 10
    }

    public static void main(String[] args) {
        assert new Solution().kthGrammar(1, 1) == 0;
        assert new Solution().kthGrammar(2, 1) == 0;
        assert new Solution().kthGrammar(2, 2) == 1;
        // 0
        // 0 1
        // 0 1 1 0
        // 0 1 1 0 1 0 0 1
        assert new Solution().kthGrammar(4, 1) == 0;
        assert new Solution().kthGrammar(4, 2) == 1;
        assert new Solution().kthGrammar(4, 3) == 1;
        assert new Solution().kthGrammar(4, 4) == 0;
        assert new Solution().kthGrammar(4, 5) == 1;
        assert new Solution().kthGrammar(4, 6) == 0;
        assert new Solution().kthGrammar(4, 7) == 0;
        assert new Solution().kthGrammar(4, 8) == 1;
    }

}
