package com.hanry.material.demo;

import java.lang.reflect.Field;
import java.util.ArrayList;

import com.hanry.material.app.ToolbarManager;
import com.hanry.materialonginger.R;
import com.hanry.material.util.ThemeUtil;
import com.hanry.material.widget.SnackBar;
import com.hanry.material.widget.TabPageIndicator;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity implements ToolbarManager.OnToolbarGroupChangedListener {

	private DrawerLayout dl_navigator;
	private FrameLayout fl_drawer;
	private ListView lv_drawer;
	private CustomViewPager vp;
	private TabPageIndicator tpi;
	
	private DrawerAdapter mDrawerAdapter;
	private PagerAdapter mPagerAdapter;
	
	private Toolbar mToolbar;
    private ToolbarManager mToolbarManager;
    private SnackBar mSnackBar;

	private Tab[] mItems = new Tab[]{Tab.PROGRESS, Tab.BUTTONS, Tab.FAB, Tab.SWITCHES, Tab.SLIDERS, Tab.SPINNERS, Tab.TEXTFIELDS, Tab.SNACKBARS, Tab.DIALOGS};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
				
		dl_navigator = (DrawerLayout)findViewById(R.id.main_dl);
		fl_drawer = (FrameLayout)findViewById(R.id.main_fl_drawer);
		lv_drawer = (ListView)findViewById(R.id.main_lv_drawer);
		mToolbar = (Toolbar)findViewById(R.id.main_toolbar);
		vp = (CustomViewPager)findViewById(R.id.main_vp);
		tpi = (TabPageIndicator)findViewById(R.id.main_tpi);
        mSnackBar = (SnackBar)findViewById(R.id.main_sn);

        mToolbarManager = new ToolbarManager(this, mToolbar, 0, R.style.ToolbarRippleStyle, R.anim.abc_fade_in, R.anim.abc_fade_out);
        mToolbarManager.setNavigationManager(new ToolbarManager.BaseNavigationManager(R.style.NavigationDrawerDrawable, this, mToolbar, dl_navigator) {
            @Override
            public void onNavigationClick() {
                if(mToolbarManager.getCurrentGroup() != 0)
                    mToolbarManager.setCurrentGroup(0);
                else
                    dl_navigator.openDrawer(Gravity.START);
            }

            @Override
            public boolean isBackState() {
                return super.isBackState() || mToolbarManager.getCurrentGroup() != 0;
            }

            @Override
            protected boolean shouldSyncDrawerSlidingProgress() {
                return super.shouldSyncDrawerSlidingProgress() && mToolbarManager.getCurrentGroup() == 0;
            }

        });
        mToolbarManager.registerOnToolbarGroupChangedListener(this);
		
		mDrawerAdapter = new DrawerAdapter();
		lv_drawer.setAdapter(mDrawerAdapter);
		
		mPagerAdapter = new PagerAdapter(getSupportFragmentManager(), mItems);
		vp.setAdapter(mPagerAdapter);
		tpi.setViewPager(vp);
		tpi.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int position) {
				mDrawerAdapter.setSelected(mItems[position]);
                mSnackBar.dismiss();
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {}
			
			@Override
			public void onPageScrollStateChanged(int state) {}
			
		});

        mDrawerAdapter.setSelected(Tab.PROGRESS);
		vp.setCurrentItem(0);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
        mToolbarManager.createMenu(R.menu.menu_main);
		return true;
	}

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        mToolbarManager.onPrepareMenu();
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.tb_contextual:
                mToolbarManager.setCurrentGroup(R.id.tb_group_contextual);
                break;
            case R.id.tb_done:
            case R.id.tb_done_all:
                mToolbarManager.setCurrentGroup(0);
                break;
        }
        return true;
    }

    @Override
    public void onToolbarGroupChanged(int oldGroupId, int groupId) {
        mToolbarManager.notifyNavigationStateChanged();
    }

    public SnackBar getSnackBar(){
        return mSnackBar;
    }

    public enum Tab {
	    PROGRESS ("Progresses"),
	    BUTTONS ("Buttons"),
        FAB ("FABs"),
	    SWITCHES ("Switches"),
        SLIDERS ("Sliders"),
        SPINNERS ("Spinners"),
	    TEXTFIELDS ("TextFields"),
	    SNACKBARS ("SnackBars"),
        DIALOGS ("Dialogs");
	    private final String name;       

	    private Tab(String s) {
	        name = s;
	    }

	    public boolean equalsName(String otherName){
	        return (otherName != null) && name.equals(otherName);
	    }

	    public String toString(){
	       return name;
	    }

	}
	
	class DrawerAdapter extends BaseAdapter implements View.OnClickListener {

		private Tab mSelectedTab;
		
		public void setSelected(Tab tab){
			if(tab != mSelectedTab){
				mSelectedTab = tab;
				notifyDataSetInvalidated();
			}
		}
		
		public Tab getSelectedTab(){
			return mSelectedTab;
		}
		
		@Override
		public int getCount() {
			return mItems.length;
		}

		@Override
		public Object getItem(int position) {
			return mItems[position];
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View v = convertView;
			if(v == null) {
                v = LayoutInflater.from(MainActivity.this).inflate(R.layout.row_drawer, null);
                v.setOnClickListener(this);
            }

            v.setTag(position);
			Tab tab = (Tab)getItem(position);
			((TextView)v).setText(tab.toString());
			
			if(tab == mSelectedTab) {
                v.setBackgroundColor(ThemeUtil.colorPrimary(MainActivity.this, 0));
                ((TextView)v).setTextColor(0xFFFFFFFF);
            }
			else {
                v.setBackgroundResource(0);
                ((TextView)v).setTextColor(0xFF000000);
            }
			
			return v;
		}

        @Override
        public void onClick(View v) {
            int position = (Integer)v.getTag();
            vp.setCurrentItem(position);
            dl_navigator.closeDrawer(fl_drawer);
        }
    }
	
	private static class PagerAdapter extends FragmentStatePagerAdapter {
		
		Fragment[] mFragments;
		Tab[] mTabs; 
				
		private static final Field sActiveField;
		
		static {
			Field f = null;
			try {
				Class<?> c = Class.forName("android.support.v4.app.FragmentManagerImpl");
				f = c.getDeclaredField("mActive");
				f.setAccessible(true);   
			} catch (Exception e) {}
			
			sActiveField = f;
		}
		
        public PagerAdapter(FragmentManager fm, Tab[] tabs) {
            super(fm);    
            mTabs = tabs;
            mFragments = new Fragment[mTabs.length];
       
            
            //dirty way to get reference of cached fragment
            try{
    			ArrayList<Fragment> mActive = (ArrayList<Fragment>)sActiveField.get(fm);
    			if(mActive != null){
    				for(Fragment fragment : mActive){
    					if(fragment instanceof ProgressFragment)
    						setFragment(Tab.PROGRESS, fragment);
    					else if(fragment instanceof ButtonFragment)
    						setFragment(Tab.BUTTONS, fragment);
                        else if(fragment instanceof FabFragment)
                            setFragment(Tab.FAB, fragment);
    					else if(fragment instanceof SwitchesFragment)
    						setFragment(Tab.SWITCHES, fragment);
                        else if(fragment instanceof SliderFragment)
                            setFragment(Tab.SLIDERS, fragment);
                        else if(fragment instanceof SpinnersFragment)
                            setFragment(Tab.SPINNERS, fragment);
    					else if(fragment instanceof TextfieldFragment)
    						setFragment(Tab.TEXTFIELDS, fragment);
    					else if(fragment instanceof SnackbarFragment)
    						setFragment(Tab.SNACKBARS, fragment);
                        else if(fragment instanceof DialogsFragment)
                            setFragment(Tab.DIALOGS, fragment);
    				}
    			}
    		}
    		catch(Exception e){}
        }
        
        private void setFragment(Tab tab, Fragment f){
        	for(int i = 0; i < mTabs.length; i++)
        		if(mTabs[i] == tab){
        			mFragments[i] = f;
        			break;
        		}
        }
        
		@Override
        public Fragment getItem(int position) {
			if(mFragments[position] == null){
	        	switch (mTabs[position]) {
					case PROGRESS:
						mFragments[position] = ProgressFragment.newInstance();
						break;
					case BUTTONS:
						mFragments[position] = ButtonFragment.newInstance();
						break;
                    case FAB:
                        mFragments[position] = FabFragment.newInstance();
                        break;
					case SWITCHES:
						mFragments[position] = SwitchesFragment.newInstance();
						break;
                    case SLIDERS:
                        mFragments[position] = SliderFragment.newInstance();
                        break;
                    case SPINNERS:
                        mFragments[position] = SpinnersFragment.newInstance();
                        break;
					case TEXTFIELDS:
						mFragments[position] = TextfieldFragment.newInstance();
						break;
					case SNACKBARS:
						mFragments[position] = SnackbarFragment.newInstance();
						break;
                    case DIALOGS:
                        mFragments[position] = DialogsFragment.newInstance();
                        break;
				}
			}
						
			return mFragments[position];		
        }
				
		@Override
		public CharSequence getPageTitle(int position) {
			return mTabs[position].toString().toUpperCase();
		}

		@Override
        public int getCount() {
            return mFragments.length;
        }
    }
}
