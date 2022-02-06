package lcp.p29SNJvJP;

/**
 * LCP 29. 乐团站位
 *
 * https://leetcode-cn.com/problems/SNJvJP/
 *
 * 某乐团的演出场地可视作 num * num 的二维矩阵 grid（左上角坐标为 [0,0])，每个位置站有一位成员。乐团共有 9 种乐器，乐器编号为 1~9，每位成员持有 1 个乐器。
 *
 * 为保证声乐混合效果，成员站位规则为：自 grid 左上角开始顺时针螺旋形向内循环以 1，2，...，9 循环重复排列。
 *
 * 请返回位于场地坐标 [Xpos,Ypos] 的成员所持乐器编号。
 */

public class Solution {

    public int orchestraLayout(int num, int xPos, int yPos) {
        long circle = Math.min(Math.min(xPos, num - xPos - 1), Math.min(yPos, num - yPos - 1));
        long length = num - 2 * circle - 1;
        long count = (long) num * num - (length + 1) * (length + 1);
        if (xPos == circle && yPos < num - circle - 1) {
            count += yPos - circle + 1;
        } else if (yPos == num - circle - 1 && xPos < num - circle - 1) {
            count += length + xPos - circle + 1;
        } else if (xPos == num - circle - 1 && yPos > circle) {
            count += 2 * length + num - circle - yPos;
        } else {
            count += 3 * length + num - circle - xPos;
        }

        return (int) (count % 9 == 0 ? 9 : count % 9);
    }

    public static void main(String[] args) {
        assert new Solution().orchestraLayout(3, 0, 2) == 3;
        assert new Solution().orchestraLayout(4, 1, 2) == 5;
        assert new Solution().orchestraLayout(4, 1, 2) == 5;
    }

}
