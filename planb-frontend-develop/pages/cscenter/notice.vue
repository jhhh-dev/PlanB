<template>
    <v-main class="white-3">
      <v-container>
        <v-row>
         <!--카테고리 출력-->
          <v-col cols="2">
            <CsCategory/>
          </v-col>

          <v-col>
            <v-sheet
              min-height="70vh"
              rounded="0g"
            >
              <!--게시글 목록 출력-->
              <NoticeList/>
            </v-sheet>
          </v-col>
        </v-row>
      </v-container>
    </v-main>
</template>

<script>
import CsCategory from '@/components/cscenter/CsCategory';
import NoticeList from '@/components/cscenter/NoticeList';

export default {
    name: "cscenter",
    components: {
        CsCategory,
        NoticeList
    }
}
</script>

<style scoped>
    .container{
        max-width: 1280px;
    }
</style>