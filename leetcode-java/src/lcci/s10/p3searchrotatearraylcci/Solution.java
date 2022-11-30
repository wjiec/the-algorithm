package lcci.s10.p3searchrotatearraylcci;

/**
 * 面试题 10.03. 搜索旋转数组
 *
 * https://leetcode.cn/problems/search-rotate-array-lcci/
 *
 * 搜索旋转数组。给定一个排序后的数组，包含n个整数，但这个数组已被旋转过很多次了，次数不详。
 * 请编写代码找出数组中的某个元素，假设数组元素原先是按升序排列的。
 * 若有多个相同元素，返回索引值最小的一个。
 */

public class Solution {

    public int search(int[] arr, int target) {
        if (arr[0] == target) return 0;

        int l = 0, r = arr.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] == target) {
                while (mid > 0 && arr[mid - 1] == arr[mid]) mid--;
                return mid;
            }

            // 在 [mid, r] 这个范围内是递增的
            if (arr[mid] < arr[r]) {
                if (arr[mid] < target && target <= arr[r]) l = mid + 1;
                else r = mid - 1;
            } else if (arr[r] < arr[mid]) {
                if (arr[l] <= target && target < arr[mid]) r = mid - 1;
                else l = mid + 1;
            } else r--;
        }
        return -1;
    }

    public static void main(String[] args) {
        assert new Solution().search(new int[]{5,5,5,1,2,3,4,5}, 5) == 0;
        assert new Solution().search(new int[]{1,-2}, -2) == 1;

        assert new Solution().search(new int[]{15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14}, 5) == 8;
        assert new Solution().search(new int[]{15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14}, 11) == -1;
    }

}
