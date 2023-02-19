<template>
    <div>
        <v-container>
            <v-card>
                <v-card-text class="d-flex justify-space-between align-center" style="height: 50px;">
                    <v-card-title>
                        <b>결제내역 관리</b>
                    </v-card-title>
                </v-card-text>
                <hr />

                <v-card-subtitle>
                    <div>
                        <!-- 검색 -->
                        <v-text-field
                        v-model="search"
                        append-icon="mdi-magnify"
                        label="키워드로 결제내역 검색"
                        hide-details
                        solo
                        style="margin-bottom: 15px; border-radius: 5px; padding: 10px;"
                        ></v-text-field>
                    </div>
                </v-card-subtitle>
              
                <v-data-table
                    :headers="headers"
                    :items="paymentList"
                    item-key="orderId"
                    class="elevation-1 paymentListTable"
                    :items-per-page="10"
                    :search="search"
                    :footer-props="{
                        'items-per-page-text': '페이지 당 보여질 갯수',
                        pageText: '총 {2}개 항목 중 {0}-{1}',
                    }"
                >
                    <template v-slot:item="row">
                        <tr>
                            <td style="width: 15%;">{{ row.item.impUid }}</td>
                            <td style="width: ">{{ row.item.proName == null?'삭제된 상품입니다.':row.item.proName }}</td>
                            <td style="width: 15%;">{{ row.item.payPrice | won }}</td>
                            <td style="width: 10%;">{{ row.item.payType == 'html5_inicis'?'KG이니시스':'kakaopay'?'카카오페이':'' }}</td>
                            <td style="width: 15%;">{{ row.item.payDate | yyyyMMdd }}</td>
                            <td style="width: 10%;">
                                {{ row.item.status == 'paid'?'완료':'실패' }}
                            </td>
                        </tr>
                    </template>
                </v-data-table>
            </v-card>
        </v-container>
    </div>
</template>

<script>
import axios from 'axios';

const backUrl = 'http://localhost:8080';

export default {

    mounted() {
        this.getPaymentList()
    },

    methods: {

        // 주문내역 조회
        getPaymentList() {
            axios.get(backUrl + '/admin/paymentList')
                .then(res => {

                    this.paymentList = res.data;
                    
                    console.log(this.paymentList);

                })
        },

        

    },

    data () {
      return {
        // v-data-table 관련
        search: '',
        headers: [
            { text: '결제ID', value: 'impUid', align:'center' },  
            { text: '상품명', value: 'proName', align:'center' },
            { text: '결제금액', value: 'payPrice', align:'center' },
            { text: '결제유형', value: 'payType', align:'center' },
            { text: '결제일자', value: 'payDate', align:'center' },
            { text: '결제상태', value: 'status', align:'center' },

        ],
        
        // 주문내역 데이터
        paymentList: [],
        
      }
    },

    filters:{
        comma(val){
            return "￦ " + String(val).replace(/\B(?=(\d{3})+(?!\d))/g, ",");
        },
        
        won(val){
                return String(val).replace(/\B(?=(\d{3})+(?!\d))/g, ",") + " 원";
            },

        yyyyMMdd(value){ 
            // 들어오는 value 값이 공백이면 그냥 공백으로 돌려줌
            if(value == '') return '';
        
            // 현재 Date 혹은 DateTime 데이터를 javaScript date 타입화
            var js_date = new Date(value);

            // 연도, 월, 일 추출
            var year = js_date.getFullYear();
            var month = js_date.getMonth() + 1;
            var day = js_date.getDate();

            // 월, 일의 경우 한자리 수 값이 있기 때문에 공백에 0 처리
            if(month < 10){
                month = '0' + month;
            }

            if(day < 10){
                day = '0' + day;
            }

            // 최종 포맷 (ex - '2021-10-08')
            // return year + '-' + month + '-' + day;
            return year + '년 ' + month + '월 ' + day +'일';
        },
    }
}
</script>

<style lang="scss" scoped>
    .paymentListTable td {
        text-align: center;
    }
</style>