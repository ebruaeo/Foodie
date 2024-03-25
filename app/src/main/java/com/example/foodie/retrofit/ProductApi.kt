package com.example.foodie.retrofit

import com.example.foodie.data.entity.AddProductResponse
import com.example.foodie.data.entity.AllProductsResponse
import com.example.foodie.data.entity.CartProductsResponse
import com.example.foodie.data.entity.RemoveProductResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ProductApi {

    @GET("yemekler/tumYemekleriGetir.php")
    suspend fun getAllProducts(): AllProductsResponse

    @GET("yemekler/sepettekiYemekleriGetir.php")
    suspend fun getCartProducts(): CartProductsResponse

    @POST("yemekler/sepettenYemekSil.php")
    @FormUrlEncoded
    suspend fun removeProductFromCart(
        @Field("sepet_yemek_id") productId: Int,
        @Field("kullanici_adi") username: String = ApiUtils.USERNAME
    ): RemoveProductResponse

    @POST("yemekler/sepeteYemekEkle.php")
    @FormUrlEncoded
    suspend fun addProductToCart(
        @Field("yemek_adi") productName: String,
        @Field("yemek_resim_adi") productImgName: String,
        @Field("yemek_fiyat") productPrice: Int,
        @Field("yemek_siparis_adet") productCount: Int,
        @Field("kullanici_adi") username: String = ApiUtils.USERNAME
    ): AddProductResponse

}