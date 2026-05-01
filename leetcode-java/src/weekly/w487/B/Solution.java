package weekly.w487.B;

/**
 * Q2. Final Element After Subarray Deletions
 *
 * https://leetcode.cn/contest/weekly-contest-487/problems/final-element-after-subarray-deletions/
 *
 * You are given an integer array nums.
 *
 * Two players, Alice and Bob, play a game in turns, with Alice playing first.
 *
 * In each turn, the current player chooses any subarray nums[l..r] such that r - l + 1 < m,
 * where m is the current length of the array.
 *
 * The selected subarray is removed, and the remaining elements are concatenated to form the new array.
 *
 * The game continues until only one element remains.
 *
 * Alice aims to maximize the final element, while Bob aims to minimize it.
 * Assuming both play optimally, return the value of the final remaining element.
 */

public class Solution {

    @SuppressWarnings("DuplicatedCode")
    private static class SegTree {
        private record Item(int mxi, int mxv, int mni, int mnv) {
            public static Item valueOf(int i, int v) { return new Item(i, v, i, v); }
            public static Item merge(Item a, Item b) {
                return new Item(
                    a.mxv > b.mxv ? a.mxi : b.mxi, Math.max(a.mxv, b.mxv),
                    a.mnv < b.mnv ? a.mni : b.mni, Math.min(a.mnv, b.mnv)
                );
            }
        }

        private final int n;
        private final Item[] tree;
        public SegTree(int[] nums) {
            n = nums.length;
            tree = new Item[2 << (32 - Integer.numberOfLeadingZeros(n - 1))];
            build(nums, 1, 0, n - 1);
        }

        public Item query(int l, int r) { return query(1, 0, n - 1, l, r); }
        private Item query(int p, int l, int r, int ql, int qr) {
            if (ql <= l && r <= qr) return tree[p];

            int mid = l + (r - l) / 2;
            if (qr <= mid) return query(p * 2, l, mid, ql, qr);
            if (mid < ql) return query(p * 2 + 1, mid + 1, r, ql, qr);

            Item lv = query(p * 2, l, mid, ql, qr);
            Item rv = query(p * 2 + 1, mid + 1, r, ql, qr);
            return Item.merge(lv, rv);
        }

        private void build(int[] nums, int p, int l, int r) {
            if (l == r) { tree[p] = Item.valueOf(l, nums[l]); return; }

            int mid = l + (r - l) / 2;
            build(nums, p * 2, l, mid);
            build(nums, p * 2 + 1, mid + 1, r);
            tree[p] = Item.merge(tree[p * 2], tree[p * 2 + 1]);
        }
    }

    public int finalElement(int[] nums) {
        // 一次移除一段子数组, Alice 需要最大化剩下的元素, Bob 则是最小化剩下的元素
        //  - Alice 最佳的操作方式是一次性删除所有的, 最好只剩下最大的元素
        //  - Bob 最佳的操作方式是一次性删除所有最大的元素, 最好只剩下最小的元素
        return dfs(new SegTree(nums), 0, 0, nums.length - 1);
    }

    private int dfs(SegTree st, int i, int l, int r) {
        if (l == r) return st.query(l, r).mxv;

        var f = st.query(l, r);
        if (i % 2 == 0) {
            if (f.mxi == l || f.mxi == r) return f.mxv;
            return Math.max(dfs(st, i + 1, l, f.mxi), dfs(st, i + 1, f.mxi, r));
        }

        if (f.mni == l || f.mni == r) return f.mnv;
        return Math.min(dfs(st, i + 1, l, f.mni), dfs(st, i + 1, f.mni, r));
    }

    private static class Optimization {
        public int finalElement(int[] nums) {
            // Alice 可以通过剩下 a[0] 和 a[n - 1] 来直接结束游戏
            //  - 如果最终的答案不是 a[0] 和 a[n - 1] 而是中间的某一个数
            //  - 那么 bob 可以直接把这个留下的数删除掉
            //  - 所以 Alice 一定无法保留下中间的那个数
            return Math.max(nums[0], nums[nums.length - 1]);
        }
    }

    public static void main(String[] args) {
        assert new Solution().finalElement(new int[]{4, 12, 18, 10}) == 10;
        assert new Solution().finalElement(new int[]{3, 1}) == 3;
        assert new Solution().finalElement(new int[]{4, 16, 3, 9}) == 9;
    }

}
