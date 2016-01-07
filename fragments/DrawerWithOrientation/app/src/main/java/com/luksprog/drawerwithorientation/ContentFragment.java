package com.luksprog.drawerwithorientation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Dummy fragment to simulate some content.
 *
 * @author Luksprog
 */
public class ContentFragment extends Fragment {

    public static final String TAG = "content_frag";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.f_content, container, false);
    }
}
