package common;

import java.lang.reflect.Array;
import java.util.*;

public class PrettyPrinter {

    /**
     * └   ┘
     *                         <100>
     *           ┌──────────────┴─────────────────┐
     *           ----------------+------------------
     *           |                                 |
     *          200                               300
     */

    private static class TreePrinter {
        private final TreeNode tree;
        private boolean overflow = false;
        private int height = 1, width = 0;
        private final Map<TreeNode, Integer> offsets = new HashMap<>();
        public TreePrinter(TreeNode tree) {
            if ((this.tree = tree) != null) {
                walkStruct(tree, height);
                overflow = height > 10;
                height = height % 11;
                placeNode(tree, 0, (1 << height) - 1);
            }
        }

        private void walkStruct(TreeNode node, int curr) {
            if (curr > height) height = curr;
            int width = String.valueOf(node.val).length();
            if (width > this.width) this.width = width;

            if (node.left != null) walkStruct(node.left, curr + 1);
            if (node.right != null) walkStruct(node.right, curr + 1);
        }

        private void placeNode(TreeNode node, int l, int r) {
            int mid = l + (r - l) / 2;
            offsets.put(node, mid);

            if (node.left != null) placeNode(node.left, l, mid);
            if (node.right != null) placeNode(node.right, mid + 1, r);
        }

        @Override
        public String toString() {
            if (this.tree == null) return "── <null> ──";

            StringBuilder sb = new StringBuilder();
            Queue<TreeNode> queue = new ArrayDeque<>() {{ add(tree); }};
            for (int i = 0; !queue.isEmpty() && i < 11; i++) {
                StringBuilder curr = new StringBuilder();
                if (i != 0) curr.append("\n");

                for (int j = 0, n = queue.size(); j < n; j++) {
                    TreeNode node = queue.remove();

                    curr.append(middleString(node.val)).append(' ');

                    if (node.left != null) queue.add(node.left);
                    if (node.right != null) queue.add(node.right);
                }
                sb.append(curr);
            }

            if (this.overflow) sb.append("\n── <overflow> ──");
            return sb.toString();
        }

        private String middleString(int val) {
            String align = "";
            int len = String.valueOf(val).length();
            int left = (width - len) / 2; int right = width - len - left;
            if (left != right) { align = "\u2002"; }
            return String.format("<%s%s%d%s>", " ".repeat(left), align, val, " ".repeat(right));
        }
    }

    public static void println(TreeNode tree) { System.out.println(new TreePrinter(tree)); }

    private static class Indent {
        private final int count;

        public Indent() { count = 0; }
        public Indent(int n) { count = n; }
        public Indent next() { return new Indent(count + 1); }

        @Override
        public String toString() { return "  ".repeat(count); }
    }

    public static void println(Object object) {
        System.out.println(toString(object));
    }

    public static String toString(Object object) {
        if (object == null) return "null";

        Class<?> cl = object.getClass();
        if (!cl.isArray()) {
            if (object instanceof Boolean) {
                return (Boolean) object ? "T" : "F";
            } else if (object instanceof Collection<?>) {
                return toString(((Collection<?>) object).toArray(), new Indent());
            } else if (object instanceof Map<?,?>) {
                return toString((Map<?,?>) object, new Indent());
            }
            return object.toString();
        }

        return toString(object, new Indent());
    }

    private static String toString(Object object, Indent indent) {
        if (object == null) return indent.toString() + "null";
        if (object instanceof Collection<?>) {
            return toString(((Collection<?>) object).toArray(), indent);
        }

        StringBuilder sb = new StringBuilder();
        if (object.getClass().getComponentType() != null && object.getClass().getComponentType().isArray()) {
            sb.append(indent).append("[\n");
            for (int i = 0, l = Array.getLength(object); i < l; i++) {
                sb.append(toString(Array.get(object, i), indent.next()))
                    .append(i == l - 1 ? "" : ", ").append("\n");
            }
            sb.append(indent).append("]");
        } else if (object.getClass().isArray()) {
            sb.append(indent).append("[");
            for (int i = 0, l = Array.getLength(object); i < l; i++) {
                sb.append(toString(Array.get(object, i)))
                    .append(i == l - 1 ? "" : ", ");
            }
            sb.append("]");
        } else if (object instanceof Map<?,?>) {
            sb.append("{\n");
            for (var kv : ((Map<?, ?>) object).entrySet()) {
                sb.append(toString(kv.getKey(), indent.next())).append(": ");
                if (kv.getValue().getClass().isPrimitive()) {
                    sb.append(toString(kv.getValue())).append(",\n");
                } else {
                    sb.append(toString(kv.getValue(), indent.next())).append(",\n");
                }
            }
            sb.append(indent).append("}");
        } else {
            sb.append(indent).append(object);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        println(TreeNode.build());
        println(TreeNode.build(1,2,3,4,5,6,7,null,8,9,null,null,10,11,12));
    }

}
