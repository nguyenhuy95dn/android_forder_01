package com.framgia.forder.data.source;

import com.framgia.forder.data.model.Shop;
import com.framgia.forder.data.model.ShopManagement;
import com.framgia.forder.data.model.User;
import com.framgia.forder.data.source.remote.api.request.ApplyShopToDomainRequest;
import com.framgia.forder.data.source.remote.api.request.LeaveShopToDomainRequest;
import com.framgia.forder.data.source.remote.api.request.RegisterShopRequest;
import com.framgia.forder.data.source.remote.api.response.RegisterShopResponse;
import com.framgia.forder.data.source.remote.api.response.ShopManagementResponse;
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

        Observable<ShopManagementResponse> requestApplyShopToDomain(
                ApplyShopToDomainRequest applyShopToDomainRequest);

        Observable<ShopManagementResponse> requestLeaveShopFromDomain(
                LeaveShopToDomainRequest leaveShopToDomainRequest);

        Observable<RegisterShopResponse> requestRegisterShop(
                RegisterShopRequest registerShopRequest);

        Observable<List<User>> getListManagerOfShop(int shopId);
    }
}
