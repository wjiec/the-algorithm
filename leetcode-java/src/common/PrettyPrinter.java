package common;

import java.lang.reflect.Array;

public class PrettyPrinter {

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
        Class<?> cl = object.getClass();
        if (!cl.isArray()) return object.toString();
        return toString(object, new Indent());
    }

    private static String toString(Object object, Indent indent) {
        StringBuilder sb = new StringBuilder();
        if (object.getClass().getComponentType().isArray()) {
            sb.append(indent).append("[\n");
            for (int i = 0, l = Array.getLength(object); i < l; i++) {
                sb.append(toString(Array.get(object, i), indent.next()))
                    .append(i == l - 1 ? "" : ", ").append("\n");
            }
            sb.append(indent).append("]");
        } else {
            sb.append(indent).append("[");
            for (int i = 0, l = Array.getLength(object); i < l; i++) {
                sb.append(toString(Array.get(object, i)))
                    .append(i == l - 1 ? "" : ", ");
            }
            sb.append("]");
        }
        return sb.toString();
    }

}
