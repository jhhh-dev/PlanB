<template>
    <div>
        <!-- 보여질 화면 -->
        <ProductAddForm />
    </div>

</template>

<script>
import ProductAddForm from '../../components/admin/product/ProductAddForm.vue';

export default {

    layout: "adminLayout",
    components : { ProductAddForm }

}
</script>


<style lang="scss" scoped>

</style>