<template >
  <div>
    <v-navigation-drawer
      v-model="drawer"
      :mini-variant="miniVariant"
      :clipped="clipped"
      fixed
      app
    >

      <!-- 로고 영역 -->
      <div class="adminLogo">

        <nuxt-link to="/admin/member">
          <img alt="" src="../../assets/images/logo.png" width="150px" style="cursor: pointer;"  />
        </nuxt-link>
        
      </div>

      <v-list>
        <v-list-item
          v-for="(item, i) in items"
          :key="i"
          :to="item.to"
          router
          exact
        >
          <v-list-item-action>
            <v-icon>{{ item.icon }}</v-icon>
          </v-list-item-action>
          <v-list-item-content>
            <v-list-item-title v-text="item.title" />
          </v-list-item-content>
        </v-list-item>
      </v-list>
    </v-navigation-drawer>

    <!-- 상단 바 -->
    <v-app-bar :clipped-left="clipped" flat app>
      <v-app-bar-nav-icon @click.stop="drawer = !drawer" />
      <b><v-toolbar-title v-text="title" /></b>
      <v-spacer />

    <nuxt-link to="/">
      <v-btn color="secondary" >
        쇼핑몰 이동
      </v-btn>
    </nuxt-link>
      
    </v-app-bar>
  </div>
</template>

<script>
export default {
  name: "SidebarComp",
  data() {
    return {
      clipped: false,
      drawer: true,
      fixed: false,
      items: [
        // {
        //   icon: "mdi-home",
        //   title: "대시보드",
        //   to: "/admin/dashboard",
        // },
        {
          icon: "mdi-account",
          title: "회원 관리",
          to: "/admin/member",
        },
        {
          icon: "mdi-archive",
          title: "상품 관리",
          to: "/admin/product",
        },
        {
          icon: "mdi-currency-krw",
          title: "결제 관리",
          to: "/admin/payment",
        },
        {
          icon: "mdi-format-list-bulleted",
          title: "주문 관리",
          to: "/admin/order",
        },
      ],
      miniVariant: false,
      right: true,
      rightDrawer: false,
      title: "관리자 페이지",

      //메소드 선언 구간
      methods: {

        // 클릭 시 root 경로로 이동
        moveToShop() {
          console.log("쇼핑몰이동")
          // this.$router.push({ path: '/' })
          this.$nuxt.$router.push("/")
        },

        //클릭 시 어드민 대시보드
        replaceAdmin() {
          console.log("로고클릭")
          // this.$router.replace({ path: '/admin/dashboard' })
        }

}
    };
  },
};


</script>
<style >
  .adminLogo {
    width: 100%;
    border-bottom: 1px solid lightgray;
    text-align: center;
    padding-top: 20px;
    padding-bottom: 20px;
  }

  .adminLogo a {

    /* 밑줄 방지 */
    border-bottom: 0px;
  }


</style>
