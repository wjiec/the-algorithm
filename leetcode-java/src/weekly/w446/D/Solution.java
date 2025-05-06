package weekly.w446.D;

/**
 * 3525. Find X Value of Array II
 *
 * https://leetcode.cn/contest/weekly-contest-446/problems/find-x-value-of-array-ii/
 *
 * You are given an array of positive integers nums and a positive integer k.
 *
 * You are also given a 2D array queries, where queries[i] = [indexi, valuei, starti, xi].
 *
 * You are allowed to perform an operation once on nums, where you can remove any suffix
 * from nums such that nums remains non-empty.
 *
 * The x-value of nums for a given x is defined as the number of ways to perform this
 * operation so that the product of the remaining elements leaves a remainder of x modulo k.
 *
 * For each query in queries you need to determine the x-value of nums for xi after performing the following actions:
 *
 * Update nums[indexi] to valuei. Only this step persists for the rest of the queries.
 * Remove the prefix nums[0..(starti - 1)] (where nums[0..(-1)] will be used to represent the empty prefix).
 * Return an array result of size queries.length where result[i] is the answer for the ith query.
 *
 * A prefix of an array is a subarray that starts from the beginning of the array and extends to any point within it.
 *
 * A suffix of an array is a subarray that starts at any point within the array and extends to the end of the array.
 *
 * Note that the prefix and suffix to be chosen for the operation can be empty.
 *
 * Note that x-value has a different definition in this version.
 */

public class Solution {

    private static class SegmentTree {
        private static class Item {
            private final int mul;
            private final int[] mod;
            private Item(int mul, int[] mod) {
                this.mul = mul;
                this.mod = mod;
            }
            public Item(int v, int k) {
                mul = v % k;
                mod = new int[k];
                mod[mul] = 1;
            }

            // 合并两个相邻段的数据
            public static Item merge(Item a, Item b, int k) {
                int[] mod = new int[k];
                System.arraycopy(a.mod, 0, mod, 0, k);
                for (int bx = 0; bx < k; bx++) {
                    mod[(bx * a.mul) % k] += b.mod[bx];
                }

                return new Item((a.mul * b.mul) % k, mod);
            }
        }

        private final int n, k;
        private final Item[] tree;
        public SegmentTree(int[] nums, int k) {
            this.n = nums.length; this.k = k;
            tree = new Item[2 << (32 - Integer.numberOfLeadingZeros(n - 1))];
            build(nums, 1, 0, n - 1);
        }

        public void update(int i, int value) { update(1, 0, n - 1, i, value); }
        private void update(int node, int l, int r, int i, int value) {
            if (l == r) { tree[node] = new Item(value, k); return; }

            int mid = l + (r - l) / 2;
            if (i <= mid) update(node * 2, l, mid, i, value);
            else update(node * 2 + 1, mid + 1, r, i, value);
            maintain(node);
        }

        public Item query(int ql, int qr) { return query(1, 0, n - 1, ql, qr); }
        private Item query(int node, int l, int r, int ql, int qr) {
            if (ql <= l && r <= qr) return tree[node];

            int mid = l + (r - l) / 2;
            if (qr <= mid) return query(node * 2, l, mid, ql, qr);
            if (ql > mid) return query(node * 2 + 1, mid + 1, r, ql, qr);

            // 横跨两个子树
            Item lv = query(node * 2, l, mid, ql, qr);
            Item rv = query(node * 2 + 1, mid + 1, r, ql, qr);
            return Item.merge(lv, rv, k);
        }

        private void build(int[] nums, int node, int l, int r) {
            // 叶子节点
            if (l == r) { tree[node] = new Item(nums[l], k); return; }

            int mid = l + (r - l) / 2;
            build(nums, node * 2, l, mid);
            build(nums, node * 2 + 1, mid + 1, r);
            maintain(node);
        }

        private void maintain(int node) {
            tree[node] = Item.merge(tree[node * 2], tree[node * 2 + 1], k);
        }
    }

    // query = [index, value, start, x]
    //  - 将位置 index 修改为 value
    //  - 找到 [start, n) 中所有的子数组使得乘积对 k 取模为 x 的数量
    public int[] resultArray(int[] nums, int k, int[][] queries) {
        // 分治: 找到 [l, r] 范围内的子数组的乘积对 k 取模为 x 的数量
        //  - 对 [l, m], [m + 1, r] 进行分治, 其中 m = (l + r) / 2
        //  - m + 1 位置在以 l 为起始的值实际上等于 mul(nums[l, m]) * nums(m + 1)
        // 使用线段树进行求解
        SegmentTree st = new SegmentTree(nums, k);
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int index = queries[i][0], value = queries[i][1];
            int start = queries[i][2], x = queries[i][3];

            st.update(index, value);
            ans[i] = st.query(start, nums.length - 1).mod[x];
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
