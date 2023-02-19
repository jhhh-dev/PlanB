<template >
  <div>
    <v-container style="margin-bottom: 70px !important; margin: auto; max-width: 1100px;">
      <v-row justify="center" class="mt-10">
        <v-col xl="6" lg="6" md="6" sm="8" xs="12">
          <v-card>
            <v-card-title class="justify-center"> 
              <!-- <img style="width: 200px; height: 100px;text-align: center; display: block;" src="https://i.ibb.co/PgXwndV/logo.png" alt="PlanB" border="0"> -->
              <img style="width: 300px; height: 150px; margin-bottom: -30px;" src="https://i.ibb.co/Fq4DYLf/logo.png" alt="PlanB" border="0">
            </v-card-title>
            <v-card-subtitle>
              <v-text-field
                v-model="userId"
                label="이메일"
                class="form__control"
                hide-details="auto"
                placeholder="예) planb@planb.co.kr"
                :error-messages="emailErrors"
                type="text"
                outlined
                required
              >
              </v-text-field>
              <v-text-field
                v-model="userPw"
                type="password"
                label="비밀번호"
                class="form__control"
                :error-messages="pwErrors"
                placeholder="영문(대소문자), 숫자, 특수문자 조합 8~16자."
                hide-details="auto"
                outlined
              >
              </v-text-field>
              <v-text-field
                v-model="userPhone"
                
                label="휴대폰 번호"
                class="form__control"
                placeholder=" - 없이 입력해주세요."
                :error-messages="phoneErrors"
                hide-details="auto"
                outlined
                
              >
              </v-text-field>
              <v-checkbox 
              v-model="checkbox"
              :error-messages="checkErrors"
              >
                <template v-slot:label>
                  <div>
                      [필수] 만 14세 이상이며 이용약관에 동의합니다.
                  </div>
                </template>
              </v-checkbox>
                
              

              
              <!-- <v-treeview
                  selectable
                  item-disabled="locked"
                  :items="items"
                  class="treeview"
              ></v-treeview> -->
              <v-btn  
              class="login__btn loginbtn join"
              @click="check()"
              >회원가입</v-btn>
            </v-card-subtitle>
          </v-card>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>
<script>
import { validationMixin } from 'vuelidate'
import { required, maxLength, minLength, email, numeric  } from 'vuelidate/lib/validators'
import axios from 'axios'

export default {
  mixins: [validationMixin],
  

  data: () => ({
    userId: '',
    userPw: '',
    userPhone: '',
    checkbox: false,
    valid: false,
    


    emailErrors: [],
    pwErrors: [],
    phoneErrors: [],
    checkErrors: [],

    
  }),

  validations: {
      userId: { required, email },
      userPw: { required, maxLength: maxLength(16), minLength: minLength(8), 
          
              valid: function(value) {
                  const containsUppercase = /[A-Z]/.test(value)
                  const containsLowercase = /[a-z]/.test(value)
                  const containsNumber = /[0-9]/.test(value)
                  const containsSpecial = /[#?!@$%^&*-]/.test(value)
                  return containsUppercase && containsLowercase && containsNumber && containsSpecial
              }
      },
      userPhone: { required, maxLength: maxLength(11), minLength: minLength(11), numeric},
      checkbox:{
        checked:  function(val) {
          return val
        },
      },
      
  },
  
  mounted() {

  },

  methods: {

      //회원가입 테스트
      check(){
          if (!this.$v.userId.required 
                || !this.$v.userId.email) {

              this.emailErrors= ['이메일을 입력해주세요.'];

          } else if (!this.$v.userPw.required 
                      || !this.$v.userPw.maxLength 
                      || !this.$v.userPw.minLength
                      || !this.$v.userPw.valid) {

            this.emailErrors = null
            this.pwErrors = ['비밀번호는 영문(대소문자), 숫자, 특수문자 조합 8~16자.']

          } else if(!this.$v.userPhone.maxLength 
                      || !this.$v.userPhone.minLength
                      || !this.$v.userPhone.required
                      || !this.$v.userPhone.numeric){

            this.pwErrors = null
            this.phoneErrors = ['휴대폰 번호 11자를 입력해주세요.']

          }else if(!this.$v.checkbox.checked){

            this.phoneErrors = null
            this.checkErrors = ['약관에 동의해주세요.']

          }else{
            this.phoneErrors = null
            this.checkErrors = null
            //회원가입
            let data = {
                userId: this.userId,
                userPw: this.userPw,
                userPhone: this.userPhone,
            }
            axios.post(process.env.baseUrl+'/joins', JSON.stringify(data), {
                headers: {
                    "Content-Type": `application/json`,
                },
            }).then(res =>{
              if(res.data == 0){
                alert('중복된 이메일입니다.')
              }else if(res.data == -1){
                alert('중복된 핸드폰 번호입니다.')
              }else{
                alert('회원가입에 성공하였습니다.')
                this.$router.push("/login");
              }
                
            }).catch(err => {
              alert('회원가입에 실패하였습니다. 다시 시도해주세요. '+ err)
            })
          }
      },

  },
  
}

</script>
<style>
.form__control{
    margin: 30px 0 !important;
}
.loginbtn{
    width: 100%;
    height: 50px !important;
    margin-top: -10px;
    color: white !important;
    background-color: #222 !important;
    font-weight: 700;
    font-size: 15px !important;
}
.row__link{
    margin:-20px 5px;
    text-align: center;
    /* font-weight: 400; */
    font-size: 15px;
}
.text__link{
    color: black !important;
    border-bottom:none;
}
.join{
    margin-top:20px !important;
}
.treeview{
    margin: -20px 0 10px 0 !important;
}
.input-error {
line-height: 16px;
font-size: 11px;
color: red !important;
}
.title-danger {
  color: color-error;
}
.input-danger {
  border-bottom: 1px solid color-error !important;
}
</style>