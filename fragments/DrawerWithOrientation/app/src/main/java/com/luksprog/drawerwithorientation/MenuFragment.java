package com.luksprog.drawerwithorientation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * A simple dummy class to simulate a menu. This will be either(depending on device orientation) in
 * the expandable DrawerLayout or directly embedded in the activity layout
 *
 * @author Luksprog
 */
public class MenuFragment extends ListFragment {

    public static final String TAG = "menu_frag";
    private OnMenuSelectionListener mListener;

    /**
     * Interface which can be used by interested observers to be notified of menu selection
     */
    public interface OnMenuSelectionListener {

        void onMenuItemSelected(long menuItemId);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String[] menuEntries = {"Menu option 0", "Menu option 1", "Menu option 2"};
        setListAdapter(new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, menuEntries));
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        if (mListener != null) {
            mListener.onMenuItemSelected(id);
        }
    }

    public void setMenuListener(OnMenuSelectionListener listener) {
        mListener = listener;
    }
}
