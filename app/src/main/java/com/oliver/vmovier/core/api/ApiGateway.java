package com.oliver.vmovier.core.api;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import android.support.annotation.NonNull;
import com.oliver.vmovier.core.Info.InfoBus;
import com.oliver.vmovier.core.api.converter.FastJsonConverterFactory;
import com.oliver.vmovier.core.dto.CategoryDTO;
import com.oliver.vmovier.core.dto.CommentDTO;
import com.oliver.vmovier.core.dto.DailyCoverDTO;
import com.oliver.vmovier.core.dto.DataDTO;
import com.oliver.vmovier.core.dto.FirstScreenDTO;
import com.oliver.vmovier.core.dto.ListContentDTO;
import com.oliver.vmovier.core.dto.ListDTO;
import com.oliver.vmovier.core.dto.ListItemDTO;
import com.oliver.vmovier.core.dto.PostDTO;
import com.oliver.vmovier.core.dto.RecommendDTO;
import com.oliver.vmovier.core.dto.SearchDTO;
import com.oliver.vmovier.core.dto.VideoDetailDTO;
import com.oliver.vmovier.core.utils.Logger;
import io.reactivex.Single;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

public final class ApiGateway {

    private static final String TAG = "ApiGateway";

    private static final String BASE_URL = "https://app.vmovier.com";
    private static final int TIME_OUT = 3;

    private Retrofit mRetrofit;
    private VMovierService mVMovierService;

    public ApiGateway() {
        mRetrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(FastJsonConverterFactory.create())
            .client(createHttpClient())
            .build();
        mVMovierService = mRetrofit.create(VMovierService.class);
    }

    public Single<DailyCoverDTO> getDailyCover() {
        return Single.fromObservable(mVMovierService.getDailyCover().map(
            new Function<DataDTO<DailyCoverDTO>, DailyCoverDTO>() {
                @Override
                public DailyCoverDTO apply(DataDTO<DailyCoverDTO> dailyCoverDTODataDTO) throws Exception {
                    return dailyCoverDTODataDTO.getData();
                }
            })).subscribeOn(Schedulers.io());
    }

    public Single<FirstScreenDTO> getFirstScreen(int width, int height) {
        return Single.fromObservable(mVMovierService.getFirstScreen(width, height).map(
            new Function<DataDTO<FirstScreenDTO>, FirstScreenDTO>() {
                @Override
                public FirstScreenDTO apply(DataDTO<FirstScreenDTO> firstScreenDTODataDTO) throws Exception {
                    return firstScreenDTODataDTO.getData();
                }
            })).subscribeOn(Schedulers.io());
    }

    public Single<List<CategoryDTO>> getCategory() {
        return Single.fromObservable(mVMovierService.getCategory().map(
            new Function<DataDTO<List<CategoryDTO>>, List<CategoryDTO>>() {
                @Override
                public List<CategoryDTO> apply(DataDTO<List<CategoryDTO>> listDataDTO) throws Exception {
                    return listDataDTO.getData();
                }
            })).subscribeOn(Schedulers.io());
    }

    public Single<ListDTO> getFirstDiscovery() {
        return Single.fromObservable(mVMovierService.getFirstDiscovery()).map(
            new Function<DataDTO<ListDTO>, ListDTO>() {
                @Override
                public ListDTO apply(DataDTO<ListDTO> listDTODataDTO) throws Exception {
                    return listDTODataDTO.getData();
                }
            }).subscribeOn(Schedulers.io());
    }

    public Single<ListContentDTO<ListItemDTO>> getNextPage(@NonNull String path) {
        return Single.fromObservable(mVMovierService.getNextPage(path)).map(
            new Function<DataDTO<ListContentDTO<ListItemDTO>>, ListContentDTO<ListItemDTO>>() {
                @Override
                public ListContentDTO<ListItemDTO> apply(DataDTO<ListContentDTO<ListItemDTO>> listContentDTODataDTO) throws Exception {
                    return listContentDTODataDTO.getData();
                }
            })
            .subscribeOn(Schedulers.io());
    }

    public Single<VideoDetailDTO> getPost(@NonNull String id) {
        return Single.fromObservable(mVMovierService.getPost(id)).map(new Function<DataDTO<VideoDetailDTO>, VideoDetailDTO>() {
            @Override
            public VideoDetailDTO apply(DataDTO<VideoDetailDTO> videoDetailDTODataDTO) throws Exception {
                return videoDetailDTODataDTO.getData();
            }
        }).subscribeOn(Schedulers.io());
    }

    public Single<List<PostDTO>> getPostByTab(@NonNull String tab, int pageNo) {
        return Single.fromObservable(mVMovierService.getPostByTab(tab, pageNo, 10).map(
            new Function<DataDTO<List<PostDTO>>, List<PostDTO>>() {
                @Override
                public List<PostDTO> apply(DataDTO<List<PostDTO>> listDataDTO) throws Exception {
                    return listDataDTO.getData();
                }
            })).subscribeOn(Schedulers.io());
    }

    public Single<List<PostDTO>> getPostByCate(@NonNull String cateId, int pageNo) {
        return Single.fromObservable(mVMovierService.getPostByCate(cateId, pageNo, 10).map(
            new Function<DataDTO<List<PostDTO>>, List<PostDTO>>() {
                @Override
                public List<PostDTO> apply(DataDTO<List<PostDTO>> listDataDTO) throws Exception {
                    return listDataDTO.getData();
                }
            })).subscribeOn(Schedulers.io());
    }

    public Single<List<PostDTO>> getPostByTag(@NonNull String tag, int pageNo) {
        return Single.fromObservable(mVMovierService.getPostByTag(tag, pageNo, 10).map(
            new Function<DataDTO<List<PostDTO>>, List<PostDTO>>() {
                @Override
                public List<PostDTO> apply(DataDTO<List<PostDTO>> listDataDTO) throws Exception {
                    return listDataDTO.getData();
                }
            })).subscribeOn(Schedulers.io());
    }

    public Single<CommentDTO> getNextComments(@NonNull String path) {
        return Single.fromObservable(mVMovierService.getNextComments(path)).map(
            new Function<DataDTO<CommentDTO>, CommentDTO>() {
                @Override
                public CommentDTO apply(DataDTO<CommentDTO> commentDTODataDTO) throws Exception {
                    return commentDTODataDTO.getData();
                }
            }).subscribeOn(Schedulers.io());
    }

    public Single<RecommendDTO> getRecommend() {
        return Single.fromObservable(mVMovierService.getRecommend()).map(
            new Function<DataDTO<RecommendDTO>, RecommendDTO>() {
                @Override
                public RecommendDTO apply(DataDTO<RecommendDTO> recommendDTODataDTO) throws Exception {
                    return recommendDTODataDTO.getData();
                }
            }).subscribeOn(Schedulers.io());
    }

    public Single<SearchDTO> getSearch(String word) {
        return Single.fromObservable(mVMovierService.getSearch(word)).map(
            new Function<DataDTO<SearchDTO>, SearchDTO>() {
                @Override
                public SearchDTO apply(DataDTO<SearchDTO> searchDTODataDTO) throws Exception {
                    return searchDTODataDTO.getData();
                }
            }).subscribeOn(Schedulers.io());
    }

    public Single<SearchDTO> getMoreSearch(@NonNull String url) {
        return Single.fromObservable(mVMovierService.getMoreSearch(url)).map(
            new Function<DataDTO<SearchDTO>, SearchDTO>() {
                @Override
                public SearchDTO apply(DataDTO<SearchDTO> listContentDTODataDTO) throws Exception {
                    return listContentDTODataDTO.getData();
                }
            })
            .subscribeOn(Schedulers.io());
    }

    public Single<SearchDTO> getSearchByFilter(String word, String type, String order, String cateId) {
        return Single.fromObservable(mVMovierService.getSearchByFilter(word, type, order, cateId)).map(
            new Function<DataDTO<SearchDTO>, SearchDTO>() {
                @Override
                public SearchDTO apply(DataDTO<SearchDTO> stringDataDTO) throws Exception {
                    return stringDataDTO.getData();
                }
            }).subscribeOn(Schedulers.io());
    }

    private OkHttpClient createHttpClient() {
        return new OkHttpClient.Builder()
            .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(TIME_OUT, TimeUnit.SECONDS)
            .writeTimeout(TIME_OUT, TimeUnit.SECONDS)
            .callTimeout(TIME_OUT * 2, TimeUnit.SECONDS)
            .followRedirects(true)
            .followSslRedirects(true)
            .addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request request = chain.request()
                        .newBuilder()
                        .addHeader("User-Agent", getUserAgent())
                        .addHeader("Device-Id", getDeviceId())
                        .build();
                    Logger.v(TAG, request.url().toString());
                    return chain.proceed(request);
                }
            })
            .sslSocketFactory(createSSLSocketFactory(), createTrustManager())
            .build();
    }

    private String getUserAgent() {
        return InfoBus.getUserAgent();
    }

    private String getDeviceId() {
        return InfoBus.getDeviceInfo().getDeviceId();
    }

    private SSLSocketFactory createSSLSocketFactory() {
        try {
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, new TrustManager[] {createTrustManager()}, new java.security.SecureRandom());
            return sslContext.getSocketFactory();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        throw new IllegalStateException();
    }

    private X509TrustManager createTrustManager() {
        return new X509TrustManager() {
            @Override
            public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) {
            }

            @Override
            public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) {
            }

            @Override
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return new java.security.cert.X509Certificate[]{};
            }
        };
    }

}
