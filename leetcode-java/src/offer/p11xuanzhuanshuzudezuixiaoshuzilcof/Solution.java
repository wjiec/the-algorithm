package offer.p11xuanzhuanshuzudezuixiaoshuzilcof;

/**
 * 剑指 Offer 11. 旋转数组的最小数字
 *
 * https://leetcode-cn.com/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof/
 *
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。
 * 例如，数组 [3,4,5,1,2] 为 [1,2,3,4,5] 的一个旋转，该数组的最小值为1。  
 */

public class Solution {

    public int minArray(int[] numbers) {
        for (int i = 1, l = numbers.length; i < l; i++) {
            if (numbers[i] < numbers[i - 1]) return numbers[i];
        }
        return numbers[0];
    }

    public static void main(String[] args) {
        assert new Solution().minArray(new int[]{3,4,5,1,2}) == 1;
        assert new Solution().minArray(new int[]{2,2,2,0,1}) == 0;
    }

}
