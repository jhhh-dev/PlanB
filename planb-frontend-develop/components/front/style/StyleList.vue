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
                            ????????????
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

        //?????????
        loadNum: 0,


        items: [
            {name: '??????', value: 0},
            {name: '??????', value: 1}
        ],
        option: 0,

    }),

    mounted() {
        //this.reviewList();
    },
    //api ????????? ???
    methods:{

        //?????? ??????
        async reviewDetail(reviewIds){
            this.reviewIdSave = reviewIds
            //???????????????
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
                    alert('?????? ??????'+err)
                })
            )
        },
        //????????? ?????????
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
                    alert('???????????? ????????? ??????????????????.')
                })
            }).catch((err)=>{
                this.$router.push('/login')
                alert('???????????? ????????? ??????????????????.')
            })
            
        },
        //????????? ????????????
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
                    alert('?????? ??????'+err)
                })
                
            }).catch((err)=>{
                alert('?????? ??????'+err)
            })
            
        },
        //
        tabCheck(){
            
            this.imageurl = ''
            
        },

        //?????? ????????????
        async getImage(review_img){
            await this.$nextTick();
                axios.get(process.env.baseUrl+'/showImage?fileName=' + review_img)
                .then(res => {
                    //console.log(res+"res")
                    //console.log(res.config+"res.config")
                    //console.log(res.config.url + "res.config.url")
                    // ????????? DB????????? ?????? ??????
                    this.imageurl = res.config.url;
                    console.info(res.config.url+"dsfsdafasfdsffd")
                    
                }).catch((err)=>{
                    alert('?????? ??????'+err)
                })
        },


        //???????????????
        infiniteHandler() {
            const EACH_LEN = 12
            console.log("?????? infiniteHandler" + this.option)
            axios.get(process.env.baseUrl+'/review/reviewList?page='+this.loadNum + "&option="+this.option)
            .then(res => {
                //console.log("????????? ???????")
                console.log(res.data+"???????????????~.~"+this.loadNum +"?????? " + this.option)

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
                        // ??? ??????(No more data) - ???????????? EACH_LEN??? ???????????? 
                        //if(res.data.length / EACH_LEN < 1) {
                        if(res.data.length/EACH_LEN < 1){
                            console.log("????????????")
                            this.$refs.infiniteLoading.stateChanger.complete()
                        }
                        
                    } else {
                        // ??? ??????(No more data)
                        this.$refs.infiniteLoading.stateChanger.complete()
                    }
                }, 0)

            })
            .catch(err => {
                console.log(err)
                alert('??????'+err);
                
            })
        },

        reset() {
            //console.log("?????? ??????")
            this.$refs.infiniteLoading.stateChanger.reset();
            //console.log("?????? ?????? ???")
        },

        async test (value) {
            //????????????
            this.list = []
            this.loadNum = 0
            await this.reset().then(()=>{
                console.log("?????? test " + this.option + "///"+value)
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