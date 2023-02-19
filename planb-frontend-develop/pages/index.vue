<template>
  <div style="height: auto;">

    <!-- 배너 이미지 -->
    <div style="height: auto; width: 100%; margin-bottom: 50px;">
      <MainBanner />

    </div>

    <!-- 신규등록상품 -->
    <div style="height: auto; width: 100%; margin-bottom: 100px;">
      <NewProduct />
    </div>

    <!-- 인기상품 -->
    <div style="height: auto; width: 100%; margin-bottom: 100px;">
      <PopularProduct />
    </div>

    <!-- 스타일 후기 -->
    <div style="height: auto; width: 100%; margin-bottom: 50px;">
      <StyleList />
    </div>

    

  </div>
</template>

<script>
import MainBanner from "../components/front/mainpage/mainBanner.vue"
import NewProduct from "../components/front/mainpage/newProduct.vue"
import PopularProduct from "../components/front/mainpage/popularProduct.vue"
import StyleList from "../components/front/mainpage/styleList.vue"




export default {
  name: "IndexPage",
  head() {
    return {
      title: 'PlanB',
    }
  },
  components: {
    MainBanner,
    NewProduct,
    PopularProduct,
    StyleList
  },
};
</script>
