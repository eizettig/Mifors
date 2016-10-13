package com.zettig.mifors.View;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.*;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.zettig.mifors.ActivityCallback;
import com.zettig.mifors.R;

public class UrlFragment extends Fragment {

    EditText urlEditText;
    Button button;
    ActivityCallback callback;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.url_fragment,container,false);
        callback = (ActivityCallback)getActivity();
        urlEditText = (EditText)view.findViewById(R.id.urlTextInput);
        urlEditText.setSelection(urlEditText.getText().length());
        button = (Button)view.findViewById(R.id.loadButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.showList(urlEditText.getText().toString());
            }
        });
        return view;
    }

}
