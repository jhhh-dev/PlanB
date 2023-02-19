<template>

    <div>
        <div style="display: flex; justify-content: space-between;">
            <h1>스타일</h1>

            <nuxt-link to="/style" v-if="styleList.length != 0">
                <v-btn color="secondary" small>더보기</v-btn>
            </nuxt-link>
        </div>
    
        <v-row style="margin-top: 10px;" v-if="styleList.length != 0" justify="start">
            <v-col v-for="(data, index) in styleList" :key="index" cols="3">
    
                <div class="cardBox" @click="styleDialog(data)">
    
                    <div class="imgBox">
                        <img :src="data.reviewImg" width="100%" />
                    </div>
    
                    <div style="padding: 5px;">
                        <p style="font-weight: bold; color: #a5a5a5;">
                            <v-icon>
                                mdi-account-circle
                            </v-icon>
                            {{ data.userId }}
                        </p>
                        
                        <span>{{ data.reviewContent }}</span>
                    </div>

                </div>



                

            </v-col>
        </v-row>

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

                        <div @click="changeLike()" style="padding: 4px; cursor: pointer; position: relative; top: -5px;" >
                            <v-icon size="20" v-if="this.modalLikeOk === 0" color="secondary">mdi-heart-outline</v-icon>
                            <v-icon size="20" v-else color="error">mdi-heart</v-icon>
                            <span>{{ this.modalLikeCount }}</span>
                        </div>

                    </div>
                    
                </v-card-text>

            </v-card>
        </v-dialog>

        
    
    
    </div>

</template>

<script>
import axios from 'axios';
const backUrl = 'http://localhost:8080';

export default {
    mounted() {

        this.$nextTick(() => {

            // 스타일 가져오기
            this.getStyle()

        })
    },

    props: {
        productId: '',
    },

    data() {
        return {
            // 스타일 리스트와 이미지파일
            styleList: '',
            styleImage: '',

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

        }
    },

    methods: {

        // 해당 상품의 스타일 후기 가져오기
        getStyle() {

            // console.log("props 데이터 : " + this.$props.productId)
            axios.get(backUrl + '/getStyle?proId=' + this.$props.productId)
                .then(res => {

                    // 스타일 가져오기
                    this.styleList = res.data;
                    console.log(this.styleList);

                    // 스타일 첨부 이미지 가져오기
                    for (let i = 0; i <= this.styleList.length; i++) {

                        axios.get(backUrl + '/showImage?fileName=' + this.styleList[i].reviewImg)
                            .then(res => {

                                // 변수에 DB이미지 링크 담기
                                // this.styleImage = res.config.url;
                                this.styleList[i].reviewImg = res.config.url;

                                // console.log(i + "번째 사진파일 : " + this.styleImage);
                            })

                    }


                })
        },

        // 후기 클릭 시 Dialog 열고, 좋아요 확인
        styleDialog(data) {
            
            // Dialog 열기
            this.dialog = true;

            console.log(data);

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
        likeCheck() {

            axios.get(backUrl + '/likeCount?userId=' + sessionStorage.getItem('userId') + '&reviewId=' + this.modalReviewId)
                .then(res => {

                    this.modalLikeOk = res.data;

                })
        },

        // 로그인 한 유저의 리뷰_좋아요 클릭
        changeLike() {

            if (sessionStorage.getItem('isLogin') != null && sessionStorage.getItem('userId') != null) {
                axios({
                    url: backUrl + '/changeLike',
                    method: "POST",
                    data: {
                        userId: sessionStorage.getItem('userId'),
                        reviewId: this.modalReviewId
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
    width: 250px;
    height: 330px;

    border-radius: 10px;
    cursor: pointer;

    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;

    margin: auto;

    font-size: 14px;
    color: gray;
}
.imgBox {
    
    border-radius: 10px;
    width: 250px;
    height: 250px;

    // 세로 가운데 정렬
    display: flex;
    flex-direction: column;
    justify-content: center;
    
    overflow: hidden;
    
}

.imgBox img {
    position: relative;
    width: 100%;
    transition-duration: 0.3s;
    border-radius: 10px;
}

/* 마우스 올릴시에 크기 커지도록 */
.imgBox img:hover {
  transform: scale(1.1, 1.1);
  transition-duration: 0.5s;
}

</style>