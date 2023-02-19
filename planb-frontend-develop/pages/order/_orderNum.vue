<template>
    <div class="mainBox">
        <div class="fullAreaBox" >


            <!-- 통합 컴포넌트 -->
            <div>
                <OrderMain
                :item = this.item
                :productId = this.productId
                 />
            </div>


        </div>
    </div>
</template>

<script>
import axios from 'axios';
import OrderMain from '../../components/detail/OrderMain.vue';

const backUrl = 'http://localhost:8080';

    export default {
  components: { OrderMain },

        mounted() {
            
            // 대상 상품의 번호를 productId에 담기
            this.productId = this.$route.params.orderNum;

            // 상품 정보 가져오기
            this.getDetailInfo();

        },
        
        data() {
            return {
                
                // url로 받아오는 상품 번호
                productId: '',

                // 상품 번호에 해당하는 상품 정보
                item:[],
                
            }
        },

        methods: {

            // url로 받아온 productId 로 상품정보 가져오기
            getDetailInfo() {
                
                axios({
                    url: backUrl + '/detailInfo?proId=' + this.productId,
                    method: "GET",

                }).then(res => {
                    
                    //변수에 담기
                    this.item=res.data;

                }).catch(err => {

                    alert(err);
                })
            },

        },
    }
</script>

<style lang="scss" scoped>
    .mainBox {
        background-color: #fafafa;
    }
    .fullAreaBox {
        width: 100%;
        padding: 20px 40px 160px;
        max-width: 780px;

        margin: auto;
    }
    
</style>