package ability;

// 区间相关操作
public class Range {

    // 求两个区间(不包括端点)相交的长度
    public static long overlap(long a1, long a2, long b1, long b2) {
        return Math.max(Math.min(a2, b2) - Math.max(a1, b1), 0);
    }

}
