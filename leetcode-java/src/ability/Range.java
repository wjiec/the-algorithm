package ability;

// 区间相关操作
public class Range {

    // 求两个区间(不包括端点)相交的长度
    public static long overlap(long al, long ar, long bl, long br) {
        return Math.max(Math.min(ar, br) - Math.max(al, bl), 0);
    }

}
