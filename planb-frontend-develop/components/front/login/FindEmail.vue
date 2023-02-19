<template >
    <div>
        <v-container style="margin: auto; max-width: 1100px;">
            <v-row justify="center" class="mt-10">
                <v-col xl="6" lg="6" md="6" sm="8" xs="12">
                    <!-- 이메일 찾기 -->
                    <v-card class="findEmailForm" v-if="isEmailOn">
                        <v-card-title class="justify-center"> 
                            이메일 찾기
                        </v-card-title>
                        <hr/>
                        <div>
                            <small>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;가입 시 등록한 휴대폰 번호를 입력하면
                            이메일 주소의 일부를 알려드립니다.</small>
                        </div>
                        <v-card-subtitle>
                            <form action="" class="form">
                                <v-text-field
                                v-model="userPhone"
                                type="text"
                                label="핸드폰 번호"
                                class="form__control"
                                hide-details="auto"
                                :error-messages="phoneErrors"
                                outlined
                                required
                                
                                >
                                </v-text-field>
                                <v-btn  
                                class="login__btn loginbtn"
                                @click="findEmail()"
                                >이메일 찾기</v-btn>
                            </form>
                        </v-card-subtitle>
                    </v-card>

                    <!-- 이메일 출력 -->
                    <v-card class="findEmailForm" v-if="isEmailOff">
                        <v-card-title class="justify-center"> 
                            이메일 찾기 성공!
                        </v-card-title>
                        <hr/>
                        <div>
                            <small>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;가입하신 이메일입니다.</small>
                        </div>
                        <v-card-subtitle>
                            <div class="emaildiv">
                                {{ email }}
                            </div>
                            <nuxt-link to="/logins/findPw">
                                <v-btn  
                                class="login__btn loginbtn"
                                >비밀번호 찾기</v-btn>
                            </nuxt-link>
                            <nuxt-link to="/login">
                                <v-btn  
                                class="login__btn loginbtn"
                                >로그인</v-btn>
                            </nuxt-link>
                        </v-card-subtitle>
                    </v-card>
                </v-col>
            </v-row>
        </v-container>
    </div>
</template>
<script>
import { validationMixin } from 'vuelidate'
import { required  } from 'vuelidate/lib/validators'
import axios from 'axios'

export default {
    
    mixins: [validationMixin],

    data: () => ({
        userPhone: '',
        isEmailOn: true,
        isEmailOff: false,
        email: '',

        phoneErrors: [],
    }),

    validations: {
        userPhone: { required },
    },

    methods: {
        async findEmail () {
            if (!this.$v.userPhone.required) {
                
                this.phoneErrors= ['핸드폰 번호를 입력해주세요.'];
                
            }else{
                this.phoneErrors=[]
                //await axios.get('http://localhost:8080/join/findEmail', {
                
                await axios.get(process.env.baseUrl + '/join/findEmail',{
                    params : {
                        
                        userPhone: this.userPhone,
                    }
                })
                .then((res) => {
                    console.log(res.data)
                    if(res.data == "NO"){
                        alert("일치하는 정보가 없습니다.");
                    }
                    else{
                        this.email = res.data;
                        this.isEmailOn = false;
                        this.isEmailOff = true;
                    }
                    
                })
                .catch(err=>{
                    alert("오류 발생.");
                })
            }
        
        },

        
    },

    
};
</script>
<style>
    .form__control{
        margin: 30px 0 !important;
    }
    .loginbtn{
        width: 100%;
        height: 50px !important;
        margin-top: 10px;
        color: white !important;
        background-color: #222 !important;
        font-weight: 700;
        font-size: 15px !important;
    }
    .row__link{
        margin:-20px 5px 100px;
        padding-bottom: 20px;
        text-align: center;
        /* font-weight: 400; */
        font-size: 15px;
    }
    .text__link{
        color: black !important;
        border-bottom:none;
    }
    .findEmailForm{
        margin-bottom: 50px;
    }
    .emaildiv{
        font-size: 30px;
        text-align: center;
        margin: 50px;
    }

</style>