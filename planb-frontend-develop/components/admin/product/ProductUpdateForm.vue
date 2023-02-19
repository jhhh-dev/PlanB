<template>
    <div>
        <v-row justify="center">
            <v-dialog v-model="dialog" persistent max-width="700px" style="height: auto;" @input="getProductInfo()">
                
                <!-- 최초 상품 저장 버튼 -->
                <template v-slot:activator="{ on, attrs }">
                    <v-btn color="secondary" dark v-bind="attrs" v-on="on" small>
                        수정
                    </v-btn>
                </template>


                <form @submit.prevent="submitForm" class="form" enctype="multipart/form-data">

                    <v-card height="auto">
                        <v-card-title style="background-color: black; color: white; margin-bottom: 10px;">
                            상품 수정하기
                        </v-card-title>

                        <v-card-subtitle>

                            <v-row justify="center">

                                <!-- 썸네일 이미지 -->
                                <v-col cols="4" style="margin-top: 65px;">

                                    <div style="width: 200px; height: 200px; border: 1px solid lightgray;">
                                          
                                        <!-- 기본값은 DB 사진 / 새로 첨부하면 새로운 이미지 띄우기 -->
                                        <img v-if="preview === ''" :src="imageUrl" style="width: 100%; max-width: 200px;" />
                                        <img v-else :src="preview" style="width: 100%; max-width: 200px;" />
                                    </div>
                                    <span style="font-size: 12px; color: red;">* 미첨부 시 기존 사진이 유지됩니다.</span>

                                    
                                    <div style="text-align: center;">
                                        
                                        <client-only>
                                            <v-file-input ref="refFile"
                                                    v-model="uploadFile"
                                                    accept="image/png, image/jpeg, image/bmp"
                                                    color="primary accent-4"
                                                    counter
                                                    label="사진 첨부"
                                                    prepend-icon="mdi-camera"
                                                    
                                                    @change="previewFile(uploadFile)"

                                                    single-line
                                                    :show-size="1000"
                                            ></v-file-input>
                                            
                                        </client-only>

                                    </div>
                                </v-col>
                            
                                <v-col cols="auto">
                                    <table class="updateTable" style="margin-top: 50px;">
                                        <tbody>
                                            <tr>
                                                <th>상품명</th>
                                                <td>
                                                    <v-text-field ref="productName" v-model="productName"
                                                        :rules="[() => !!productName || '필수 입력 값입니다.']" 
                                                        :error-messages="errorMessages"
                                                        placeholder="예) 나이키 에어포스 1 '07 로우 화이트" 
                                                        required 
                                                        outlined 
                                                        hide-details="auto"
                                                        value="productName"
                                                        dense></v-text-field>
                                                </td>
                                            </tr>
                                            
                                            <tr>
                                                <th>브랜드</th>
                                                <td>
                                                    <v-text-field ref="productBrand" v-model="productBrand"
                                                        :rules="[() => !!productBrand || '필수 입력 값입니다.']" :error-messages="errorMessages"
                                                        label="브랜드 입력" placeholder="예) 나이키" required outlined hide-details="auto"
                                                        value="productBrand" dense></v-text-field>
                                                </td>
                                            </tr>
                            
                                            <tr>
                                                <th>상품 가격</th>
                                                <td>
                                                    <v-text-field ref="zip" v-model="productPrice" :rules="[() => !!productPrice || '필수 입력 값입니다.']"
                                                        label="가격 입력" placeholder="151000" type="Number" required outlined hide-details="auto"
                                                        value="productPrice" dense></v-text-field>
                                                </td>
                                            </tr>

                                            <tr>
                                                <th>상품 분류</th>
                                                <td>
                                                    <v-autocomplete ref="productCategory" v-model="productCategory"
                                                        :rules="[() => !!productCategory || '필수 선택 값입니다.']" :items="selectCategory" label="분류 선택"
                                                        placeholder="스니커즈" required outlined hide-details="auto" value="productCategory"
                                                        dense></v-autocomplete>
                                                </td>
                                            </tr>
                            
                                            <tr>
                                                <th>사이즈 선택</th>
                                                <td>
                                                    <v-autocomplete ref="productSize" v-model="productSize"
                                                        :rules="[() => !!productSize || '필수 선택 값입니다.']" :items="selectSize" label="사이즈 선택"
                                                        placeholder="260" required outlined hide-details="auto" value="productSize"
                                                        dense></v-autocomplete>
                                                </td>
                                            </tr>
                                            
                                            
                                        </tbody>
                                    </table>
                                </v-col>
                            </v-row>


                        </v-card-subtitle>
                        
                        <v-card-actions>
                            <v-spacer></v-spacer>
                            <v-btn color="dark" text @click="dialog = false">
                                취소
                            </v-btn>
                            <v-btn color="primary" text @click="submit()">
                                수정하기
                            </v-btn>

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
    
    props: [
        "productId",
        
    ],

    data: () => ({
        
        dialog: false,

        errorMessages: '',

        productName: null,
        productBrand: null,
        productPrice: null,
        productSize: null,
        productImage : null,
        productCategory : null,
        uploadFile: [],
        selectSize: ['215','220','225','230','235','240','245','255','260','265','270','275','280','285','290'],
        selectCategory: ['스니커즈','로퍼','샌들/슬리퍼','부츠','힐/펌프스'],
        Image: null,

        // 썸네일 이미지
        preview: '',

        // Back 단 에서 가져온 이미지
        imageUrl: '',


    }),

    mounted() {

        
    },

    methods: {  

        // 상품 정보 가져오기
        getProductInfo() {
            
            axios({
                url: backUrl + '/admin/getProductInfo?proId=' + this.productId,
                method: "GET",
                
            }).then(res => {
                
                console.log(res);
                this.productName = res.data.proName;
                this.productBrand = res.data.proBrand;
                this.productPrice = res.data.proPrice;
                this.productSize = res.data.proSize;
                this.productImage = res.data.proImg;
                this.productCategory = res.data.proCate == 10?'스니커즈':res.data.proCate==20?'로퍼':res.data.proCate==30?'샌들/슬리퍼':res.data.proCate==40?'부츠':'힐/펌프스';


                // 이미지 파일 가져오기
                axios.get(backUrl + '/showImage?fileName=' + this.productImage)
                .then(res => {

                    // 변수에 DB이미지 링크 담기
                    this.imageUrl = res.config.url;
                    
                })
                

            }).catch(err => {

                alert(err);
            })
        },

        // 상품 이미지 미리보기
        previewFile(file){
            if (!file ) {

                return;
                //  input 이벤트는 실행 됐으나, 실제 파일이 업로드가 되지 않은 경우  그대로 종료 

            } 
            // 그 외의 경우에는 필요한 기능들이 작동하도록 작성

            const fileData = (data) => {
                this.preview = data
            }
            const reader = new FileReader()
            reader.readAsDataURL(file)
            reader.addEventListener("load", function () {
                fileData(reader.result)
            }, false);
            
        },

        // 상품 수정하기
        submit() {

            if (this.productName == '' || this.productBrand == '' || this.productPrice == '' || this.productCategory == null || this.productSize == null) {
                
                    alert("값을 조건에 맞게 모두 입력해주시기 바랍니다.");

            } else {
                
                const formData = new FormData();
                
                formData.append('proId', this.productId)
                formData.append('proName', this.productName)
                formData.append('proBrand', this.productBrand)
                formData.append('proPrice', this.productPrice)
                formData.append('proCate', this.productCategory == '스니커즈'?10:this.productCategory=='로퍼'?20:this.productCategory=='샌들/슬리퍼'?30:this.productCategory=='부츠'?40:50)
                formData.append('proSize', this.productSize)

                formData.append('uploadFile', this.uploadFile)
                
                axios({
                    url: backUrl + '/admin/updateProduct',
                    method: "POST",
                    data: formData,
                    headers: {
                        'Content-Type': 'multipart/form-data'
                    }

                }).then(res => {
                    
                    // console.log("전송 완료");
                    
                    // 성공 alert
                    alert("상품 수정이 완료되었습니다.");

                    // productList 랜더링
                    this.$emit("productListRendering");

                    // dialog 창 닫기
                    this.dialog = false;

                }).catch(err => {

                    alert(err);
                })
            }
          
        },

        
        


    },
}
</script>

<style lang="scss" scoped>
    .updateTable {
    width: 100%;
    border-top: 1px solid lightgray;
}

.updateTable th, td {
    padding: 10px;
    border-bottom: 1px solid lightgray;

}

</style>