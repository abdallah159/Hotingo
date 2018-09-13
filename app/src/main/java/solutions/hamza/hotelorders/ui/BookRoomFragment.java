package solutions.hamza.hotelorders.ui;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import solutions.hamza.hotelorders.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookRoomFragment extends DialogFragment {


    public BookRoomFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_book_room, container, false);
    }

}
