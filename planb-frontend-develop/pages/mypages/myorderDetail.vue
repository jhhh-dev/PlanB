<template>
    <div>
      
      <MyOrderDetail />
      
    </div>
  </template>
  <script>
  import MyOrderDetail from "/components/front/mypage/MyOrderDetail.vue";
  export default {
    name: "",
    head(){
    return{
    
    }
    },
    components: {
        MyOrderDetail,
    },
    beforeRouteEnter (to, from, next) {
      const isLogin = typeof window !== 'undefined' ? sessionStorage.getItem('isLogin') : null;
      if(!isLogin) {
        next('/login')
      } else {
        next()
      }
    }
  };
  </script>
  <style>
  </style>