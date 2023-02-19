<template>
    <div>
        <v-container>
            
            <v-row justify="start">
                <!-- <v-col xl="6" lg="6" md="6" sm="8" xs="12"> -->
                <v-col cols="6">
                    <v-card>
                        <v-card-title>
                            <b>상품 등록</b>
                        </v-card-title>
                        <hr />

                        <v-card-subtitle>
                            <table class="addTable">
                                <tbody>
                                    <tr>
                                        <th>상품명</th>
                                        <td>
                                            <v-text-field 
                                            ref="productName"
                                            v-model="productName"
                                            :rules="proId_rule"
                                            :error-messages="errorMessages"
                                            label="상품명 입력"
                                            placeholder="예) 나이키 에어포스 1 '07 로우 화이트"
                                            required
                                            outlined
                                            dense
                                            autofocus
                                            hide-details="auto"
                                            ></v-text-field>
                                        </td>
                                    </tr>

                                    <tr>
                                        <th>브랜드</th>
                                        <td>
                                            <v-text-field 
                                            ref="productBrand"
                                            v-model="productBrand"
                                            :rules="proBrand_rule"
                                            :error-messages="errorMessages"
                                            label="브랜드 입력"
                                            placeholder="예) 나이키"
                                            required
                                            outlined
                                            dense
                                            hide-details="auto"
                                            ></v-text-field>
                                        </td>
                                    </tr>

                                    <tr>
                                        <th>상품 가격</th>
                                        <td>
                                            <v-text-field 
                                            ref="zip"
                                            v-model="productPrice"
                                            :rules="proPrice_rule"
                                            label="가격 입력"
                                            placeholder="151000"
                                            type="Number"
                                            required
                                            outlined
                                            dense
                                            hide-details="auto"
                                            ></v-text-field>
                                        </td>
                                    </tr>

                                    <tr>
                                        <th>상품 분류</th>
                                        <td>
                                            <v-autocomplete
                                            ref="productCategory"
                                            v-model="productCategory"
                                            :rules="proCate_rule"
                                            :items="selectCategory"
                                            label="분류 선택"
                                            placeholder="스니커즈"
                                            required
                                            outlined
                                            dense
                                            hide-details="auto"
                                        ></v-autocomplete>
                                        </td>
                                    </tr>

                                    <tr>
                                        <th>사이즈 선택</th>
                                        <td>
                                            <v-autocomplete
                                            ref="productSize"
                                            v-model="productSize"
                                            :rules="proSize_rule"
                                            :items="selectSize"
                                            label="사이즈 선택"
                                            placeholder="260"
                                            required
                                            outlined
                                            dense
                                            hide-details="auto"
                                        ></v-autocomplete>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>


                            <div style="display: flex; justify-content: end; margin-top: 10px;">
                                <div style="width: 100px;">
                                    <nuxt-link to="/admin/product">
                                        <v-btn color="secondary" class="btnBack">돌아가기</v-btn>
                                    </nuxt-link>
                                </div>
                                <div style="width: 100px; position: relative; top: 12px;">

                                    <!-- 상품 저장 하위 컴포넌트  -->
                                    <ProductAddConfirm 
                                        :productName="this.productName"
                                        :productBrand="this.productBrand"
                                        :productPrice="this.productPrice"
                                        :productSize="this.productSize"
                                        :productCategory="this.productCategory"
                                        :uploadFile="this.uploadFile"
                                        :preview="this.preview"
                                    />
                                </div>
                            </div>

                        </v-card-subtitle>

                    </v-card>
                </v-col>

                <!-- 썸네일 이미지 -->
                <v-col cols="4" style="margin-top: 65px;">
                    <div style="width: 300px;">
                        <img :src="preview" style="max-width: 300px;" />
                    </div>
                    <br />

                    
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
                            <!-- <v-btn @click="uploadCheck()" color="primary">첨부 콘솔 확인</v-btn>
                            <p>File Name : {{ this.uploadFile.name }}</p> -->
                        </client-only>


                    </div>
                </v-col>

            </v-row>

        </v-container>
    </div>
</template>

<script>
import ProductAddConfirm from './ProductAddConfirm.vue';

export default {
    
    components: { ProductAddConfirm },

    data() {
        return {
            errorMessages: '',

            productName: null,
            productBrand: null,
            productPrice: null,
            productCategory: null,
            productSize: null,
            uploadFile: [],

            selectSize: ['215','220','225','230','235','240','245','255','260','265','270','275','280','285','290'],
            selectCategory: ['스니커즈','로퍼','샌들/슬리퍼','부츠','힐/펌프스'],

            Image: null,

            preview: '',

            proId_rule: [
                v => !!v || '상품명은 필수 입력사항입니다.',
                v => !( v && v.length >= 40) || '상품명은 40자 이상 입력할 수 없습니다.'
            ],

            proBrand_rule: [
                v => !!v || '브랜드는 필수 입력사항입니다.',
                v => !( v && v.length >= 10) || '브랜드는 10자 이상 입력할 수 없습니다.'
            ],

            proPrice_rule: [
                v => !!v || '상품 가격은 필수 입력사항입니다.',
                v => !( v && v.length >= 8) || '상품 가격은 천만 원 이상 입력할 수 없습니다.'
            ],

            proCate_rule: [
                v => !!v || '상품 분류는 필수 선택사항입니다.',
            ],

            proSize_rule: [
                v => !!v || '사이즈는 필수 선택사항입니다.',
            ],
        };
    },

    methods: {
        uploadCheck() {
            console.log("이미지파일 : " + this.uploadFile);
        },

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
    },
    
}
</script>



<style lang="scss" scoped>
.addTable {
    width: 100%;
    border-top: 1px solid lightgray;
}

.addTable th, td {
    padding: 10px;
    border-bottom: 1px solid lightgray;

}

.addTable th {
    width: 120px;
    
}

</style>