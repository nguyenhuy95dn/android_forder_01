package com.framgia.forder.data.source.remote;

import com.framgia.forder.data.model.Domain;
import com.framgia.forder.data.model.DomainManagement;
import com.framgia.forder.data.model.User;
import com.framgia.forder.data.source.DomainDataSource;
import com.framgia.forder.data.source.remote.api.request.AddUserInDomainRequest;
import com.framgia.forder.data.source.remote.api.request.RegisterDomainRequest;
import com.framgia.forder.data.source.remote.api.response.BaseResponse;
import com.framgia.forder.data.source.remote.api.response.ChangeRuleOfUserResponse;
import com.framgia.forder.data.source.remote.api.response.DeleteDomainResponse;
import com.framgia.forder.data.source.remote.api.response.DeleteUserInDomainResponse;
import com.framgia.forder.data.source.remote.api.response.DomainManagementResponse;
import com.framgia.forder.data.source.remote.api.response.DomainResponse;
import com.framgia.forder.data.source.remote.api.response.DomainToRequestShopResponse;
import com.framgia.forder.data.source.remote.api.response.EditDomainResponse;
import com.framgia.forder.data.source.remote.api.response.RegisterDomainResponse;
import com.framgia.forder.data.source.remote.api.response.UserInDomainResponse;
import com.framgia.forder.data.source.remote.api.service.FOrderApi;
import java.util.ArrayList;
import java.util.List;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Age on 4/12/2017.
 */

public class DomainRemoteDataSource implements DomainDataSource.RemoteDataSource {
    private final FOrderApi mFOrderApi;

    public DomainRemoteDataSource(FOrderApi api) {
        mFOrderApi = api;
    }

    @Override
    public Observable<List<Domain>> getListDomain(int userId, String userEmail, String userToken) {
        return mFOrderApi.getDomains(userId, userEmail, userToken)
                .flatMap(new Func1<DomainResponse, Observable<List<Domain>>>() {
                    @Override
                    public Observable<List<Domain>> call(DomainResponse domainResponse) {
                        if (domainResponse == null || domainResponse.getDomainList() == null) {
                            return Observable.just(null);
                        }
                        List<Domain> list = new ArrayList<>();
                        for (DomainResponse.ChooseDomain chooseDomain : domainResponse
                                .getDomainList()) {
                            list.add(chooseDomain.getDomain());
                        }
                        return Observable.just(list);
                    }
                });
    }

    @Override
    public Observable<List<DomainManagement>> getListDomainManagement() {
        return mFOrderApi.getListDomainManagement()
                .flatMap(new Func1<DomainManagementResponse, Observable<List<DomainManagement>>>() {
                    @Override
                    public Observable<List<DomainManagement>> call(
                            DomainManagementResponse domainManagementResponse) {
                        if (domainManagementResponse != null) {
                            return Observable.just(
                                    domainManagementResponse.getListDomainManagement());
                        }
                        return Observable.error(new NullPointerException());
                    }
                });
    }

    @Override
    public Observable<RegisterDomainResponse> requestRegisterDomain(
            RegisterDomainRequest registerDomainRequest) {
        return mFOrderApi.requestRegisterDomain(registerDomainRequest);
    }

    @Override
    public Observable<DeleteUserInDomainResponse> requestDeleteUserInDomain(int domainId,
            int userId) {
        return mFOrderApi.requestDeleteUserInDomain(domainId, userId);
    }

    @Override
    public Observable<DeleteDomainResponse> requestDeleteDomain(int domainId) {
        return mFOrderApi.requestDeleteDomain(domainId);
    }

    @Override
    public Observable<EditDomainResponse> requestEditDdomain(int domainId, String name,
            String status) {
        return mFOrderApi.requestEditDomain(domainId, name, status);
    }

    @Override
    public Observable<List<User>> getListUserInDomain(int domainId) {
        return mFOrderApi.getListUserInDomain(domainId)
                .flatMap(new Func1<UserInDomainResponse, Observable<List<User>>>() {
                    @Override
                    public Observable<List<User>> call(UserInDomainResponse response) {
                        if (response != null) {
                            return Observable.just(response.getUserList());
                        }
                        return Observable.error(new NullPointerException());
                    }
                });
    }

    @Override
    public Observable<ChangeRuleOfUserResponse> requestChangeRuleOfUserInDomain(int domainId,
            int userId, String role) {
        return mFOrderApi.requestChangeRuleOfUserInDomain(domainId, userId, role);
    }

    @Override
    public Observable<BaseResponse> requestAddUserInDomain(
            AddUserInDomainRequest addUserInDomainRequest) {
        return mFOrderApi.requestAddUserInDomain(addUserInDomainRequest);
    }

    @Override
    public Observable<List<User>> getListUserToAddInDomain(int domainId) {
        return mFOrderApi.getListUserToAddInDomain(domainId)
                .flatMap(new Func1<UserInDomainResponse, Observable<List<User>>>() {
                    @Override
                    public Observable<List<User>> call(UserInDomainResponse response) {
                        if (response != null) {
                            return Observable.just(response.getUserList());
                        }
                        return Observable.error(new NullPointerException());
                    }
                });
    }

    @Override
    public Observable<List<DomainToRequestShopResponse.DomainToRequest>> getListDomainToRequestShop(
            int shopId) {
        return mFOrderApi.getListDomainToRequestShop(shopId)
                .flatMap(
                        new Func1<DomainToRequestShopResponse,
                                Observable<List<DomainToRequestShopResponse.DomainToRequest>>>() {
                            @Override
                            public Observable<List<DomainToRequestShopResponse.DomainToRequest>>
                            call(
                                    DomainToRequestShopResponse response) {
                                if (response != null) {
                                    return Observable.just(response.getDomainToRequestList());
                                }
                                return Observable.error(new NullPointerException());
                            }
                        });
    }
}
