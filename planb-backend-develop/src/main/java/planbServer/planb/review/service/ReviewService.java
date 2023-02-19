package planbServer.planb.review.service;

import planbServer.planb.review.model.*;

import java.util.List;

public interface ReviewService {
    void reviewInsert(ReviewDTO reviewDTO);
    Integer reviewCheck(ReviewDTO reviewDTO);
    ReviewDTO reviewDetail(Integer reviewId);
    Integer likeCount(Integer reviewId);
    void insertLike(LikeDTO likeDTO);
    void deleteLike(LikeDTO likeDTO);
    LikeDTO checkLike(LikeDTO likeDTO);
    List<ReviewDTO> reviewListPageRecentfilter(PageDTO pageDTO);

    void reviewDelete(ReviewDTO reviewDTO);
}
