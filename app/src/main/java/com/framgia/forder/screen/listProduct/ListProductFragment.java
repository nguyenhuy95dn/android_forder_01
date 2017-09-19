package com.framgia.forder.screen.listProduct;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import com.framgia.forder.R;
import com.framgia.forder.data.model.Product;
import com.framgia.forder.data.source.CategoryRepository;
import com.framgia.forder.data.source.DomainRepository;
import com.framgia.forder.data.source.ProductRepository;
import com.framgia.forder.data.source.UserRepository;
import com.framgia.forder.data.source.local.DomainLocalDataSource;
import com.framgia.forder.data.source.local.ProductLocalDataSource;
import com.framgia.forder.data.source.local.UserLocalDataSource;
import com.framgia.forder.data.source.local.realm.RealmApi;
import com.framgia.forder.data.source.local.sharedprf.SharedPrefsApi;
import com.framgia.forder.data.source.local.sharedprf.SharedPrefsImpl;
import com.framgia.forder.data.source.remote.CategoryRemoteDataSource;
import com.framgia.forder.data.source.remote.DomainRemoteDataSource;
import com.framgia.forder.data.source.remote.ProductRemoteDataSource;
import com.framgia.forder.data.source.remote.UserRemoteDataSource;
import com.framgia.forder.data.source.remote.api.service.FOrderServiceClient;
import com.framgia.forder.databinding.FragmentListproductBinding;
import com.framgia.forder.screen.listProduct.adapter.ListProductAdapter;
import com.framgia.forder.screen.main.LoadCartListener;
import com.framgia.forder.utils.navigator.Navigator;
import com.framgia.forder.widgets.dialog.DialogManager;
import java.util.ArrayList;
import java.util.List;

/**
 * ProductList Screen.
 */
public class ListProductFragment extends Fragment {

    private ListProductContract.ViewModel mViewModel;
    private LoadCartListener mLoadCartListener;

    public static ListProductFragment newInstance() {
        return new ListProductFragment();
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<Product> products = new ArrayList<>();
        ListProductAdapter productAdapter = new ListProductAdapter(getActivity(), products);
        Navigator navigator = new Navigator(getParentFragment());
        DialogManager dialogManager = new DialogManager(getActivity());

        String[] priceArray = getResources().getStringArray(R.array.price_fillter);
        String[] sortByArray = getResources().getStringArray(R.array.sort_by);
        List<String> categories = new ArrayList<>();
        ArrayAdapter<String> adapterPrice =
                new ArrayAdapter<>(getActivity(), R.layout.spinner_item_text_color_default,
                        priceArray);
        ArrayAdapter<String> adapterCategory =
                new ArrayAdapter<>(getActivity(), R.layout.spinner_item_text_color_default,
                        categories);
        ArrayAdapter<String> adapterSort =
                new ArrayAdapter<>(getActivity(), R.layout.spinner_item_text_color_default,
                        sortByArray);

        adapterPrice.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        adapterCategory.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        adapterSort.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        mViewModel = new ListProductViewModel(productAdapter, navigator, dialogManager,
                mLoadCartListener, adapterPrice, adapterCategory, adapterSort);
        RealmApi realmApi = new RealmApi();

        SharedPrefsApi prefsApi = new SharedPrefsImpl(getActivity());
        DomainRepository domainRepository =
                new DomainRepository(new DomainRemoteDataSource(FOrderServiceClient.getInstance()),
                        new DomainLocalDataSource(prefsApi, new UserLocalDataSource(prefsApi)));
        int currentDomainId = domainRepository.getCurrentDomain().getId();

        ProductRepository productRepository = new ProductRepository(
                new ProductRemoteDataSource(FOrderServiceClient.getInstance()),
                new ProductLocalDataSource(realmApi), currentDomainId);
        UserRepository userRepository =
                new UserRepository(new UserRemoteDataSource(FOrderServiceClient.getInstance()),
                        new UserLocalDataSource(prefsApi));
        CategoryRepository categoryRepository = new CategoryRepository(
                new CategoryRemoteDataSource(FOrderServiceClient.getInstance()));

        ListProductContract.Presenter presenter =
                new ListProductPresenter(mViewModel, productRepository, userRepository,
                        domainRepository, categoryRepository);
        mViewModel.setPresenter(presenter);

        FragmentListproductBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_listproduct, container, false);
        binding.setViewModel((ListProductViewModel) mViewModel);
        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        mViewModel.onStart();
    }

    @Override
    public void onStop() {
        mViewModel.onStop();
        super.onStop();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mLoadCartListener = (LoadCartListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement LoadCartListener");
        }
    }
}
