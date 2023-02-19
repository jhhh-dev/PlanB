<template>
    <div>
        <!-- 관리자페이지 - 결제내역 리스트 -->
        <PaymentList />
    </div>
</template>

<script>
import PaymentList from '../../components/admin/payment/PaymentList.vue';

    export default {
    layout: "adminLayout",
    components: { PaymentList }
}
</script>

<style lang="scss" scoped>
    
</style>