<template>
    <div>
      
      <MyLike />
      
    </div>
  </template>
  <script>
  import MyLike from "/components/front/mypage/MyLike.vue";
  export default {
    name: "",
    head(){
    return{
    
    }
    },
    components: {
        MyLike,
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