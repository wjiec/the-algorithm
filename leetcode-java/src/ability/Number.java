package ability;

// 数字的一些相关操作
public class Number {

    // 返回一个数有多少位
    public static int lenOf(long v) {
        int len = 0;
        for (; v != 0; v /= 10) len++;
        return len;
    }

    // 获取一个数的每位数字, 大端模式
    public static int[] digits(long v) {
        int len = lenOf(v);
        int[] digits = new int[len];
        for (int i = len - 1; i >= 0; i--, v /= 10) {
            digits[i] = (int) (v % 10);
        }
        return digits;
    }

}
