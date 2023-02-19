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
                                <v-row ><nuxt-link to="/mypages/myorder" class="snb__link smenu">구매 내역</nuxt-link> </v-row>
                                <v-row ><nuxt-link to="/mypages/mylike" class="snb__link smenu">관심 상품</nuxt-link> </v-row>
                                <v-row ><nuxt-link to="/mypages/myreview" class="snb__link smenu sclick">리뷰 내역</nuxt-link> </v-row>
                            </v-col>
                        </v-card> 
                    </div>
                </div>
                <div class="right-content" style=" width: 80%; height: 100%; float:right; padding-left: 10px;">
                    <div>
                        <div style=" width: 100%; height: 100px;">
                            <div style="float: left; margin:20px 20px 0;">
                                <div style="float: left; margin:20px;"><h1>리뷰 내역</h1></div> 
                                <div style="float: left; margin-top:35px; font-weight: bold;"></div>
                            </div>
                        </div>
                        <hr />
                        <v-container>
                            <v-card>
                                <v-card-subtitle>
                                    <v-simple-table>
                                        <template v-slot:default>
                                            <colgroup>
                                                <col width="200px">
                                                <col width="600px">
                                                <col width="150px">
                                            </colgroup>
                                            <thead>
                                                <tr>
                                                    <th class="text-left"></th>
                                                    <th class="text-left">제품명</th>
                                                    <th class="text-left">작성 날짜</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                
                                                <tr v-for="(data, i) in list" :key="i">
                                                    <td class="text-left dialogList" @click="dialog=true,reviewView (data.reviewId)"> 
                                                        <v-img
                                                            v-bind:src="`${data.reviewImgList}`"
                                                            width="70"
                                                            height="60"
                                                            cover
                                                        ></v-img>    
                                                    </td>
                                                    <td class="text-left"> {{ data.proName }}</td>
                                                    <td class="text-left"> {{ data.reviewDate }}</td>
                                                </tr>
                                            
                                                <tr >
                                                    <td colspan="3" @click="moreList()" v-if="listCheck">
                                                        더보기
                                                    </td>
                                                    
                                                </tr>
                                                <!-- 내역 없을 시 -->
                                                <tr v-if="islist">
                                                    <td colspan="3">
                                                        <p class="nothing">리뷰 내역이 없습니다.</p>
                                                        <div>
                                                        </div>
                                                    </td>
                                                </tr>

                                            </tbody>
                                        </template>
                                    </v-simple-table>
                                </v-card-subtitle>
                            </v-card>
                        </v-container>
                    </div>
                </div>
            </div>
        </div>
        <v-dialog
            v-model="dialog"
            persistent
            max-width="600"
        >                
            <v-card>
                <v-card-title class="text-h5 grey lighten-2">
                    
                    <div class="nameDivIcon">
                        <v-icon> mdi-account-circle </v-icon>
                    </div>
                    <div class="nameDiv">
                        {{user_name}}
                    </div>
                </v-card-title>                   
                <v-img
                    height="450"
                    :src="imageurl"
                ></v-img>
                <v-card-text>
                    <nuxt-link :to="{ path: '/detail/' + `${pro_id}` }">
                        {{ pro_name }}
                    </nuxt-link>
                </v-card-text>
                <v-card-text>
                    {{ review_content }}
                </v-card-text>
                <v-divider></v-divider>
                <v-card-actions>
                    <v-spacer></v-spacer>
                    <input type="hidden" v-model="reviewIdSave" />
                    <v-btn
                        color="gray"
                        @click="reviewDelete ()"
                    >
                        삭제하기
                    </v-btn>
                    <v-btn
                        color="primary"
                        @click="dialog = false"
                    >
                        목록으로
                    </v-btn>
                </v-card-actions>
            </v-card>
        </v-dialog>
    </div>
</template>
<script>
import axios from "axios"
export default {
    data: () => ({
        list: [],
        reviewImg: '',
        proName: '',
        reviewDate: '',
        islist: false,

        pageNum: 0,
        listCheck: true,

        dialog: false,

        user_name: '',
        pro_name: '',
        review_content: '',
        imageurl: '',
        pro_id: '',

        reviewIdSave: '',
        reviewId: ''


    }),

    mounted() {
        this.selectReviewList();
    },

    methods: {
        async selectReviewList () {
            await axios.get(process.env.baseUrl+'/userInfo/selectReviewList?page='+this.pageNum, {
                params : {
                   // userId: 'test@planb.com',
                    userId: sessionStorage.getItem('userId')
                }
            })
            .then((res) => {
                console.log(res.data)
                if(res.data.length == 0){
                    this.islist = true
                    this.listCheck = false
                } else{
                    //console.log(res+"process.env.baseUrl"+process.env.baseUrl)
                    this.list = res.data
                    for(let i = 0;this.list.length > i; i++){
                        this.list[i].reviewImgList = process.env.baseUrl+"/showImage?fileName="+ this.list[i].reviewImg
                    }
                    
                    // for (let i = 0; this.list.length > i; i++) {
                    //     console.log(res+"process.env.baseUrl"+this.list[i].reviewImg)
                    //     axios.get(process.env.baseUrl+"/showImage?fileName="+ this.list[i].reviewImg)
                    //     .then(res => {
                    //         console.log(res+"process.env.baseUrl"+res.config.url)
                    //         this.list[i].reviewImgList = res.config.url;

                    //     })
                    // }

                    this.pageNum++
                    console.log(res.data.length/5+"지금 몇개")
                    if(res.data.length/5 <1){
                        this.listCheck = false
                    }
                }
            });
        },
        //리뷰 더보기
        moreList (){
            axios.get(process.env.baseUrl+'/userInfo/selectReviewList?page='+this.pageNum, {
                params : {
                    //userId: 'test@planb.com',
                    userId: sessionStorage.getItem('userId')
                }
            })
            .then((res) => {
                console.log(res.data)
                if(res.data.length == 0){
                    //console.log("지금이 리뷰더보기 처음" + res.data.length)
                    this.listCheck = false;
                } else{
                    console.log(res)
                    //this.list = res.data

                    const data = res.data
                    for(let key in data){
                        this.list.push(data[key])
                        //console.log("after", this.list)

                        for(let i = 0;this.list.length > i; i++){
                            this.list[i].reviewImgList = process.env.baseUrl+"/showImage?fileName="+ this.list[i].reviewImg
                        }
                    }

                    console.log(res.data.length/5 + "지금 몇개")
                    this.pageNum++
                    if(res.data.length/5 <1){
                        this.listCheck = false
                    }
                }
            });
        },

        //리뷰상세보기
        reviewView (reviewId) {
            this.reviewIdSave = reviewId
            axios.get(process.env.baseUrl+'/review/reviewDetail', {
                params : {
                    reviewId: reviewId,
                }
            })
            .then((res) => {
                console.log(res.data)
                this.user_name = res.data.userName
                this.review_content = res.data.reviewContent
                // this.review_img = res.data.reviewImg
                this.like_count = res.data.likeCount
                this.pro_name = res.data.proName
                this.pro_id = res.data.proId

                this.getImage(res.data.reviewImg)
                
            })
            
        },

        //사진 불러오기
        async getImage(review_img){
            await this.$nextTick();
                axios.get(process.env.baseUrl+'/showImage?fileName=' + review_img)
                .then(res => {
                    // console.log(res+"res")
                    // console.log(res.config+"res.config")
                    // console.log(res.config.url + "res.config.url")
                    // 변수에 DB이미지 링크 담기
                    this.imageurl = res.config.url;
                    //console.info(res.config.url+"dsfsdafasfdsffd")
                    
                })
        },

        //리뷰 삭제
        reviewDelete (){
            axios.get(process.env.baseUrl+'/review/reviewDelete', {
                params : {
                    reviewId: this.reviewIdSave,
                    //userId: 'test@planb.com',
                    userId: sessionStorage.getItem('userId')
                }
            })
            .then((res) => {
                this.dialog = false
                //this.$router.push('/mypages/myreview') //새로고침
                //this.$router.go(this.$router.currentRoute);
                this.pageNum = 0
                this.listCheck = true
                this.selectReviewList ()
            })
            .catch((err)=>{
                alert('에러' + err)
            })
        }
    },

};
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
.dialogList:hover {
    cursor: pointer;
}
</style>