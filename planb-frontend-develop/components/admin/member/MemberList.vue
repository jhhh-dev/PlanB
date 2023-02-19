<template>
  <div>

    <v-container>
      <v-card>

        <v-card-text class="d-flex justify-space-between align-center" style="height: 50px;">
          <v-card-title>
            <b>회원 관리</b>
          </v-card-title>

        </v-card-text>
        <hr />


        <v-card-subtitle>
          <!-- 검색 구간 -->
          <div
            style="width: 100%; display: flex; border-radius: 5px; padding: 10px; box-shadow: 2px 2px 2px 2px lightgray;">
            <input type="text" placeholder="회원명 또는 이메일 검색"
              style="width: 100%; height: 35px; padding-left: 10px; outline: none;" @keyup.enter=handlelSearch()
              :value="searchWord" @input="setSearchWord" />
            <!-- <img src="../../../assets/images/search.png" width="20px" height="20px" style="position: relative; top: 8px; margin-right: 10px; cursor: pointer;" @click=handlelSearch() /> -->
            <v-icon style="pointer;" @click=handlelSearch()>mdi-magnify</v-icon>
          </div>

          <br />
          <div style="display: flex; justify-content: space-between; height: 40px;">

            <div style="margin-left: 10px; padding-top: 5px;">

              <!-- 결과가 있을 때 -->
              <div v-if="searchFinish === true">
                <b>
                  '{{ searchWord }}' (으)로 검색된 회원 : {{ memberList.length }} 명
                </b>
              </div>

              <div v-if="searchFinish === false">
                <b>
                  조회된 회원 : {{ memberList.length }} 명
                </b>
              </div>

            </div>

            <!-- selectOption 검색 옵션 -->
            <div style="position: relative; top: -5px;">
              <v-select :items="searchOption" v-model="searchOptionSelected"
                :style="{ width: '150px', height: '15px', marginLeft: '90px' }" @change="testMethod()" outlined dense />


            </div>
          </div>

          <!-- 데이터 출력 구간 -->
          <v-simple-table class="memberTable">
            <template v-slot:default>
              <thead>
                <tr>
                  <!-- <th style="text-align: center;">번호</th> -->
                  <th style="text-align: center;">이메일</th>
                  <th style="text-align: center;">회원명</th>
                  <th style="text-align: center;">연락처</th>
                  <th style="text-align: center;">주소</th>
                  <th style="text-align: center;">가입일자</th>


                </tr>
              </thead>
              <tbody>
                <tr v-for="(data, index) in calData" :key="index">
                  <!-- <td style="width: 8%;">{{ index + 1 }}</td> -->
                  <td style="width: 20%;">{{ data.userId }}</td>
                  <td style="width: 15%;">{{ data.userName }}</td>
                  <td style="width: 15%;">{{ data.userPhone }}</td>
                  <td class="">{{ data.userAddr }}</td>
                  <td style="width: 17%;">{{ data.userDate | yyyyMMdd }}</td>

                </tr>

              </tbody>
            </template>
          </v-simple-table>




        </v-card-subtitle>
      </v-card>
    </v-container>

    <v-pagination :length="numofpage" v-model="curpagenum" color="secondary lighten-3" :total-visible="7"> </v-pagination>
    <br />


  </div>

</template>

<script>
import axios from 'axios';

// 백엔드와 통신할 url alias
const backUrl = 'http://localhost:8080';


export default {

  //변수값 설정 / 초기화
  data() {

    return {

      // 회원목록 테이블 Header 와 데이터 매핑
      headers: [
        // { text: '번호', value: 'idx', align: 'start', sortable: false },
        { text: '이메일', value: 'userId', align: 'center' },
        { text: '회원명', value: 'userName', align: 'center' },
        { text: '연락처', value: 'userPhone', align: 'center' },
        { text: '가입일자', value: 'userDate', align: 'center' },

      ],

      searchWord: '', // 검색 키워드
      searchFinish: false,  //검색완료 시 true 로 변경
      searchOption: ['최신순', '오래된순'],  // 검색 옵션
      searchOptionSelected: '최신순', // 검색 옵션 기본값
      searchCount: 0, // 검색딘 회원수

      memberList: [], // 검색된 회원 리스트

      // Pagination
      curpagenum: 1, // 1개 페이지에 (현재 UI에 보여지고있는 Page의 숫자)
      datapage: 10,  // 10개씩 출력

    }

  },

  // 화면 구성(랜더링) 전에 작업
  computed: {

    // 페이지네이션 관련
    startOffset() {
      return ((this.curpagenum - 1) * this.datapage);
    },
    endOffset() {
      return (this.startOffset + this.datapage);
    },
    numofpage() {
      return Math.ceil(this.memberList.length / this.datapage);
    },
    calData() {
      return this.memberList.slice(this.startOffset, this.endOffset);
    }
  },

  // 화면 최초 접근 시 실행
  mounted() {

    this.getMemberList()

  },

  methods: {

    // 회원 정보 출력
    async getMemberList() {

      // 검색단어 없을 때
      if (this.searchWord == '') {

        axios({
          url: backUrl + '/admin/memberList',
          method: "POST",
          data: {
            searchWord: '',
            searchOption: this.searchOptionSelected, // 검색 옵션만 보내기
          },

        }).then(res => {
          this.memberList = res.data;
          this.searchCount = this.memberList.length;
          this.searchFinish = false;
          this.searchWord = '';

        }).catch(err => {

          alert(err);
        });


      } else {

        // 검색단어가 있을 때
        axios({
          url: backUrl + '/admin/memberList',
          method: "POST",
          data: {
            searchWord: this.searchWord,
            searchOption: this.searchOptionSelected,
          },

        }).then(res => {
          this.memberList = res.data;
          this.searchCount = this.memberList.length;
          this.searchfinish = true;

          console.log("검색 갯수: " + this.searchCount);
          console.log("검색 여부: " + this.searchFinish);
          console.log("검색 옵션: " + this.searchOption);

        }).catch(err => {

          alert(err);
        });

      }
    },

    // v-select 변경 이벤트
    testMethod(e) {
      console.log(this.searchOptionSelected);


      this.getMemberList();
    },


    setSearchWord(e) {
      this.searchWord = e.target.value;

    },

    //검색 이벤트
    handlelSearch(e) {

      this.searchFinish = true;

      console.log("검색 단어 : " + this.searchWord);
      console.log("검색 옵션 : " + this.searchOptionSelected);
      console.log("검색 결과 : " + this.searchFinish);

      // 1페이지로 이동 (2페이지에서 1페이지 자료를 검색하면 이슈있어서 방지하기위함)
      this.curpagenum = 1

      // 회원 리스트 조회
      this.getMemberList();

    },


  },
  // methods 종료

  filters: {
    // filter로 쓸 filter ID 지정
    yyyyMMdd: function (value) {
      // 들어오는 value 값이 공백이면 그냥 공백으로 돌려줌
      if (value == '') return '';

      // 현재 Date 혹은 DateTime 데이터를 javaScript date 타입화
      var js_date = new Date(value);

      // 연도, 월, 일 추출
      var year = js_date.getFullYear();
      var month = js_date.getMonth() + 1;
      var day = js_date.getDate();

      // 월, 일의 경우 한자리 수 값이 있기 때문에 공백에 0 처리
      if (month < 10) {
        month = '0' + month;
      }

      if (day < 10) {
        day = '0' + day;
      }

      // 최종 포맷 (ex - '2021-10-08')
      // return year + '-' + month + '-' + day;
      return year + '년 ' + month + '월 ' + day + '일';
    },



  }


}
</script>

<style lang="scss" scoped>
.memberTable th,
td {
  text-align: center;
}
</style>