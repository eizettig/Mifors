package com.zettig.mifors.View;


import com.zettig.mifors.Model.Data.Group;
import java.util.List;

public interface View {
    void showList(List<Group> list);
    void showError(String error);
}
