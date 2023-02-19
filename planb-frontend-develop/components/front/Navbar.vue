<template >
  <!-- 전체 묶는 div -->
  <div class="bigBox">

    <!-- 윗줄 - 고객센터 라인 -->
    <div style="width: 100%; display: flex; justify-content: end; margin-bottom: 15px;">
      <div>
        <nuxt-link v-if="isAdmin" to="/admin/member" class="nav_link2" exact>Admin</nuxt-link>
        <nuxt-link to="/cscenter/notice" class="nav_link2" exact>고객센터</nuxt-link>
        
        <nuxt-link v-if="!isLogin" to="/login" class="nav_link2" exact>로그인</nuxt-link>
        <nuxt-link v-if="isLogin" to="/mypage" class="nav_link2 mypage" exact>마이페이지</nuxt-link>
        <nuxt-link v-if="isLogin" to="/" class="nav_link2" @click.native="logout()" exact>로그아웃</nuxt-link>
      </div>
    </div>
    

    <!-- 아랫줄 - 로고 + NavBar 라인 -->
    <div>
      <div style="display: flex; justify-content: space-between;">
    
        <!-- 로고 이미지 -->
        <div style="position: relative; top: -15px;">
          <img alt="" src="../../assets/images/logo.png" width="150px" style="cursor: pointer;" @click="goHome()" />
        </div>
    
        <div style="display: flex; ">
          
          <!--메뉴-->
          <nuxt-link to="/" class="nav_link" exact>Home</nuxt-link>
          <nuxt-link to="/style" class="nav_link" exact>Style</nuxt-link>
          <nuxt-link to="/shop" class="nav_link" exact>Shop</nuxt-link>
          <!--<nuxt-link to="/searchpage" class="nav_link" exact>Search</nuxt-link>-->

          <!--검색창-->
          <div class="search">
            <img
              src="@/assets/images/search.png" width="23px" height="23px"
              style="position: relative; margin-right: 10px; cursor: pointer;"
              @click="$router.push('/shop')"/>
          </div>

        </div>
      </div>
    </div>
  </div>
</template>

<script>

export default {
  name: "NavbarComp",
  
  data() {
    return{
      nav: false,
      searchword: null,


      isAdmin: false,
      isLogin: null,

    }
  },

  // created () {
  //     //this.selectUserInfo();
  //     //this.submit();
  //     this.loginCheck()
  // },

  mounted(){
    this.loginCheck()

  },

  updated() {
    
  },

  //메소드 선언 구간
  methods: {
    
    loginCheck(){
      //this.isLogin = sessionStorage.getItem('isLogin');
      //this.isLogin = typeof window !== 'undefined' ? sessionStorage.getItem('isLogin') : false;
      //console.log(sessionStorage.getItem('isLogin')+"isLogin");
      this.isLogin = sessionStorage.getItem('isLogin') ? true : false
      this.isAdmin = sessionStorage.getItem('userRole') == 'ADMIN' ? true : false
    },

    goHome() {
      // 클릭 시 root 경로로 이동
      this.$router.push({ path: '/' })
    },

    // 검색창에 검색어 입력 시 SHOP 페이지로 이동
    search(){
      console.log(this.searchword);
      if(this.searchword){
          this.$router.push({
          name: "shop",
          query: { keyword: this.searchword }
          });
        //shop 페이지일 경우 리로드
        if(this.$route.name == "shop"){
          this.$router.go(this.$router.currentRoute);
        }

        

      } else{
        alert('검색어를 입력해주세요.');
      }
    },

    //로그아웃
    logout(){
      
      sessionStorage.clear();
      this.$router.push('/')
      this.$router.go()
    },
  }
}
</script>

<style>
.bigBox {
  width: 100%; 
  height: 120px;
  /* 상 우 하 좌 */
  padding: 10px 80px 10px 80px;

  position: fixed; 
  z-index: 10; 
  
  background-color: #ffffff; 
  box-shadow: 0px 0px 0px 1px lightgray;
}

.search{
  width: 100%;
  display: flex;
  padding: 10px;
  margin-left: 10px;
}

.search_content{
  width: 100%;
  height: 100%;
  background: white;
}

.search_wrap{
  padding: 12px 24px;
  border-bottom: 1px solid white;
}

</style>