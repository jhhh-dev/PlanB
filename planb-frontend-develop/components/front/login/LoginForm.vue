<template >
  <div>
    <v-container style="margin: auto; max-width: 1100px;">
      <v-row justify="center" class="mt-10">
        <v-col xl="6" lg="6" md="6" sm="8" xs="12"> 
          <v-card>
            <v-card-title class="justify-center"> 
              <!-- <img style="width: 200px; height: 100px;text-align: center; display: block;" src="https://i.ibb.co/PgXwndV/logo.png" alt="PlanB" border="0"> -->
              <img style="width: 300px; height: 150px; margin-bottom: -30px;" src="https://i.ibb.co/Fq4DYLf/logo.png" alt="PlanB" border="0">
            </v-card-title>
            <v-card-subtitle>
              <form action="" class="form" @click.prevent="">
                <v-text-field
                  v-model="userId"
                  label="이메일"
                  class="form__control"
                  hide-details="auto"
                  :error-messages="idErrors"
                  outlined
                  required
                  
                >
                </v-text-field> 
                <v-text-field
                  v-model="userPw"
                  type="password"
                  label="비밀번호"
                  class="form__control"
                  hide-details="auto"
                  :error-messages="pwErrors"
                  outlined
                  required
                  
                >
                </v-text-field>
                <v-btn  
                  class="login__btn loginbtn"
                  @click="login(userId, userPw)"
                >로그인</v-btn>
              </form>
            </v-card-subtitle>
            <v-row class="row__link">
              <v-col ><nuxt-link to="/join" class="text__link">회원가입</nuxt-link> </v-col>
              <v-col ><nuxt-link to="/logins/findEmail" class="text__link">이메일 찾기</nuxt-link> </v-col>
              <v-col ><nuxt-link to="/logins/findPw" class="text__link">비밀번호 찾기</nuxt-link> </v-col>
            </v-row>
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
  //name: "loginPage",

  data: () => ({
    userId: '',
    userPw: '',

    idErrors: [],
    pwErrors: [],
  }),

  validations: {
      userId: { required },
      userPw: { required },
  },

  computed: {
      // idErrors () {
      //     const errors = []
      //     if (!this.$v.userId.$dirty) return errors
      //     !this.$v.userId.required && errors.push('required.')
      //     return errors
      // },
      // pwErrors () {
      //     const errors = []
      //     if (!this.$v.userPw.$dirty) return errors
      //     !this.$v.userPw.required && errors.push('required.')
      //     return errors
      // },
  },

  methods: {
      // submit () {
      //     this.$v.$touch()
      // },

      async login(userId, userPw){
          
          // const result = await axios.post('http://localhost:8080/userInfo/pwCheck', JSON.stringify(data1), {
          //     headers: {
          //         "Content-Type": `application/json`,
          //     },
          // });
          this.userId = userId
          this.userPw = userPw
        
          
          if (!this.$v.userId.required) {
            
            this.idErrors= ['이메일을 입력해주세요.'];
            
          } else if (!this.$v.userPw.required) {
            this.idErrors = []
            this.pwErrors= ['비밀번호을 입력해주세요.'];
            
          } else {
            
              const params = {
                  "userId" : this.userId,
                  "userPw" : this.userPw
              }
              axios.post(process.env.baseUrl+'/login', JSON.stringify(params),{
                  headers: {'content-type':'application/json'}
              }).then(res=>{
                
                console.log( res.data['userRole']+"롤롤롤")
                //sessionStorage.setItem("token", res.headers['accesstoken'])
                sessionStorage.setItem("userId", res.data['userId'])
                //sessionStorage.setItem("userName", res.data['userName'])
                sessionStorage.setItem("isLogin", true)
                sessionStorage.setItem("userRole", res.data['userRole'])
                
               // console.log(jwt_decode(res.headers['accesstoken'])+"토큰 디코더")
                //console.log(sessionStorage.getItem('token')+"토큰")
                console.log(sessionStorage.getItem('userId')+"아이디")
                console.log(sessionStorage.getItem('isLogin')+"isLogin")
                console.log(sessionStorage.getItem('userRole')+"userRole")
                

                //this.$nuxt.$router.push("/")
                this.$router.go();
                //this.$nuxt.$router.go(this.$router.currentRoute)
              }).catch(e=>{
                  console.log(e)
                  alert("이메일 또는 비밀번호가 일치하지 않습니다.")
              })


          }
          
      },

      test(){
        console.log(sessionStorage.getItem('userId')+"아이디")
      },

      //로그인 시 메인으로 이동
      checkLogin(){
        console.log(sessionStorage.getItem('isLogin')+"isLogin")
        if(sessionStorage.getItem('isLogin')){
          this.$nuxt.$router.push("/")
        }
      }
  },

  mounted() {
      //this.selectUserInfo();
      //this.submit();
      this.test();
      this.checkLogin();

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

</style>