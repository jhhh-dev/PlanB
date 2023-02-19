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
                                <v-row ><nuxt-link to="/mypages/mylike" class="snb__link smenu sclick">관심 상품</nuxt-link> </v-row>
                                <v-row ><nuxt-link to="/mypages/myreview" class="snb__link smenu">리뷰 내역</nuxt-link> </v-row>   
                            </v-col>
                        </v-card> 
                    </div>
                </div>

                <div class="right-content" style=" width: 80%; height: 100%; float:right; padding-left: 10px;">
                    <div>
                        <div style=" width: 100%; height: 100px;">
                            <div style="float: left; margin:20px 20px 0;">
                                <div style="float: left; margin:20px;"><h1>관심 상품</h1></div> 
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
                                                <col width="600px">
                                                <col width="200px">
                                                <col width="150px">
                                            </colgroup>
                                            <thead>
                                                <tr>
                                                    <th class="text-left">제품명</th>
                                                    <th class="text-left">금액</th>
                                                    <th class="text-left"></th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr v-for="(data, i) in list" :key="i">
                                                    <td class="text-left"> {{ data.proName }}</td>
                                                    <td class="text-left"> {{ data.proPrice }}</td>
                                                    <td class="text-left">
                                                        <v-card-actions>
                                                            <nuxt-link to="">
                                                                <v-btn 
                                                                color="lighten-2" 
                                                                style="font-weight: 100; height: 40px; background-color: #222; color: white !important;"
                                                                
                                                                :to="{ path: '/detail/' + `${data.proId}` }"
                                                                >
                                                                    구매하기
                                                                </v-btn>
                                                            </nuxt-link>
                                                        </v-card-actions>
                                                    </td>
                                                </tr>
                                                <tr v-if="islist">
                                                    <td colspan="3">
                                                    <!-- 내역 없을 시 -->
                                                        <p class="nothing">관심 상품이 없습니다.</p>
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
        proPrice: '',
        islist: false,
    }),

    mounted() {
        this.selectBMList();
    },

    methods: {
        async selectBMList () {
            await axios.get(process.env.baseUrl+'/userInfo/selectBMList', {
                params : {
                    //userId: 'test@planb.com',
                    userId: sessionStorage.getItem('userId')
                }
            })
            .then((res) => {
                console.log(res.data)
                if(res.data == ''){
                    this.islist = true;
                } else{
                    console.log(res)
                    this.list = res.data
                }
            });
        },

        // 상품 이름 클릭 시 상세 페이지 이동
        detailButton(proId) {
            console.log('상세 정보 ' + proId)

            this.$nuxt.$router.push("/detail/"+proId);

        },
    },
    
};
</script>

<style>
.nothing {
    margin: 35px 0 10px;
}

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

.userBtn {
    margin: 0 auto !important;
}
</style>