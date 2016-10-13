package com.zettig.mifors.Model.Data;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "WPDWGR01",strict=false)
public class Responce {

    @Path("IDOC")
    @ElementList(name = "E1WPW01", inline = true)
    private List<Group> itemList;

    public List<Group> getItemList() {
        return itemList;
    }
}
