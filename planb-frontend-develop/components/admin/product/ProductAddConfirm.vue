<template>
    <div>
        <v-row justify="center">
            <v-dialog v-model="dialog" persistent max-width="700px" style="height: auto;">
                
                <!-- 최초 상품 저장 버튼 -->
                <template v-slot:activator="{ on, attrs }">
                    <v-btn color="primary" dark v-bind="attrs" v-on="on">
                        상품 저장
                    </v-btn>
                </template>


                <form @submit.prevent="submitForm" class="form" enctype="multipart/form-data">

                    <v-card>
                        <v-card-title style="background-color: black; color: white; margin-bottom: 10px;">
                            입력 정보 확인
                        </v-card-title>

                        <v-card-text style="padding-left: 50px; padding-right: 50px;">
                            <table class="confirmTable" style="width: 100%; height: auto;">
                                <tr>
                                    <td rowspan="5" style="width: 200px;">
                                        <div style="width: 100%;">
                                            <img :src="preview" style="width: 100px;" />
                                        </div>
                                    </td>

                                    <th style="width: 100px;">
                                        상품명
                                    </th>
                                    <td>
                                        {{productName}}
                                    </td>
                                </tr>

                                <tr>
                                    <th>
                                        브랜드
                                    </th>
                                    <td>
                                        {{productBrand}}
                                    </td>
                                </tr>

                                <tr>
                                    <th>
                                        상품 가격
                                    </th>
                                    <td>
                                        {{ productPrice | commaWon }}
                                    </td>
                                </tr>

                                <tr>
                                    <th>
                                        상품 분류
                                    </th>
                                    <td>
                                        {{productCategory}}
                                    </td>
                                </tr>

                                <tr>
                                    <th>
                                        사이즈
                                    </th>
                                    <td>
                                        {{productSize}}
                                    </td>
                                </tr>
                            
                            </table>
                        </v-card-text>
                        
                        <v-card-actions>
                            <v-spacer></v-spacer>
                            <v-btn color="dark" text @click="dialog = false">
                                취소
                            </v-btn>
                            <v-btn color="primary" text @click="submit()">
                                확인
                            </v-btn>
                            
                            <!-- <v-btn color="primary" text @click="check()">
                                파일 체크
                            </v-btn> -->
                        </v-card-actions>
                    </v-card>
                </form>



            </v-dialog>
        </v-row>
    </div>
</template>

<script>
import axios from 'axios';

const backUrl = 'http://localhost:8080';

export default {

    // 부모 컴포넌트 ProductAddForm 에서 받아오는 값
    props: {
        productName: {
            required: true,
        },
        productBrand: {
            required: true,
        },
        productPrice: {
            required: true,
        },
        productCategory: {
            required: true,
        },
        productSize: {
            required: true,
        },
        uploadFile: {
            required: true,
        },

        preview: '',
    },

    data: () => ({

        dialog: false,

    }),

    methods: {

        //Insert 메서드
        submit() {

            if (this.uploadFile == null || this.uploadFile == '' || this.productName == null || this.productBrand == null ||
                this.productPrice == null || this.productCategory == null || this.productSize == null) {
                alert("값을 조건에 맞게 모두 입력해주시기 바랍니다.");

            } else {

                const formData = new FormData();
                formData.append('uploadFile', this.uploadFile)
                formData.append('proName', this.productName)
                formData.append('proBrand', this.productBrand)
                formData.append('proPrice', this.productPrice)
                formData.append('proCate', this.productCategory == '스니커즈'?10:this.productCategory=='로퍼'?20:this.productCategory=='샌들/슬리퍼'?30:this.productCategory=='부츠'?40:50)
                formData.append('proSize', this.productSize)
                

                console.log(formData)

                axios({
                    url: backUrl + '/admin/insertProduct',
                    method: "POST",
                    data: formData,
                    headers: {
                        'Content-Type': 'multipart/form-data'
                    }

                }).then(res => {

                    // console.log(this.uploadFile);
                    // console.log("전송 성공");

                    // 성공 alert
                    alert("저장이 완료되었습니다. \n상품 목록으로 이동합니다.");

                    // dialog 창 닫기
                    this.dialog = false;

                    // productList로 이동
                    this.$nuxt.$router.push("/admin/product")



                }).catch(err => {

                    alert(err);
                })

            }


        },
    },

    filters:{
            commaWon(val){
                return String(val).replace(/\B(?=(\d{3})+(?!\d))/g, ",")+" 원";
            },
        },
}
</script>

<style lang="scss" scoped>
.confirmTable {
    width: 100%;
    border-top: 1px solid lightgray;
}

.confirmTable th, td {
    padding: 10px;
    border-bottom: 1px solid lightgray;
    text-align: center;

}

.confirmTable th {
    width: 120px;
    border-left: 1px solid lightgray;
    border-right: 1px solid lightgray;
}
</style>