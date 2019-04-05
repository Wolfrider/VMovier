package com.oliver.vmovier.detail.video;

import java.util.List;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import com.oliver.vmovier.base.BaseRxViewModel;
import com.oliver.vmovier.base.recyclerview.IRVItemVO;
import com.oliver.vmovier.core.dto.CommentDTO;
import com.oliver.vmovier.core.guice.ObjectProviders;
import io.reactivex.functions.Consumer;

public class VideoDetailViewModel extends BaseRxViewModel {

    private VideoDetailBO mVideoDetailBO;
    private MutableLiveData<ProfileVO> mVideoProfile;
    private MutableLiveData<InteractiveVO> mVideoInteractive;
    private MutableLiveData<List<IRVItemVO>> mVideoContent;

    public VideoDetailViewModel() {
        super();
        mVideoDetailBO = ObjectProviders.get(VideoDetailBO.class);
        mVideoProfile = new MutableLiveData<>();
        mVideoInteractive = new MutableLiveData<>();
        mVideoContent = new MutableLiveData<>();
    }

    public void init(String id) {
        mRxRes.append(mVideoDetailBO.getPost(id).subscribe(new Consumer<List<IRVItemVO>>() {
            @Override
            public void accept(List<IRVItemVO> baseVideoDetailVOS) throws Exception {
                mVideoProfile.postValue(mVideoDetailBO.getVideoProfile());
                mVideoInteractive.postValue(mVideoDetailBO.getVideoInteractive());
                mVideoContent.postValue(baseVideoDetailVOS);
            }
        }));
    }

    public void loadMore() {
        mRxRes.append(mVideoDetailBO.getNextComments().subscribe(new Consumer<List<IRVItemVO>>() {
            @Override
            public void accept(List<IRVItemVO> itemVOS) throws Exception {
                mVideoContent.postValue(itemVOS);
            }
        }));
    }

    public LiveData<ProfileVO> getProfile() {
        return mVideoProfile;
    }

    public LiveData<InteractiveVO> getInteractive() {
        return mVideoInteractive;
    }

    public LiveData<List<IRVItemVO>> getContent() {
        return mVideoContent;
    }
}
