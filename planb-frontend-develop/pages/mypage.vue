<template>
    <div>
      
      <MyVue />
      
    </div>
  </template>
  <script>
  import MyVue from "/components/front/mypage/MyVue.vue";
  export default {
    data() {
      return {
        name: "",
      }
    },
    head(){
      return{
    
      }
    },
  
    components: {
      MyVue,
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