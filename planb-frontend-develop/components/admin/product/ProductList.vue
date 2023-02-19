<template>
    <div>
        <v-container>
            <v-card>
                <v-card-text class="d-flex justify-space-between align-center" style="height: 50px;">
                    <v-card-title>
                        <b>상품 관리</b>
                    </v-card-title>
                </v-card-text>
                <hr />

                <v-card-subtitle>
                    <div>
                        <!-- 검색 -->
                        <v-text-field
                        v-model="search"
                        append-icon="mdi-magnify"
                        label="키워드로 상품리스트 검색"
                        hide-details
                        solo
                        style="margin-bottom: 15px; border-radius: 5px; padding: 10px;"
                        ></v-text-field>
                    </div>

                    <div style="display: flex; flex-direction: row-reverse; ">
                        

                        <v-card-actions>
                            <nuxt-link to="/admin/productAdd">
                                <v-btn color="secondary" class="btnAdd" style="color: white;"> 상품 등록 </v-btn>
                            </nuxt-link>
                        </v-card-actions>

                    </div>
                </v-card-subtitle>
              
                <v-data-table
                    :headers="headers"
                    :items="productList"
                    item-key="proId"
                    class="elevation-1 proListTable"
                    :items-per-page="10"
                    :search="search"
                    :footer-props="{
                        'items-per-page-text': '페이지 당 보여질 갯수',
                        pageText: '총 {2}개 항목 중 {0}-{1}',
                    }"
                >
                    <template v-slot:item="row">
                        <tr>
                            <td>
                                <span @click="detailButton(row.item)" style="cursor: pointer;" class="detailView">
                                    {{row.item.proName}}
                                </span>
                                
                            </td>
                            <td style="width: 15%;">{{row.item.proBrand}}</td>
                            <td style="width: 15%;">{{row.item.proPrice | comma}}</td>
                            
                            <td style="width: 12%;">
                                <v-btn v-if="row.item.proHide == true" class="mx-2" dark small color="success" @click="updateStatus(row.item)">판매중</v-btn>
                                <v-btn v-else-if="row.item.proHide == false" class="mx-2" dark small color="secondary" @click="updateStatus(row.item)">숨겨짐</v-btn>
                            </td>

                            <td style="width: 12%;">
                                <v-icon color="error" @click="deleteButton(row.item)">mdi-trash-can</v-icon>
                            </td>

                            <td style="width: 12%;">
                                <!-- 상품 수정 컴포넌트 -->
                                <ProductUpdateForm
                                v-bind:productId="row.item.proId"
                                @productListRendering="getProductList"
                                
                                />
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
import ProductUpdateForm from './ProductUpdateForm.vue';

const backUrl = 'http://localhost:8080';

export default {
  components: { ProductUpdateForm },

    mounted() {
        this.getProductList()
    },

    methods: {

        async getProductList() {
            await axios.get(backUrl + '/admin/productList')
                .then(res => {

                    this.productList = res.data;
                    // console.log(this.productList);

                })
        },

        // 상품 이름 클릭 시 상세 페이지 이동
        detailButton(item) {
            // console.log('상세 정보 ' + item.proId)

            this.$nuxt.$router.push("/detail/"+item.proId);

        },


        // 숨김 상태 변경
        updateStatus(item) {
            

            if (!confirm("판매 상태를 변경하시겠습니까?")) {
                // 취소(아니오) 버튼 클릭 시 이벤트
                return;

            } else {
                // 확인(예) 버튼 클릭 시 이벤트

                axios({
                    url: backUrl + '/admin/productHide',
                    method: "POST",
                    data: {
                        // 변수에 담아 보내기
                        proId: item.proId,
                        proHide: item.proHide,
                    },

                }).then(res => {

                    alert("변경되었습니다.")

                    // 성공한 후 다시 렌더링
                    this.getProductList()

                }).catch(err => {

                    alert(err);
                })
            }
            
            
        },

        // 상품 수정
        updateProductInfo(item) {
            console.log('상품 수정 : ' + item.proId)

            
        },

        // 상품 삭제
        deleteButton(item) {
            
            console.log('삭제 대상 : ' + item.proId)
            
            if(confirm("해당 상품을 삭제하시겠습니까?")){
                
                // 삭제 진행
                axios({
                    url: backUrl + '/admin/deleteProduct?proId='+item.proId,
                    method: "GET",

                }).then(res => {
                    
                    // 성공 alert
                    alert("삭제 완료되었습니다.");

                    // productList 랜더링
                    this.getProductList();

                }).catch(err => {

                    alert(err);
                })

            }else{
                
                
                return;
            }

            

        },


    },

    data () {
      return {
        // 출력 테이블 표 관련
        // singleSelect: false,    //singleSelect 의 기본값 = false (스위치 관련)
        search: '', //검색 단어
        headers: [
            { text: '상품명', value: 'proName', align:'center' },
            { text: '브랜드', value: 'proBrand', align:'center' },
            { text: '가격', value: 'proPrice', align:'center' },
            { text: '상태', value: 'proHide', align:'center' },
            { text: '상품 삭제', value: 'delete', sortable: false, align:'center' },
            { text: '상품 수정', value: 'detail', sortable: false, align:'center' },

        ],
        
        productList: [],
        
      }
    },

    filters:{
    comma(val){
        return "￦ " + String(val).replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    }
}
}
</script>

<style lang="scss" scoped>
    //버튼 : 상품 등록
    .btnProAdd {
        color: white;
    }

    .proListTable td {
        text-align: center;
    }

    .detailView:hover {
        font-weight: bold;
    }
</style>