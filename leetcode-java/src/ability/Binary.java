package ability;

import common.Checker;

// 二进制相关工具类
public class Binary {

    // 以小端模式获取一个整数的每一个二进制位
    // 二进制的偏移量与索引相对应
    public static int[] toBinary(long v) {
        int[] bits = new int[64];
        for (int i = 0; v != 0; v >>= 1, i++) {
            bits[i] = (int) (v & 1L);
        }
        return bits;
    }

    public static void main(String[] args) {
        assert Checker.check(toBinary(1), Array.make(64, 0, 1));
        assert Checker.check(toBinary(2), Array.make(64, 0, 0, 1));
        assert Checker.check(toBinary(3), Array.make(64, 0, 1, 1));
    }

}
