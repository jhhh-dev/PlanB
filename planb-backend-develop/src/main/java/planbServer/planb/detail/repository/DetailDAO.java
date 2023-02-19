package planbServer.planb.detail.repository;

import org.apache.ibatis.annotations.Mapper;
import planbServer.planb.detail.model.BookmarkVO;
import planbServer.planb.detail.model.DetailProductDTO;
import planbServer.planb.detail.model.OrderDTO;
import planbServer.planb.detail.model.PaymentDTO;
import planbServer.planb.detail.model.ReviewDTO;
import planbServer.planb.review.model.LikeDTO;

import java.util.List;


@Mapper
public interface DetailDAO {

    public DetailProductDTO getDetailInfo(int proId);
    public List<ReviewDTO> getStyle(int proId);
    public List<DetailProductDTO> getBrandProduct(int proId);
    public void insertPayment(PaymentDTO paymentDTO);
    public void insertOrder(OrderDTO orderDTO);
    public void plusReadCount(int proId);
    public void insertBookmark(BookmarkVO bookmarkVO);
    public void deleteBookmark(BookmarkVO bookmarkVO);
    public int getBookmarkCount(BookmarkVO bookmarkVO);
    public int getReviewLike(LikeDTO likeDTO);
    public int getLikeCount(int reviewId);
    public void insertLike(LikeDTO likeDTO);
    public void deleteLike(LikeDTO likeDTO);

}
