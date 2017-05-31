package com.framgia.forder.screen.updateProduct;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
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
import com.framgia.forder.data.source.UserDataSource;
import com.framgia.forder.data.source.local.DomainLocalDataSource;
import com.framgia.forder.data.source.local.UserLocalDataSource;
import com.framgia.forder.data.source.local.sharedprf.SharedPrefsApi;
import com.framgia.forder.data.source.local.sharedprf.SharedPrefsImpl;
import com.framgia.forder.data.source.remote.CategoryRemoteDataSource;
import com.framgia.forder.data.source.remote.DomainRemoteDataSource;
import com.framgia.forder.data.source.remote.ProductRemoteDataSource;
import com.framgia.forder.data.source.remote.api.service.FOrderServiceClient;
import com.framgia.forder.databinding.FragmentUpdateProductBinding;
import com.framgia.forder.utils.navigator.Navigator;
import com.framgia.forder.widgets.dialog.DialogManager;
import java.util.ArrayList;
import java.util.List;

import static com.framgia.forder.utils.Constant.REQUEST_SELECT_IMAGE;

/**
 * UpdateProduct Screen.
 */
public class UpdateProductFragment extends Fragment {
    private static final String EXTRA_PRODUCT = "EXTRA_PRODUCT";
    private UpdateProductContract.ViewModel mViewModel;

    public static UpdateProductFragment newInstance(Product product) {
        UpdateProductFragment updateProductFragment = new UpdateProductFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(EXTRA_PRODUCT, product);
        updateProductFragment.setArguments(bundle);
        return updateProductFragment;
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupViewModel();
        setupPresenter();
        FragmentUpdateProductBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_update_product, container,
                        false);
        binding.setViewModel((UpdateProductViewModel) mViewModel);
        return binding.getRoot();
    }

    private void setupPresenter() {
        CategoryRepository categoryRepository = new CategoryRepository(
                new CategoryRemoteDataSource(FOrderServiceClient.getInstance()));
        SharedPrefsApi prefsApi = new SharedPrefsImpl(getActivity().getApplicationContext());
        UserDataSource.LocalDataSource userLocalDataSource = new UserLocalDataSource(prefsApi);

        DomainRepository domainRepository =
                new DomainRepository(new DomainRemoteDataSource(FOrderServiceClient.getInstance()),
                        new DomainLocalDataSource(prefsApi, userLocalDataSource));
        ProductRepository productRepository = new ProductRepository(
                new ProductRemoteDataSource(FOrderServiceClient.getInstance()), null);
        UpdateProductContract.Presenter presenter =
                new UpdateProductPresenter(mViewModel, categoryRepository, domainRepository,
                        productRepository);
        mViewModel.setPresenter(presenter);
    }

    private void setupViewModel() {
        Product product = (Product) getArguments().get(EXTRA_PRODUCT);
        DialogManager dialogManager = new DialogManager(getActivity());
        List<String> mCategories = new ArrayList<>();
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(getActivity(), android.R.layout.simple_selectable_list_item,
                        mCategories);
        Navigator navigatorForStartGallery = new Navigator(this);
        Navigator navigator = new Navigator(getParentFragment());
        mViewModel = new UpdateProductViewModel(getActivity(), product, dialogManager, adapter,
                navigatorForStartGallery, navigator);
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_CANCELED) {
            return;
        }
        if (requestCode == REQUEST_SELECT_IMAGE) {
            Uri selectedImage = data.getData();
            mViewModel.setImage(selectedImage.toString());
        }
    }
}
