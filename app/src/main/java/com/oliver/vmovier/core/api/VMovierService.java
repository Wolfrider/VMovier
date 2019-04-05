package com.oliver.vmovier.core.api;

import java.util.List;

import com.oliver.vmovier.core.dto.CategoryDTO;
import com.oliver.vmovier.core.dto.CommentDTO;
import com.oliver.vmovier.core.dto.DailyCoverDTO;
import com.oliver.vmovier.core.dto.DataDTO;
import com.oliver.vmovier.core.dto.ListContentDTO;
import com.oliver.vmovier.core.dto.ListDTO;
import com.oliver.vmovier.core.dto.FirstScreenDTO;
import com.oliver.vmovier.core.dto.ListItemDTO;
import com.oliver.vmovier.core.dto.PostDTO;
import com.oliver.vmovier.core.dto.RecommendDTO;
import com.oliver.vmovier.core.dto.SearchDTO;
import com.oliver.vmovier.core.dto.VideoDetailDTO;
import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface VMovierService {

    @GET("/apiv3/DayCover/getDayCover")
    Observable<DataDTO<DailyCoverDTO>> getDailyCover();

    @GET("/apiv3/index/openScreen")
    Observable<DataDTO<FirstScreenDTO>> getFirstScreen(@Query("width") int width,
                                                       @Query("height") int height);

    @POST("/apiv3/cate/getList")
    Observable<DataDTO<List<CategoryDTO>>> getCategory();

    @GET("/apiv3/index/index")
    Observable<DataDTO<ListDTO>> getFirstDiscovery();

    @GET("{path}")
    Observable<DataDTO<ListContentDTO<ListItemDTO>>> getNextPage(@Path("path") String path);

    @GET("/apiv3/post/view")
    Observable<DataDTO<VideoDetailDTO>> getPost(@Query("postid") String id);

    @FormUrlEncoded
    @POST("/apiv3/post/getPostByTab")
    Observable<DataDTO<List<PostDTO>>> getPostByTab(@Field("tab") String tab, @Field("p") int pageNo, @Field("size") int size);

    @FormUrlEncoded
    @POST("/apiv3/post/getPostInCate")
    Observable<DataDTO<List<PostDTO>>> getPostByCate(@Field("cateid") String cateId, @Field("p") int pageNo, @Field("size") int size);

    @FormUrlEncoded
    @POST("/apiv3/tag/getPostByTag")
    Observable<DataDTO<List<PostDTO>>> getPostByTag(@Field("tagid") String tagid, @Field("p") int pageNo, @Field("size") int size);

    @GET()
    Observable<DataDTO<CommentDTO>> getNextComments(@Url String path);

    @GET("/apiv3/search?kw=")
    Observable<DataDTO<RecommendDTO>> getRecommend();

    @GET("/apiv3/search")
    Observable<DataDTO<SearchDTO>> getSearch(@Query("kw") String word);

    @GET()
    Observable<DataDTO<SearchDTO>> getMoreSearch(@Url String url);

    @GET("/apiv3/search")
    Observable<DataDTO<SearchDTO>> getSearchByFilter(@Query("kw") String word, @Query("type") String type,
                                                  @Query("order") String order, @Query("cateid") String cateId);

}
