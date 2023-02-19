package planbServer.planb.review.service;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import planbServer.planb.admin.model.ProductVO;
import planbServer.planb.review.model.*;
import planbServer.planb.review.repository.ReviewDAO;
import planbServer.planb.util.ChangeName;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

@Service
@Slf4j
public class ReviewServiceImpl implements ReviewService{


    @Autowired
    ReviewDAO reviewDAO;

    @Override
    public void reviewInsert(ReviewDTO reviewDTO) {

        reviewDAO.reviewInsert(reviewDTO);
    }

    @Override
    public Integer reviewCheck(ReviewDTO reviewDTO) {
        return reviewDAO.reviewCheck(reviewDTO);
    }

    @Override
    public ReviewDTO reviewDetail(Integer reviewId) {
        return reviewDAO.reviewDetail(reviewId);
    }

    @Override
    public Integer likeCount(Integer reviewId) {
        return reviewDAO.likeCount(reviewId);
    }

    @Override
    public void insertLike(LikeDTO likeDTO) {
        reviewDAO.insertLike(likeDTO);
    }

    @Override
    public void deleteLike(LikeDTO likeDTO) {
        reviewDAO.deleteLike(likeDTO);
    }

    @Override
    public LikeDTO checkLike(LikeDTO likeDTO) {
        return reviewDAO.checkLike(likeDTO);
    }

    @Override
    public List<ReviewDTO> reviewListPageRecentfilter(PageDTO pageDTO) {
        return reviewDAO.reviewListPageRecentfilter(pageDTO);
    }

    @Override
    public void reviewDelete(ReviewDTO reviewDTO) {
        reviewDAO.reviewDelete(reviewDTO);
    }
}
