<template>
    <div>
      
      <MyOrder />
      
    </div>
  </template>
  <script>
  import MyOrder from "/components/front/mypage/MyOrder.vue";
  export default {
    name: "",
    head(){
    return{
    
    }
    },
    components: {
        MyOrder,
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