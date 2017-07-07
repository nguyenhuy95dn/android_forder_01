package com.framgia.forder.data.source;

import com.framgia.forder.data.model.Shop;
import com.framgia.forder.data.model.ShopInDomain;
import com.framgia.forder.data.model.ShopManagement;
import com.framgia.forder.data.model.User;
import com.framgia.forder.data.source.remote.ShopRemoteDataSource;
import com.framgia.forder.data.source.remote.api.request.ApplyShopToDomainRequest;
import com.framgia.forder.data.source.remote.api.request.LeaveShopToDomainRequest;
import com.framgia.forder.data.source.remote.api.request.RegisterShopRequest;
import com.framgia.forder.data.source.remote.api.request.UpdateShopRequest;
import com.framgia.forder.data.source.remote.api.response.BaseResponse;
import com.framgia.forder.data.source.remote.api.response.DeleteShopInDomainResponse;
import com.framgia.forder.data.source.remote.api.response.RegisterShopResponse;
import com.framgia.forder.data.source.remote.api.response.ShopManagementResponse;
import java.util.List;
import rx.Observable;

/**
 * Created by Duong on 4/18/2017.
 */

public class ShopRepository {
    private final ShopDataSource.RemoteDataSource mRemoteDataSource;

    public ShopRepository(ShopRemoteDataSource remoteDataSource) {
        mRemoteDataSource = remoteDataSource;
    }

    public Observable<List<Shop>> getListShop(int domainId) {
        return mRemoteDataSource.getListShop(domainId);
    }

    public Observable<List<Shop>> getRelativeShops(int domainId) {
        return mRemoteDataSource.getRelativeShops(domainId);
    }

    public Observable<List<ShopManagement>> getListShopManagement(int userId) {
        return mRemoteDataSource.getListShopManagement(userId);
    }

    public Observable<ShopManagementResponse> requestApplyShopToDomain(
            ApplyShopToDomainRequest applyShopToDomainRequest) {
        return mRemoteDataSource.requestApplyShopToDomain(applyShopToDomainRequest);
    }

    public Observable<ShopManagementResponse> requestLeaveShopFromDomain(
            LeaveShopToDomainRequest leaveShopToDomainRequest) {
        return mRemoteDataSource.requestLeaveShopFromDomain(leaveShopToDomainRequest);
    }

    public Observable<RegisterShopResponse> requestRegisterShop(
            RegisterShopRequest registerShopRequest) {
        return mRemoteDataSource.requestRegisterShop(registerShopRequest);
    }

    public Observable<List<User>> getListManagerOfShop(int shopId) {
        return mRemoteDataSource.getListManagerOfShop(shopId);
    }

    public Observable<RegisterShopResponse> updateShop(int shopId,
            UpdateShopRequest updateShopRequest) {
        return mRemoteDataSource.updateShop(shopId, updateShopRequest);
    }

    public Observable<List<ShopInDomain>> getListShopInDomain(int domainId) {
        return mRemoteDataSource.getListShopInDomain(domainId);
    }

    public Observable<DeleteShopInDomainResponse> requestDeleteShopInDomain(int domainId,
            int shopId) {
        return mRemoteDataSource.requestDeleteShopInDomain(domainId, shopId);
    }

    public Observable<BaseResponse> requestChangeStatusShopManagement(int shopId, String status) {
        return mRemoteDataSource.requestChangeStatusShopManagement(shopId, status);
    }
}
