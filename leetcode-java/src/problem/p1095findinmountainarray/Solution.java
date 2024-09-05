package problem.p1095findinmountainarray;

import common.Tag;

import java.util.List;

/**
 * 1095. Find in Mountain Array
 *
 * https://leetcode.cn/problems/find-in-mountain-array/
 *
 * (This problem is an interactive problem.)
 *
 * You may recall that an array arr is a mountain array if and only if:
 *
 * arr.length >= 3
 * There exists some i with 0 < i < arr.length - 1 such that:
 * arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
 * arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
 * Given a mountain array mountainArr, return the minimum index such that
 * mountainArr.get(index) == target. If such an index does not exist, return -1.
 *
 * You cannot access the mountain array directly. You may only access the array using a MountainArray interface:
 *
 * MountainArray.get(k) returns the element of the array at index k (0-indexed).
 * MountainArray.length() returns the length of the array.
 * Submissions making more than 100 calls to MountainArray.get will be judged Wrong Answer.
 * Also, any solutions that attempt to circumvent the judge will result in disqualification.
 * @noinspection DuplicatedCode
 */

public class Solution {

    interface MountainArray {
        int get(int index);
        int length();
    }

    @Tag({"山脉数组", "二分查找"})
    public int findInMountainArray(int target, MountainArray mountainArr) {
        // 首先找到山脉数组的峰顶在什么位置
        int peak = findPeak(mountainArr, 0, mountainArr.length());
        // 然后我们尝试在左边找target的最小下标
        {
            int ans = -1, l = 0, r = peak + 1;
            while (l < r) {
                int mid = l + (r - l) / 2;
                int midValue = mountainArr.get(mid);
                if (midValue > target) r = mid;
                else if (midValue < target) l = mid + 1;
                else { ans = mid; r = mid; }
            }

            if (ans != -1) return ans;
        }

        // 左边找不到, 那么尝试去右边找
        int ans = -1, l = peak, r = mountainArr.length();
        while (l < r) {
            int mid = l + (r - l) / 2;
            int midValue = mountainArr.get(mid);
            if (midValue < target) r = mid;
            else if (midValue > target) l = mid + 1;
            else { ans = mid; r = mid; }
        }

        return ans;
    }

    private int findPeak(MountainArray mountain, int l, int r) {
        if (r - l <= 2) {
            // 此时峰值可能是 l 或者 l + 1
            int left = mountain.get(l);
            int right = mountain.get(l + 1);
            return left > right ? l : (l + 1);
        }

        int mid = l + (r - l) / 2;
        int midValue = mountain.get(mid);
        int midRight = mountain.get(mid + 1);
        // 如果 mountain[mid] < mountain[mid + 1], 则说明峰顶在 mid 右边
        if (midValue < midRight) return findPeak(mountain, mid + 1, r);

        // 如果 mountain[mid] > mountain[mid + 1], 那么峰顶可能是 mid 或在 mid 的左边
        return findPeak(mountain, l, mid + 1);
    }

    private static class RealMountainArray implements MountainArray {
        private final List<Integer> underlying;
        public RealMountainArray(List<Integer> list) { underlying = list; }
        @Override public int get(int index) { return underlying.get(index); }
        @Override public int length() { return underlying.size(); }
    }

    public static void main(String[] args) {
        assert new Solution().findPeak(new RealMountainArray(List.of(1,2,3,4,5,3,1)), 0, 7) == 4;
        assert new Solution().findPeak(new RealMountainArray(List.of(0,1,2,4,2,1)), 0, 6) == 3;
        assert new Solution().findPeak(new RealMountainArray(List.of(1,2,3,4,5,1)), 0, 6) == 4;
        assert new Solution().findPeak(new RealMountainArray(List.of(1,1,1,2,1,1,1)), 0, 7) == 3;
        assert new Solution().findPeak(new RealMountainArray(List.of(1,2,1,1,1,1,1)), 0, 7) == 1;
    }

}
