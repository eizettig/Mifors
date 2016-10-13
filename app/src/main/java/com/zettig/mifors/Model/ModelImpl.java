package com.zettig.mifors.Model;

import com.zettig.mifors.Model.Api.ApiInterface;
import com.zettig.mifors.Model.Api.ApiModule;
import com.zettig.mifors.Model.Data.Group;
import com.zettig.mifors.Model.Data.Responce;

import java.util.List;

import rx.Observable;
import rx.functions.Func1;

public class ModelImpl implements Model {

    Observable<List<Group>> list;

    @Override
    public Observable<List<Group>> getItemList(String url) {
        if (list != null){
            return list;
        } else {
            ApiInterface apiInterface = ApiModule.getApiInterface();
            list = apiInterface.getData(url)
                    .flatMap(new Func1<Responce, Observable<List<Group>>>() {
                        @Override
                        public Observable<List<Group>> call(Responce responce) {
                            return Observable.just(responce.getItemList());
                        }
                    });
            return list;
        }
    }
}
