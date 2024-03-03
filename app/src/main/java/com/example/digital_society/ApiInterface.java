package com.example.digital_society;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

   @FormUrlEncoded
    @POST("insertData.php")
    Call<MyModel> registerData(@Field("societyname") String societyname,
                               @Field("username") String username,
                               @Field("password") String password);

   @GET("Chairman_login.php")
    Call<List<MyModel>> Chairman_login(@Query("username") String username,
                                       @Query("password") String password);
 
   @GET("get_societyname.php")
    Call<List<MyModel>> get_societyname(@Query("id") String id);

   @FormUrlEncoded
    @POST("registerMember.php")
    Call<Register_Member_Model> registerMember(@Field("firstname") String firstname,
                                               @Field("lastname") String lastname,
                                               @Field("flat_no") String flat_no,
                                               @Field("no_of_members") String no_of_members,
                                               @Field("member_password") String member_password);

   @GET("Member_Login.php")
    Call<List<Register_Member_Model>> Member_Login(@Query("flat_no") String flat_no,
                                                   @Query("member_password") String member_password);

   @GET("get_all_member.php")
    Call<List<Register_Member_Model>> getallmember();


   @GET("get_specific_member.php")
    Call<List<Register_Member_Model>> getspecificmember(@Query("id") String id);


   @FormUrlEncoded
    @POST("Add_Notice.php")
   Call<Notice_Model> addNotice(@Field("Notice") String notice);

   @GET("Get_Notice.php")
    Call<List<Notice_Model>> getNotice();

   @FormUrlEncoded
    @POST("update_mem_profile.php")
    Call<List<Register_Member_Model>> updateProfile(@Field("firstname") String firstname,
                                                    @Field("lastname") String lastname);

}
