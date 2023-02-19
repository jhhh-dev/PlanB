<template>

    <div>
        <div style="display: flex; justify-content: space-between;">
            <h1>브랜드 상품</h1>
        </div>
    
        <v-row style="margin-top: 10px;" v-if="brandProduct.length != 0" justify="start">
            <v-col v-for="(data, index) in brandProduct" :key="index" cols="3">
    
                <div class="cardBox" @click="detailButton( data.proId )">
    
                    <div class="imgBox">
                        <img :src="data.proImg" width="100%" />
                    </div>
    
                    <div style="padding: 5px;">
                        <b style="text-decoration: underline;">
                            {{ data.proBrand }}
                        </b><br/>
                        <span>{{ data.proName }}</span><br/>
                        <b>{{ data.proPrice | comma }}</b>
                        
                        <!-- <span>{{ data.reviewContent }}</span> -->
                    </div>

                </div>

            </v-col>
        </v-row>

        <div v-else style="height: 200px; display:flex; align-items:center; justify-content: center;">
            <h1 style="color: #a5a5a5; ">동일 브랜드의 상품이 없습니다</h1>
        </div>
    
    
    </div>

</template>

<script>
import axios from 'axios';
const backUrl = 'http://localhost:8080';

export default {
    mounted() {

        this.$nextTick(() => {

            // 동일 브랜드의 상품 가져오기
            this.getBrandProduct()

        })
    },

    props: {
        productId: '',
    },

    data() {
        return {
            // 브랜드 상품 리스트와 이미지파일
            brandProduct: '',
        }
    },

    methods: {

        // 동일 브랜드의 상품 가져오기
        getBrandProduct() {

            // console.log("props 데이터 : " + this.$props.productId)
            axios.get(backUrl + '/getBrandProduct?proId=' + this.$props.productId)
                .then(res => {

                    // 상품 가져오기
                    this.brandProduct = res.data;
                    console.log(this.brandProduct);

                    // 상품 이미지 가져오기
                    for (let i = 0; i <= this.brandProduct.length; i++) {

                        axios.get(backUrl + '/showImage?fileName=' + this.brandProduct[i].proImg)
                            .then(res => {

                                // 변수에 DB이미지 링크 담기
                                // this.styleImage = res.config.url;
                                this.brandProduct[i].proImg = res.config.url;

                            })

                    }


                })
        },

        // 상품 클릭 시 상세 페이지 이동
        detailButton(item) {

            console.log(item);
            this.$nuxt.$router.push("/detail/"+item);

        },

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
    width: 250px;
    height: 330px;
    // border: 1px solid lightgray;
    border-radius: 10px;

    cursor: pointer;

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