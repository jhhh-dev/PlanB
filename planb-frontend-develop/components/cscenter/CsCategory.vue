<template>
  <div>
            <v-sheet rounded="lg">
              <v-list color="transparent">
                <v-list-item>
                  <v-list-item-content>
                    <v-list-item-title>
                      <h2>고객센터</h2>
                    </v-list-item-title>
                  </v-list-item-content>
                </v-list-item>
                <v-divider class="my-2"></v-divider>
                <v-list-item
                  link @click="$router.push('/cscenter/notice')"
                >
                  <v-list-item-content>
                    <v-list-item-title>
                      공지사항
                    </v-list-item-title>
                  </v-list-item-content>
                </v-list-item>
                <v-list-item
                  link @click="$router.push('/cscenter/faq')"
                >
                  <v-list-item-content>
                    <v-list-item-title>
                      자주 묻는 질문
                    </v-list-item-title>
                  </v-list-item-content>
                </v-list-item>

                <v-divider class="my-2"></v-divider>

              </v-list>
            </v-sheet>
  </div>
</template>
<script>
  export default {

  }
</script>
<style scoped>

</style>

