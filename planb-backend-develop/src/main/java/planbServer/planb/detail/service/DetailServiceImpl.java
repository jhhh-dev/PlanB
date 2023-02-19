package planbServer.planb.detail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import planbServer.planb.detail.model.BookmarkVO;
import planbServer.planb.detail.model.DetailProductDTO;
import planbServer.planb.detail.model.OrderDTO;
import planbServer.planb.detail.model.PaymentDTO;
import planbServer.planb.detail.repository.DetailDAO;
import planbServer.planb.detail.model.ReviewDTO;
import planbServer.planb.review.model.LikeDTO;

import java.util.List;

@Service
public class DetailServiceImpl implements DetailService {

    @Autowired
    DetailDAO detailDAO;

    public DetailProductDTO getDetailInfo(int proId){

        return detailDAO.getDetailInfo(proId);
    }

    public List<ReviewDTO> getStyle(int proId){

        return  detailDAO.getStyle(proId);
    }

    public List<DetailProductDTO> getBrandProduct(int proId){

        return detailDAO.getBrandProduct(proId);
    }

    public void insertPayment(PaymentDTO paymentDTO) {
        detailDAO.insertPayment(paymentDTO);
    }
    public void insertOrder(OrderDTO orderDTO) {
        detailDAO.insertOrder(orderDTO);
    }

    public void plusReadCount(int proId) {
        detailDAO.plusReadCount(proId);
    }

    public void insertBookmark(BookmarkVO bookmarkVO){
        detailDAO.insertBookmark(bookmarkVO);
    }
    public void deleteBookmark(BookmarkVO bookmarkVO){
        detailDAO.deleteBookmark(bookmarkVO);
    }
    public int getBookmarkCount(BookmarkVO bookmarkVO){
        return detailDAO.getBookmarkCount(bookmarkVO);
    }

    public int getReviewLike(LikeDTO likeDTO){
        return detailDAO.getReviewLike(likeDTO);
    }
    public int getLikeCount(int reviewId){
        return detailDAO.getLikeCount(reviewId);
    }
    public void insertLike(LikeDTO likeDTO){
        detailDAO.insertLike(likeDTO);
    }
    public void deleteLike(LikeDTO likeDTO){
        detailDAO.deleteLike(likeDTO);
    }
}
