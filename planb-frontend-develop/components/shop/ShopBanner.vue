<template>
    <div>
        <v-carousel
        cycle
        hide-delimiters
        height= "auto">

            <v-carousel-item>
                <div style="display: flex; justify-content: center; background-color: #F5F5F5;">
                    <img src="@/assets/images/Pbanner1.jpg" alt="" height="150px" />
                </div>
            </v-carousel-item>

            <v-carousel-item>
                <div style="display: flex; justify-content: center; background-color: #6968EA;">
                    <img src="@/assets/images/Pbanner2.jpg" alt="" height="150px" />
                </div>
            </v-carousel-item>
            
            <v-carousel-item>
                <div style="display: flex; justify-content: center; background-color: #EEEEEE">
                    <img src="@/assets/images/Pbanner3.jpg" alt="" height="150px" />
                </div>
            </v-carousel-item>

            <v-carousel-item>
                <div style="display: flex; justify-content: center; background-color: #F7F0E8;">
                    <img src="@/assets/images/Pbanner4.jpg" alt="" height="150px" />
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


    },
    


}
</script>
<style >
    
</style>