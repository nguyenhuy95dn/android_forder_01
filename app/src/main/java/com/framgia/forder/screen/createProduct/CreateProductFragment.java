package com.framgia.forder.screen.createProduct;

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
import com.framgia.forder.data.model.ShopManagement;
import com.framgia.forder.data.source.CategoryRepository;
import com.framgia.forder.data.source.DomainRepository;
import com.framgia.forder.data.source.ProductRepository;
import com.framgia.forder.data.source.UserDataSource;
import com.framgia.forder.data.source.UserRepository;
import com.framgia.forder.data.source.local.DomainLocalDataSource;
import com.framgia.forder.data.source.local.UserLocalDataSource;
import com.framgia.forder.data.source.local.sharedprf.SharedPrefsApi;
import com.framgia.forder.data.source.local.sharedprf.SharedPrefsImpl;
import com.framgia.forder.data.source.remote.CategoryRemoteDataSource;
import com.framgia.forder.data.source.remote.DomainRemoteDataSource;
import com.framgia.forder.data.source.remote.ProductRemoteDataSource;
import com.framgia.forder.data.source.remote.api.service.FOrderServiceClient;
import com.framgia.forder.databinding.FragmentCreateProductBinding;
import com.framgia.forder.utils.navigator.Navigator;
import com.framgia.forder.widgets.dialog.DialogManager;
import java.util.ArrayList;
import java.util.List;

/**
 * Createproduct Screen.
 */
public class CreateProductFragment extends Fragment {
    public static final int REQUEST_SELECT_IMAGE = 100;
    private static final String EXTRA_SHOPMANAGEMENT = "EXTRA_SHOPMANAGEMENT";

    private CreateProductContract.ViewModel mViewModel;
    private CreateProductContract.Presenter mPresenter;

    public static CreateProductFragment newInstance(ShopManagement shopManagement) {
        CreateProductFragment createProductFragment = new CreateProductFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(EXTRA_SHOPMANAGEMENT, shopManagement);
        createProductFragment.setArguments(bundle);
        return createProductFragment;
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupViewModel();
        setupPresenter();
        mViewModel.setPresenter(mPresenter);
        FragmentCreateProductBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_create_product, container,
                        false);
        binding.setViewModel((CreateProductViewModel) mViewModel);
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
        UserRepository userRepository = new UserRepository(null, new UserLocalDataSource(prefsApi));
        ProductRepository productRepository = new ProductRepository(
                new ProductRemoteDataSource(FOrderServiceClient.getInstance()), null);
        mPresenter = new CreateProductPresenter(mViewModel, categoryRepository, domainRepository,
                userRepository, productRepository);
    }

    private void setupViewModel() {
        ShopManagement shopManagement = (ShopManagement) getArguments().get(EXTRA_SHOPMANAGEMENT);
        DialogManager dialogManager = new DialogManager(getActivity());
        List<String> mCategories = new ArrayList<>();
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(getActivity(), R.layout.spinner_item_text_color_default,
                        mCategories);
        Navigator navigatorForStartGaller = new Navigator(this);
        Navigator navigator = new Navigator(getParentFragment());
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        mViewModel = new CreateProductViewModel(getActivity(), dialogManager, adapter,
                navigatorForStartGaller, navigator, shopManagement);
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
