<template>
    <div>
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
                        <!-- 내역 없을 시 -->
                        <tr v-if="islist">
                            <td colspan="3">
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
    }),

    mounted() {
        this.selectOrderList();
    },

    methods: {
        async selectOrderList () {
            await axios.get(process.env.baseUrl+'/userInfo/selectOrderList', {
                params : {
                    //serId: 'test@planb.com',
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