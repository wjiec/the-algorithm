package weekly.bw53.p3;

public class Solution {

    // error
    public int minimumXORSum(int[] nums1, int[] nums2) {
        int[][] dict = new int[nums1.length][nums2.length];
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                dict[i][j] = nums1[i] ^ nums2[j];
            }
        }

        return 0;
    }

    public static void main(String[] args) {
        assert new Solution().minimumXORSum(new int[]{100,26,12,62,3,49,55,77,97},
            new int[]{98,0,89,57,34,92,29,75,13}) == 200;
        assert new Solution().minimumXORSum(new int[]{1,2}, new int[]{2,3}) == 2;
        assert new Solution().minimumXORSum(new int[]{1,0,3}, new int[]{5,3,4}) == 8;
    }

}
