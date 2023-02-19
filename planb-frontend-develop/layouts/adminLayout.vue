<template>
  <v-app>
    <v-main>

      <Sidebar />

      <div style="width: 100%;">
        <Nuxt />
      </div>
    </v-main>
  </v-app>
</template>

<script>
import Sidebar from '../components/admin/Sidebar.vue';



export default {
  name: "AdminLayout",
  components: { Sidebar },



};
</script>

<style>

/* div.container 에 기본적으로 margin 47.5px 이 설정되어있음  */
.container {

  margin: 0;
}
</style>