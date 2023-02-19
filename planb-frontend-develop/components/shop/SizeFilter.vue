<template>
    <div class="m-text15 p-b-17">
      Size
      <v-row>
        <v-col>
          <v-flex
            shrink
            style="width: 80px"
          >
            <v-text-field
              v-model="size[0]"
              class="mt-0"
              hide-details
              single-line
              type="number"
            ></v-text-field>
          </v-flex>
        </v-col>
        <v-col>
          <v-flex
            shrink
            style="width:80px"
          >
            <v-text-field
              v-model="size[1]"
              class="mt-0"
              hide-details
              single-line
              type="number"
            ></v-text-field>
          </v-flex>
        </v-col>
      </v-row>

    <v-flex>
      <v-range-slider
        v-model="size"
        :max="290"
        :min="210"
        :step="5"
        class="align-center"
        thumb-label
        color="black"
        track-color="grey"
        thumb-color="black"
      ></v-range-slider>
    </v-flex>

    </div>
</template>
<script>
import { defineComponent } from '@vue/composition-api'

export default {
  data(){
    return{
      size: [210,290]
    }
   },
   
}
</script>
<style scoped>

</style>
