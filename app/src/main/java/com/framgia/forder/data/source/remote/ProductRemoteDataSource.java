package com.framgia.forder.data.source.remote;

import com.framgia.forder.data.model.Comment;
import com.framgia.forder.data.model.Product;
import com.framgia.forder.data.source.ProductDataSource;
import com.framgia.forder.data.source.remote.api.request.CommentRequest;
import com.framgia.forder.data.source.remote.api.request.OrderRequest;
import com.framgia.forder.data.source.remote.api.request.RegisterProductRequest;
import com.framgia.forder.data.source.remote.api.request.UpdateProductRequest;
import com.framgia.forder.data.source.remote.api.response.BaseResponse;
import com.framgia.forder.data.source.remote.api.response.CommentResponse;
import com.framgia.forder.data.source.remote.api.response.OrderCartResponse;
import com.framgia.forder.data.source.remote.api.response.ProductResponse;
import com.framgia.forder.data.source.remote.api.response.RegisterProductResponse;
import com.framgia.forder.data.source.remote.api.response.UpdateProductResponse;
import com.framgia.forder.data.source.remote.api.service.FOrderApi;
import java.util.List;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Duong on 4/14/2017.
 */

public class ProductRemoteDataSource extends BaseRemoteDataSource
        implements ProductDataSource.RemoteDataSource {

    public ProductRemoteDataSource(FOrderApi api) {
        super(api);
    }

    @Override
    public Observable<List<Product>> getListProduct(int domainId) {
        return mFOrderApi.getListProduct(domainId)
                .flatMap(new Func1<ProductResponse, Observable<List<Product>>>() {
                    @Override
                    public Observable<List<Product>> call(ProductResponse productResponse) {
                        if (productResponse != null) {
                            return Observable.just(productResponse.getListProduct());
                        }
                        return Observable.error(new NullPointerException());
                    }
                });
    }

    @Override
    public Observable<List<Product>> getListProductByCategory(int domainId, int categoryId) {
        return mFOrderApi.getListProductByCategory(domainId, categoryId)
                .flatMap(new Func1<ProductResponse, Observable<List<Product>>>() {
                    @Override
                    public Observable<List<Product>> call(ProductResponse productResponse) {
                        if (productResponse != null) {
                            return Observable.just(productResponse.getListProduct());
                        }
                        return Observable.error(new NullPointerException());
                    }
                });
    }

    @Override
    public Observable<List<Product>> getListProductByFillter(int domainId, int categoryId,
            String priceSort, int priceFrom, int priceTo) {
        return mFOrderApi.getListProductByFillter(domainId, categoryId, priceSort, priceFrom,
                priceTo).flatMap(new Func1<ProductResponse, Observable<List<Product>>>() {
            @Override
            public Observable<List<Product>> call(ProductResponse productResponse) {
                if (productResponse != null) {
                    return Observable.just(productResponse.getListProduct());
                }
                return Observable.error(new NullPointerException());
            }
        });
    }

    @Override
    public Observable<List<Product>> getListProductInShop(int shopId, int domainId) {
        return mFOrderApi.getListProductShop(shopId, domainId)
                .flatMap(new Func1<ProductResponse, Observable<List<Product>>>() {
                    @Override
                    public Observable<List<Product>> call(ProductResponse productResponse) {
                        if (productResponse != null) {
                            return Observable.just(productResponse.getListProduct());
                        }
                        return Observable.error(new NullPointerException());
                    }
                });
    }

    @Override
    public Observable<OrderCartResponse> orderProduct(int domainId, OrderRequest orderRequest) {
        return mFOrderApi.requestOrderProduct(domainId, orderRequest);
    }

    @Override
    public Observable<List<Comment>> getListCommentInProduct(int productId, int domainId) {
        return mFOrderApi.getListCommentInProduct(productId, domainId)
                .flatMap(new Func1<CommentResponse, Observable<List<Comment>>>() {

                    @Override
                    public Observable<List<Comment>> call(CommentResponse commentResponse) {
                        if (commentResponse != null) {
                            return Observable.just(commentResponse.getCommentList());
                        }
                        return Observable.error(new NullPointerException());
                    }
                });
    }

    @Override
    public Observable<BaseResponse> sendComment(CommentRequest commentRequest) {
        return mFOrderApi.sendComment(commentRequest);
    }

    @Override
    public Observable<List<Product>> getListProductInShopInformation(int shopId) {
        return mFOrderApi.getListProductShopInformation(shopId)
                .flatMap(new Func1<ProductResponse, Observable<List<Product>>>() {
                    @Override
                    public Observable<List<Product>> call(ProductResponse productResponse) {
                        if (productResponse != null) {
                            return Observable.just(productResponse.getListProduct());
                        }
                        return Observable.error(new NullPointerException());
                    }
                });
    }

    @Override
    public Observable<RegisterProductResponse> requestRegisterProduct(
            RegisterProductRequest registerProductRequest) {
        return mFOrderApi.requestRegisterProduct(registerProductRequest);
    }

    @Override
    public Observable<UpdateProductResponse> requestUpdateProduct(int productId,
            UpdateProductRequest updateProductRequest) {
        return mFOrderApi.requestUpdateProduct(productId, updateProductRequest);
    }

    @Override
    public Observable<BaseResponse> requestDeleteProductInShop(int productId, int shopId) {
        return mFOrderApi.requestDeleteProductInShop(productId, shopId);
    }

    @Override
    public Observable<BaseResponse> deleteCommentInProduct(int commentId) {
        return mFOrderApi.deleteCommentInProduct(commentId);
    }
}
