<template>
  <div style="margin-top: 200px;">
    <v-row justify="center">
      <v-col xl="4" lg="4" md="6" sm="8" xs="12">
        <v-card>
          <v-card-title>
            <h2>관리자 로그인</h2>
          </v-card-title>
          <v-card-actions>
            <form action="" class="form">
              <v-text-field label="ID 입력" class="form__control" hide-details="auto" autofocus>
              </v-text-field>
              <v-text-field label="비밀번호 입력" class="form__control" hide-details="auto">
              </v-text-field>

              <div style="display: flex; justify-content: end;">
                <v-btn color="error lighten-1" class="login__btn"> 돌아가기</v-btn>
                &emsp;
                <v-btn color="secondary lighten-2" class="login__btn"> 로그인</v-btn>
              </div>
            </form>
          </v-card-actions>
        </v-card>
      </v-col>
    </v-row>
  </div>
</template>

<script>
import axios from 'axios';

export default {

  layout: "adminLayoutLogin",

  data() {
    return {

    }
  },

  methods: {

    adminCheck() {

      axios({
        url: backUrl + '/adminCheck',
        method: "POST",
        data: formData,
        headers: {
          'Content-Type': 'multipart/form-data'
        }

      }).then(res => {

        console.log("전송 성공");

        
        // 멤버리스트로 이동
        this.$nuxt.$router.push("/admin/member")



      }).catch(err => {

        alert(err);
      })
    }

  },
}
</script>
