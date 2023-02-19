<template>
    <div>
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
                                        <v-btn color="lighten-2" style="font-weight: 100; height: 40px; background-color: #222; color: white !important;">구매하기 </v-btn>
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
            await axios.get(process.env.baseUrl + '/userInfo/selectBMList', {
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
            })
            .catch(err => {

            })
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