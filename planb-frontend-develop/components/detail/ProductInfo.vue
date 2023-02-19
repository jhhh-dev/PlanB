<template>
    <v-row justify="center">
                    
        <v-col cols="6">
            <div class="imageBox">
                <img :src="imageurl" 
                width="100%" />                
            </div>
        </v-col>

        <v-col cols="6">
            <div class="optionBox" style="width: 100%; border-left: 1px solid lightgray; height: auto; padding: 0px 30px 30px 30px;">
                
                <div style="margin-bottom: 50px; font-size: 20px;">
                    <span><b>{{ item.proBrand }}</b></span> <br />
                    <p style="color:gray">{{ item.proName }}</p>
                </div>

                <div style="display: flex; justify-content: space-between; margin-bottom: 20px;">
                    <span style="color: gray; font-size: 16px;">사이즈</span>

                    <b style="font-size: 18px;">{{ item.proSize }} MM</b>
                </div>

                <div style="border-bottom: 1px solid lightgray;"></div>

                <div style="display: flex; justify-content: space-between; margin-top: 20px;">
                    <span style="color: gray; font-size: 16px;">구매 가격</span>
                    
                    <b style="font-size: 18px;">{{ item.proPrice | comma }} 원</b>
                </div>

                <div style="display: flex; flex-direction: column; height: 120px; justify-content: space-between; margin-top: 50px;">
                    
                    <v-btn color="secondary" style="height: 50px;" @click="productOrder()">
                        <v-icon size="18">mdi-credit-card-outline</v-icon>
                        &nbsp;
                        <span style="font-size: 18px;">구매하기</span>
                    </v-btn>
                    
                    <v-btn style="height: 50px;" @click="changeBookmark()" v-if="this.bookmarkOK === 0">
                        <v-icon size="18">mdi-bookmark-outline</v-icon>
                        &nbsp; 
                        <span style="font-size: 18px;">관심상품</span>
                    </v-btn>

                    <v-btn style="height: 50px;" @click="changeBookmark()" v-else color="error">
                        <v-icon size="18">mdi-bookmark-outline</v-icon>
                        &nbsp; 
                        <span style="font-size: 18px;">관심상품</span>
                    </v-btn>
                    
                </div>

                <div style="margin-top: 50px; ">
                    <p style="color: gray;">배송 정보</p>
                    <v-row>
                        <v-col cols="2">
                            <img src="@/assets/images/빠른배송.png" width="50px" style="margin-bottom: 15px;"/>
                            
                            <img src="@/assets/images/일반배송.png" width="50px" style="margin-bottom: 15px;"/>
                        </v-col>

                        <v-col>
                            <div>
                                <b>빠른배송 5,000원</b><br/>
                                지금 결제 시 내일 도착 예정
                            </div>
                            <br />
                            <div>
                                <b>일반배송 3,000원</b><br/>
                                검수 후 배송 · 5-7일 내 도착 예정
                            </div>
                        </v-col>
                    </v-row>

                </div>

                <div style="background-color: rgb(39, 39, 39); width: 100%; display: flex; justify-content: center; margin-top: 30px;">
                    <img src="@/assets/images/detail_banner.jpg" height="80px"/>
                </div>
                

                <br /><br />
                

                <div style="margin-top: 20px; ">
                    
                    <p><b>구매 전 꼭 확인해주세요!</b></p>
                    <PanelList />
                    
                </div>
                <br />

                <div style="color: gray; font-size: 13px; padding-top: 30px; border-top: 1px solid lightgray;">
                    플랜비(주)는 통신판매 중개자로서 통신판매의 당사자가 아닙니다. 본 상품은 개별판매자가 등록한 상품으로 상품, 상품정보, 거래에 관한 의무와 책임은 각 판매자에게 있습니다. 단, 이용약관 및 정책, 기타 거래 체결 과정에서 고지하는 내용 등에 따라 검수하고 보증하는 내용에 대한 책임은 플랜비(주)에 있습니다.
                </div>
                
                
            </div>
        </v-col>
    </v-row>
</template>

<script>
import axios from 'axios';
import PanelList from './PanelList.vue';


    const backUrl = 'http://localhost:8080';

    export default {

        components: { PanelList },

        props:{
            item : {},
            
        },

        data() {
            return {
                proId:'',                
                imageurl:'',

                //관심상품 등록여부
                bookmarkOK:'',

            }
        },

        mounted() {
            
            // console.log(sessionStorage.getItem('isLogin'))
            // console.log(sessionStorage.getItem('userId'))
        },

        // mounted 보다 늦게 실행
        updated() {
            
            // 렌더링이 끝난 후 실행
            this.$nextTick(() => {
                
                
                this.getImage();

                // 관심상품 등록 여부
                this.getBookmarkCount();
            })
            
        },
        
        methods: {

            // 이미지 파일 가져오기
           async getImage(){
              await this.$nextTick();
                axios.get(backUrl + '/showImage?fileName=' + this.item.proImg)
                .then(res => {

                    // 변수에 DB이미지 링크 담기
                    this.imageurl = res.config.url;
                    
                })
            },

            // 구매하기 버튼
            productOrder() {

                // console.log(this.item.proId);

                //로그인 여부 확인
                if (sessionStorage.getItem('isLogin') != null && sessionStorage.getItem('userId') != null) {
                    // 구매화면으로 이동
                    this.$nuxt.$router.push("/order/" + this.item.proId);

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

            // 관심상품 등록 여부
            getBookmarkCount(){

                axios.get(backUrl + '/bookmarkCount?userId=' + sessionStorage.getItem('userId') + '&proId=' + this.item.proId)
                .then(res => {

                    // 유저와 상품에 해당하는 bookmark 갯수
                    this.bookmarkOK = res.data;
                    
                })
            },

            // 북마크 버튼 클릭
            changeBookmark(){

                //로그인 여부 확인
                if(sessionStorage.getItem('isLogin') != null && sessionStorage.getItem('userId') != null)
                {

                    if (!confirm(this.bookmarkOK === 1?"관심상품을 해제 하시겠습니까?":"관심상품으로 등록 하시겠습니까?")) {
                        // 취소(아니오) 버튼 클릭 시 이벤트

                        return;

                    } else {
                        // 확인(예) 버튼 클릭 시 이벤트

                        axios({
                            url: backUrl + '/bookmarkChange',
                            method: "POST",
                            data: {
                                userId: sessionStorage.getItem('userId'),
                                proId: this.item.proId
                            },

                        }).then(res => {

                            alert(this.bookmarkOK === 1?"관심상품에서 해제되었습니다.":"관심상품으로 등록되었습니다.");

                            // 북마크 카운트 가져오기
                            this.getBookmarkCount();
                            
                            // 화면 새로고침
                            // this.$router.go(this.$router.currentRoute);

                        }).catch(err => {
                            alert(err);
                        })


                    }


                    
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
        },
        
        filters:{
            comma(val){
                return String(val).replace(/\B(?=(\d{3})+(?!\d))/g, ",");
            },
        },
    }
</script>

<style lang="scss" scoped>
.imageBox {
    width: 100%; 
    height: auto; 
    position: sticky; 
    top: 100px; 
    background-color: #f1f1f1; 
    text-align: center; 
    border-radius: 10px;
}
</style>