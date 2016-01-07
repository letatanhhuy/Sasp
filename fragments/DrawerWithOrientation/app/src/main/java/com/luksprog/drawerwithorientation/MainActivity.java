package com.luksprog.drawerwithorientation;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;


/**
 * This activity handles both the DrawerLayout with a Fragment as the menu and also the change in
 * orientation.
 */
public class MainActivity extends ActionBarActivity implements MenuFragment.OnMenuSelectionListener {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private Toolbar mToolbar;
    private boolean mIsLandscape;
    private ContentFragment mContentFrag;
    private MenuFragment mMenuFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_main);
        // see in which orientation we are, tru for landscape
        mIsLandscape = getResources().getBoolean(R.bool.current);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        if (mIsLandscape) {
            handleMenu();
            handleContent();
        } else {
            handleMenu();
            handleContent();
            setupDrawer();
        }
    }

    /**
     * This method handles the addition of the MenuFragment(the one used as the menu in the
     * DrawerLayout or as a item list on the left in landscape orientation). It first checks to
     * see if the FragmenrManager doesn't already have a reference to the MenuFragment. If it has
     * it doesn't do anything as the system will take care of placing the fragment in its
     * position. If it doesn't have a reference then it needs to add it to the proper FrameLayout
     * (which will be either in the DrawerLayout or in the content layout)
     */
    private void handleMenu() {
        mMenuFrag = (MenuFragment) getSupportFragmentManager().findFragmentByTag(MenuFragment.TAG);
        if (mMenuFrag == null) {
            mMenuFrag = new MenuFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.menu, mMenuFrag,
                    MenuFragment.TAG).commit();
        }
        // set the activity as the listener for the MenuFragment(this could be done in the
        // onAttach() callback of the MenuFragment as well)
        mMenuFrag.setMenuListener(this);
    }

    /**
     * Does the same work for the ContentFragment as the handleMenu() function does for MenuFragment
     */
    private void handleContent() {
        mContentFrag = (ContentFragment) getSupportFragmentManager()
                .findFragmentByTag
                        (ContentFragment.TAG);
        if (mContentFrag == null) {
            mContentFrag = new ContentFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.content, mContentFrag,
                    ContentFragment.TAG).commit();
        }
    }

    private void setupDrawer() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                mToolbar, R.string.drawer_open, R.string.drawer_close) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred but only if we are in
        // the portrait orientation
        if (!mIsLandscape) {
            mDrawerToggle.syncState();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles but only if we are in
        // the portrait orientation
        if (!mIsLandscape) {
            mDrawerToggle.onConfigurationChanged(newConfig);
        }
    }

    @Override
    public void onMenuItemSelected(long menuItemId) {
        Toast.makeText(this, "Menu item with id " + menuItemId + " was selected", Toast.LENGTH_SHORT).show();
        if (!mIsLandscape) {
            // close the drawer but only if we are in portrait
            mDrawerLayout.closeDrawers();
        }
        // do work?
    }
}
