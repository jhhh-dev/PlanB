<template>
    <div>
        <div style="text-align: center;">
            <div style="width: 80%; display: inline-block;">
                <div class="left-content" style="width: 20%; height: 100%; float:left;">
                    <div class="snb_list" style="margin:40px 0 20px;">
                        <v-card>  
                            <v-col class="">
                                <v-row ><nuxt-link to="/mypage" class="snb__link" style="font-weight: bolder; font-size: 25px;">마이 페이지</nuxt-link> </v-row>
                                <v-row ><nuxt-link to="/mypages/userInfo" class="snb__link smenu">회원 정보</nuxt-link> </v-row>
                                <v-row ><nuxt-link to="/mypages/myorder" class="snb__link smenu sclick">구매 내역</nuxt-link> </v-row>
                                <v-row ><nuxt-link to="/mypages/mylike" class="snb__link smenu">관심 상품</nuxt-link> </v-row>
                                <v-row ><nuxt-link to="/mypages/myreview" class="snb__link smenu">리뷰 내역</nuxt-link> </v-row>
                            </v-col>
                        </v-card> 
                    </div>
                </div>
                <div class="right-content" style=" width: 80%; height: 100%; float:right; padding-left: 10px;">
                    <div>
                        <div style=" width: 100%; height: 100px;">
                            <div style="float: left; margin:20px 20px 0;">
                                <div style="float: left; margin:20px;"><h1>구매 상세</h1></div> 
                                <div style="float: left; margin-top:35px; font-weight: bold;"></div>
                            </div>
                        </div>
                        <hr />
                        <div style="width: 70%; margin-left: 30px; margin-bottom: 50px;">
                            <v-row style=" display: none;">
                                <v-col>
                                    <v-text-field label="이메일"></v-text-field>
                                </v-col>
                                <v-col>
                                    <v-card-actions>
                                        <nuxt-link to="">
                                            <v-btn color="lighten-2" class="userBtn">구매하기 </v-btn>
                                        </nuxt-link>
                                    </v-card-actions>
                                </v-col>
                            </v-row>
                            <v-card class="mx-auto detailForm">
                                <v-card-text>
                                    <div>주문번호 {{ order_id }} &nbsp;&nbsp;&nbsp;&nbsp; 구매날짜 {{ order_date }}</div>
                                    <nuxt-link :to="{ path: '/detail/' + `${pro_id}` }">
                                        <p class="text-h6 text--primary">
                                            {{ pro_name }}
                                        </p>
                                    </nuxt-link>
                                    <div class="text--primary payDetail">
                                        <v-row class="row_detail">
                                            <v-col class="detailTitle" style="font-weight: bold;">결제 정보</v-col>
                                            <v-col class="detailDate"></v-col>
                                        </v-row>
                                        <v-row class="row_detail">
                                            <v-col class="detailTitle">상품 가격</v-col>
                                            <v-col class="detailDate">{{ pay_price }} 원</v-col>
                                        </v-row>
                                        <v-row class="row_detail">
                                            <v-col class="detailTitle">배송비</v-col>
                                            <v-col class="detailDate">{{ order_fee }} 원</v-col>
                                        </v-row>
                                        <hr />
                                        <v-row class="row_detail" style="margin-top: 10px;">
                                            <v-col class="detailTitle">결제 방식</v-col>
                                            <v-col class="detailDate">{{ pay_type }}</v-col>
                                        </v-row>
                                        <v-row class="row_detail">
                                            <v-col class="detailTitle">총 결제 금액</v-col>
                                            <v-col class="detailDate">{{ pay_price_all }} 원</v-col>
                                        </v-row>
                                    </div>
                                    <div class="text--primary payDetail" style="margin-top: 20px;">
                                        <v-row class="row_detail">
                                            <v-col class="detailTitle" style="font-weight: bold;">배송 정보</v-col>
                                        </v-row>
                                        <v-row class="row_detail">
                                            <v-col class="detailTitle">{{ order_name }}</v-col>
                                        </v-row>
                                        <v-row class="row_detail">
                                            <v-col class="detailTitle">{{ order_addr }}</v-col>
                                        </v-row>
                                        <v-row class="row_detail" style="margin-bottom: 5px;">
                                            <v-col class="detailTitle">{{ order_phone }}</v-col>
                                        </v-row>
                                        <hr />
                                    </div>
                                </v-card-text>
                                <v-card-actions>
                                    <div class="payDetail" style="margin-bottom: 40px; ">
                                        <v-row>
                                            
                                            <v-col>
                                                <!-- <nuxt-link to="/mypages/reviewForm">
                                                    <v-btn color="lighten-2" style="font-weight: 100; height: 40px; background-color: #222; color: white !important;">리뷰쓰기 </v-btn>
                                                </nuxt-link> -->
                                                
                                                <v-btn
                                                color="lighten-2"
                                                dark
                                                to="/mypages/myreview"
                                                class="dialogbtn"
                                                v-if="reviewValid2"
                                                >
                                                리뷰 확인
                                                </v-btn>

                                                <v-dialog
                                                v-model="dialog"
                                                persistent
                                                max-width="600px"
                                                >
                                                    <template v-slot:activator="{ on, attrs }">
                                                        <v-btn
                                                        color="lighten-2"
                                                        dark
                                                        v-bind="attrs"
                                                        v-on="on"
                                                        class="dialogbtn"
                                                        v-if="reviewValid1"
                                                        >
                                                        리뷰 쓰기
                                                        </v-btn>
                                                    </template>
                                                    <v-card>
                                                        <v-card-title>
                                                            &nbsp;&nbsp; 리뷰 작성
                                                        </v-card-title>
                                                        <v-form>
                                                            <v-card-text>
                                                                <v-container>
                                                                    <v-row>
                                                                        <v-col>
                                                                            <v-textarea
                                                                                outlined
                                                                                name="input-7-4"
                                                                                label="리뷰 내용을 입력하세요!"
                                                                                v-model="reviewContent"
                                                                                :error-messages="contentErrors"
                                                                                aria-required="true"
                                                                            ></v-textarea>
                                                                        </v-col>
                                                                    </v-row>
                                                                    <v-file-input
                                                                        :rules="rules"
                                                                        accept="image/png, image/jpeg, image/jpg"
                                                                        placeholder=""
                                                                        prepend-icon="mdi-camera"
                                                                        label="사진을 올려주세요!"
                                                                        v-model="uploadFile"
                                                                        :error-messages="imgErrors"
                                                                        aria-required="true"
                                                                    ></v-file-input>
                                                                
                                                                </v-container>
                                                                <!-- <small>*indicates required field</small> -->
                                                            </v-card-text>
                                                            <v-card-actions>
                                                                <v-spacer></v-spacer>
                                                                <v-btn
                                                                color="blue darken-1"
                                                                text
                                                                @click="dialog = false"
                                                                >
                                                                    취소
                                                                </v-btn>
                                                                <v-btn
                                                                color="blue darken-1"
                                                                text
                                                                @click="reviewSubmit()"
                                                                >
                                                                    작성하기
                                                                </v-btn>
                                                            </v-card-actions>
                                                        </v-form>
                                                    </v-card>
                                                </v-dialog>


                                            </v-col>
                                        </v-row>
                                    </div>
                                </v-card-actions>
                            </v-card>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
import { validationMixin } from 'vuelidate'
import { required, maxValue } from 'vuelidate/lib/validators'
import axios from "axios"

export default {
    mixins: [validationMixin],
    

    data: () => ({
        email: '',
        phone: '',
        order_id: '',
        order_date: '',
        pro_name: '',
        pay_price: '',
        order_fee: '',
        pay_type: '',
        pay_price_all: '',
        order_name: '',
        order_addr: '',
        order_phone: '',
        dialog: false,
        pro_id: '',

        rules: [
            value => !value || value.size < 1000000 || '사진을 등록해주세요.(1MB)',
        ],

        //maxValue: 1000000,

        reviewContent: '',
        reviewImg: null,
        reviewValid: false,
        uploadFile: '',

        reviewValid1: true,
        reviewValid2: false,

        contentErrors: [],
        imgErrors: [],
    }),

    validations: {
        reviewContent: { required },
        uploadFile: { required },
    },

    mounted() {
        this.selectOrderDetatil();
        this.reviewCheck();
    },

    methods: {
        async selectOrderDetatil () {
            await axios.get(process.env.baseUrl+'/userInfo/selectOrderDetail', {
                params : {
                    //orderId: $route.query.orderId,
                    //orderId: $route.params.orderId
                    orderId: this.$route.query.orderId,
                }
            })
            .then((res) => {
                console.log(res.data)
                this.order_id = res.data.orderId
                this.order_date = res.data.orderDate
                this.pro_name = res.data.proName
                this.pay_price = res.data.payPrice
                this.order_fee = res.data.orderFee
                this.pay_type = res.data.payType
                this.pay_price_all = res.data.payPrice
                this.order_name = res.data.orderReciver
                this.order_addr = res.data.orderAddr
                this.order_phone = res.data.orderPhone
                this.pro_id = res.data.proId

                axios.get(process.env.baseUrl+'/review/reviewCheck', {
                    params : {
                        
                        orderId: this.order_id,
                    }
                })
                .then((res) => {
                    console.log(res.data)
                    if(res.data >= 1){
                        this.reviewValid1 = false
                        this.reviewValid2 = true
                    }else{
                        this.reviewValid1 = true
                        this.reviewValid2 = false
                    }
                });
            });
        },

        // async insertReview(){
        //     await axios.post('http://localhost:8080/userInfo/selectOrderDetail', {
        //         params : {
        //             //orderId: $route.query.orderId,
        //             //orderId: $route.params.orderId
        //             orderId: this.$route.query.orderId,
        //         }
        //     })
        // },

        reviewSubmit(){
            const formData = new FormData();
            formData.append('uploadFile', this.uploadFile)
            formData.append('reviewContent', this.reviewContent)
            formData.append('proId', this.pro_id)
            formData.append('orderId', this.order_id)
            formData.append('userId', sessionStorage.getItem('userId'))
            
            console.log("test")
            console.log(formData)

            if (!this.$v.reviewContent.required) {

                this.contentErrors= ['내용을 입력해주세요.'];

            } else if (!this.$v.uploadFile.required
                   // || !this.$v.uploadFile.maxValue
                        ) {

                this.contentErrors = null
                this.imgErrors = ['사진을 등록해주세요.']

            } else{

                this.pwErrors = null
                axios({
                    url: process.env.baseUrl+'/review/reviewInsert',
                    method: "POST",
                    data: formData,
                    headers: {
                        'Content-Type': 'multipart/form-data'
                    }

                }).then(res => {
                    
                    // console.log(this.uploadFile);
                    // console.log("전송 성공");

                    // 성공 alert
                    alert("리뷰 등록이 완료되었습니다.");

                    // dialog 창 닫기
                    this.dialog = false;
                    this.$nuxt.$router.push("/mypages/myorder")
                    //this.reviewCheck();
                    
                    // productList로 이동
                //  this.$nuxt.$router.push("/admin/product")
                    
                    

                }).catch(err => {

                    alert("리뷰 등록 불가. 내용을 확인해주세요.");
                })

            }
        },

        reviewCheck(){
            console.log(this.order_id + "이거왜")
            axios.get(process.env.baseUrl+'/review/reviewCheck', {
                params : {
                    
                    orderId: this.order_id,
                }
            })
            .then((res) => {
                console.log(res.data)
                if(res.data >= 1){
                    this.reviewValid = true
                }else{
                    this.reviewValid = false
                }
            });
        },




        
    },
    
}
</script>
    




<style>

.snb__link{
    font-size: 20px;
    margin: 10px 20px;
    color: black !important;
    border-bottom:none;
    
}
.smenu{
    color: rgb(141, 140, 140) !important;
}
.ctitle{
    color: #222;
}
.sclick{
    font-weight: bold; 
    color: #222 !important; 
    text-decoration: underline !important;
}

.detailForm{
    margin: 20px 0 !important;
    width: 100%;
    padding-top: 30px;
}
.payDetail{
    width: 60%;
    margin: 0 auto;
}
.detailTitle{
    text-align: left;
}
.detailDate{
    text-align: right;
}
.row_detail{
    margin-top: -5px !important;
}
.dialogbtn{
    font-weight: 100; 
    height: 40px !important; 
    background-color: #222; 
    color: white !important;
}

</style>