package com.example.va;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddShopList extends Fragment{

    private FragmentAListener listner;

    EditText e;
    Button b;

    public interface FragmentAListener {
        String onInputASent();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_add_shop_list, container, false);

        e = v.findViewById(R.id.lis);
        b = v.findViewById(R.id.snd);
        Bundle bundle = new Bundle();

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = e.getText().toString();
                listner.onInputASent();
            }
        });

        return v;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof FragmentAListener) {
            listner = (FragmentAListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement FragmentAListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();

        listner = null;
    }
}
