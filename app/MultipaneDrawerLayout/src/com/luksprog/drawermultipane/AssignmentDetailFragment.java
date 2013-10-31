package com.luksprog.drawermultipane;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.luksprog.drawermultipane.dummy.DummyContent;

/**
 * A fragment representing a single Item detail screen. This fragment is either
 * contained in a {@link AssignmentsListActivity} in two-pane mode (on tablets)
 * or a {@link AssignmentDetailActivity} on handsets.
 */
public class AssignmentDetailFragment extends Fragment {
	/**
	 * The fragment argument representing the item ID that this fragment
	 * represents.
	 */
	public static final String ARG_ITEM_ID = "item_id";

	public static final String CAT_ITEM = "category_id";

	/**
	 * The dummy content this fragment is presenting.
	 */
	private DummyContent.Assigment mItem;

	/**
	 * Mandatory empty constructor for the fragment manager to instantiate the
	 * fragment (e.g. upon screen orientation changes).
	 */
	public AssignmentDetailFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments().containsKey(ARG_ITEM_ID)) {
			// Load the dummy content specified by the fragment
			// arguments. In a real-world scenario, use a Loader
			// to load content from a content provider.
			int categoryIndex = getArguments().getInt(CAT_ITEM);
			int assignmentIndex = getArguments().getInt(ARG_ITEM_ID);
			mItem = DummyContent.ITEM_MAP.get(
					DummyContent.CATEGORIES[categoryIndex])
					.get(assignmentIndex);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_item_detail,
				container, false);
		// Show the dummy content as text in a TextView.
		if (mItem != null) {
			((TextView) rootView.findViewById(R.id.item_detail))
					.setText("Details for the selected item:"
							+ mItem.content
							+ " from category "
							+ DummyContent.CATEGORIES[getArguments().getInt(
									CAT_ITEM)] + "!");
		}
		return rootView;
	}

	/**
	 * In order for the AssigmentsDetailFragment to show the details data it
	 * needs to know the category and the item in that category that was
	 * selected.
	 * 
	 * @param category
	 *            the position of the category in the DummyContent.CATEGORIES
	 *            array
	 * @param position
	 *            the position of the Assignment in the above category list of
	 *            assignments.
	 */
	public void updateDetails(int category, int position) {
		mItem = DummyContent.ITEM_MAP.get(DummyContent.CATEGORIES[category])
				.get(position);
		((TextView) getView().findViewById(R.id.item_detail))
				.setText("Details for the selected item:" + mItem.content
						+ " from category " + DummyContent.CATEGORIES[category]
						+ "!");
	}
}
