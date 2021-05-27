package common;

import java.util.*;

public abstract class NTreeNode {

    public int val;
    public List<? extends NTreeNode> children;

    public NTreeNode() {}
    public NTreeNode(int val) { this.val = val; }
    public NTreeNode(int val, List<NTreeNode> children) { this.val = val; this.children = children; }
    public String toString() { return String.format("[val = %d, children=%s]", val, children); }

    public static <T extends NTreeNode> T build(Class<T> cl, Integer... numbers) {
        if (numbers.length == 0) {
            return null;
        }

        T root = valueOf(cl, numbers[0]);
        Queue<T> nodes = new ArrayDeque<>();
        nodes.add(root);

        for (int i = 2; i < numbers.length; i++) {
            List<T> children = new ArrayList<>();
            for (; i < numbers.length && numbers[i] != null; i++) {
                children.add(valueOf(cl, numbers[i]));
            }

            var node = nodes.remove();
            if (children.size() != 0) {
                nodes.addAll(children);
                node.children = children;
            }
        }

        return root;
    }

    private static <T extends NTreeNode> T valueOf(Class<T> cl, int val) {
        try {
            var c = cl.getDeclaredConstructor(int.class);
            c.setAccessible(true);
            return c.newInstance(val);
        } catch (Throwable ignored) {}
        return null;
    }

}
