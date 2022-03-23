package common;

import java.util.ArrayList;
import java.util.List;

public class NestedInteger {

    private Integer value;
    private List<NestedInteger> list;
    public NestedInteger() { this(new ArrayList<>()); }
    public NestedInteger(Integer v) { this(v, null); }
    public NestedInteger(List<NestedInteger> l) { this(null, l); }
    public NestedInteger(Integer v, List<NestedInteger> l) { value = v; list = l; }

    public boolean isInteger() { return value != null; }
    public Integer getInteger() { return value; }
    public List<NestedInteger> getList() { return list; }

    public void setInteger(int value) { this.value = value; list = null; }
    public void add(NestedInteger ni) { this.list.add(ni); }

    @Override
    public String toString() { return isInteger() ? value.toString() : list.toString(); }
}
