package daily.d210511p1734decodexoredpermutation;

import common.Checker;

/**
 * 1734. Decode XORed Permutation
 *
 * https://leetcode-cn.com/problems/decode-xored-permutation/
 *
 * There is an integer array perm that is a permutation of the first n positive integers, where n is always odd.
 *
 * It was encoded into another integer array encoded of length n - 1, such that encoded[i] = perm[i] XOR perm[i + 1].
 * For example, if perm = [1,3,2], then encoded = [2,1].
 *
 * Given the encoded array, return the original array perm. It is guaranteed that the answer exists and is unique.
 */

public class Solution {

    public int[] decode(int[] encoded) {
        int sz = encoded.length + 1, xorPerm = 0, xorEnc = 0;
        for (int i = 1; i <= sz; i++) {
            xorPerm ^= i;
            if (i % 2 == 1 && i < sz - 1) {
                xorEnc ^= encoded[i];
            }
        }

        var perm = new int[sz];
        perm[0] = xorPerm ^ xorEnc;
        for (int i = 0; i < sz - 1; i++) {
            perm[i + 1] = perm[i] ^ encoded[i];
        }

        return perm;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().decode(new int[]{3,1}), new int[]{1,2,3});
        assert Checker.check(new Solution().decode(new int[]{6,5,4,6}), new int[]{2,4,1,5,3});
    }

}
