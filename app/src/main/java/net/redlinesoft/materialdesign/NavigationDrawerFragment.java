package net.redlinesoft.materialdesign;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup; 


/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationDrawerFragment extends Fragment {


    public NavigationDrawerFragment() {
        // Required empty public constructor
    }
    
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    
    private View containerView;
    
    private boolean mLearnDrawer;
    private boolean mFromSaveInstance;
    
    static  final String PREF_FILENAME = "testpref";
    static  final String KEY_DRAWER = "userkey";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLearnDrawer = Boolean.valueOf(readFromPreferences(getActivity(),KEY_DRAWER,"false"));
        
        if (savedInstanceState!=null) {
            mFromSaveInstance=true;
        }
        
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
    }

    public void setUp(int fragmentId, DrawerLayout drawerLayout, Toolbar toolbar) {
        
        containerView=getActivity().findViewById(fragmentId);
        
        mDrawerLayout = drawerLayout;
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(),drawerLayout, toolbar,R.string.drawer_open, R.string.drawer_close){

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                
                if (!mLearnDrawer) {
                    mLearnDrawer=true;
                    saveToPreferences(getActivity(),KEY_DRAWER,mLearnDrawer+"");
                }
                
                getActivity().invalidateOptionsMenu();
                
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                mLearnDrawer=false;
                saveToPreferences(getActivity(),KEY_DRAWER,mLearnDrawer+"");
                getActivity().invalidateOptionsMenu();
            }
            
        };
        
        if (mLearnDrawer && !mFromSaveInstance) {
            mDrawerLayout.openDrawer(containerView);
        }
        
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();    
            }
        });
    
    }
    
    public static void saveToPreferences(Context context, String preferenceName, String preferenceValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILENAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(preferenceName,preferenceValue);
        editor.apply();
    }

    public static String readFromPreferences(Context context, String preferenceName, String defaultValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILENAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString(preferenceName,defaultValue); 
    }
}
