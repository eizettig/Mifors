package com.zettig.mifors.Model.Data;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

@Root(name = "E1WPW01",strict = false)
public class Group implements Comparable<Group>{

    @Element(name = "WARENGR")
    private int itemId;

    @Path("E1WPW02")
    @Element(name = "BEZEICH")
    private String itemName;

    @Path("E1WPW02")
    @Element(name = "HIERARCHIE")
    private int hierarchy;

    @Path("E1WPW02")
    @Element(name = "VERKNUEPFG",required = false)
    private int itemParent;

    public int getItemId() {
        return itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public int getHierarchy() {
        return hierarchy;
    }

    public int getItemParent() {
        return itemParent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Group group = (Group) o;

        if (itemId != group.itemId) return false;
        return itemName != null ? itemName.equals(group.itemName) : group.itemName == null;

    }

    @Override
    public int hashCode() {
        int result = itemId;
        result = 31 * result + (itemName != null ? itemName.hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(Group o) {
        return 0;
    }
}
