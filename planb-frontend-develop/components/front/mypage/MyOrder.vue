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
                  <div style="float: left; margin:20px;"><h1>구매 내역</h1></div> 
                  <div style="float: left; margin-top:35px; font-weight: bold;"></div>
                </div>
              </div>
              <hr />
              <v-container>
              <v-card>
                <v-card-subtitle>
                  <v-simple-table>
                    <template v-slot:default>
                      <thead>
                        <tr>
                          <th class="text-left">제품명</th>
                          <th class="text-left">금액</th>
                          <th class="text-left">구매 날짜</th>
                        </tr>
                      </thead>
                      <tbody>
                        <tr v-for="(data, i) in list" :key="i">
                          <td class="text-left"> 
                            <nuxt-link v-bind:to="`/mypages/myorderDetail?orderId=${data.orderId}`">
                              {{ data.proName }}
                            </nuxt-link>
                          </td>
                          <td class="text-left"> {{ data.payPrice }}</td>
                          <td class="text-left">  {{ data.orderDate }}</td>
                        </tr>
                        <tr >
                            <td colspan="3" @click="moreList()" v-if="listCheck">
                                더보기
                            </td>
                        </tr>
                        <tr v-if="islist">
                          <td colspan="3">
                            <!-- 내역 없을 시 -->
                            <p class="nothing">구매 내역이 없습니다.</p>
                            <div>
                              <nuxt-link to="/shop">
                                  <v-btn 
                                      color="lighten-2" 
                                      class="userBtn"
                                  >shop 바로가기 </v-btn>
                              </nuxt-link>
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
  </div>
</template>
<script>
import axios from "axios"
export default {
    data: () => ({
        list: [],
        proName: '',
        payPrice: '',
        orderDate: '',
        islist: false,
        pageNum: 0,
        listCheck: true,
    }),

    mounted() {
        this.selectOrderList();
    },

    methods: {
        async selectOrderList () {
            await axios.get(process.env.baseUrl+'/userInfo/selectOrderList?page='+this.pageNum, {
                params : {
                    //userId: 'test@planb.com',
                    userId: sessionStorage.getItem('userId')
                }
            })
            .then((res) => {
                console.log(res.data)
                if(res.data.length == 0){
                    this.islist = true
                    this.listCheck = false
                } else{
                    console.log(res)
                    this.list = res.data
                    this.pageNum++
                    console.log(res.data.length/5+"지금 몇개")
                    if(res.data.length/5 <1){
                        this.listCheck = false
                    }
                }
            });
        },

        moreList (){
            axios.get(process.env.baseUrl+'/userInfo/selectOrderList?page='+this.pageNum, {
                params : {
                    userId: sessionStorage.getItem('userId')
                }
            })
            .then((res) => {
                console.log(res.data)
                if(res.data.length == 0){
                  this.listCheck = false
                } else{
                    console.log(res)
                    //this.list = res.data

                    const data = res.data
                    for(let key in data){
                        this.list.push(data[key])
                        console.log("after", this.list)
                    }

                    this.pageNum++
                    console.log(res.data.length/5+"지금 몇개")
                    if(res.data.length/5 <1){
                        this.listCheck = false
                    }
                }
            });
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