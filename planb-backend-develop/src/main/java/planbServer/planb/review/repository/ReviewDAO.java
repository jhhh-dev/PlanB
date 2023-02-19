package planbServer.planb.review.repository;

import org.apache.ibatis.annotations.Mapper;
import planbServer.planb.review.model.*;

import java.util.List;

@Mapper
public interface ReviewDAO {
    void reviewInsert(ReviewDTO reviewDTO);
    Integer reviewCheck(ReviewDTO reviewDTO);
    ReviewDTO reviewDetail(Integer reviewId);
    Integer likeCount(Integer reviewId);
    void insertLike(LikeDTO likeDTO);
    void deleteLike(LikeDTO likeDTO);
    LikeDTO checkLike(LikeDTO likeDTO);

    //페이징 처리 리스트 조회
    List<ReviewDTO> reviewListPageRecentfilter(PageDTO pageDTO);

    void reviewDelete(ReviewDTO reviewDTO);
}
