<template>
    <div>
      
      <MyReview />
      
    </div>
  </template>
  <script>
  import MyReview from "/components/front/mypage/MyReview.vue";
  export default {
    name: "",
    head(){
    return{
    
    }
    },
    components: {
        MyReview,
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