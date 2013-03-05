/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.luskprog.doubledpager;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Our fragment for when the phone is in landscape.
 * 
 * @author Luksprog
 * 
 */
public class DoublePageFragment extends Fragment {

	private boolean mIsTheProperOrientation = false;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {		
		MainActivity act = (MainActivity) getActivity();
		mIsTheProperOrientation = act.isLandscape();		
		// layout containing two TextView simulating two Pages text
		LinearLayout ll = new LinearLayout(getActivity());
		ll.setOrientation(LinearLayout.VERTICAL);
		TextView tv1 = new TextView(getActivity());		
		TextView tv2 = new TextView(getActivity());
		if (mIsTheProperOrientation) {
			// get the data array as we have to show text for two pages.
			String[] data = ((MainActivity) getActivity())
					.getDoublePage(getArguments().getInt("position"));
			tv1.setText(data[0]);
			tv2.setText(data[1]);
		}
		ll.addView(tv1);
		ll.addView(tv2);
		return ll;
	}

}
