package com.framgia.forder.screen.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.framgia.forder.R;
import com.framgia.forder.utils.navigator.Navigator;

import static com.framgia.forder.screen.main.MainViewModel.Tab.TAB_CART;
import static com.framgia.forder.screen.main.MainViewModel.Tab.TAB_HOME;
import static com.framgia.forder.screen.main.MainViewModel.Tab.TAB_NOTIFICATION;
import static com.framgia.forder.screen.main.MainViewModel.Tab.TAB_PROFILE;
import static com.framgia.forder.screen.main.MainViewModel.Tab.TAB_SEARCH;

/**
 * Created by tri on 13/04/2017.
 */

public class FragmentContainer extends Fragment {

    private static final String EXTRA_TAB_FOOTER = "EXTRA_TAB_FOOTER";

    public static FragmentContainer newInstance(@MainViewModel.Tab int tab) {
        FragmentContainer fragment = new FragmentContainer();
        Bundle bundle = new Bundle();
        bundle.putInt(EXTRA_TAB_FOOTER, tab);
        fragment.setArguments(bundle);
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_container, container, false);
        Navigator navigator = new Navigator(this);
        @MainViewModel.Tab int tab = getArguments().getInt(EXTRA_TAB_FOOTER);
        switch (tab) {
            case TAB_HOME:
                //TODO new Fragment Home
                break;
            case TAB_SEARCH:
                //TODO new Fragment Search
                break;
            case TAB_CART:
                //TODO new Fragment Card
                break;
            case TAB_NOTIFICATION:
                //TODO new Fragment Notifications
                break;
            case TAB_PROFILE:
                //TODO new Fragment Profile
                break;
            default:
                break;
        }
        return view;
    }
}
