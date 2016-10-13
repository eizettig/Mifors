package com.zettig.mifors.Model;

import com.zettig.mifors.Model.Data.Group;

import java.util.List;

import rx.Observable;

public interface Model {
    Observable<List<Group>> getItemList(String url);
}
