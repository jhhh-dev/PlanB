<template>
    <div style="padding-left: 18%; padding-right: 18%; margin-top: 20px; ">
        <div style="display: flex; justify-content: space-between;">
            <div>
                <h1>Most Popular</h1>
                <span style="color: gray">인기 상품</span>
            </div>
            
            <!-- <nuxt-link to="/shop">
                <br />
                <span style="color: gray;"> + 페이지로 이동</span>
            </nuxt-link> -->
        </div>
        
        <div v-if="popularList.length != 0">
            <v-row style="margin-top: 10px;" >
                <v-col v-for="(data, index) in popularList" :key="index" cols="3" >
        
                    <div class="cardBox" @click="detailButton( data.proId )">
        
                        <div class="imgBox">
                            <img :src="data.proImg" width="100%" />
                        </div>
        
                        <div style="padding: 5px;">

                            <div style="padding-top: 10px; display: flex; justify-content: space-between; margin-bottom: 5px;">
                                <b>
                                    {{ data.proBrand }}
                                </b>
                                <span style="color: gray; position: relative; top: -3px;">
                                    <v-icon color="secondary lighten-5">mdi-bookmark</v-icon>
                                    {{ data.bookmarkCount }}
                                </span>
                            </div>
                            <span style="color: gray;">
                                {{ data.proName }}
                            </span><br/>
                            
                            <div style="padding-top: 10px; display: flex; justify-content: space-between;">
                                <b>
                                    {{ data.proPrice | comma }}
                                </b>

                                
                            </div>
                            
                        </div>

                    </div>

                </v-col>
            </v-row>

            <!-- <div style="text-align: center; margin-top: 30px;">
                <v-btn
                    color="secondary lighten-4"
                    v-on:click="appendNews()"
                    :disabled="this.dataFull === true"
                >
                    More ({{ this.cntProduct }} / {{ this.totProduct }})
                </v-btn>
            </div> -->

        </div>
        

        <div v-else style="height: 200px; display:flex; align-items:center; justify-content: center;">
            <h1 style="color: #a5a5a5; ">등록된 상품이 없습니다</h1>
        </div>
    
    
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
        
        // 리스트 가져오기
        this.getPopularList()

    },

    data() {
        return {
            
            // slick carousel
            settings:{
                "dots": false,
                "autoplay": true,
                "autoplaySpeed": 3000,
                // "focusOnSelect": true,
                "infinite": true,
                "slidesToShow": 5,
                "slidesToScroll": 1,
                // "touchThreshold": 5
            },

            // 더보기 관련
            popularList: {}, // 전체 상품 리스트
            // product: {}, // 화면에 노출되는 상품
            // totProduct: 0, // 전체 상품 데이터 수
            // cntProduct: 4, // 화면에 노출할 상품 데이터 수 (초기 세팅 = 4)
            // dataFull: false, // 전체 데이터보다 많은 데이터 호출 여부

        }
    },

    methods: {

        // 인기상품 리스트 가져오기
        getPopularList() {

            axios.get(backUrl + '/mainPopular')
                .then(res => {
                    
                    // 상품 담기
                    this.popularList = res.data;

                    // 상품 이미지 가져오기
                    for (let i = 0; i <= this.popularList.length; i++) {
                        axios.get(backUrl + '/showImage?fileName=' + this.popularList[i].proImg)
                            .then(res => {

                                // 변수에 DB이미지 링크 담기
                                this.popularList[i].proImg = res.config.url;

                            })
                    }


                    // let data = []
                    
                    // for(var i=0; i<this.cntProduct; i++){
                    //     data.push(res.data[i]) // 보여줄 상품(data 배열) 에 최초 4개(cntProduct) 추가하기 
                    // }
                    
                    // this.popularList = res.data //전체 상품 담기
                    // this.product = data  //보여줄 데이터 변수에 data 담기
                    // this.totProduct = this.popularList.length //전체 상품 갯수


                })
        },

        // 상품 클릭 시 상세 페이지 이동
        detailButton(item) {

            console.log(item);
            this.$nuxt.$router.push("/detail/"+item);

        },



        // 더보기 클릭
        // appendNews() {
   
        //     // 전체 뉴스 개수보다 노출되는 뉴스 개수가 작은 경우
        //     if(this.cntProduct < this.totProduct){
        //         this.cntProduct += 4 // 노출 뉴스 개수 4개 증가
                
        //         let data = []

        //         for(var i=0;i<this.cntProduct;i++){
        //             data.push(this.popularList[i]) // 전체 뉴스에서 노출 뉴스 개수만큼 데이터 추출하여 data 배열에 추가
        //         }

        //         this.product = data // news 객체에 data 배열 업데이트
            
        //     // 전체 뉴스 개수와 노출되는 뉴스 개수가 같으면
        //     }else{
        //         this.dataFull = true // dataFull 객체를 true 상태로 변경
        //         alert('모든 상품을 불러왔습니다') // 모든 데이터 출력 알림
        //     }

        // },

    },

    filters:{
        comma(val){
            return String(val).replace(/\B(?=(\d{3})+(?!\d))/g, ",") + " 원";
        }
    }
}
</script>

<style lang="scss" scoped>
h1 {
    font-size: 20px;
    font-weight: bold;
}

.cardBox {
    margin-bottom: 20px;

    // width: 230px;
    height: 350px;
    // border: 1px solid lightgray;
    border-radius: 10px;

    cursor: pointer;

    // overflow: hidden;
    // text-overflow: ellipsis;
    // white-space: nowrap;

}
.imgBox {
    background-color: #f1f1f1;
    border-radius: 10px;
    max-height: 250px;
}

.imgBox img {
    position: relative;
    width: 100%;
    transition-duration: 0.3s;
}

/* 마우스 올릴시에 크기 커지도록 */
.imgBox img:hover {
  transform: scale(1.1, 1.1);
  transition-duration: 0.5s;
}
</style>