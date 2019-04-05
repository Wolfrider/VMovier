package com.oliver.vmovier.detail.video;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.oliver.vmovier.base.recyclerview.IRVItemVO;
import com.oliver.vmovier.core.api.ApiGateway;
import com.oliver.vmovier.core.dto.CommentDTO;
import com.oliver.vmovier.core.dto.CommentItemDTO;
import com.oliver.vmovier.core.dto.FormatContentItemDTO;
import com.oliver.vmovier.core.dto.PostDTO;
import com.oliver.vmovier.core.dto.RelateVideoDTO;
import com.oliver.vmovier.core.dto.TextAttrDTO;
import com.oliver.vmovier.core.dto.VideoBucketDTO;
import com.oliver.vmovier.core.dto.VideoContentDTO;
import com.oliver.vmovier.core.dto.VideoDetailDTO;
import com.oliver.vmovier.core.post.PostVO;
import com.oliver.vmovier.core.utils.Logger;
import com.oliver.vmovier.detail.article.FooterVO;
import com.oliver.vmovier.detail.article.HeaderVO;
import com.oliver.vmovier.detail.article.ImageVO;
import com.oliver.vmovier.detail.article.TextVO;
import com.oliver.vmovier.detail.article.ArticleVO;
import com.oliver.vmovier.detail.article.IArticleItemVO;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.functions.Function;

public class VideoDetailBO {

    private static final String TAG = "VideoDetailBO";

    private ApiGateway mApiGateway;
    private String mNextCommentUrl;
    private ProfileVO mVideoProfile;
    private InteractiveVO mVideoInteractive;

    public VideoDetailBO(@NonNull ApiGateway apiGateway) {
        mApiGateway = apiGateway;
    }

    public Single<List<IRVItemVO>> getPost(@NonNull String id) {
        Logger.d(TAG, "getPost " + id);
        return mApiGateway.getPost(id).map(new Function<VideoDetailDTO, List<IRVItemVO>>() {
            @Override
            public List<IRVItemVO> apply(VideoDetailDTO videoDetailDTO) throws Exception {
                mVideoProfile = convertVideoProfile(videoDetailDTO);
                mVideoInteractive = convertVideoInteractive(videoDetailDTO);
                List<IRVItemVO> data = new ArrayList<>(16);
                data.add(convertIntroVO(videoDetailDTO));
                data.add(convertRelateVO(videoDetailDTO.getRelateVideo().get(0)));
                if (null != videoDetailDTO.getComments()) {
                    data.addAll(convertCommentsVO(videoDetailDTO.getComments(), videoDetailDTO.getCommentsCount()));
                }
                return data;
            }
        });
    }

    public ProfileVO getVideoProfile() {
        return mVideoProfile;
    }

    public InteractiveVO getVideoInteractive() {
        return mVideoInteractive;
    }

    public Single<List<IRVItemVO>> getNextComments() {
        if (!TextUtils.isEmpty(mNextCommentUrl)) {
            Logger.d(TAG, "getNextComments " + mNextCommentUrl);
            return mApiGateway.getNextComments(mNextCommentUrl).map(new Function<CommentDTO, List<IRVItemVO>>() {
                @Override
                public List<IRVItemVO> apply(CommentDTO commentDTO) throws Exception {
                    List<IRVItemVO> data = new ArrayList<>(16);
                    if (null != commentDTO) {
                        data.addAll(convertCommentsVO(commentDTO, -1));
                    }
                    return data;
                }
            });
        }
        return Single.fromObservable(Observable.<List<IRVItemVO>>empty());
    }

    private ProfileVO convertVideoProfile(VideoDetailDTO videoDetailDTO) {
        ProfileVO videoProfileVO = new ProfileVO();
        VideoContentDTO videoContentDTO = videoDetailDTO.getVideoList().get(0);
        videoProfileVO.setDuration(videoContentDTO.getDuration());
        videoProfileVO.setTitle(videoContentDTO.getTitle());
        videoProfileVO.setDefaultIndex(videoContentDTO.getDefaultIndex());
        for (VideoBucketDTO bucketDTO: videoContentDTO.getBucket()) {
            videoProfileVO.addProfile(bucketDTO.getProfile(), bucketDTO.getUrl());
        }
        return videoProfileVO;
    }

    private InteractiveVO convertVideoInteractive(VideoDetailDTO videoDetailDTO) {
        InteractiveVO interactiveVO = new InteractiveVO();
        interactiveVO.setLikeCount(videoDetailDTO.getLikeCount());
        interactiveVO.setShareCount(videoDetailDTO.getShareCount());
        interactiveVO.setShareTitle(videoDetailDTO.getShareTitle());
        interactiveVO.setShareLink(videoDetailDTO.getShareLink());
        return interactiveVO;
    }

    private IRVItemVO convertIntroVO(VideoDetailDTO videoDetailDTO) {
        IntroVO introVO = new IntroVO();
        introVO.setTitle(videoDetailDTO.getTitle());
        introVO.setContent(videoDetailDTO.getIntro());
        introVO.setSubTitle(videoDetailDTO.getCates().get(0), videoDetailDTO.getDuration());
        introVO.setArticleVO(createArticleVO(videoDetailDTO));
        return introVO;
    }

    private IRVItemVO convertRelateVO(RelateVideoDTO relateVideoDTO) {
        RelateVideoVO relateVO = new RelateVideoVO();
        relateVO.setName(relateVideoDTO.getName());
        relateVO.setScheme(relateVideoDTO.getScheme());
        for (PostDTO dto : relateVideoDTO.getPostList()) {
            PostVO postVO = new PostVO();
            postVO.setId(dto.getPostId());
            postVO.setTitle(dto.getTitle());
            postVO.setSubTitle(dto.getCategory().get(0).getName(), dto.getDuration());
            postVO.setImageUrl(dto.getImageUrl());
            relateVO.getRelateList().add(postVO);
        }
        return relateVO;
    }

    private List<IRVItemVO> convertCommentsVO(CommentDTO commentDTO, int total) {
        mNextCommentUrl = commentDTO.getNextPageUrl();
        List<CommentItemDTO> commentItemDTOS = commentDTO.getCommentList();
        List<IRVItemVO> commentsList = new ArrayList<>(16);
        if (null != commentItemDTOS) {
            for (int i = 0; i < commentItemDTOS.size(); ++i) {
                CommentsVO itemVO = createCommentItemVO(commentItemDTOS.get(i), false);
                if (0 == i && total > 0) {
                    itemVO.setTotalCount(total);
                }
                commentsList.add(itemVO);
            }
        }
        return commentsList;
    }

    private CommentsVO createCommentItemVO(CommentItemDTO itemDTO, boolean isSubComment) {
        CommentsVO itemVO = new CommentsVO();
        itemVO.setAvatarUrl(itemDTO.getUserInfo().getAvatarUrl());
        itemVO.setUserName(itemDTO.getUserInfo().getName());
        itemVO.setPublishTime(itemDTO.getPublishTime());
        itemVO.setApproveCount(itemDTO.getApproveCount());
        String content = itemDTO.getContent();
        if (isSubComment) {
            content = String.format(Locale.CHINA, "回复 %s: %s",
                itemDTO.getReplyUserInfo().getName(), content);
        }
        itemVO.setContent(content);
        if (null != itemDTO.getSubComments() && !itemDTO.getSubComments().isEmpty()) {
            List<CommentsVO> subCommentList = new ArrayList<>(16);
            for (CommentItemDTO subItem : itemDTO.getSubComments()) {
                subCommentList.add(createCommentItemVO(subItem, true));
            }
            itemVO.setSubCommentList(subCommentList);
        }
        return itemVO;
    }

    private ArticleVO createArticleVO(VideoDetailDTO videoDetailDTO) {
        ArticleVO articleVO = new ArticleVO();
        articleVO.setTitle(videoDetailDTO.getTitle());
        articleVO.getContents().addAll(createArticleItems(videoDetailDTO));
        return articleVO;
    }

    private List<IArticleItemVO> createArticleItems(VideoDetailDTO videoDetailDTO) {
        List<IArticleItemVO> items = new ArrayList<>();
        items.add(createHeaderVO(videoDetailDTO));
        List<FormatContentItemDTO> itemDTOList = videoDetailDTO.getArticleContent();
        for (FormatContentItemDTO itemDTO : itemDTOList) {
            if (TextUtils.equals("normal", itemDTO.getType())) {
                items.add(createTextVO(itemDTO));
            } else if (TextUtils.equals("image", itemDTO.getType())) {
                items.add(createImageVO(itemDTO));
            }
        }
        items.add(createFooterVO(videoDetailDTO));
        return items;
    }

    private HeaderVO createHeaderVO(VideoDetailDTO videoDetailDTO) {
        HeaderVO headerVO = new HeaderVO();
        headerVO.setTitle(videoDetailDTO.getTitle());
        return headerVO;
    }

    private FooterVO createFooterVO(VideoDetailDTO videoDetailDTO) {
        FooterVO footerVO = new FooterVO();
        footerVO.setAuthor(videoDetailDTO.getEditor().getAuthor());
        footerVO.setPublishTime(videoDetailDTO.getPublishTime());
        return footerVO;
    }

    private TextVO createTextVO(FormatContentItemDTO itemDTO) {
        TextVO textVO = new TextVO();
        textVO.setText(itemDTO.getContent());
        TextAttrDTO textAttrDTO = itemDTO.getTextAttrDTO();
        textVO.setTextAlign(textAttrDTO.getAlign());
        textVO.setTextColor(textAttrDTO.getColor());
        textVO.setTextSize(textAttrDTO.getSize());
        return textVO;
    }

    private ImageVO createImageVO(FormatContentItemDTO itemDTO) {
        TextAttrDTO textAttrDTO = itemDTO.getTextAttrDTO();
        ImageVO imageVO = new ImageVO();
        imageVO.setWidth(textAttrDTO.getWidth());
        imageVO.setHeight(textAttrDTO.getHeight());
        imageVO.setUrl(textAttrDTO.getUrl());
        return imageVO;
    }

}
