package com.framgia.forder.data.source;

import com.framgia.forder.data.model.Shop;
import com.framgia.forder.data.model.ShopInDomain;
import com.framgia.forder.data.model.ShopManagement;
import com.framgia.forder.data.model.User;
import com.framgia.forder.data.source.remote.api.request.ApplyShopToDomainRequest;
import com.framgia.forder.data.source.remote.api.request.RegisterShopRequest;
import com.framgia.forder.data.source.remote.api.request.UpdateShopRequest;
import com.framgia.forder.data.source.remote.api.response.BaseResponse;
import com.framgia.forder.data.source.remote.api.response.DeleteShopInDomainResponse;
import com.framgia.forder.data.source.remote.api.response.RegisterShopResponse;
import java.util.List;
import rx.Observable;

/**
 * Created by Duong on 4/18/2017.
 */

public interface ShopDataSource {
    interface RemoteDataSource {
        Observable<List<Shop>> getListShop(int domainId);

        Observable<List<Shop>> getRelativeShops(int domainId);

        Observable<List<ShopManagement>> getListShopManagement(int userId);

        Observable<BaseResponse> requestApplyShopToDomain(
                ApplyShopToDomainRequest applyShopToDomainRequest);

        Observable<BaseResponse> requestLeaveShopFromDomain(int domainId, int shopId,
                boolean leaveDomain);

        Observable<RegisterShopResponse> requestRegisterShop(
                RegisterShopRequest registerShopRequest);

        Observable<List<User>> getListManagerOfShop(int shopId);

        Observable<RegisterShopResponse> updateShop(int shopId,
                UpdateShopRequest updateShopRequest);

        Observable<List<ShopInDomain>> getListShopInDomain(int domainId);

        Observable<DeleteShopInDomainResponse> requestDeleteShopInDomain(int domainId, int shopId);

        Observable<BaseResponse> requestChangeStatusShopManagement(int shopId, String status);
    }
}
