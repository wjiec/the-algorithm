package season.spring2022.p1;

public class Solution {

    private int ans;

    public int perfectMenu(int[] materials, int[][] cookbooks, int[][] attribute, int limit) {
        dfs(materials, cookbooks, attribute, limit, 0, 0, 0);
        return ans == 0 ? -1 : ans;
    }

    private void dfs(int[] materials, int[][] cookbooks, int[][] attribute, int limit, int i, int x, int y) {
        if (i == cookbooks.length) {
            if (y >= limit && x > ans) ans = x;
            return;
        }

        // 不做这道菜
        dfs(materials, cookbooks, attribute, limit, i + 1, x, y);

        // 做这道菜
        int[] copy = new int[materials.length];
        System.arraycopy(materials, 0, copy, 0, materials.length);

        boolean can = true;
        int[] cookbook = cookbooks[i];
        for (int j = 0; j < cookbook.length; j++) {
            if (materials[j] < cookbook[j]) {
                can = false;
                break;
            }
            materials[j] -= cookbook[j];
        }

        // 可以做这个菜
        if (can) {
            dfs(materials, cookbooks, attribute, limit, i + 1, x + attribute[i][0], y + attribute[i][1]);
        }
        System.arraycopy(copy, 0, materials, 0, materials.length);
    }

    public static void main(String[] args) {
    }

}
