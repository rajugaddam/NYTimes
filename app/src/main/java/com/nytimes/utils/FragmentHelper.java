package com.nytimes.utils;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;




public class FragmentHelper {

    /**
     * @param fragment
     * @param container
     * @param fm
     */
    public static void addAndInitFragmentWithBackStack(Fragment fragment, int container, FragmentManager fm) {
        if(fm.findFragmentByTag(fragment.getClass().getSimpleName())==null) {
            FragmentTransaction ft = fm.beginTransaction();
            ft.add(container, fragment, fragment.getClass().getSimpleName());
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ft.addToBackStack(fragment.getClass().getSimpleName());
            ft.commit();
        }
    }
}
