<template>
    <div>
        <div class="fullAreaBox" >

            <!-- 상단 div -->
            <div style="width: 100%;">

                <ProductInfo 
                :item = this.item />

            </div>

            <br/>
            <div style="border-bottom: 1px solid lightgray;"></div>
            <br/>

            <!-- 스타일 -->
            <div>
                <ProductStyle
                :productId = this.productId />
            </div>

            <br/>
            <div style="border-bottom: 1px solid lightgray;"></div>
            <br/>

            <!-- 같은 브랜드 다른 상품 -->
            <div>
                
                <BrandProduct
                :productId = this.productId />
            </div>

            

        </div>


    </div>
</template>

<script>
import axios from 'axios';
import BrandProduct from '../../components/detail/BrandProduct.vue';
import ProductInfo from '../../components/detail/ProductInfo.vue'
import ProductStyle from '../../components/detail/ProductStyle.vue';

const backUrl = 'http://localhost:8080';

    export default {
        
        components: { ProductInfo, ProductStyle, BrandProduct },

        mounted() {
            
            // 변수에 담기
            this.productId = this.$route.params.detailNum;

            // 상품 정보 가져오기
            this.getDetailInfo();

            // 조회 수 증가하기
            this.plusReadCount();

        },
  
        
        data() {
            return {
                
                productId: '',
                item:[],
            }
        },

        methods: {
            
            // 상품정보 가져오기
            getDetailInfo() {
                
                axios({
                    url: backUrl + '/detailInfo?proId=' + this.productId,
                    method: "GET",

                }).then(res => {
                    
                    //변수에 담기
                    this.item=res.data;
                    // console.log(this.item);

                }).catch(err => {

                    alert(err);
                })
            },

            // 조회 수 증가
            plusReadCount(){

                // console.log("조회수 증가 대상은? : " + this.productId);

                axios({
                    url: backUrl + '/plusReadCount?proId=' + this.productId,
                    method: "GET",

                }).then(res => {


                }).catch(err => {

                    alert(err);

                })


            }
        },
    }
</script>

<style lang="scss" scoped>

.fullAreaBox {
    padding: 50px 15% 50px 15%;
}

.fullAreaBox p {
    font-size: 18px;
}
</style>