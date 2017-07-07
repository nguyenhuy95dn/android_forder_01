package com.framgia.forder.utils.binding;

import android.databinding.BindingAdapter;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.arlib.floatingsearchview.FloatingSearchView;
import com.bumptech.glide.Glide;
import com.framgia.forder.R;
import com.framgia.forder.screen.mainpage.shop.ShopPageAdapter;

/**
 * Created by le.quang.dao on 20/03/2017.
 */

public final class BindingUtils {

    private BindingUtils() {
        // No-op
    }

    /**
     * setAdapter For RecyclerView
     */
    @BindingAdapter({ "recyclerAdapter" })
    public static void setAdapterForRecyclerView(RecyclerView recyclerView,
            RecyclerView.Adapter adapter) {
        recyclerView.setAdapter(adapter);
    }

    @BindingAdapter({ "adapter" })
    public static void setViewPagerAdapter(final ViewPager viewPager,
            final FragmentPagerAdapter adapter) {
        viewPager.setAdapter(adapter);
    }

    @BindingAdapter({ "pager" })
    public static void setViewPagerTabs(final TabLayout tabLayout, final ViewPager viewPager) {
        tabLayout.setupWithViewPager(viewPager, true);
    }

    @BindingAdapter({ "currentFragment" })
    public static void setCurrentViewPager(final ViewPager viewPager, final int currentPage) {
        viewPager.setCurrentItem(currentPage);
        viewPager.beginFakeDrag();
    }

    @BindingAdapter("imageUrl")
    public static void loadImage(ImageView imageView, String url) {
        if (TextUtils.isEmpty(url)) {
            url = "";
        }
        Uri uri = Uri.parse(url);
        Glide.with(imageView.getContext())
                .load(uri)
                .placeholder(R.drawable.ic_placeholder)
                .dontAnimate()
                .into(imageView);
    }

    @BindingAdapter({ "spinnerAdapter" })
    public static void setAdapterForSpinner(AppCompatSpinner spinner,
            ArrayAdapter<String> adapter) {
        spinner.setAdapter(adapter);
    }

    @BindingAdapter("layoutManager")
    public static void setLayoutManager(RecyclerView recyclerView,
            LayoutManagers.LayoutManagerFactory layoutManagerFactory) {
        recyclerView.setLayoutManager(layoutManagerFactory.create(recyclerView));
    }

    @BindingAdapter("errorText")
    public static void setErrorText(EditText editText, String text) {
        editText.setError(text);
    }

    @BindingAdapter("background")
    public static void setBackgroundColor(View view, int resId) {
        view.setBackgroundResource(resId);
    }

    @BindingAdapter("colorText")
    public static void setColorText(TextView view, int resId) {
        view.setTextColor(resId);
    }

    @BindingAdapter("text")
    public static void setText(TextView view, int resId) {
        view.setText(resId);
    }

    @BindingAdapter("selected")
    public static void setPosition(AppCompatSpinner compatSpinner, int position) {
        compatSpinner.setSelection(position);
    }

    @BindingAdapter("leftDrawableText")
    public static void setLeftImageText(TextView view, int resId) {
        view.setCompoundDrawablesWithIntrinsicBounds(resId, 0, 0, 0);
    }

    @BindingAdapter("onClickShowPopupMenuOrder")
    public static void showPopPupMenuOrder(final View view,
            final PopupMenu.OnMenuItemClickListener listener) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(view.getContext(), view);
                MenuInflater menuInflater = popupMenu.getMenuInflater();
                menuInflater.inflate(R.menu.menu_order, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(listener);
                popupMenu.show();
            }
        });
    }

    @BindingAdapter("adapterViewPageShop")
    public static void setAdapterViewPageHeroInfo(final ViewPager viewPage,
            final ShopPageAdapter shopPageAdapter) {
        viewPage.setAdapter(shopPageAdapter);
    }

    @BindingAdapter("showPopupMenuRuleOfOwner")
    public static void showPopPupMenuRuleOfOwner(final View view,
            final PopupMenu.OnMenuItemClickListener listener) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(view.getContext(), view);
                MenuInflater menuInflater = popupMenu.getMenuInflater();
                menuInflater.inflate(R.menu.menu_user_in_domain, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(listener);
                popupMenu.show();
            }
        });
    }

    @BindingAdapter("showPopupMenuUserAndShop")
    public static void showPopPupMenuUserAndShop(final View view,
            final PopupMenu.OnMenuItemClickListener listener) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(view.getContext(), view);
                MenuInflater menuInflater = popupMenu.getMenuInflater();
                menuInflater.inflate(R.menu.menu_domain_management, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(listener);
                popupMenu.show();
            }
        });
    }

    @BindingAdapter("showPopupMenuCancelManager")
    public static void showPopPupMenuCancelManager(final View view,
            final PopupMenu.OnMenuItemClickListener listener) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(view.getContext(), view);
                MenuInflater menuInflater = popupMenu.getMenuInflater();
                menuInflater.inflate(R.menu.menu_cancel_manager, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(listener);
                popupMenu.show();
            }
        });
    }

    @BindingAdapter("clearText")
    public static void clearTextFloatingSearchView(FloatingSearchView floatingSearchView,
            boolean b) {
        floatingSearchView.clearQuery();
    }

    @BindingAdapter("errorTextInputLayout")
    public static void setErrorTextInputLayout(final TextInputLayout textInputLayout,
            final String text) {
        textInputLayout.setError(text);
        EditText editText = textInputLayout.getEditText();
        if (editText == null) {
            return;
        }
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //No-Op
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() < 1) {
                    textInputLayout.setError(text);
                }
                if (s.length() > 0) {
                    textInputLayout.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                //No-Op
            }
        });
    }
}
