<template>
    <div>
        <!-- 관리자페이지 - 주문리스트 -->
        <OrderList />
    </div>
</template>

<script>
import OrderList from '../../components/admin/order/OrderList.vue';

    export default {
    layout: "adminLayout",
    components: { OrderList }
}
</script>

<style lang="scss" scoped>
    
</style>