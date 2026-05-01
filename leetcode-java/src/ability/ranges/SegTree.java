package ability.ranges;

public class SegTree<T extends SegTree.Maintainable<T>> {
    public interface Maintainable<T extends Maintainable<T>> {
        T maintain(T value);
    }

    public static void main(String[] args) {
    }
}

