<template >
    <div>
        <v-container style="margin: auto; max-width: 1100px;">
            <v-row justify="center" class="mt-10">
                <v-col xl="6" lg="6" md="6" sm="8" xs="12">
                    <!-- 비밀번호 찾기 -->
                    <v-card class="findEmailForm" v-if="isEmailOn">
                        <v-card-title class="justify-center"> 
                            비밀번호 찾기
                        </v-card-title>
                        <hr/>
                        <div>
                            <small>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</small>
                        </div>
                        <v-card-subtitle>
                            <form action="" class="form">
                                <v-text-field
                                v-model="userId"
                                type="text"
                                label="이메일"
                                class="form__control"
                                hide-details="auto"
                                :error-messages="idErrors"
                                outlined
                                required
                                
                                >
                                </v-text-field>
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
                                @click="findPw()"
                                >비밀번호 찾기</v-btn>
                            </form>
                        </v-card-subtitle>
                    </v-card>

                    <!-- 스낵바 -->
                    <v-snackbar
                    v-model="snackbar"
                    :timeout="timeout"
                    >
                        <p>회원정보를 확인하고 있습니다.</p>

                    </v-snackbar>

                    <!-- 비밀번호 문구 출력 -->
                    <v-card class="findEmailForm" v-if="isEmailOff">
                        <v-card-title class="justify-center"> 
                            비밀번호 찾기 성공!
                        </v-card-title>
                        <hr/>
                        <div>
                            <small>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</small>
                        </div>
                        <v-card-subtitle>
                            <div>
                                회원님의 이메일로 임시 비밀번호를 발송해 드렸습니다.<br/>
                            </div>
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
        userId: '',
        userPhone: '',
        isEmailOn: true,
        isEmailOff: false,
      //  email: '',

        idErrors: [],
        phoneErrors: [],

        snackbar: false,
        timeout: 3000,
    
    }),

    validations: {
        userId: { required },
        userPhone: { required },
    },

    methods: {
        async findPw () {

            if (!this.$v.userId.required) {
                
                this.idErrors= ['이메일을 입력해주세요.'];
                
            }else if (!this.$v.userPhone.required) {
                this.idErrors = []
                this.phoneErrors= ['핸드폰 번호를 입력해주세요.'];
                
            } else {
                this.phoneErrors=[]
                let data = {
                    userId: this.userId,
                    userPhone: this.userPhone,
                }
                this.snackbar = true
                axios.post(process.env.baseUrl+'/logins/sendMail', JSON.stringify(data), {
                    headers: {
                        "Content-Type": `application/json`,
                    },
                })
                .then((res) => {
                    console.log(res.data)
                    if(res.data == 0){
                        this.snackbar = false;
                        alert("일치하는 정보가 없습니다.");
                    }
                    else{
                    // this.email = res.data;
                        this.isEmailOn = false;
                        this.isEmailOff = true;
                    }
                    
                })
                .catch(err=>{
                    alert("오류 발생." + err)
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