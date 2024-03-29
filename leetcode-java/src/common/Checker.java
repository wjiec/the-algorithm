package common;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Checker {

    public static boolean check(Object a, Object b) {
        if (a == null || b == null) return a == b;

        Class<?> ca = a.getClass(), cb = b.getClass();
        if (ca.isArray() && cb.isArray()) {
            if (Array.getLength(a) != Array.getLength(b)) {
                return false;
            }

            for (int i = 0, l = Array.getLength(a); i < l; i++) {
                if (!check(Array.get(a, i), Array.get(b, i))) {
                    return false;
                }
            }
            return true;
        }

        if (ca == Double.class && cb == Double.class) {
            double x = (double) a, y = (double) b;
            return (x > y ? x - y : y - x) < 1e-5;
        }

        return a.equals(b);
    }

    public static boolean anyOrder(Object a, Object b) {
        Class<?> ca = a.getClass(), cb = b.getClass();
        if (ca.isArray() && cb.isArray()) {
            if (Array.getLength(a) != Array.getLength(b)) {
                return false;
            }

            List<Object> la = new ArrayList<>(), lb = new ArrayList<>();
            for (int i = 0, l = Array.getLength(a); i < l; i++) {
                la.add(Array.get(a, i)); lb.add(Array.get(b, i));
            }
            return anyOrder(la, lb);
        }

        return a.equals(b);
    }

    public static <T extends List<?>> boolean anyOrder(T a, T b) {
        return a.size() == b.size() && a.containsAll(b);
    }

    public static <T extends List<?>> boolean anyOrder(T a, Object b) {
        return anyOrder(a, Builder.buildList(b));
    }

    public static boolean check(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        } else if (p == null || q == null) {
            return false;
        }
        return p.val == q.val && check(p.left, q.left) && check(p.right, q.right);
    }

    public static boolean check(ListNode list, int ...numbers) {
        if (list == null || numbers.length == 0) {
            return list == null && numbers.length == 0;
        }

        for (var n : numbers) {
            if (list != null && list.val == n) {
                list = list.next;
            } else {
                return false;
            }
        }

        return list == null;
    }

    public static boolean check(ListNode a, ListNode b) {
        while (a != null && b != null && a.val == b.val) {
            a = a.next; b = b.next;
        }
        return a == null && b == null;
    }

    public static void main(String[] args) {
        assert check(new int[]{1, 2, 3}, new int[]{1, 2, 3});
        assert !check(new int[]{1, 2, 3}, new int[]{1, 2, 3, 4});
        assert check(1, 1);
        assert check("hello", "hello");
        assert check(9.261, 9.261000000000001);
    }

}
