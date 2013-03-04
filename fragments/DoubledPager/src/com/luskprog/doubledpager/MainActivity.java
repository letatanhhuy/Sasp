package com.luskprog.doubledpager;

import java.util.ArrayList;
import java.util.List;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.View;

public class MainActivity extends FragmentActivity {

	private boolean mIsLandscape = false;
	private ViewPager mPortraitPager, mLandscapePager;
	private List<String> mBookPages = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// see the orientation of the screen
		mIsLandscape = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE ? true
				: false;
		mPortraitPager = (ViewPager) findViewById(R.id.portraitPager);
		mLandscapePager = (ViewPager) findViewById(R.id.landscapePager);
		CustomAdapter adapter = new CustomAdapter(getSupportFragmentManager());
		// setup the right ViewPager based on the orientation and kill the
		// other.
		if (mIsLandscape) {
			mPortraitPager.setVisibility(View.GONE);
			mLandscapePager.setAdapter(adapter);
			mPortraitPager.setAdapter(null);
		} else {
			mLandscapePager.setVisibility(View.GONE);
			mPortraitPager.setAdapter(adapter);
			mLandscapePager.setAdapter(null);
		}
		// simulate the data
		for (int i = 0; i < 30; i++) {
			mBookPages.add("Book Page no." + i);
		}
	}

	/**
	 * Our custom adapter class that will be used.
	 * 
	 * @author Luksprog
	 * 
	 */
	private class CustomAdapter extends FragmentStatePagerAdapter {

		public CustomAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			Bundle args = new Bundle();
			// get the fragment but also pass the position to them
			if (mIsLandscape) {
				DoublePageFragment doublePageFragment = new DoublePageFragment();
				args.putInt("position", position);
				doublePageFragment.setArguments(args);
				return doublePageFragment;
			} else {
				PageFragment pageFragment = new PageFragment();
				args.putInt("position", position);
				pageFragment.setArguments(args);
				return pageFragment;

			}
		}

		@Override
		public int getCount() {
			int count = mBookPages.size();
			if (mIsLandscape) {
				int tmp = count;
				count = tmp / 2;
				// add one if we have an odd number of pages(for the double page
				// situation)
				if (tmp % 2 != 0) {
					count++;
				}
			}
			return count;

		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/**
	 * Method simulating the retrieving of data for our single page fragment.
	 * 
	 * @param position
	 *            the position for which we need data(this is a simple value so
	 *            we can use it directly)
	 * @return the data
	 */
	public CharSequence getSinglePage(int position) {
		return mBookPages.get(position);
	}

	/**
	 * Method simulating the retrieving of data for our double page fragment.
	 * 
	 * @param position
	 *            the position for which we need data(for double page we need to
	 *            calculate the right position)
	 * @return the data
	 */
	public String[] getDoublePage(int position) {
		// careful as we could have an odd number of pages
		String[] data = new String[2];
		data[0] = mBookPages.get(position * 2);
		data[1] = mBookPages.get(position * 2 + 1);
		return data;
	}

}
