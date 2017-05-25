package com.framgia.forder.data.source.remote;

import com.framgia.forder.data.model.Product;
import com.framgia.forder.data.model.Shop;
import com.framgia.forder.data.model.ShopManagement;
import com.framgia.forder.data.model.User;
import com.framgia.forder.data.source.ShopDataSource;
import com.framgia.forder.data.source.remote.api.request.ApplyShopToDomainRequest;
import com.framgia.forder.data.source.remote.api.request.LeaveShopToDomainRequest;
import com.framgia.forder.data.source.remote.api.request.RegisterShopRequest;
import com.framgia.forder.data.source.remote.api.request.UpdateShopRequest;
import com.framgia.forder.data.source.remote.api.response.ManagerResponse;
import com.framgia.forder.data.source.remote.api.response.ProductResponse;
import com.framgia.forder.data.source.remote.api.response.RegisterShopResponse;
import com.framgia.forder.data.source.remote.api.response.ShopManagementResponse;
import com.framgia.forder.data.source.remote.api.response.ShopResponse;
import com.framgia.forder.data.source.remote.api.service.FOrderApi;
import java.util.ArrayList;
import java.util.List;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Duong on 4/19/2017.
 */

public class ShopRemoteDataSource extends BaseRemoteDataSource
        implements ShopDataSource.RemoteDataSource {

    public ShopRemoteDataSource(FOrderApi fOrderApi) {
        super(fOrderApi);
    }

    @Override
    public Observable<List<Shop>> getListShop(int domainId) {
        final List<Shop> shops = new ArrayList<>();
        // todo edit later
        return mFOrderApi.getListShop(domainId)
                .flatMap(new Func1<ProductResponse, Observable<List<Shop>>>() {
                    @Override
                    public Observable<List<Shop>> call(ProductResponse productResponse) {
                        if (productResponse != null) {
                            for (Product product : productResponse.getListProduct()) {
                                //TODO remove when server update API
                                product.getShop()
                                        .setUser(new User(1, "Trần Đức Quốc",
                                                "tran.duc.quoc@framgia.com"));
                                shops.add(product.getShop());
                            }
                            return Observable.just(shops);
                        }
                        return Observable.error(new NullPointerException());
                    }
                });
    }

    @Override
    public Observable<List<Shop>> getRelativeShops(int domainId) {
        // todo edit later
        return mFOrderApi.getRelativeShops(domainId)
                .flatMap(new Func1<ProductResponse, Observable<List<Shop>>>() {
                    @Override
                    public Observable<List<Shop>> call(ProductResponse productResponse) {
                        if (productResponse != null) {
                            List<Shop> shops = new ArrayList<>();
                            for (Product product : productResponse.getListProduct()) {
                                //TODO remove when server update API
                                product.getShop()
                                        .setUser(new User(1, "Trần Đức Quốc",
                                                "tran.duc.quoc@framgia.com"));
                                shops.add(product.getShop());
                            }
                            return Observable.just(shops);
                        }
                        return Observable.error(new NullPointerException());
                    }
                });
    }

    @Override
    public Observable<List<ShopManagement>> getListShopManagement(int userId) {
        return mFOrderApi.getLitShopManagement(userId)
                .flatMap(new Func1<ShopManagementResponse, Observable<List<ShopManagement>>>() {
                    @Override
                    public Observable<List<ShopManagement>> call(
                            ShopManagementResponse shopResponse) {
                        if (shopResponse != null) {
                            return Observable.just(shopResponse.getListShopManagement());
                        }
                        return Observable.error(new NullPointerException());
                    }
                });
    }

    @Override
    public Observable<ShopManagementResponse> requestApplyShopToDomain(
            ApplyShopToDomainRequest applyShopToDomainRequest) {
        return mFOrderApi.requestApplyShopToDomain(applyShopToDomainRequest);
    }

    @Override
    public Observable<ShopManagementResponse> requestLeaveShopFromDomain(
            LeaveShopToDomainRequest leaveShopToDomainRequest) {
        return mFOrderApi.requestLeaveShopFromDomain(leaveShopToDomainRequest);
    }

    @Override
    public Observable<RegisterShopResponse> requestRegisterShop(
            RegisterShopRequest registerShopRequest) {
        return mFOrderApi.requestRegisterShop(registerShopRequest);
    }

    @Override
    public Observable<List<User>> getListManagerOfShop(int shopId) {
        return mFOrderApi.getListManagerOfShop(shopId)
                .flatMap(new Func1<ManagerResponse, Observable<List<User>>>() {
                    @Override
                    public Observable<List<User>> call(ManagerResponse managerResponse) {
                        if (managerResponse != null) {
                            return Observable.just(managerResponse.getListUser());
                        }
                        return Observable.error(new NullPointerException());
                    }
                });
    }

    @Override
    public Observable<ShopResponse> updateShop(UpdateShopRequest updateShopRequest) {
        return mFOrderApi.updateShop(updateShopRequest);
    }
}
