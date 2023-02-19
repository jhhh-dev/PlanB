<template>
    <div class="shop-page">
        <v-container fluid justify-center>
            <!--배너-->
            <div class="banner">
                <ShopBanner/>
            </div>

            <div class="content">
                <!--필터 영역-->
                <div class="search-filter">
                    <v-col>
                        <v-card class="filter-card pa-4 ma-10">
                             <div class = "filter-side">
                                <h4 class="sr-only">카테고리 필터</h4>
                                <div class="filter-wrap p-t-22 p-b-50 bo3">
                                    <div class="m-text15 m-b-20">
                                    <h3>Category</h3>
                                    </div>

                                    <v-flex xs12>
                                        <v-checkbox
                                        no-gutters
                                        v-model="CateSelected"
                                        color="black"
                                        label="Sneakers"
                                        :value=10
                                        ></v-checkbox>
                                    </v-flex>
                                    <v-flex>
                                        <v-checkbox
                                        no-gutters
                                        v-model="CateSelected"
                                        color="black"
                                        label="Loafers"
                                        :value=20
                                        ></v-checkbox>
                                    </v-flex>
                                    <v-flex xs12>
                                        <v-checkbox
                                        no-gutters
                                        v-model="CateSelected"
                                        color="black"
                                        label="Sandals/Slippers"
                                        :value=30
                                        ></v-checkbox>
                                    </v-flex>
                                    <v-flex xs12>
                                        <v-checkbox
                                        v-model="CateSelected"
                                        color="black"
                                        label="Boots"
                                        :value=40
                                        ></v-checkbox>
                                    </v-flex>
                                    <v-flex xs12>
                                        <v-checkbox
                                        v-model="CateSelected"
                                        color="black"
                                        label="Hills/Pumps"
                                        :value=50
                                        ></v-checkbox>
                                    </v-flex>
                                </div>

                                <v-divider class="my-2"></v-divider>
                                <h4 class="sr-only">가격 필터</h4>
                                <div class="filter-price p-t-22 p-b-50 bo3">
                                    <div class="m-text15 p-b-17">
                                    <h3>Price</h3>
                                    </div>

                                    <v-flex xs12>
                                        <v-select
                                            v-model= "PriceSelected"
                                            label="가격 옵션"
                                            :items="PriceOptions"
                                            item-text="name"
                                            item-value="value"
                                            dense solo single-line
                                            item-color= "black"/>
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
                                        v-model="SizeSelected"
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
                                        <v-col cols="4" style="margin-left: 25px">
                                            <p>{{SizeSelected[0]}}</p>
                                        </v-col>
                                        <v-col>
                                        <p>~</p>
                                        </v-col>
                                        <v-col>
                                            <p>{{SizeSelected[1]}}</p>
                                        </v-col>
                                    </v-row>
                                </div>
                                <div class = "btnWrap">
                                    <v-btn
                                    @click = "resetFilter()"
                                    depressed outlined color="black">초기화</v-btn>
                                    <v-btn 
                                    @click = "applyFilter()"
                                    depressed outlined color="black"
                                    style="float: right">적용</v-btn>
                                </div>
                            </div>
                        </v-card>
                    </v-col>
                </div>
                <!--상품 목록-->
                <div class="content-list">
                    <v-row>
                        <div class="search-content">
                            <div class="productSide">
                                <ProductList ref="child" />
                            </div>
                        </div>
                    </v-row>
                </div>

            </div>
        </v-container>
    </div>

</template>

<script>
import ShopBanner from "@/components/shop/ShopBanner";
import ProductList from "@/components/shop/ProductList";

import axios from "axios";
import Vue from "vue";

Vue.prototype.$http = axios;
const baseURI = 'http://localhost:8080';



export default {
    name: "shop",
    components: {
        ShopBanner,
        ProductList,
    },

    data(){
        return{
            CateSelected: [],
            PriceOptions:[ //정렬 옵션
                {name: '선택 안함', value: 0},
                {name: '10만원 이하', value: 1},
                {name: '10만원 - 30만원', value: 2},
                {name: '30만원 - 50만원', value: 3},
                {name: '50만원 이상', value: 4},
            ],
            PriceSelected: 0,
            SizeSelected: [210,290],

            cateoption: null,
            
        };
    },

    methods: {
        
        //필터 적용
        applyFilter(){

            //카테고리 옵션 설정
            this.cateoption = null;
            if(this.CateSelected.length != 0){
                for(let i = 0; i < this.CateSelected.length; i++){
                    this.cateoption = this.cateoption + this.CateSelected[i] + ',';
                }
                this.cateoption = this.cateoption.slice(0,-1);
                this.$refs.child.cateOption = this.cateoption;
            } else{
                this.$refs.child.cateOption = null;
            }
            
            //가격 옵션 설정
            switch(this.PriceSelected){
                case 0: this.$refs.child.minPrice = 0; this.$refs.child.maxPrice = 5000000; break;
                case 1: this.$refs.child.minPrice = 0; this.$refs.child.maxPrice = 100000; break;
                case 2: this.$refs.child.minPrice = 100000; this.$refs.child.maxPrice = 300000; break;
                case 3: this.$refs.child.minPrice = 300000; this.$refs.child.maxPrice = 500000; break;
                case 4: this.$refs.child.minPrice = 500000; this.$refs.child.maxPrice = 5000000; break;
            }

            //사이즈 옵션 설정
            this.$refs.child.minSize = this.SizeSelected[0];
            this.$refs.child.maxSize = this.SizeSelected[1];

            this.$refs.child.goReset();
            this.$refs.child.getProductLists();
        },

        //필터 옵션 초기화
        resetFilter(){
            console.log('필터 초기화');

            this.$refs.child.cateOption = null;
            this.$refs.child.minPrice = 0;
            this.$refs.child.maxPrice = 5000000;
            this.$refs.child.minSize = 210;
            this.$refs.child.maxSize = 290;

            this.$refs.child.goReset();
            this.$refs.child.getProductLists();
        },
    },
}
    
</script>
    

<style scoped>

    .search{
        width: 500px;
        display: flex;
        border-bottom: 2px solid black;
        padding-bottom: 16px;
        margin: 0 8px;
    }

    .search-area{
        margin: 0 auto;
    }

    .search-filter{
        float: left;
        margin-right: 10px;
        padding-right: 10px;
        overflow-x: hidden;
        overflow-y: auto;
    }

    .btnWrap{
        width: 240px;
        height: 40px;
        margin: 0 auto;
    }

    .v-btn{
      color: black;
      width: 50px;
      height: 20px;
    }

    .filter-card{
        width: 280px;
    }


</style>