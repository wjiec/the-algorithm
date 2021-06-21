package problem.p1013partitionarrayintothreepartswithequalsum;

/**
 * 1013. Partition Array Into Three Parts With Equal Sum
 *
 * https://leetcode-cn.com/problems/partition-array-into-three-parts-with-equal-sum/
 *
 * Given an array of integers arr, return true if we
 * can partition the array into three non-empty parts with equal sums.
 *
 * Formally, we can partition the array if we can find indexes i + 1 < j with
 * (arr[0] + arr[1] + ... + arr[i]
 *      == arr[i + 1] + arr[i + 2] + ... + arr[j - 1]
 *      == arr[j] + arr[j + 1] + ... + arr[arr.length - 1])
 */

public class Solution {

    public boolean canThreePartsEqualSum(int[] arr) {
        int sum = 0, avg, cnt = 3;
        for (var n : arr) sum += n;
        if (sum % 3 != 0) return false;

        avg = sum / 3;
        for (var n : arr) {
            avg -= n;
            if (avg == 0 && cnt >= 1) {
                avg = sum / 3;
                cnt--;
            }
        }
        return avg == sum / 3 && cnt == 0;
    }

    public static void main(String[] args) {
        assert new Solution().canThreePartsEqualSum(new int[]{0,0,0,0});
        assert new Solution().canThreePartsEqualSum(new int[]{0,2,1,-6,6,-7,9,1,2,0,1});
        assert !new Solution().canThreePartsEqualSum(new int[]{0,2,1,-6,6,7,9,-1,2,0,1});
        assert new Solution().canThreePartsEqualSum(new int[]{3,3,6,5,-2,2,5,1,-9,4});
    }

}
