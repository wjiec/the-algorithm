package daily.d210712p275hindexii;

/**
 * 275. H-Index II
 *
 * https://leetcode-cn.com/problems/h-index-ii/
 *
 * Given an array of integers citations where citations[i] is the number of citations
 * a researcher received for their ith paper and citations is sorted in an ascending order,
 * return compute the researcher's h-index.
 *
 * According to the definition of h-index on Wikipedia: A scientist has an index h
 * if h of their n papers have at least h citations each,
 * and the other n − h papers have no more than h citations each.
 *
 * If there are several possible values for h, the maximum one is taken as the h-index.
 *
 * You must write an algorithm that runs in logarithmic time.
 */

public class Solution {

    public int hIndex(int[] citations) {
        int h = 0, i = citations.length - 1;
        while (i >= 0 && citations[i] > h) {
            h++;
            i--;
        }
        return h;
    }

    public static void main(String[] args) {
        assert new Solution().hIndex(new int[]{0,1,3,5,6}) == 3;
    }

}
