<template>
    <div style="padding-left: 18%; padding-right: 18%; margin-top: 20px; ">
        <div style="display: flex; justify-content: space-between;">
            <div>
                <h1>Style picks!</h1>
                <span style="color: gray">스타일</span>
            </div>
            
            <nuxt-link to="/style">
                <br />
                <span style="color: gray;"> + 페이지로 이동</span>
            </nuxt-link>
        </div>
        
        <br />
        <VueSlickCarousel v-bind="settings" v-if="styleList.length != 0" style="margin: auto;">

            <div v-for="(data, index) in styleList" :key="index" style="outline: none;">
                <div class="cardBox" @click="styleDialog(data)" v-bind:style="{ backgroundImage: 'url(' + data.reviewImg + ')' }">
                        
                    <!-- <img :src="data.reviewImg" height="100%" style="max-height: 100%;" /> -->
                    <div></div>
                    <span style="">
                        {{ data.userId }}
                    </span>

                </div>
            </div>

        </VueSlickCarousel>

        <div v-else style="height: 200px; display:flex; align-items:center; justify-content: center;">
            <h1 style="color: #a5a5a5; ">등록된 후기가 없습니다</h1>
        </div>
        



        <!-- dialog 테스트 test -->
        <v-dialog v-model="dialog" persistent max-width="350" @click:outside="fn_cancel" @keydown.esc="fn_cancel">
            <v-card>
                

                <div style=" display: flex; justify-content: center; max-height: 500px; overflow: hidden;">
                    <!-- 리뷰 이미지 -->
                    <img :src="this.modalReviewImg" style="max-width: 400px; object-fit: cover;"/>
                </div>
                
                <v-card-text>
                    <div style="padding-top: 10px;">
                        <!-- 리뷰 내용 -->
                        {{ this.modalReviewContent }}
                    </div>
                </v-card-text>
        
                <v-card-text style="padding-bottom: 5px;">
                    <div style="display: flex; justify-content: space-between; height: 50px; line-height: 50px;">
                        <div style="">
                            <v-icon>
                                mdi-account-circle
                            </v-icon>
                            <span>
                                <!-- 유저 ID -->
                                {{ this.modalUserId }}
                            </span>
                        </div>


                        <div style="display: flex;">
                            <nuxt-link :to="'/detail/'+`${modalProId}`">
                                <span style="color: gray;">상품보기</span>
                                &emsp;
                            </nuxt-link>


                            <div @click="changeLike()" style="padding: 4px; cursor: pointer; position: relative; top: -5px;" >
                                <v-icon size="20" v-if="this.modalLikeOk === 0" color="secondary">mdi-heart-outline</v-icon>
                                <v-icon size="20" v-else color="error">mdi-heart</v-icon>
                                <span>{{ this.modalLikeCount }}</span>
                            </div>
                        </div>

                    </div>
                    
                </v-card-text>

            </v-card>
        </v-dialog>

        
        <br/><br/><br/>
        
        
    
    
    </div>

</template>
<script>
import VueSlickCarousel from 'vue-slick-carousel'
import 'vue-slick-carousel/dist/vue-slick-carousel.css'
// optional style for arrows & dots
import 'vue-slick-carousel/dist/vue-slick-carousel-theme.css'

import axios from 'axios';
const backUrl = 'http://localhost:8080';

export default {
    components: { VueSlickCarousel },
    mounted() {

        // 스타일 후기 가져오기
        this.getStyleList();

    },

    data() {
        return {
            // 스타일 리스트와 이미지파일
            styleList: '',

            // Dialog & 각 상품의 데이터를 Dialog 에 출력하기 위한 변수
            dialog:'',
            modalReviewId:'',
            modalProId:'',
            modalUserId:'',
            modalReviewImg:'',
            modalReviewContent:'',
            modalReviewDate:'',
            modalProName:'',

            // 좋아요 관련
            modalLikeOk:'',
            modalLikeCount:'',

            // slick carousel
            settings:{
                // "centerMode": true,
                // "centerPadding": 5,
                "dots": false,
                "autoplay": true,
                "autoplaySpeed": 3000,
                // "focusOnSelect": true,
                "infinite": true,
                "slidesToShow": 5,
                "slidesToScroll": 1,
                // "touchThreshold": 5
            }


        }
    },

    methods: {

        // 해당 상품의 스타일 후기 가져오기
        getStyleList() {

            axios.get(backUrl + '/mainReview')
                .then(res => {

                    // 스타일 가져오기
                    this.styleList = res.data;

                    console.log(res.data);

                    // 스타일 첨부 이미지 가져오기
                    for (let i = 0; i <= this.styleList.length; i++) {

                        axios.get(backUrl + '/showImage?fileName=' + this.styleList[i].reviewImg)
                            .then(res => {

                                // 변수에 DB이미지 링크 담기
                                this.styleList[i].reviewImg = res.config.url;

                            })

                    }


                })
        },

        // 후기 클릭 시 Dialog 열고, 좋아요 확인
        styleDialog(data) {
            
            // Dialog 열기
            this.dialog = true;

            // 변수에 담기
            this.modalReviewId = data.reviewId;
            this.modalProId = data.proId;
            this.modalUserId = data.userId;
            this.modalReviewImg = data.reviewImg;
            this.modalReviewContent = data.reviewContent;
            this.modalReviewDate = data.reviewDate;
            this.modalProName = data.proName;

            // 좋아요 체크여부 확인
            this.likeCheck();

            // 좋아요 갯수 확인
            this.LikeCount();

        },

        // Dialog 바깥 클릭 시 닫기
        fn_cancel() {
            
            this.dialog = false;
        },

        // 로그인 한 유저의 리뷰_좋아요 여부
        likeCheck(){

            axios.get(backUrl + '/likeCount?userId=' + sessionStorage.getItem('userId') + '&reviewId=' + this.modalReviewId)
            .then(res => {

                this.modalLikeOk = res.data;
                
            })
        },

        // 로그인 한 유저의 리뷰_좋아요 클릭
        changeLike(){

            if(sessionStorage.getItem('isLogin') != null && sessionStorage.getItem('userId') != null)
            {
                axios({
                    url: backUrl + '/changeLike',
                    method: "POST",
                    data: {
                        userId : sessionStorage.getItem('userId'),
                        reviewId : this.modalReviewId
                        },

                }).then(res => {
                    
                    // 좋아요 체크 메서드
                    this.likeCheck();

                    // 좋아요 갯수 재확인
                    this.LikeCount();

                }).catch(err => {
                    alert(err);
                })

            } else {

                if (!confirm("로그인 후 이용하실 수 있습니다.\n'확인'을 누르면 로그인페이지로 이동됩니다.")) {
                    // 취소(아니오) 버튼 클릭 시 이벤트
                    return;

                } else {
                    // 확인(예) 버튼 클릭 시 이벤트
                    this.$nuxt.$router.push("/login");
                }

            }

            

        },

        // 해당 게시글의 좋아요 갯수
        LikeCount(){

            axios.get(backUrl + '/likeAllCount?reviewId=' + this.modalReviewId)
                .then(res => {

                    this.modalLikeCount = res.data;
                    
                })
        },

    },
}
</script>
<style lang="scss" scoped>
h1 {
    font-size: 20px;
    font-weight: bold;
}

.cardBox {
    // width: 95%;
    height: 300px;
    margin-left: 3%;

    border: 1px solid lightgray;
    border-radius: 10px;
    cursor: pointer;

    // 사진이 cardbox를 넘어가면 숨기기
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;

    // 사진과 작성자 정렬
    display: flex;
    flex-direction: column;
    justify-content: space-between;

    background-size: cover;
    background-position: center;

    outline: none;
}

.cardBox img {
    position: relative;
    width: 100%;
    max-height: 250px;
    transition-duration: 0.3s;
}

/* 마우스 올릴시에 크기 커지도록 */
.cardBox img:hover {
    transform: scale(1.1, 1.1);
    transition-duration: 0.5s;
}

.cardBox span {
    font-weight: 500;
    color: white;

    padding: 10px;
}


</style>