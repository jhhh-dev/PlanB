<template>
    <div>
      
      <User />
      
    </div>
  </template>
  <script>
  import User from "/components/front/mypage/User.vue";
  export default {
    name: "",
    head(){
      return{
        
      }
    },
    components: {
        User,
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