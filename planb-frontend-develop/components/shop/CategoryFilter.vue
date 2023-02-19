<template>
  <div>
    <h4 class="sr-only">
      카테고리 필터
    </h4>
    <div class="filter-category p-t-22 p-b-50 bo3">
    <div class="m-text15 m-b-20">
      <h3>Category</h3>
      <h3>data 확인용: {{CateSelected}}</h3>
      <h3>data 확인용: {{PriceSelected}}</h3>
      <h3>data 확인용: {{Size}}</h3>
    </div>

      <v-flex xs12>
        <v-checkbox
          no-gutters
          v-model="CateSelected"
          color="black"
          label="Sneakers"
          value=10
        ></v-checkbox>
      </v-flex>
      <v-flex>
        <v-checkbox
          no-gutters
          v-model="CateSelected"
          color="black"
          label="Loafers"
          value=20
        ></v-checkbox>
      </v-flex>
      <v-flex xs12>
        <v-checkbox
          no-gutters
          v-model="CateSelected"
          color="black"
          label="Sandals/Slippers"
          value=30
        ></v-checkbox>
      </v-flex>
      <v-flex xs12>
        <v-checkbox
          v-model="CateSelected"
          color="black"
          label="Boots"
          value=40
        ></v-checkbox>
      </v-flex>
      <v-flex xs12>
        <v-checkbox
          v-model="CateSelected"
          color="black"
          label="Hills/Pumps"
          value=50
        ></v-checkbox>
      </v-flex>
    </div>

    <v-divider class="my-2"></v-divider>
    <h4 class="sr-only">
      가격 필터
    </h4>
    <div class="filter-price p-t-22 p-b-50 bo3">
    <div class="m-text15 p-b-17">
      <h3>Price</h3>
    </div>
      <v-flex xs12>
        <v-checkbox
          v-model="PriceSelected"
          color="black"
          label="10만원 이하"
          value=1
        ></v-checkbox>
      </v-flex>
      <v-flex xs12>
        <v-checkbox
          v-model="PriceSelected"
          color="black"
          label="10만원 - 30만원 이하"
          value=2
        ></v-checkbox>
      </v-flex>
      <v-flex xs12>
        <v-checkbox
          v-model="PriceSelected"
          color="black"
          label="30만원 - 50만원 이하"
          value=3
        ></v-checkbox>
      </v-flex>
      <v-flex xs12>
        <v-checkbox
          v-model="PriceSelected"
          color="black"
          label="50만원 이상"
          value=4
        ></v-checkbox>
      </v-flex>
    </div>
    
    <v-divider class="my-2"></v-divider>
    <h4 class="sr-only">
      사이즈 필터
    </h4>
    <div class="filter-size m-text15 p-b-17">
      <h3>Size</h3>
      <v-flex>
        <v-range-slider
          v-model="Size"
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
      <v-row>
        <v-col>
            <!-- <v-text-field
              v-model="Size[0]"
              class="mt-0"
              hide-details
              single-line
              type="number"
            ></v-text-field> -->
            <p>{{Size[0]}}</p>
        </v-col>
        <v-col>
        <p>~</p>
        </v-col>
        <v-col>
            <!-- <v-text-field
              v-model="Size[1]"
              class="mt-0"
              hide-details
              single-line
              type="number"
            ></v-text-field> -->
            <p>{{Size[1]}}</p>
        </v-col>
      </v-row>
    </div>

    <v-btn
    @clitk = "resetFilter()"
    depressed outlined color="black">초기화</v-btn>
    <v-btn 
    @click = "applyFilter()"
    depressed outlined color="black">적용</v-btn>
  </div>
</template>

<script>
  export default {
    data(){
      return{
        CateSelected: [],
        PriceSelected: [],
        SizeSelected: [],
        Size: [210,290],

      }
    },

    methods: {
      applyFilter(){
        alert('클릭');
      },

      resetFilter(){

      },
    }
  }
</script>

<style scoped>
  ul li a{
    color: black !important;
  }
  a:hover{
    text-decoration: underline;
  }

  .sizeBtn{
    margin: 0 -6px 0 0;
    padding: 7px 0 14px;
  }
</style>