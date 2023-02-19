<template>
  <v-app>
    <v-main>

      <Navbar/>
        <div style="padding-top: 120px;">
          <Nuxt />
        </div>
      <Footer/>

      <v-btn v-scroll="onScroll" v-show="fab" fab dark fixed bottom right color="secondary" @click="toTop" style="opacity: 0.8;">
        <v-icon>mdi-format-vertical-align-top</v-icon>
      </v-btn>
      
    </v-main>
  </v-app>
</template>

<script>
import Footer from '../components/front/Footer.vue';
import Navbar from '../components/front/Navbar.vue';

export default {
    name: "DefaultLayout",
    components: { Footer, Navbar },
    data() {
      return {
        fab: false
      }
    },
    methods: {
      
      onScroll(e) {
        if (typeof window === 'undefined') return
        const top = window.pageYOffset || e.target.scrollTop || 0
        this.fab = top > 20
      },
      toTop() {
        this.$vuetify.goTo(0)
      }
      
    },

};
</script>
