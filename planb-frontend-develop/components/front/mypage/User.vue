<template>
    <div>
        <div style="text-align: center;">
            <div style="width: 80%; display: inline-block;">
                <div class="left-content" style="width: 20%; height: 100%; float:left;">
                    <div class="snb_list" style="margin:40px 0 20px;">
                        <v-card>  
                            <v-col class="">
                                <v-row ><nuxt-link to="/mypage" class="snb__link" style="font-weight: bolder; font-size: 25px;">마이 페이지</nuxt-link> </v-row>
                                <v-row ><nuxt-link to="/mypages/userInfo" class="snb__link smenu sclick">회원 정보</nuxt-link> </v-row>
                                <v-row ><nuxt-link to="/mypages/myorder" class="snb__link smenu">구매 내역</nuxt-link> </v-row>
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
                                <div style="float: left; margin:20px; font-size: 30px !important;"><h1 style="font-size: 30px !important;">{{ name }}</h1></div> 
                                <div style="float: left; margin-top:35px; font-weight: bold;">님의 회원 정보</div>
                            </div>
                        </div>
                    <hr />
                    <div style="width: 100%; margin-left: 30px; margin-bottom: 50px;">
                        <v-row style=" display: none;">
                            <v-col>
                                <v-text-field label="이메일"></v-text-field>
                            </v-col>
                            <v-col>
                                <v-card-actions>
                                    <nuxt-link to="">
                                        <v-btn color="lighten-2" class="userBtn">구매하기 </v-btn>
                                    </nuxt-link>
                                </v-card-actions>
                            </v-col>
                        </v-row>
                        <v-row>
                            <v-col></v-col>
                            <v-col></v-col>
                        </v-row>

                        <!-- 이메일 -->
                        <v-row>
                            <v-col>
                                <v-text-field 
                                label="이메일"
                                v-model="email"
                                readonly
                                ></v-text-field>
                            </v-col>
                            <v-col>  
                            </v-col>
                        </v-row>

                        <!-- 비밀번호 -->
                        <v-row v-if="isPwOn" style="">
                            <v-col>
                                <v-text-field 
                                    label="비밀번호"
                                    readonly
                                ></v-text-field>
                            </v-col>
                            <v-col>
                                <v-card-actions>
                                    <nuxt-link to="">
                                        <v-btn color="lighten-2" class="userBtn" @click="pwOnOff">변경 </v-btn>
                                    </nuxt-link>
                                </v-card-actions>
                            </v-col>
                        </v-row>
                        <!-- 비밀번호 등 개인정보 변경 폼 -->
                        <v-row>
                            <div v-if="isPwOff" style="width: 50%; margin-left: 10px;">
                                <fieldset style="padding: 10px 15px 10px 0; border:none;">
                                    <legend style="display: none; ">비밀번호 변경</legend>
                                    <p style="text-align: left; font-weight: bold;">비밀번호 변경</p>
                                    <v-form>
                                        <v-text-field
                                            label="기존 비밀번호"
                                            v-model="pwOld"
                                            type="password"
                                            :error-messages="pwErrors"
                                            required
                                        >
                                        </v-text-field>
                                        <v-text-field
                                            label="새 비밀번호"
                                            v-model="pwNew"
                                            type="password"
                                            :error-messages="pwErrorsNew"
                                            required
                                        >
                                        </v-text-field>
                                        <nuxt-link to="" style="margin-right: 20px;">
                                            <v-btn color="lighten-2" class="userBtn userBtnCancle" @click="pwOnOff">취소 </v-btn>
                                        </nuxt-link>
                                        <v-btn 
                                            color="lighten-2" 
                                            class="userBtn"
                                            :disabled="pwValid"
                                            @click="pwSubmit()"
                                        >변경 </v-btn>
                                    </v-form>
                                    <v-snackbar
                                        v-model="snackbarPw"
                                        :timeout="timeout"
                                        style="text-align: center !important;"
                                        >
                                        {{ textPw }}
                                    </v-snackbar>
                                </fieldset>
                            </div>
                        </v-row>
                        <v-snackbar
                            v-model="snackbarPwOk"
                            :timeout="timeout"
                            style="text-align: center !important;"
                            >
                            {{ textPwOk }}

                        </v-snackbar>
                        <v-row>
                            <v-col></v-col>
                            <v-col></v-col>
                        </v-row>

                        <!-- 이름 -->
                        <v-row v-if="isNameOn" style="">
                            <v-col>
                                <v-text-field 
                                label="이름"
                                v-model="name"
                                readonly
                                ></v-text-field>
                            </v-col>
                            <v-col>
                                <v-card-actions>
                                    <nuxt-link to="">
                                        <v-btn color="lighten-2" class="userBtn" @click="nameOnOff">변경 </v-btn>
                                    </nuxt-link>
                                </v-card-actions>
                            </v-col>
                        </v-row>
                        <!-- 이름 변경 폼 -->
                        <v-row>
                            <div v-if="isNameOff" style="width: 50%; margin-left: 10px;">
                                <fieldset style="padding: 10px 15px 10px 0; border:none;">
                                    <legend style="display: none; ">이름 변경</legend>
                                    <p style="text-align: left; font-weight: bold;">이름 변경</p>
                                    <form @submit.prevent>
                                        <v-text-field
                                            label="새 이름"
                                            v-model.trim="nameNew"
                                            type="text"
                                            :error-messages="nameErrors"
                                            
                                        >
                                        </v-text-field>
                                        <p class="typo__p" v-if="idcheck">이미 사용하고 있는 이름입니다!</p>
                                        <nuxt-link to="" style="margin-right: 20px;">
                                            <v-btn color="lighten-2" class="userBtn userBtnCancle" @click="nameOnOff">취소 </v-btn>
                                        </nuxt-link>
                                        <v-btn 
                                            color="lighten-2" 
                                            class="userBtn"
                                            type="submit"
                                            :disabled="nameValid"  
                                            @click="nameSubmit()"
                                        >변경 </v-btn>
                                    </form>
                                </fieldset>
                            </div>
                        </v-row>
                        <v-snackbar
                            v-model="snackbar"
                            :timeout="timeout"
                            >
                            {{ text }}
                        </v-snackbar>

                        <!-- 번호 -->
                        <v-row style="">
                            <v-col>
                                <v-text-field 
                                label="휴대폰 번호"
                                v-model="phone"
                                readonly
                                ></v-text-field>
                            </v-col>
                            <v-col>
                            </v-col>
                        </v-row>

                        <!-- 주소 -->
                        <v-row style="" v-if="isAddrOn">
                            <v-col>
                                <v-text-field 
                                label="주소"
                                v-model="addr"
                                readonly
                                ></v-text-field>
                            </v-col>
                            <v-col>
                                <v-card-actions>
                                    <nuxt-link to="">
                                        <v-btn 
                                            color="lighten-2" 
                                            class="userBtn"
                                            @click="addrOnOff"
                                        >변경 </v-btn>
                                    </nuxt-link>
                                </v-card-actions>
                            </v-col>
                        </v-row>
                        <!-- 주소 변경 -->
                        <v-row>
                            <div v-if="isAddrOff" style="width: 50%; margin-left: 10px;">
                                <fieldset style="padding: 10px 15px 10px 0; border:none;">
                                    <legend style="display: none; ">주소 변경</legend>
                                    <p style="text-align: left; font-weight: bold;">주소 변경</p>
                                    <v-form>
                                        <div style="display: flex;">
                                            <v-text-field
                                                label="우편번호"
                                                v-model="addr1"
                                                type="text"
                                                :error-messages="addr1Errors"
                                                required
                                                readonly
                                                class="addrNum"
                                            >
                                            </v-text-field>
                                            <v-btn color="lighten-2" class="userBtn userBtnCancle addrSearch" @click="execDaumPostcode()">우편번호 검색 </v-btn>
                                
                                        </div>
                                        <div>
                                            <v-text-field
                                                label="주소"
                                                v-model="address"
                                                type="text"
                                                :error-messages="addr2Errors"
                                                required
                                                readonly
                                            >
                                            </v-text-field>
                                        </div>
                                        <v-text-field
                                            label="상세주소"
                                            v-model="addr3"
                                            type="text"
                                            :error-messages="addr3Errors"
                                            required
                                        >
                                        </v-text-field>
                                        <nuxt-link to="" style="margin-right: 20px;">
                                            <v-btn color="lighten-2" class="userBtn userBtnCancle" @click="addrOnOff">취소 </v-btn>
                                        </nuxt-link>
                                        <v-btn 
                                            color="lighten-2" 
                                            class="userBtn"
                                            :disabled="addrValid"  
                                            @click="addrSubmit()"    
                                        >변경 </v-btn>
                                    </v-form>
                                </fieldset>
                            </div>
                        </v-row>
                    </div>  
                </div>
            </div>
        </div>
    </div>
</div> 
</template>
<script>
import { validationMixin } from 'vuelidate'
import { required, maxLength, minLength, valid  } from 'vuelidate/lib/validators'
import axios from "axios"
//import api from '@/components/api/index.js'

export default {
    mixins: [validationMixin],
    head () {
        return{
            script: [
                {
                    type: 'text/javascript',
                    src: "//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"
                },
            ]
        }
    },

    data: () => ({
        //회원정보 요소들
        email: '',
        name: '',
        phone: '',
        addr: '',
        pwOld: '',
        pwNew: '',
        addr1: '',
        address: '',
        addr3: '',
        nameNew: '',

        //폼 온오프
        isAddrOn: true,
        isPwOn: true,
        isPwOff: false,
        isNameOn: true,
        isNameOff: false,
        isAddrOn: true,
        isAddrOff: false,


        res: [],

        nameValid: false,
        idcheck: false,

        text: '이미 사용중인 이름입니다.',
        snackbar: false,
        
        timeout: 2000,

        pwValid: false,
        pwcheck: false,

        snackbarPw: false,
        textPw: '기존 비밀번호가 일치하지 않습니다.',

        addrValid: false,
        snackbarPwOk: false,
        textPwOk: '비밀번호 변경을 완료하였습니다.',

        //에러메세지
        pwErrors: [],
        pwErrorsNew: [],
        nameErrors: [],
        addr1Errors: [],
        addr2Errors: [],
        addr3Errors: [],



    }),

    validations: {
        pwOld: { required },
        pwNew: { required, maxLength: maxLength(16), minLength: minLength(8), 
            
                valid: function(value) {
                    const containsUppercase = /[A-Z]/.test(value)
                    const containsLowercase = /[a-z]/.test(value)
                    const containsNumber = /[0-9]/.test(value)
                    const containsSpecial = /[#?!@$%^&*-]/.test(value)
                    return containsUppercase && containsLowercase && containsNumber && containsSpecial
                }
        },
        nameNew: {required, minLength: minLength(2),maxLength: maxLength(8) },
        addr1: {required},
        address: {required},
        addr3: {required},
        
    },

    computed: {

    },

    mounted() {
        this.selectUserInfo();
        //this.submit();
        console.log(sessionStorage.getItem('userRole')+"userRole")
    },


    methods: {
        submit () {
            this.$v.$touch()
            
        },

        pwOnOff: function(){
            this.isPwOn = !this.isPwOn;
            this.isPwOff = !this.isPwOff;
            this.pwOld = '';
            this.pwNew = '';
        },
        nameOnOff: function(){
            this.isNameOn = !this.isNameOn;
            this.isNameOff = !this.isNameOff;
            this.nameNew = ''
        },
        addrOnOff: function(){
            this.isAddrOn = !this.isAddrOn;
            this.isAddrOff = !this.isAddrOff;
            this.address = '';
            this.addr1= '';
            this.addr3='';
        },

        //회원정보 불러오기
        async selectUserInfo(){
            

            await axios.get(process.env.baseUrl+'/userInfo', {
                params : {
                    //userId: 'test@planb.com',
                    userId: sessionStorage.getItem('userId')
                }
            })
            // api.get('/userInfo?userId',
                
            //         sessionStorage.getItem('userId')
                
            // )
            .then((res) => {
                console.log(res.data)
                this.email = sessionStorage.getItem('userId')
                this.name = res.data.userName
                this.phone = res.data.userPhone
                this.addr = res.data.userAddr
                
            }).catch(e => {
                alert(e+"심각한 문제가 있습니다.")
                this.$router.push({ path: '/login' })

            })
            //console.log('실행 슝 갑니다');
            //console.log(response.data);
            //this.res = response.data;
        },

        //이름 변경
        async nameSubmit(){
            if(!this.$v.nameNew.maxLength
                || !this.$v.nameNew.minLength
                || !this.$v.nameNew.required){
                    this.nameErrors = ['이름 2~8자를 입력해주세요.']
            } else{
                this.nameErrors = []
                // axios.get(process.env.baseUrl+'/userInfo/idcheck', {
                let data = {
                    userId: sessionStorage.getItem('userId'),
                    userName: this.nameNew,
                }
                axios.post(process.env.baseUrl+'/userInfo/updateName', 
                    JSON.stringify(data),{
                    headers : {
                        "Content-Type": `application/json`,
                    } 
                }).then((res)=>{
                    console.log(res.data);
                    if (res.data == 1) {
                        // let data = {
                        //     userId: sessionStorage.getItem('userId'),
                        //     userName: this.nameNew,
                        // }
                        // axios.post(process.env.baseUrl+'/userInfo/updateName', 
                        //     JSON.stringify(data),{
                        //     headers : {
                        //         "Content-Type": `application/json`,
                        //     } 
                        // }).then((res)=> {
                            this.selectUserInfo();
                            this.isNameOn = true;
                            this.isNameOff = false;
                            this.nameNew = '';
                        // }).catch(e => {
                        //     alert(e+"분명히 문제가 있습니다.")
                        // })
                        
                    } else if(res.data == 0){
                        //console.log('안 됨 ! ');
                        this.snackbar = true;

                    }
                }).catch(e => {
                    alert(e+"분명히 문제가 있습니다.")
                })
            }
        },

        //비밀번호 변경
        async pwSubmit(){
            let data1 = {
                //userId: 'test@planb.com',
                userId: sessionStorage.getItem('userId'),
                userPw: this.pwOld,
            }
            let data2 = {
                //userId: 'test@planb.com',
                userId: sessionStorage.getItem('userId'),
                userPw: this.pwNew,
               // userPwNew: this.pwNew,
            }
            if(!this.$v.pwOld.required){
                this.pwErrors = ['기존 비밀번호를 입력해주세요.']
            }else if(!this.$v.pwNew.maxLength
                    || !this.$v.pwNew.minLength
                    || !this.$v.pwNew.required
                    || !this.$v.pwNew.valid
                    ){
                this.pwErrors = []
                this.pwErrorsNew = ['영문(대소문자), 숫자, 특수문자 조합 8~16자를 입력해주세요.']     
            }else{
            
                axios.post(process.env.baseUrl+'/userInfo/pwCheck',JSON.stringify(data1), {
                    headers: {
                        "Content-Type": `application/json`
                    },
                })
                // axios.post(process.env.baseUrl+'/userInfo/updatePw', JSON.stringify(data2), {
                //     headers: {
                //         "Content-Type": `application/json`
                //     },
                //})
                .then((res)=>{
                    //console.log(res.data+"뭐야")
                    //res.log(res.data);
                    if (res.data == 1) {
                        axios.post(process.env.baseUrl+'/userInfo/updatePw', JSON.stringify(data2), {
                        headers: {
                            "Content-Type": `application/json`
                        },
                        }).then((res)=>{
                            
                            this.selectUserInfo();
                            this.pwErrors = [];
                            this.pwErrorsNew = [];
                            this.isPwOn = true;
                            this.isPwOff = false;
                            this.snackbarPwOk = true;
                            this.pwOld = '';
                            this.pwNew = '';
                        })
                        
                    } else if(res.data == 0){
                        //console.log("이거 왜이러지")
                        //기존 비밀번호 불일치
                        this.snackbarPw = true;
                    }
                }).catch(e=>{
                    alert(e + "오류가 발생했네")
                })
            }
            
        },

        //주소 변경
        async addrSubmit(){
            if(!this.$v.addr1.required
                || !this.$v.address.required
                || !this.$v.addr3.required){
                this.addr3Errors = ['주소를 입력해주세요.']
            
            }else{
                this.addr3Errors = []
                let data = {
                    userId: sessionStorage.getItem('userId'),
                    userAddr: this.address +", "+ this.addr3,
                }
                axios.post(process.env.baseUrl+'/userInfo/updateAddr', JSON.stringify(data),{
                    headers: {
                        "Content-Type": `application/json`
                    }

                }).then((res)=>{
                    //console.log(result.data);
                    this.selectUserInfo();
                    this.isAddrOn = true;
                    this.isAddrOff = false;
                    
                    this.address = '';
                    this.addr1= '';
                    this.addr3='';
                }).catch(e=>{
                    alert(e + "오류가 발생했네")
                })
            }
        },

        //주소 api
        execDaumPostcode() {
            new window.daum.Postcode({
                oncomplete: (data) => {
                if (this.extraAddress !== "") {
                    this.extraAddress = "";
                }
                if (data.userSelectedType === "R") {
                    // 사용자가 도로명 주소를 선택했을 경우
                    this.address = data.roadAddress;
                } else {
                    // 사용자가 지번 주소를 선택했을 경우(J)
                    this.address = data.jibunAddress;
                }
        
                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if (data.userSelectedType === "R") {
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if (data.bname !== "" && /[동|로|가]$/g.test(data.bname)) {
                    this.extraAddress += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if (data.buildingName !== "" && data.apartment === "Y") {
                    this.extraAddress +=
                        this.extraAddress !== ""
                        ? `, ${data.buildingName}`
                        : data.buildingName;
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if (this.extraAddress !== "") {
                    this.extraAddress = `(${this.extraAddress})`;
                    }
                } else {
                    this.extraAddress = "";
                }
                // 우편번호를 입력한다.
                this.addr1 = data.zonecode;
                },
            }).open();
        },
        
    },
}
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
.sclick{
    font-weight: bold; 
    color: #222 !important; 
    text-decoration: underline !important;
}
.userBtn{
    font-weight: 100; 
    height: 40px; 
    background-color: #222 !important; 
    color: white !important;
    margin-left: -20px;
}
.userBtnCancle{
    background-color: rgb(160, 159, 159) !important;
}
.addrSearch{
    margin-top: 10px !important;
    background-color: #222 !important;
}
.addrNum{
    padding-right: 30px;
    
}
</style>