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
import android.widget.TextView;

/**
 * Our single page fragment for when the phone is in portrait.
 * 
 * @author Luksprog
 * 
 */
public class PageFragment extends Fragment {

	private boolean mIsTheProperOrientation = false;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		MainActivity act = (MainActivity) getActivity();
		mIsTheProperOrientation = !act.isLandscape();
		TextView tv = new TextView(getActivity());
		// get the text for this page from the activity only if we are in the
		// proper orientation(portrait in this case)
		if (mIsTheProperOrientation) {
			tv.setText(((MainActivity) getActivity())
					.getSinglePage(getArguments().getInt("position")));
		}
		return tv;
	}

}
