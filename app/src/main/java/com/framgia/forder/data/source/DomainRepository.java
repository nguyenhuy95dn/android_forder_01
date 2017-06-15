package com.framgia.forder.data.source;

import com.framgia.forder.data.model.Domain;
import com.framgia.forder.data.model.DomainManagement;
import com.framgia.forder.data.model.User;
import com.framgia.forder.data.source.remote.api.request.RegisterDomainRequest;
import com.framgia.forder.data.source.remote.api.response.DeleteDomainResponse;
import com.framgia.forder.data.source.remote.api.response.DeleteUserInDomainResponse;
import com.framgia.forder.data.source.remote.api.response.EditDomainResponse;
import com.framgia.forder.data.source.remote.api.response.RegisterDomainResponse;
import java.util.List;
import rx.Observable;

/**
 * Created by Age on 4/12/2017.
 */

public class DomainRepository {

    private final DomainDataSource.RemoteDataSource mRemoteDataSource;
    private final DomainDataSource.LocalDataSource mLocalDataSource;

    public DomainRepository(DomainDataSource.RemoteDataSource remoteDataSource,
            DomainDataSource.LocalDataSource localDataSource) {
        mRemoteDataSource = remoteDataSource;
        mLocalDataSource = localDataSource;
    }

    public Observable<List<Domain>> getListDomain() {
        User user = mLocalDataSource.getUser();
        int userId = user.getId();
        String userEmail = user.getEmail();
        String userToken = user.getToken();
        return mRemoteDataSource.getListDomain(userId, userEmail, userToken);
    }

    public void saveCurrentDomain(Domain domain) {
        mLocalDataSource.saveCurrentDomain(domain);
    }

    public Domain getCurrentDomain() {
        return mLocalDataSource.getCurrentDomain();
    }

    public User getUser() {
        return mLocalDataSource.getUser();
    }

    public Observable<List<DomainManagement>> getListDomainManagement() {
        return mRemoteDataSource.getListDomainManagement();
    }

    public Observable<RegisterDomainResponse> requestRegisterDomain(
            RegisterDomainRequest registerDomainRequest) {
        return mRemoteDataSource.requestRegisterDomain(registerDomainRequest);
    }

    public Observable<DeleteUserInDomainResponse> requestDeleteUserInDomain(int domainId,
            int userId) {
        return mRemoteDataSource.requestDeleteUserInDomain(domainId, userId);
    }

    public Observable<DeleteDomainResponse> requestDeleteDomain(int domainId) {
        return mRemoteDataSource.requestDeleteDomain(domainId);
    }

    public Observable<EditDomainResponse> requestEditDomain(int domainId, String name,
            String status) {
        return mRemoteDataSource.requestEditDdomain(domainId, name, status);
    }
}
