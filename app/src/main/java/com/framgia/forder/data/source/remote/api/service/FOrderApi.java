package com.framgia.forder.data.source.remote.api.service;

import com.framgia.forder.data.model.OrderManagement;
import com.framgia.forder.data.source.remote.api.request.ApplyShopToDomainRequest;
import com.framgia.forder.data.source.remote.api.request.CommentRequest;
import com.framgia.forder.data.source.remote.api.request.LeaveShopToDomainRequest;
import com.framgia.forder.data.source.remote.api.request.OrderRequest;
import com.framgia.forder.data.source.remote.api.request.RegisterDomainRequest;
import com.framgia.forder.data.source.remote.api.request.RegisterProductRequest;
import com.framgia.forder.data.source.remote.api.request.RegisterShopRequest;
import com.framgia.forder.data.source.remote.api.request.UpdateProductRequest;
import com.framgia.forder.data.source.remote.api.request.UpdateProfileRequest;
import com.framgia.forder.data.source.remote.api.request.UpdateShopRequest;
import com.framgia.forder.data.source.remote.api.response.BaseResponse;
import com.framgia.forder.data.source.remote.api.response.CategoryResponse;
import com.framgia.forder.data.source.remote.api.response.ChangeRuleOfUserResponse;
import com.framgia.forder.data.source.remote.api.response.CloseOrderResponse;
import com.framgia.forder.data.source.remote.api.response.CommentResponse;
import com.framgia.forder.data.source.remote.api.response.DeleteDomainResponse;
import com.framgia.forder.data.source.remote.api.response.DeleteShopInDomainResponse;
import com.framgia.forder.data.source.remote.api.response.DeleteUserInDomainResponse;
import com.framgia.forder.data.source.remote.api.response.DomainManagementResponse;
import com.framgia.forder.data.source.remote.api.response.DomainResponse;
import com.framgia.forder.data.source.remote.api.response.EditDomainResponse;
import com.framgia.forder.data.source.remote.api.response.ManagerResponse;
import com.framgia.forder.data.source.remote.api.response.NotificationResponse;
import com.framgia.forder.data.source.remote.api.response.OrderCartResponse;
import com.framgia.forder.data.source.remote.api.response.OrderHistoryShopResponse;
import com.framgia.forder.data.source.remote.api.response.OrderManagementResponse;
import com.framgia.forder.data.source.remote.api.response.OrderManagerShopReponse;
import com.framgia.forder.data.source.remote.api.response.OrderResponse;
import com.framgia.forder.data.source.remote.api.response.ProductResponse;
import com.framgia.forder.data.source.remote.api.response.RegisterDomainResponse;
import com.framgia.forder.data.source.remote.api.response.RegisterProductResponse;
import com.framgia.forder.data.source.remote.api.response.RegisterShopResponse;
import com.framgia.forder.data.source.remote.api.response.SearchResponse;
import com.framgia.forder.data.source.remote.api.response.ShopInDomainResponse;
import com.framgia.forder.data.source.remote.api.response.ShopManagementResponse;
import com.framgia.forder.data.source.remote.api.response.ShopResponse;
import com.framgia.forder.data.source.remote.api.response.UpdateProductResponse;
import com.framgia.forder.data.source.remote.api.response.UpdateProfileResponse;
import com.framgia.forder.data.source.remote.api.response.UserInDomainResponse;
import com.framgia.forder.data.source.remote.api.response.UserResponse;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by le.quang.dao on 10/03/2017.
 */

public interface FOrderApi {

    // TODO: 11/04/2017 remove later
    @GET("/search/users")
    Observable<Void> searchGithubUsers(@Query("per_page") int limit, @Query("q") String searchTerm);

    @GET("v1/authen_user_tokens")
    Observable<UserResponse> login(@Query("user_email") String userEmail,
            @Query("password") String passWord, @Query("device_id") String deviceToken);

    @GET("v1/domains")
    Observable<DomainResponse> getDomains(@Query("user_id") int id,
            @Query("user_email") String userEmail, @Query("user_token") String userToken);

    @GET("v1/products")
    Observable<ProductResponse> getListProduct(@Query("domain_id") int id);

    @GET("v1/shops")
    Observable<ShopResponse> getListShop(@Query("domain_id") int id);

    // TODO edit later
    @GET("v1/products")
    Observable<ProductResponse> getRelativeShops(@Query("domain_id") int id);

    // TODO edit later: There is no api profile link yet
    @POST("v1/profile")
    Observable<UpdateProfileResponse> updateUserInformation(
            @Body UpdateProfileRequest updateProfileRequest);

    @GET("v1/searches")
    Observable<SearchResponse> search(@Query("domain_id") int domainId,
            @Query("keyword") String keyWord);

    @GET("v1/notifications")
    Observable<NotificationResponse> getListNotification();

    @GET("v1/products")
    Observable<ProductResponse> getListProductShop(@Query("shop_id") int shopId,
            @Query("domain_id") int id);

    @POST("v1/orders/")
    Observable<OrderResponse> orderRequest(@Body OrderRequest orderRequest);

    @GET("order_managers")
    Observable<OrderManagementResponse> getOrderManagement();

    @GET("v1/comments")
    Observable<CommentResponse> getListCommentInProduct(@Query("product_id") int productId,
            @Query("domain_id") int id);

    @POST("v1/comments")
    Observable<BaseResponse> sendComment(@Body CommentRequest commentRequest);

    @GET("v1/orders")
    Observable<OrderResponse> getListOrder(@Query("user_id") int userId,
            @Query("domain_id") int domainId);

    @GET("v1/dashboard/shops")
    Observable<ShopManagementResponse> getLitShopManagement(@Query("user_id") int userId);

    @POST("v1/dashboard/shops")
    Observable<ShopManagementResponse> requestApplyShopToDomain(
            @Body ApplyShopToDomainRequest requestApplyShopToDomain);

    @POST("v1/dashboard/shops")
    Observable<ShopManagementResponse> requestLeaveShopFromDomain(
            @Body LeaveShopToDomainRequest requestLeaveShopFromDomain);

    @POST("v1/dashboard/shops")
    Observable<RegisterShopResponse> requestRegisterShop(
            @Body RegisterShopRequest registerShopRequest);

    @GET("v1/products")
    Observable<ProductResponse> getListProductShopInformation(@Query("shop_id") int shopId);

    @GET("v1/shop_managers")
    Observable<ManagerResponse> getListManagerOfShop(@Query("shop_id") int shopId);

    @PUT("v1/dashboard/shops/{shop_id}")
    Observable<RegisterShopResponse> updateShop(@Path("shop_id") int shopId,
            @Body UpdateShopRequest updateShop);

    @POST("v1/dashboard/products")
    Observable<RegisterProductResponse> requestRegisterProduct(
            @Body RegisterProductRequest registerProductRequest);

    @GET("v1/categories")
    Observable<CategoryResponse> getCategories(@Query("domain_id") int domainId);

    @PUT("v1/dashboard/products/{id}")
    Observable<UpdateProductResponse> requestUpdateProduct(@Path("id") int productId,
            @Body UpdateProductRequest updateProductRequest);

    @GET("v1/dashboard/orders")
    Observable<OrderManagementResponse> getListOrderManagementShop(@Query("shop_id") int shopId);

    @POST("v1/orders_product_all")
    Observable<OrderCartResponse> requestOrderProduct(@Query("domain_id") int domainId,
            @Body OrderRequest orderRequest);

    @PUT("v1/dashboard/order_products/0")
    Observable<OrderManagerShopReponse> acceptAndRejectInOrder(@Query("shop_id") int shopId,
            @Body OrderManagement acceptAndRejectInOrderRequest);

    @GET("v1/dashboard/order_products")
    Observable<CloseOrderResponse> notifyDoneOrderToServer(@Query("shop_id") int shopId);

    @GET("v1/domains/0")
    Observable<DomainManagementResponse> getListDomainManagement();

    @GET("v1/dashboard/order_managers")
    Observable<OrderHistoryShopResponse> getListOrderHistoryShop(@Query("shop_id") int shopId);

    @POST("v1/domains")
    Observable<RegisterDomainResponse> requestRegisterDomain(
            @Body RegisterDomainRequest registerDomainRequest);

    @GET("v1/orders/0")
    Observable<OrderResponse> getListOrderHistoryToday();

    @GET("v1/orders/0")
    Observable<OrderResponse> getListOrderHistoryByDate(@Query("time_start") String timeStart,
            @Query("time_end") String timeEnd);

    @DELETE("v1/user_domains/1")
    Observable<DeleteUserInDomainResponse> requestDeleteUserInDomain(
            @Query("domain_id") int domainId, @Query("user_id") int userId);

    @DELETE("v1/domains/{domain_id}")
    Observable<DeleteDomainResponse> requestDeleteDomain(@Path("domain_id") int domainId);

    @GET("v1/domains/{domain_id}/edit")
    Observable<EditDomainResponse> requestEditDomain(@Path("domain_id") int domainId,
            @Query("name") String name, @Query("status") String status);

    @GET("v1/dashboard/shop_domains")
    Observable<ShopInDomainResponse> getListShopInDomain(@Query("domain_id") int domainId);

    @DELETE("v1/shop_domains/0")
    Observable<DeleteShopInDomainResponse> requestDeleteShopInDomain(
            @Query("domain_id") int domainId, @Query("shop_id") int shopId);

    @GET("v1/dashboard/shop_managers")
    Observable<UserInDomainResponse> getListUserInDomain(@Query("domain_id") int domainId);

    @PUT("v1/user_domains/1")
    Observable<ChangeRuleOfUserResponse> requestChangeRuleOfUserInDomain(
            @Query("domain_id") int domainId, @Query("user_id") int userId,
            @Query("role") String role);

    @GET("v1/dashboard/shops/{shop_id}/edit")
    Observable<BaseResponse> requestChangeStatusShopManagement(@Path("shop_id") int shopId);

    @DELETE("v1/dashboard/products/{product_id}")
    Observable<BaseResponse> requestDeleteProductInShop(@Path("product_id") int productId,
            @Query("shop_id") int shopId);
}
