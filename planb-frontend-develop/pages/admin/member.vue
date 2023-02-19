<template>
    <div>
      
      <!-- 멤버리스트 -->
      <MemberList />
      
    </div>
  </template>

<script>
import MemberList from '../../components/admin/member/MemberList.vue'
    export default {
  components: { MemberList },
        layout: "adminLayout",
    }
</script>