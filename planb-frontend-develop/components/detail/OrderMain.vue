<template>
    <div>

        <!-- 주문 상품 정보 -->
        <div class="bindBox" style="padding: 30px 30px 30px 30px;">

            <table class="productTable">
                <tr>
                    <td rowspan="3" style="width: 130px;">
                        <div style="width: 100px; height: 100px; background-color: #f1f1f1; border-radius: 10px;">
                            <img :src="imageurl" style="width: 100px; max-height: 100px;" />
                        </div>
                    </td>
                    <td>
                        <b>{{ this.item.proBrand }}</b>
                    </td>
                </tr>

                <tr>
                    <td>
                        <b style="color: gray">{{ this.item.proName }}</b>
                    </td>
                </tr>

                <tr>
                    <td>
                        <b style="color: gray;">{{ this.item.proSize }} MM</b>
                    </td>
                </tr>

            </table>
        </div>

        <!-- 배송 정보 -->
        <div class="bindBox" style="padding: 30px 30px 30px 30px;">

            <h1>배송 주소</h1>
            <div>
                <table class="deliveryTable" style="width: 100%;">
                    <tbody>
                        <tr>
                            <th style="width: 80px;">
                                <span>받는 분</span>
                            </th>

                            <td style="">
                                <v-text-field ref="orderReciver" v-model="orderReciver" placeholder="수령인의 이름" required
                                    hide-details="auto" autofocus></v-text-field>
                            </td>
                        </tr>

                        <tr>
                            <th style="width: 80px;">
                                <span>연락처</span>
                            </th>
                            <td style="">
                                <!-- <span style="font-weight: bold; font-size: 14px;">휴대폰 번호</span> -->
                                <v-text-field ref="orderPhone" v-model="orderPhone" placeholder="( - ) 없이 입력" required
                                    hide-details="auto"></v-text-field>

                            </td>
                        </tr>

                        <tr>
                            <th style="width: 80px;">
                                <span>주소</span>
                            </th>

                            <td>
                                <div style="display: flex; justify-content: space-between;">
                                    <v-text-field v-model="orderAddr" placeholder="주소 검색 후, 자동입력 됩니다." required readonly
                                        hide-details="auto"></v-text-field>

                                    <div style="width: ; margin-left: 10px;">
                                        <v-btn color="secondary" class="button primary-button"
                                            @click="execDaumPostcode">검색</v-btn>
                                    </div>
                                </div>
                            </td>
                        </tr>

                        <tr>
                            <th style="width: 80px;">
                                <span>상세 주소</span>
                            </th>

                            <td>
                                <v-text-field ref="orderAddrDetail" v-model="orderAddrDetail"
                                    placeholder="건물, 아파트, 동/호수 입력" required hide-details="auto"></v-text-field>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>

            <div style="margin-top: 20px; padding-top: 20px; border-top: 1px solid lightgray;">
                <h1>배송 방법</h1>

                <div :class="{ 'selectDel': this.deliveryType === 1, 'unselectDel': this.deliveryType === 2 }"
                    @click="changeDeliveryType(1)">
                    <img src="@/assets/images/빠른배송.png" width="50px" style="margin-right: 20px;" />
                    <div>
                        <b>빠른배송 5,000원</b><br />
                        <span style="color:gray; font-size: 14px;">지금 결제 시 내일 도착 예정</span>
                    </div>
                </div>

                <div :class="{ 'selectDel': this.deliveryType === 2, 'unselectDel': this.deliveryType === 1 }"
                    @click="changeDeliveryType(2)">
                    <img src="@/assets/images/일반배송.png" width="50px" style="margin-right: 20px;" />
                    <div>
                        <b>일반배송 3,000원</b><br />
                        <span style="color:gray; font-size: 14px;">검수 후 배송 · 5-7일 내 도착 예정</span>
                    </div>
                </div>

            </div>
        </div>

        <!-- 최종 주문 정보 -->
        <div class="bindBox" style="padding: 30px 30px 30px 30px;">

            <h1>최종 주문 정보</h1>
            <br />

            <div
                style="padding-bottom: 15px; border-bottom: 1px solid lightgray; display: flex; justify-content: space-between;">
                <b>총 결제금액</b>
                <b style="color: red; font-size: 20px; float: right;">
                    {{ this.totalPrice = this.item.proPrice + this.orderFee | won }}
                </b>
            </div>

            <div class="priceInfo" style="padding-top: 15px;">
                <div style="display: flex; justify-content: space-between; font-size: 14px;">
                    <span style="color:gray">구매가</span>
                    <span>
                        {{ this.item.proPrice | won }}
                    </span>
                </div>

                <div style="display: flex; justify-content: space-between; font-size: 14px;">
                    <span style="color:gray">검수비</span>
                    <span>무료</span>
                </div>

                <div style="display: flex; justify-content: space-between; font-size: 14px;">
                    <span style="color:gray">배송비</span>

                    <span>{{ this.orderFee | won }}</span>
                    <!-- 배송 유형에 따른 금액 책정 -->
                    <!-- <span v-if="this.deliveryType === 1"> {{ this.orderFee = 5000 | won }} </span>
                    <span v-else> {{ this.orderFee = 3000 | won }} </span> -->
                </div>
            </div>
        </div>

        <!-- 결제 관련 -->
        <div class="bindBox" style="padding: 30px 30px 30px 30px;">

            <h1>결제 방법</h1>
            <br />
            <b>일반 결제</b> <span style="font-size: 12px; color: gray;">일시불·할부</span>


            <div style="display: flex; justify-content: space-between;">
                <div :class="{ 'selectPayType': this.paymentType === 1, 'unselectPayType': this.paymentType !== 1 }"
                    @click="changePayType(1)">

                    <b>신용카드</b>

                </div>

                <div :class="{ 'selectPayType': this.paymentType === 2, 'unselectPayType': this.paymentType !== 2 }"
                    @click="changePayType(2)">
                    <b>카카오페이</b>
                    <img :src='`https://kream.co.kr/_nuxt/img/buy_pay_kakaopay.7cd2486.png`' />

                </div>


            </div>

            <br />
            <div>
                <div>
                    <table class="checkTable">
                        <tbody>
                            <tr>
                                <td>
                                    <v-checkbox v-model="selectedChk" value="check1" color="secondary"
                                        hide-details="auto" @change="this.checkbox">
                                        <template v-slot:label>
                                            <div>
                                                빠른배송 구매는 보관판매 상품으로 결제 즉시 출고를 준비합니다.<br />
                                                판매자의 보관 판매 상품으로 이미 검수 합격한 상품입니다. 결제 즉시 출고를 준비합니다.
                                            </div>
                                        </template>
                                    </v-checkbox>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <v-checkbox v-model="selectedChk" value="check2" color="secondary"
                                        hide-details="auto" @change="this.checkbox">
                                        <template v-slot:label>
                                            <div>
                                                `결제하기`를 선택하시면 즉시 결제가 진행되며, 단순 변심이나 실수에 의한 취소가 불가능합니다.<br />
                                                <b style="color: #b1b1b1; font-size: 13px;">
                                                    본 거래는 개인간 거래로 전자상거래법(제17조)에 따른 청약철회(환불, 교환) 규정이 적용되지 않습니다.
                                                </b>
                                            </div>
                                        </template>
                                    </v-checkbox>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <v-checkbox v-model="selectedChk" value="check3" color="secondary"
                                        hide-details="auto" @change="this.checkbox">
                                        <template v-slot:label>
                                            <div style="font-weight: bold;">
                                                구매 조건을 모두 확인하였으며, 거래 진행에 동의합니다.
                                            </div>
                                        </template>
                                    </v-checkbox>
                                </td>
                            </tr>
                        </tbody>

                    </table>

                    <br />
                    <div style="padding-bottom: 15px; display: flex; justify-content: space-between;">
                        <b>총 결제금액</b>
                        <b style="color: red; font-size: 20px; float: right;">
                            {{ this.totalPrice = this.item.proPrice + this.orderFee | won }}
                        </b>
                    </div>

                    <br />
                    <v-btn color="secondary" style="width: 100%; border-radius: 10px;" x-large
                        v-bind:disabled="this.selectedChk.length < 3" @click="this.requestPay">
                        결제하기
                    </v-btn>



                </div>

            </div>


        </div>



    </div>
</template>

<!-- 1. 아임포트 라이브러리 추가 -->
<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>

<script>
import axios from 'axios';
import '@/assets/orderMain.css';

const backUrl = 'http://localhost:8080';

    export default {

        //해당 페이지에 다음카카오 주소검색 서비스 사용을 위한 코드 삽입
        head() {
            return {
                script: [
                    {
                        type: 'text/javascript',
                        src: "//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"
                    },
                ]
            }
        },

        props:{
            item : {},
            productId : {},
        },
        
        data() {
            return {

                // 썸네일 이미지
                imageurl:'',

                // 받는 사람
                orderReciver:'',
                // 받는 사람 연락처
                orderPhone:'',
                // 도로명 주소
                orderAddr:'',
                // 상세 주소
                orderAddrDetail:'',
                // 배송 유형 : 빠른배송 - 일반배송
                deliveryType: 1,
                // 배송비
                orderFee: 5000,

                // 총 주문 금액
                totalPrice: null,

                // 결제 방법
                paymentType:'',

                // 결제 확인 check
                selectedChk:[],

                // 아임포트 - 가맹점 식별코드
                impCode:'imp72246430',

                
            }
        },

        methods: {
            // 백엔드 이미지 파일 가져오기
            getImage(){
                this.$nextTick();
                axios.get(backUrl + '/showImage?fileName=' + this.item.proImg)
                .then(res => {
                    // 변수에 DB이미지 링크 담기
                    this.imageurl = res.config.url;
                })
            },

            // 배송 방법 변경
            changeDeliveryType(num) {
                this.deliveryType = num
                console.log(this.deliveryType === 1?'빠른배송':'일반배송')
                
                // 배송비 지정
                this.deliveryType === 1?(this.orderFee=5000):(this.orderFee=3000)
            },

            // 결제 유형 변경
            changePayType(num) {
                this.paymentType = num
                console.log(this.paymentType === 1?'신용카드':this.paymentType === 2?'카카오페이':'')
            },

            // 결제 체크박스 확인
            checkbox() {
                console.log("체크: " + this.selectedChk)
            },

            // 다음 주소 api
            execDaumPostcode() {
                new window.daum.Postcode({
                    oncomplete: (data) => {
                        if (this.extraAddress !== "") {
                            this.extraAddress = "";
                        }
                        if (data.userSelectedType === "R") {
                            // 사용자가 도로명 주소를 선택했을 경우
                            this.address = data.roadAddress;
                        } else {
                            // 사용자가 지번 주소를 선택했을 경우(J)
                            this.address = data.jibunAddress;
                        }

                        // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                        if (data.userSelectedType === "R") {
                            // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                            // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                            if (data.bname !== "" && /[동|로|가]$/g.test(data.bname)) {
                                this.extraAddress += data.bname;
                            }
                            // 건물명이 있고, 공동주택일 경우 추가한다.
                            if (data.buildingName !== "" && data.apartment === "Y") {
                                this.orderAddrDetail +=
                                    this.orderAddrDetail !== ""
                                        ? `, ${data.buildingName}`
                                        : data.buildingName;
                            }
                            // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                            if (this.extraAddress !== "") {
                                this.extraAddress = `(${this.extraAddress})`;
                            }
                        } else {
                            this.extraAddress = "";
                        }
                        // 우편번호를 입력한다.
                        this.orderAddr = data.address
                    },
                }).open();
            },

            
            // 결제하기 버튼 클릭 시 
            requestPay() {
                
                // 로그인 안되어있으면 결제 차단
                // alert("로그인 후 서비스를 이용해주시기 바랍니다.")

                if (this.orderReciver == '' || this.orderPhone == '' || this.orderAddr == '' || this.orderAddrDetail == '') {
                    
                    alert("값을 조건에 맞게 모두 입력해주시기 바랍니다.");

                } else {

                    //결제 유형 선택 확인
                    if(this.paymentType === ''){
                        alert('결제 방법을 선택해주시기 바랍니다.')

                    } else {

                        //2. 객체 초기화 (가맹점 식별코드 삽입)
                        var IMP = window.IMP;
                        IMP.init(this.impCode);

                        //임시 변수 -> 안하면 this.변수 꺼내올 때 null or undefined
                        const ex_userId = sessionStorage.getItem("userId");
                        const ex_productId = this.productId;
                        const ex_orderFee = this.orderFee;
                        const ex_orderReciver = this.orderReciver;
                        const ex_orderAddr = this.orderAddr + ', ' + this.orderAddrDetail;
                        const ex_orderPhone = this.orderPhone;


                        //3. 결제창 호출
                        IMP.request_pay({
                            pg : this.paymentType === 1?'html5_inicis':this.paymentType === 2?'kakaopay':"",     // 하나의 아임포트계정으로 여러 PG를 사용할 때 구분자 역할을 합니다. (html5_inicis, danal_tpay, payco … )
                            pay_method : 'card',    // 결제수단을 선택합니다. 기본값은 card이며 {card, trans, vbank, phone … } 결제 수단별 값을 지원합니다.
                            merchant_uid : 'merchant_' + new Date().getTime(),  // 가맹점에서 생성/관리하는 고유 주문번호
                            name : this.item.proName,   // 결제시 표시되는 상품명
                            buyer_tel : this.orderPhone, // 구매자의 연락처를 나타냅니다. 누락되었을때 일부 PG에서 오류를 발생시킵니다.
                            amount : this.totalPrice,   // 결제할 금액
                            // amount : '100',   // 결제할 금액

                        }, function(rsp) {

                            //성공 시 콘솔확인
                            console.log(rsp);

                            if ( rsp.success ) {

                                // 결제가 성공한 후 데이터 Insert
                                axios({
                                    url: backUrl + '/payment',
                                    method: "POST",
                                    data: {
                                        payType : rsp.pg_provider, //결제 방법
                                        payPrice : rsp.paid_amount, //결제 금액
                                        impUid: rsp.imp_uid, //결제 번호
                                        merchantUid: rsp.merchant_uid, //주문 번호
                                        status: rsp.status, //결제 결과
                                        },

                                }).then(res => {

                                    // 성공 alert
                                    console.log("결제데이터 INSERT 프로세스 완료.");

                                    
                                }).catch(err => {

                                    alert(err);
                                    alert("결제 insert 실패");
                                })


                                // 결제 INSERT 완료되면 주문 Insert
                                axios({
                                    url: backUrl + '/order',
                                    method: "POST",
                                    data: {
                                        userId: ex_userId,
                                        proId: ex_productId,
                                        orderFee: ex_orderFee,
                                        orderReciver: ex_orderReciver,
                                        orderAddr: ex_orderAddr,
                                        orderPhone: ex_orderPhone,
                                        payId: rsp.imp_uid
                                    },
                                    headers: { "Content-Type": "application/json" },

                                }).then(res => {

                                    // 성공 alert
                                    alert("주문이 완료되었습니다. \n마이페이지로 이동합니다.");

                                    // 마이페이지 > 주문내역 으로 이동
                                    self.$nuxt.$router.push("/mypages/myorder")
                                    
                                }).catch(err => {

                                    alert(err);
                                    alert("주문 insert 실패");
                                })

                                
                                //5. 서버사이드에서 validation check -> 시간 가능하다면 진행

                                //6. 최종 서버 응답 클라이언트에서 단계 4.에서 보낸 서버사이드 응답 에따라 결제 성공 실패 출력
                                // var msg = '결제가 완료되었습니다.';
                                // msg += '고유ID : ' + rsp.imp_uid;
                                // msg += '상점 거래ID : ' + rsp.merchant_uid;
                                // msg += '결제 금액 : ' + rsp.paid_amount;


                            } else {
                                var msg = '결제에 실패하였습니다.\n';
                                // msg += '에러내용 : ' + rsp.error_msg;
                                msg += rsp.error_msg+".";

                                // 실패 문구 출력
                                alert(msg);
                            }
                            // alert(msg);
                            // 실패할때만 msg 문구 출력하도록



                        }) // 아임포트 관련 종료

                    } // 결제유형 선택 종료
                }
                

                
    
            }, //결제 메서드 종료

            
            
        }, //메서드 전체 종료

        
        mounted() {

            console.log(sessionStorage.getItem('isLogin'))
            console.log(typeof window)

        },

        // 렌더링이 끝난 후 실행
        updated() {
            this.$nextTick(() => {
                
                
                this.getImage();
            })
        },

        // 숫자 표현 필터
        filters:{
            comma(val){
                return String(val).replace(/\B(?=(\d{3})+(?!\d))/g, ",");
            },

            won(val){
                return String(val).replace(/\B(?=(\d{3})+(?!\d))/g, ",") + " 원";
            },
        },


    }

</script>

<style lang="scss" scoped>

</style>