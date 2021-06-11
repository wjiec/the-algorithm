package common;

public class Checker {

    public static boolean check(int[] l, int[] r) {
        if (l.length != r.length) {
            return false;
        }

        for (int i = 0; i < l.length; i++) {
            if (l[i] != r[i]) {
                return false;
            }
        }
        return true;
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
        for (var n : numbers) {
            if (list != null && list.val == n) {
                list = list.next;
            } else {
                return false;
            }
        }

        return list == null;
    }

}
