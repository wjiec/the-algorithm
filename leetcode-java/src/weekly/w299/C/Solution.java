package weekly.w299.C;

public class Solution {

    public int maximumsSplicedArray(int[] nums1, int[] nums2) {
        int s1 = 0, s2 = 0, n = nums1.length;
        for (var x : nums1) s1 += x;
        for (var x : nums2) s2 += x;

        int[] diff = new int[n];
        for (int i = 0; i < n; i++) diff[i] = nums1[i] - nums2[i];

        int sum1 = 0, min1 = 0, max1 = 0;
        for (int i = 0; i < n; i++) {
            sum1 += diff[i];
            max1 = Math.max(sum1 - min1, max1);
            min1 = Math.min(min1, sum1);
        }

        int sum2 = 0, min2 = 0, max2 = 0;
        for (int i = 0; i < n; i++) {
            sum2 += diff[i];
            min2 = Math.min(sum2 - max2, min2);
            max2 = Math.max(max2, sum2);
        }
        return Math.max(s2 + max1, s1 - min2);
    }

    public static void main(String[] args) {
        assert new Solution().maximumsSplicedArray(new int[]{60,60,60}, new int[]{10,90,10}) == 210;
        assert new Solution().maximumsSplicedArray(new int[]{20,40,20,70,30}, new int[]{50,20,50,40,20}) == 220;
    }

}
