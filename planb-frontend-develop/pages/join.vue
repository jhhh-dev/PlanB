<template>
    <div>
      
      <JoinForm />
      
    </div>
  </template>
  <script>
  import JoinForm from "/components/front/login/JoinForm.vue";
  export default {
    name: "AboutPage",
    head(){
    return{
      
    }
    },
    components: {
        JoinForm,
    },
  };
  </script>
  <style>
  </style>