package com.example.va.View;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.va.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddShopList extends Fragment {

    private OnFragmentInteractionListener mListener;
    EditText e;
    Button b;

    static AddShopList newInstance(){
        return new AddShopList();
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_add_shop_list, container, false);

        e = v.findViewById(R.id.lis);
        b = v.findViewById(R.id.snd);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = e.getText().toString();
                sendBack(input);
            }
        });

        return v;
    }

    private void sendBack(String input) {
        if (mListener != null) {
            mListener.onFragmentInteraction(input);
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(String sendBackText);

    }
}
