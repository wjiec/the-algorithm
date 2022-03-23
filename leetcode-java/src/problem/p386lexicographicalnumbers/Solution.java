package problem.p386lexicographicalnumbers;

import java.util.ArrayList;
import java.util.List;

/**
 * 386. Lexicographical Numbers
 *
 * https://leetcode-cn.com/problems/lexicographical-numbers/
 *
 * Given an integer n, return all the numbers in the range [1, n] sorted in lexicographical order.
 *
 * You must write an algorithm that runs in O(n) time and uses O(1) extra space. 
 */

public class Solution {

    public List<Integer> lexicalOrder(int n) {
        List<Integer> ans = new ArrayList<>();
        for (int i = 1; ans.size() < n; i++) {
            while (i <= n) {
                ans.add(i);
                i *= 10;
            }
            while (i % 10 == 9 || i > n) {
                i /= 10;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().lexicalOrder(13));
        System.out.println(new Solution().lexicalOrder(2));
        System.out.println(new Solution().lexicalOrder(99));
        System.out.println(new Solution().lexicalOrder(199));
    }

}
