package com.zettig.mifors.Presenter;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.zettig.mifors.Const;
import com.zettig.mifors.Model.Data.Group;
import com.zettig.mifors.Model.Model;
import com.zettig.mifors.Model.ModelImpl;
import com.zettig.mifors.View.View;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

public class ItemListPresenter implements Presenter {
    Subscription subscription = Subscriptions.empty();
    Model model = new ModelImpl();
    View view;
    private final String TAG="Mylogs";

    public ItemListPresenter(View view) {
        this.view = view;
    }

    @Override
    public void loadData(String url) {

        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
        subscription = model.getItemList(url)
                .flatMap(new Func1<List<Group>, Observable<Group>>() {
                    @Override
                    public Observable<Group> call(List<Group> list) {
                        return Observable.from(list);
                    }
                })
                .filter(new Func1<Group, Boolean>() {
                    @Override
                    public Boolean call(Group group) {
                        return group.getItemParent()==0;
                    }
                })
                .distinct()
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Group>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof IOException){
                            view.showError("No internet connection");
                        }else {
                            view.showError("Something wrong");
                        }
                        Log.d(TAG,e.toString());
                    }

                    @Override
                    public void onNext(List<Group> list) {
                        if (list != null && !list.isEmpty()){
                            view.showList(list);
                        } else {
                            view.showError("The list is empty");
                        }
                        Log.d(TAG,"Size"+list.size());
                    }
                });
    }

    @Override
    public void onStop() {
        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
