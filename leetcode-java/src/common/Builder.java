package common;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Builder {

    public static List<Object> buildList(Object object) {
        Class<?> cl = object.getClass();
        if (cl.isArray()) {
            Class<?> el = cl.getComponentType();
            List<Object> list = new ArrayList<>();
            for (int i = 0; i < Array.getLength(object); i++) {
                if (!el.isArray()) list.add(Array.get(object, i));
                else list.add(buildList(Array.get(object, i)));
            }
            return list;
        }
        return List.of(object);
    }

    public static void main(String[] args) {
        assert Checker.check(buildList(new int[]{1, 2, 3}), List.of(1, 2, 3));
        assert Checker.check(buildList(new int[][]{{1, 1},{2, 2}, {3, 3}}),
            List.of(List.of(1, 1), List.of(2, 2), List.of(3, 3)));
    }

}
