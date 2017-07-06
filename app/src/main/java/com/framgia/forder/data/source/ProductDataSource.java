package com.framgia.forder.data.source;

import android.support.annotation.NonNull;
import com.framgia.forder.data.model.Cart;
import com.framgia.forder.data.model.Comment;
import com.framgia.forder.data.model.Product;
import com.framgia.forder.data.source.local.realm.BaseLocalDataSource;
import com.framgia.forder.data.source.remote.api.request.CommentRequest;
import com.framgia.forder.data.source.remote.api.request.OrderRequest;
import com.framgia.forder.data.source.remote.api.request.RegisterProductRequest;
import com.framgia.forder.data.source.remote.api.request.UpdateProductRequest;
import com.framgia.forder.data.source.remote.api.response.BaseResponse;
import com.framgia.forder.data.source.remote.api.response.OrderCartResponse;
import com.framgia.forder.data.source.remote.api.response.RegisterProductResponse;
import com.framgia.forder.data.source.remote.api.response.UpdateProductResponse;
import java.util.List;
import rx.Observable;

public interface ProductDataSource {
    interface LocalDataSource extends BaseLocalDataSource {

        Observable<Void> addShoppingCard(@NonNull Product product, @NonNull int domainId);

        Observable<Void> deleteShoppingCard(@NonNull int productId, @NonNull int domainId);

        Observable<Void> editNoteShoppingCart(@NonNull int productId, @NonNull int domainId,
                String note);

        Observable<Void> upQuantity(@NonNull int productId, @NonNull int domainId);

        Observable<Void> downQuantity(@NonNull int productId, @NonNull int domainId);

        Observable<Integer> getNumberItem(@NonNull int domainId);

        Observable<Double> getTotalPrice(@NonNull int domainId);

        Observable<List<Cart>> getAllShoppingCart(int domainId);

        Observable<Void> removeAllOrder(@NonNull int domainId);

        Observable<Void> removeOrderOneShop(@NonNull int shopId, @NonNull int domainId);
    }

    interface RemoteDataSource {
        Observable<List<Product>> getListProduct(int domainId);

        Observable<List<Product>> getListProductInShop(int shopId, int domainId);

        Observable<OrderCartResponse> orderProduct(int domainId, OrderRequest orderRequest);

        Observable<List<Comment>> getListCommentInProduct(int productId, int domainId);

        Observable<BaseResponse> sendComment(CommentRequest request);

        Observable<List<Product>> getListProductInShopInformation(int shopId);

        Observable<RegisterProductResponse> requestRegisterProduct(
                RegisterProductRequest registerProductRequest);

        Observable<UpdateProductResponse> requestUpdateProduct(int productId,
                UpdateProductRequest updateProductRequest);

        Observable<BaseResponse> requestDeleteProductInShop(int shopId);
    }
}
