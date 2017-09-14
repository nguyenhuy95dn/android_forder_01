package com.framgia.forder.data.source;

import com.framgia.forder.data.model.Shop;
import com.framgia.forder.data.model.ShopInDomain;
import com.framgia.forder.data.model.ShopManagement;
import com.framgia.forder.data.model.User;
import com.framgia.forder.data.source.remote.ShopRemoteDataSource;
import com.framgia.forder.data.source.remote.api.request.ApplyShopToDomainRequest;
import com.framgia.forder.data.source.remote.api.request.RegisterShopRequest;
import com.framgia.forder.data.source.remote.api.request.UpdateShopRequest;
import com.framgia.forder.data.source.remote.api.response.BaseResponse;
import com.framgia.forder.data.source.remote.api.response.CheckFollowShopResponse;
import com.framgia.forder.data.source.remote.api.response.DeleteShopInDomainResponse;
import com.framgia.forder.data.source.remote.api.response.RegisterShopResponse;
import com.framgia.forder.data.source.remote.api.response.ShopRequestResponse;
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

    public Observable<BaseResponse> requestApplyShopToDomain(
            ApplyShopToDomainRequest applyShopToDomainRequest) {
        return mRemoteDataSource.requestApplyShopToDomain(applyShopToDomainRequest);
    }

    public Observable<BaseResponse> requestLeaveShopFromDomain(int domainId, int shopId,
            boolean leaveDomain) {
        return mRemoteDataSource.requestLeaveShopFromDomain(domainId, shopId, leaveDomain);
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

    public Observable<List<ShopRequestResponse.ShopContain>> getListShopRequest(int domainId) {
        return mRemoteDataSource.getListShopRequest(domainId);
    }

    public Observable<BaseResponse> requestToAcceptRejectShopToDomain(int domainId, int shopId,
            String status) {
        return mRemoteDataSource.requestToAcceptRejectShopToDomain(domainId, shopId, status);
    }

    public Observable<BaseResponse> requestFollowShop(int shopId, String type) {
        return mRemoteDataSource.requestFollowShop(shopId, type);
    }

    public Observable<BaseResponse> requestRateShop(int shopId, float ratePoint) {
        return mRemoteDataSource.requestRateShop(shopId, ratePoint);
    }

    public Observable<CheckFollowShopResponse> checkFollowShop(int shopId) {
        return mRemoteDataSource.checkFollowShop(shopId);
    }
}
