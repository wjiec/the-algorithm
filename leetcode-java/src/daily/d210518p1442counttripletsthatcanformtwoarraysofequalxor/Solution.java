package daily.d210518p1442counttripletsthatcanformtwoarraysofequalxor;

/**
 * 1442. Count Triplets That Can Form Two Arrays of Equal XOR
 *
 * https://leetcode-cn.com/problems/count-triplets-that-can-form-two-arrays-of-equal-xor/
 *
 * Given an array of integers arr.
 *
 * We want to select three indices i, j and k where (0 <= i < j <= k < arr.length).
 *
 * Let's define a and b as follows:
 *
 * a = arr[i] ^ arr[i + 1] ^ ... ^ arr[j - 1]
 * b = arr[j] ^ arr[j + 1] ^ ... ^ arr[k]
 * Note that ^ denotes the bitwise-xor operation.
 *
 * Return the number of triplets (i, j and k) Where a == b.
 */

public class Solution {

    public int countTriplets(int[] arr) {
        int sz = arr.length, rs = 0;
        int[] xors = new int[sz + 1];
        for (int i = 0; i < sz; i++) {
            xors[i + 1] = xors[i] ^ arr[i];
        }

        for (int i = 0; i < sz; i++) {
            for (int j = i + 1; j < sz; j++) {
                if (xors[i] == xors[j + 1]) {
                    rs += j - i;
                }
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        assert new Solution().countTriplets(new int[]{2,3}) == 0;
        assert new Solution().countTriplets(new int[]{2,3,1,6,7}) == 4;
        assert new Solution().countTriplets(new int[]{1,1,1,1,1}) == 10;
        assert new Solution().countTriplets(new int[]{1,3,5,7,9}) == 3;
        assert new Solution().countTriplets(new int[]{7,11,12,9,5,2,7,17,22}) == 8;
    }

}
