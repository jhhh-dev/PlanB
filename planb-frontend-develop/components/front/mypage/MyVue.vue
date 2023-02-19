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
                <v-row ><nuxt-link to="/mypages/myreview" class="snb__link smenu">리뷰 내역</nuxt-link> </v-row>
              </v-col>
            </v-card> 
          </div>
        </div>
        <div class="right-content" style=" width: 80%; height: 100%; float:right; padding-left: 10px;">
          <div>
            <div style=" width: 100%; height: 100px;">
              <div style="float: left; margin:20px 20px 0;">
                <div style="float: left; margin:20px;"><h1 style="font-size: 30px !important;">{{ userName }}</h1></div> 
                <div style="float: left; margin-top:35px; font-weight: bold;">님의 마이페이지</div>
              </div>
            </div>
            <hr />
            <v-container>
              <v-card>
                <v-card-text class="d-flex justify-space-between align-center">
                  <v-card-title class="ctitle"> 구매 내역 </v-card-title>
                  <v-card-actions>
                    <nuxt-link to="/mypages/myorder">
                      <v-btn color="lighten-2">더보기 </v-btn>
                    </nuxt-link>
                  </v-card-actions>
                </v-card-text>
                <hr />

                <OrderList />
              </v-card>
            </v-container>

            <v-container>
              <v-card>
                <v-card-text class="d-flex justify-space-between align-center">
                  <v-card-title class="ctitle"> 관심 상품 </v-card-title>
                  <v-card-actions>
                    <nuxt-link to="/mypages/mylike">
                      <v-btn color="lighten-2">더보기 </v-btn>
                    </nuxt-link>
                  </v-card-actions>
                </v-card-text>
                <hr />

                <BookMarkList />

              </v-card>
            </v-container>

            <v-container>
              <v-card>
                <v-card-text class="d-flex justify-space-between align-center">
                  <v-card-title class="ctitle"> 리뷰 내역 </v-card-title>
                  <v-card-actions>
                    <nuxt-link to="/mypages/myreview">
                      <v-btn color="lighten-2">더보기 </v-btn>
                    </nuxt-link>
                  </v-card-actions>
                </v-card-text>
                <hr />
        
                <ReviewList />

              </v-card>
            </v-container>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
  <script>
  import BookMarkList from "/components/front/mypage/BookMarkList.vue";
  import ReviewList from "/components/front/mypage/ReviewList.vue";
  import OrderList from "/components/front/mypage/OrderList.vue";
  import axios from "axios";

  export default {
    components: {
      BookMarkList,
      ReviewList,
      OrderList,
    },
    data: () => ({

      //userName: typeof  window !== 'undefined' ? sessionStorage.getItem('userName') : null
      userName: '',
    }),

    mounted() {
        this.selectName();
    },

    methods: {
      selectName(){
        axios.get(process.env.baseUrl +'/userInfo/selectUserName', {
            params : {
                userId: sessionStorage.getItem('userId'),  
            }
        }).then((res)=>{
            this.userName = res.data.userName
            
        }).catch((err)=>{
            alert('오류 발생'+err)
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
  </style>