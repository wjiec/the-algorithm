package daily.d210711p274hindex;

import java.util.Arrays;

/**
 * 274. H-Index
 *
 * https://leetcode-cn.com/problems/h-index/
 *
 * Given an array of integers citations where citations[i] is the number of
 * citations a researcher received for their ith paper, return compute the researcher's h-index.
 *
 * According to the definition of h-index on Wikipedia: A scientist has an index h
 * if h of their n papers have at least h citations each,
 * and the other n − h papers have no more than h citations each.
 *
 * If there are several possible values for h, the maximum one is taken as the h-index.
 */

public class Solution {

    public int hIndex(int[] citations) {
        Arrays.sort(citations);
        int h = 0, i = citations.length - 1;
        while (i >= 0 && citations[i] > h) {
            h++;
            i--;
        }
        return h;
    }

    public static void main(String[] args) {
        assert new Solution().hIndex(new int[]{3,0,6,1,5}) == 3;
    }

}
