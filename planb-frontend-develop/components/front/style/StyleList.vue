<template >
    <div>
        <v-container class="my-10" style="margin: auto; max-width: 1300px;">
            
            <v-tabs v-model="option" centered>
                <v-tab v-for="item in items" :key="item.name" :items="option" item-value="value"  @click="test (item.value)">
                {{ item.name }}
                </v-tab>
            </v-tabs>
            <v-row class="my-5">
                <v-col xl="4" lg="4" md="4" sm="6" xs="12" v-for="(data, i) in list" :key="i">
                    <v-card class="dialogList" @click="dialog = true,reviewDetail(data.reviewId)">
                        <v-img
                            height="400"
                            v-bind:src="`${data.reviewImgList}`"
                        ></v-img>
                        
                        <v-card-text>
                            <v-row class="nameRow">
                                <div class="nameRowId">
                                    <v-icon> mdi-account-circle </v-icon>
                                    {{ data.userName }}
                                </div>
                                <div class="nameRowId">
                                    <v-icon>mdi-heart</v-icon>
                                    {{ data.likeCount }}
                                </div>
                            </v-row>
                        </v-card-text>
                        <v-card-text>
                            <p> 
                                {{data.reviewContent}}
                            </p>                   
                        </v-card-text>
                    </v-card>
                </v-col>
            </v-row>

            <v-dialog
                v-model="dialog"
                persistent
                max-width="600"
            >                
                <input type="hidden" v-model="reviewIdSave" />
                <v-card>
                    <v-card-title class="text-h5 grey lighten-2 ">
                        
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
                    
                    
                    <v-card-text class="proNameDiv">
                        <div class="proNameDiv1">
                            <nuxt-link :to="{ path: '/detail/' + `${pro_id}` }">
                                {{ pro_name }}
                            </nuxt-link>
                        </div>

                        <div v-if="this.likeValidGray">
                            <v-btn
                                icon
                                color="gray"
                                @click="likeActionInsert()"
                            >
                                <v-icon>mdi-heart</v-icon>
                                {{ like_count }}
                            </v-btn>
                        </div>
                        <div v-if="this.likeValidRed">
                            <v-btn
                                icon
                                color="red"
                                @click="likeActionDelete()"
                            >
                                
                            <v-icon>mdi-heart</v-icon>
                            <span>{{ like_count }}</span>
                                
                            </v-btn>
                        </div>
                    </v-card-text>
                    <v-card-text>
                        {{ review_content }}
                    </v-card-text>
                    <v-divider></v-divider>
                    <v-card-actions>
                        <v-spacer></v-spacer>
                        <v-btn
                            color="primary"
                            @click="dialog = false, tabCheck()"
                        >
                            목록으로
                        </v-btn>
                    </v-card-actions>
                </v-card>
            </v-dialog>
        </v-container>


        <infinite-loading ref="infiniteLoading" @infinite="infiniteHandler"></infinite-loading>
        
    </div>
</template>

<script>
import axios from "axios";
import InfiniteLoading from "vue-infinite-loading";

export default {
    name: "",

    data: () => ({
        model: 0,
        dialog: false,
        reviewImg: '',
        res: '',
        list: [],
        reviewId: '',

        like_count: '',
        review_img: '',
        review_content: '',
        user_name: '',
        pro_name: '',
        pro_id: '',

        likeValidGray: true,
        likeValidRed: false,
        
        reviewIdSave: '',

        imageurl:'',

        //페이징
        loadNum: 0,


        items: [
            {name: '인기', value: 0},
            {name: '최신', value: 1}
        ],
        option: 0,

    }),

    mounted() {
        //this.reviewList();
    },
    //api 테스트 중
    methods:{

        //리뷰 상세
        async reviewDetail(reviewIds){
            this.reviewIdSave = reviewIds
            //좋아요체크
            axios.get('http://localhost:8080/review/checkLike', {
                params : {
                    userId: sessionStorage.getItem('userId'),
                    reviewId: reviewIds,
                }
            }).then((res)=>{
                if(res.data == 'OK'){
                    this.likeValidRed = true
                    this.likeValidGray = false
                }else{
                    this.likeValidRed = false
                    this.likeValidGray = true
                }
            }).then(
                axios.get('http://localhost:8080/review/reviewDetail', {
                    params : {
                        reviewId: reviewIds,
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
                    
                }).catch((err)=>{
                    alert('오류 발생'+err)
                })
            )
        },
        //좋아요 누르기
        likeActionInsert(){
            axios.get('http://localhost:8080/review/insertLike', {
                params : {
                    userId: sessionStorage.getItem('userId'),
                    reviewId: this.reviewIdSave,
                }
            }).then((res)=>{
                axios.get('http://localhost:8080/review/likeCount', {
                    params : {
                        reviewId: this.reviewIdSave,
                    }
                }).then((res)=>{
                    this.like_count = res.data
                    this.likeValidGray = !this.likeValidGray
                    this.likeValidRed = !this.likeValidRed
                }).catch((err)=>{
                    this.$router.push('/login')
                    alert('로그인이 필요한 서비스입니다.')
                })
            }).catch((err)=>{
                this.$router.push('/login')
                alert('로그인이 필요한 서비스입니다.')
            })
            
        },
        //좋아요 취소하기
        likeActionDelete(){
            axios.get('http://localhost:8080/review/deleteLike', {
                params : {
                    userId: sessionStorage.getItem('userId'),
                    reviewId: this.reviewIdSave,
                }
            }).then((res)=>{
                axios.get('http://localhost:8080/review/likeCount', {
                    params : {
                        reviewId: this.reviewIdSave,
                    }
                }).then((res)=>{
                    this.like_count = res.data
                    this.likeValidGray = !this.likeValidGray
                    this.likeValidRed = !this.likeValidRed
                }).catch((err)=>{
                    alert('오류 발생'+err)
                })
                
            }).catch((err)=>{
                alert('오류 발생'+err)
            })
            
        },
        //
        tabCheck(){
            
            this.imageurl = ''
            
        },

        //사진 불러오기
        async getImage(review_img){
            await this.$nextTick();
                axios.get(process.env.baseUrl+'/showImage?fileName=' + review_img)
                .then(res => {
                    //console.log(res+"res")
                    //console.log(res.config+"res.config")
                    //console.log(res.config.url + "res.config.url")
                    // 변수에 DB이미지 링크 담기
                    this.imageurl = res.config.url;
                    console.info(res.config.url+"dsfsdafasfdsffd")
                    
                }).catch((err)=>{
                    alert('오류 발생'+err)
                })
        },


        //무한스크롤
        infiniteHandler() {
            const EACH_LEN = 12
            console.log("지금 infiniteHandler" + this.option)
            axios.get(process.env.baseUrl+'/review/reviewList?page='+this.loadNum + "&option="+this.option)
            .then(res => {
                //console.log("샐행은 되니?")
                console.log(res.data+"무한로딩중~.~"+this.loadNum +"지금 " + this.option)

                setTimeout(() => {
                    if(res.data.length != 0) {
                        
                        const data = res.data
                        for(let key in data){
                            this.list.push(data[key])
                            //console.log("after", this.list)

                            for(let i = 0;this.list.length > i; i++){
                                this.list[i].reviewImgList = process.env.baseUrl+"/showImage?fileName="+ this.list[i].reviewImg
                            }
                        }
                        
                        //this.list = res.data
                        console.log("after", this.list.length, this.loadNum)
                        this.loadNum++
                        
                        this.$refs.infiniteLoading.stateChanger.loaded()
                        // 끝 지정(No more data) - 데이터가 EACH_LEN개 미만이면 
                        //if(res.data.length / EACH_LEN < 1) {
                        if(res.data.length/EACH_LEN < 1){
                            console.log("여기왔음")
                            this.$refs.infiniteLoading.stateChanger.complete()
                        }
                        
                    } else {
                        // 끝 지정(No more data)
                        this.$refs.infiniteLoading.stateChanger.complete()
                    }
                }, 0)

            })
            .catch(err => {
                console.log(err)
                alert('에러'+err);
                
            })
        },

        reset() {
            //console.log("리셋 실행")
            this.$refs.infiniteLoading.stateChanger.reset();
            //console.log("리셋 실행 후")
        },

        async test (value) {
            //리셋하기
            this.list = []
            this.loadNum = 0
            await this.reset().then(()=>{
                console.log("지금 test " + this.option + "///"+value)
                this.option = value
                this.infiniteHandler()
            })
        },


    },

    components: {
        InfiniteLoading,
    }
};
</script>
<style>
.dialogList:hover {
    cursor: pointer;
}
.nameRow{
    justify-content: space-between;
}
.nameRowId{
    margin:5px;
}
.nameDiv{
    font-size: 15px;
}
.nameDivIcon{
    margin-right: 5px;
    margin-top: -6px;
}
.proNameDiv{
    display: flex;
    justify-content: space-between;
}
.proNameDiv1{
    margin-top: 8px;
}

</style>