<template>
    <div>
        <div class="content_title p-t-5 p-b-16 m-b-20">
            <div class="title">
                <h3>자주 묻는 질문</h3>
            </div>
        </div>

        <!--게시글 목록 출력-->
        <div class="contentBox">
        <v-expansion-panels>
            <v-expansion-panel
            v-for="(data,i) in faqList"
            :key="i"
            >
            <v-expansion-panel-header>
                {{ data.faqTitle }}
            </v-expansion-panel-header>
            <v-expansion-panel-content style="white-space: pre-line">
                 {{ data.faqContent }}
            </v-expansion-panel-content>
            </v-expansion-panel>
        </v-expansion-panels>
        </div>
    </div>
</template>

<script>
import Vue from "vue";
import axios from "axios";
Vue.prototype.$http = axios;

const baseURI = 'http://localhost:8080';

export default {
    mounted(){
        this.getFaqList()
    },

   data: () => ({
        faqList: [],
        title: '',
        content: ''
    }),
    methods: {
        async getFaqList(){
            await axios.get(`${baseURI}/csCenter/faqList`)
            .then( res => {
                console.log(res.data)
                this.faqList = res.data;
            });
        }
    }
}

</script>

<style scoped>
    .content_title{
        border-bottom: 3px solid #222;
    }
    .title{
        display: flex;
        font-size: 24px;
        letter-spacing: -.36px;
        padding: 5px 0 6px;
    }
    .title>h3{
        line-height: 29px;
        font-size: inherit;
    }
</style>