<template>
    <div>
        <!-- 상품관리 리스트 컴포넌트 -->
        <ProductList />


    </div>
</template>

<script>
import ProductList from '../../components/admin/product/ProductList.vue'

export default {
  components: { ProductList },
    layout: "adminLayout",

    
}
</script>

<style lang="scss" scoped>

</style>