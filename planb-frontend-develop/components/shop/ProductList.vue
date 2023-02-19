<!--SHOP 페이지 : 상품 목록 출력 컴포넌트-->
<template>
  <div>
    <v-container class="my-10">
      <h1 class="sr-only">검색창</h1>
      <!--검색창-->
      <div class="search-content">
          <v-row justify="center">
              <div class="search-area">
                  <v-col>
                      <div class="search" justify-content="center">
                          <input type="text" placeholder="Search . . ." style="outline: none" size=60
                          v-model="keyword"
                          @keyup.enter="search()" />
                      </div>
                  </v-col>
              </div>
          </v-row>
      </div>

      <h1 class="sr-only">상품 목록</h1>
    
      <div class = "contentBox">
      <!--정렬 옵션-->
        <v-col
          class="d-flex"
          cols="1"
          sm = "2"
        >
          <v-select
            v-model= "sortOption"
            label="최신순"
            :items="sortOptions"
            item-text="name"
            item-value="value"
            @change="sortSelected()"
            dense solo single-line
            item-color= "black"/>
        </v-col>

        <!--상품 목록 출력 카드-->
        <v-row style = "margin-top: 10px;">
          <v-col v-for="(product,i) in products" :key="i" cols="3">
            <div class = "cardBox" @click="$router.push('/detail/' + products[i].proId)">
              <div class = "imgBox">
                <img :src="products[i].proImg" :alt="product.proName"/>
              </div>
              <div style = "padding: 5px;">
                <div style = "padding-top: 10px; display: flex; justify-content: space-between; margin-bottom: 5px;">
                  <b> {{ product.proBrand }} </b>
                </div>
                <span style="color: gray;">
                  {{ product.proName }}
                </span><br/>
              
                <div style = "padding-top: 10px">
                  <b> {{ product.proPrice | comma }} 원 </b>
                </div>
              </div>
            </div>
          </v-col>
        </v-row>
      </div>
    </v-container>

      <!--infinite scroll-->
      <infinite-loading ref="infiniteLoading" @infinite="getProductLists" spinner="spiral"></infinite-loading>
  </div>
</template>

<script>
import axios from "axios";
import Vue from "vue";
import InfiniteLoading from 'vue-infinite-loading';
Vue.prototype.$http = axios;

const baseURI = 'http://localhost:8080';

export default {

    components:{
      InfiniteLoading,
    },



    data(){
      return{
        products: [],  //상품 정보 저장
        
        keyword: null, //검색 키워드
        sortOptions: [ //정렬 옵션
          {name: '최신순', value: 'recent'},
          {name: '조회순', value: 'popular'}
        ],  
        sortOption: 'recent', //정렬옵션-기본값: 최신순
        cateOption: null, //카테고리 옵션
        minPrice: 0, //가격옵션-최소가격
        maxPrice: 5000000, //가격옵션-최대가격
        minSize: 210, //사이즈 옵션-최소사이즈
        maxSize: 290, //사이즈 옵션-최대사이즈

        loadNum: 0, //infinite scroll - 페이지
    }},

    methods: {

      //infinite scroll이 적용된 상품 데이터 로드
      getProductLists(){
        const EACH_LEN = 12
        axios.get(`${baseURI}/shop/productList`,{
            params:{
              keyword: this.keyword,
              sortOption: this.sortOption,
              cateOption: this.cateOption,
              minPrice: this.minPrice,
              maxPrice: this.maxPrice,
              minSize: this.minSize,
              maxSize: this.maxSize,
              page: this.loadNum,
            }
          })
          .then(res => {
            console.log("로딩중, loadNum: "+this.loadNum);

            setTimeout(() => {
              if(res.data.length){
                //상품 목록 로드
                const data = res.data;
                for(let key in data){
                  this.products.push(data[key])

                  //상품 이미지 로드
                  for(let i = 0; i < this.products.length; i++){
                    axios.get(`${baseURI}/showImage?fileName=` + this.products[i].proImg)
                    .then( res => {
                      this.products[i].proImg = res.config.url;
                      
                    }).catch(err =>{
                      console.log(err);
                    })
                  }
                }
              
                console.log('검색 키워드: '+this.keyword);
                console.log('정렬 옵션: ' + this.sortOption);
                console.log('카테고리 옵션: ' + this.cateOption);
                console.log('가격 옵션: ' + this.minPrice + '~' + this.maxPrice);
                console.log('사이즈 옵션: ' + this.minSize + '~' + this.maxSize);

                this.loadNum += 1;
                this.$refs.infiniteLoading.stateChanger.loaded();
                
                console.log("after",this.products.length, this.loadNum);

                //끝 지정 - 데이터가 EACH_LEN 개 미만일 때
                if(res.data.length / EACH_LEN < 1){
                  console.log("남은 데이터가 EACH_LEN개 미만일 때");
                  this.$refs.infiniteLoading.stateChanger.complete();
                } 

              } else{
                  //끝 지정
                  this.$refs.infiniteLoading.stateChanger.complete();
              }

            }, 1000)
              
          }).catch(err =>{
            console.log(err);
          })
      },

      //infiniteLoading에서 불러온 데이터 초기화
      reset(){
        this.$refs.infiniteLoading.stateChanger.reset();
      },

      //reset() 호출, 저장된 목록 및 페이징 데이터 초기화
      async goReset(){
        this.products = [];
        this.loadNum = 0;
        await this.reset().then(()=>{
          console.log("목록 초기화");
          this.getProductLists();
          
        })
      },

      //검색어 입력
      search(){
        if(this.keyword){
          console.log("검색 단어 입력: "+this.keyword);

          this.goReset();
          this.getProductLists();
        } else{
          this.keyword = null;
          this.goReset();
          this.getProductLists();
        }
      },

      //정렬 옵션 변경
      sortSelected(){
        console.log("정렬 옵션: "+this.sortOption);

        this.goReset();
        this.getProductLists();
      },

  
      
    },

    filters: {
      comma(val){
                return String(val).replace(/\B(?=(\d{3})+(?!\d))/g, ",");
            },
        },
    }

</script>

<style scoped>
  .v-btn{
      color: black;
  }
  .v-btn :hover{
      color: white;
  }

  .search-content{
   margin-bottom: 10px;
   padding-bottom: 30px;
  }

  .search{
    width: 500px;
    display: flex;
    border-bottom: 2px solid black;
    padding-bottom: 16px;
    margin: 0 8px;
}

  .SortOption{
      width: 100%;
      height: 50px;
      margin-top: 20px;
      margin-left: 10px;
      margin-right: 40px;
      float: right;
  }

  .contentBox{
    
    padding-top: 20px;
  }

  .cardBox{
    padding-right: 20px;
    margin-bottom: 20px;
    height: 350px;
    border-radius: 10px;

    cursor: pointer;
  }

  .imgBox {
    background-color: #f1f1f1;
    border-radius: 10px;
    max-height: 250px;
}

  .imgBox img {
    position: relative;
    width: 100%;
    transition-duration: 0.3s;
}

  /* 마우스 올릴시에 크기 커지도록 */
  .imgBox img:hover {
  transform: scale(1.1, 1.1);
  transition-duration: 0.5s;
}



</style>