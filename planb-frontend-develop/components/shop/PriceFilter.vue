<template>
  <div class="filter-price p-t-22 p-b-50 bo3">
    <div class="m-text15 p-b-17">
      Price
    </div>
      <v-flex xs12 sm4 lg12>
        <v-checkbox
          no-gutters
          v-model="checkbox"
          color="black"
          label="10만원 이하"
        ></v-checkbox>
      </v-flex>
      <v-flex xs12 sm4 lg12>
        <v-checkbox
          no-gutters
          v-model="checkbox"
          color="black"
          label="10만원 - 30만원 이하"
        ></v-checkbox>
      </v-flex>
      <v-flex xs12 sm4 lg12>
        <v-checkbox
          no-gutters
          v-model="checkbox"
          color="black"
          label="30만원 - 50만원 이하"
        ></v-checkbox>
      </v-flex>
      <v-flex xs12 sm4 lg12>
        <v-checkbox
          v-model="checkbox"
          color="black"
          label="50만원 이상"
        ></v-checkbox>
      </v-flex>
  </div>
</template>

<script>
  export default {
    /*
    mounted() {
      var filterBar = document.getElementById('filter-bar');
      noUiSlider.create(filterBar, {
        start: [ 50, 200 ],
        connect: true,
        range: {
          'min': 50,
          'max': 200
        }
      });
      var skipValues = [
        document.getElementById('value-lower'),
        document.getElementById('value-upper')
      ];
      filterBar.noUiSlider.on('update', function( values, handle ) {
        skipValues[handle].innerHTML = Math.round(values[handle]) ;
      });
      filterBar.noUiSlider.on('end', (values) => {
        this.$store.dispatch('product/setPriceRange', values);
      })
    }

   //가격 min/max
   data(){
    return{
      price: [10000,100000]
    }
   }
  */

  }
</script>