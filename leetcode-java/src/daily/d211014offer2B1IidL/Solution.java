package daily.d211014offer2B1IidL;

/**
 * 剑指 Offer II 069. 山峰数组的顶部
 *
 * https://leetcode-cn.com/problems/B1IidL/
 *
 * 符合下列属性的数组 arr 称为 山峰数组（山脉数组） ：
 *
 * arr.length >= 3
 * 存在 i（0 < i < arr.length - 1）使得：
 * arr[0] < arr[1] < ... arr[i-1] < arr[i]
 * arr[i] > arr[i+1] > ... > arr[arr.length - 1]
 * 给定由整数组成的山峰数组 arr ，返回任何满足 arr[0] < arr[1] < ... arr[i - 1] < arr[i] > arr[i + 1] > ... > arr[arr.length - 1] 的下标 i ，即山峰顶部。
 */

public class Solution {

    public int peakIndexInMountainArray(int[] arr) {
        int l = 1, r = arr.length - 2, ans = 0;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] > arr[mid + 1]) {
                ans = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().peakIndexInMountainArray(new int[]{0,1,0}) == 1;
        assert new Solution().peakIndexInMountainArray(new int[]{1,3,5,4,2}) == 2;
        assert new Solution().peakIndexInMountainArray(new int[]{0,10,5,2}) == 1;
        assert new Solution().peakIndexInMountainArray(new int[]{3,4,5,1}) == 2;
        assert new Solution().peakIndexInMountainArray(new int[]{24,69,100,99,79,78,67,36,26,19}) == 2;
    }

}
