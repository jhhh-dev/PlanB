<template>
    <div>
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
                            <th class="text-left">

                            </th>
                            <th class="text-left">제품명</th>
                            <th class="text-left">작성 날짜</th>

                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="(data, i) in list" :key="i">
                            <td class="text-left"> 
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
                        <tr v-if="islist">
                            <td colspan="3">
                                <!-- 내역 없을 시 -->
                                <p class="nothing">리뷰 내역이 없습니다.</p>
                                <div>
                                    
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
        reviewImg: '',
        proName: '',
        reviewDate: '',
        islist: false,
    }),

    mounted() {
        this.selectReviewList();
    },

    methods: {
        async selectReviewList () {
            await axios.get(process.env.baseUrl+'/userInfo/selectReviewList', {
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
                    for(let i = 0;this.list.length > i; i++){
                        this.list[i].reviewImgList = process.env.baseUrl+"/showImage?fileName="+ this.list[i].reviewImg
                    }
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