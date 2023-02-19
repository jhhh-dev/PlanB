<template>
    <div>
        <v-carousel
        cycle
        hide-delimiter-background
        style="cursor: pointer;"
        >

            <v-carousel-item>
                <div style="display: flex; justify-content: center; background-color: rgb(230, 239, 236);" @click="bannerClick()">
                    <img src="@/assets/images/banner_NB.jpg" alt="" height="500px" />
                </div>
            </v-carousel-item>

            <v-carousel-item>
                <div style="display: flex; justify-content: center; background-color: rgb(235, 235, 235);" @click="bannerClick()">
                    <img src="@/assets/images/banner_VAL.jpg" alt="" height="500px" />
                </div>
            </v-carousel-item>
            
            <v-carousel-item>
                <div style="display: flex; justify-content: center; background-color: black" @click="bannerClick()">
                    <img src="@/assets/images/banner_CH.jpg" alt="" height="500px" />
                </div>
            </v-carousel-item>

            <v-carousel-item>
                <div style="display: flex; justify-content: center; background-color: rgb(21, 117, 156);" @click="bannerClick()">
                    <img src="@/assets/images/banner_KEEN.jpg" alt="" height="500px" />
                </div>
            </v-carousel-item>

            

        </v-carousel>
            
            
        
    </div>
</template>
<script>



export default {

    data:()=>({

        items: [
          {
            src: 'https://cdn.vuetifyjs.com/images/carousel/squirrel.jpg',
          },
          {
            src: 'https://cdn.vuetifyjs.com/images/carousel/sky.jpg',
          },
          {
            src: 'https://cdn.vuetifyjs.com/images/carousel/bird.jpg',
          },
          {
            src: 'https://cdn.vuetifyjs.com/images/carousel/planet.jpg',
          },
        ],


        num:'',
    }),

    methods: {
        bannerClick() {
            this.$nuxt.$router.push("/shop");
        }

    },
    


}
</script>
<style >
    
</style>