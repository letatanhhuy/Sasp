package com.luksprog.drawermultipane;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.luksprog.drawermultipane.dummy.DummyContent;

/**
 * An activity representing a list of Items. This activity has different
 * presentations for handset and tablet-size devices. On handsets, the activity
 * presents a list of items, which when touched, lead to a
 * {@link AssignmentDetailActivity} representing item details. On tablets, the
 * activity presents the list of items and item details side-by-side using two
 * vertical panes.
 * <p>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link AssignmentListFragment} and the item details (if present) is a
 * {@link AssignmentDetailFragment}.
 * <p>
 * This activity also implements the required
 * {@link AssignmentListFragment.Callbacks} interface to listen for item
 * selections.
 */
public class AssignmentsListActivity extends ActionBarActivity implements
		AssignmentListFragment.Callbacks {

	/**
	 * Whether or not the activity is in two-pane mode, i.e. running on a tablet
	 * device.
	 */
	private boolean mTwoPane;
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private CharSequence mTitle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item_list);

		if (findViewById(R.id.item_detail_container) != null) {
			// The detail container view will be present only in the
			// large-screen layouts (res/values-large and
			// res/values-sw600dp). If this view is present, then the
			// activity should be in two-pane mode.
			mTwoPane = true;

			// In two-pane mode, list items should be given the
			// 'activated' state when touched.
			((AssignmentListFragment) getSupportFragmentManager()
					.findFragmentById(R.id.item_list))
					.setActivateOnItemClick(true);
		}

		// setup the DrawerLayout
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.left_drawer);

		// Set the adapter for the list view
		mDrawerList.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, DummyContent.CATEGORIES));
		// Set the list's click listener
		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
		setTitle(DummyContent.CATEGORIES[(((AssignmentListFragment) getSupportFragmentManager()
				.findFragmentById(R.id.item_list)))
				.getCurrentDisplayedCategory()]);
	}

	/**
	 * Callback method from {@link AssignmentListFragment.Callbacks} indicating
	 * that the item with the given ID was selected.
	 */
	@Override
	public void onItemSelected(int position) {
		// handle the detail part of the multi-pane layout.
		AssignmentListFragment alf = (AssignmentListFragment) getSupportFragmentManager()
				.findFragmentById(R.id.item_list);
		if (mTwoPane) {
			AssignmentDetailFragment adf = (AssignmentDetailFragment) getSupportFragmentManager()
					.findFragmentById(R.id.item_detail_container);
			// In two-pane mode, show the detail view in this activity by
			// adding or replacing the detail fragment using a
			// fragment transaction.
			if (adf == null) {
				adf = new AssignmentDetailFragment();
				Bundle arguments = new Bundle();
				arguments
						.putInt(AssignmentDetailFragment.ARG_ITEM_ID, position);
				arguments.putInt(AssignmentDetailFragment.CAT_ITEM,
						alf.getCurrentDisplayedCategory());
				adf.setArguments(arguments);
				getSupportFragmentManager().beginTransaction()
						.add(R.id.item_detail_container, adf).commit();
			} else {
				adf.updateDetails(alf.getCurrentDisplayedCategory(), position);
			}
		} else {
			// In single-pane mode, simply start the detail activity
			// for the selected item ID.
			// in order to show details in the AssignmentsDetails activity we
			// pass the category position and the position of the Assignment in
			// the list of assignments for that category
			Intent detailIntent = new Intent(this,
					AssignmentDetailActivity.class);
			detailIntent.putExtra(AssignmentDetailFragment.ARG_ITEM_ID,
					position);
			detailIntent.putExtra(AssignmentDetailFragment.CAT_ITEM,
					alf.getCurrentDisplayedCategory());
			startActivity(detailIntent);
		}

	}

	private class DrawerItemClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			changeCategory(position);
		}
	}

	/** Swaps fragments in the main content view */
	private void changeCategory(int position) {
		// retrieve the assignments ListFragment and update it with the data
		// required for this category. We DON't need a new fragment, we just
		// update
		// the initial fragment to show updated data for the new category
		AssignmentListFragment alf = (AssignmentListFragment) getSupportFragmentManager()
				.findFragmentById(R.id.item_list);
		if (alf != null && alf.isInLayout()
				&& alf.getCurrentDisplayedCategory() != position) {
			alf.updateDataForCategory(position);
			setTitle(DummyContent.CATEGORIES[alf.getCurrentDisplayedCategory()]);
		}
		mDrawerLayout.closeDrawer(mDrawerList);
	}

	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getSupportActionBar().setTitle(mTitle);
	}

}
