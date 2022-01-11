<template>
  <div class="detail_section">
    <h3> [ 고객정보 ] </h3>
    <v-card
        class="mx-auto my-8"
        width="350"
        max-height="550"
        style="margin-bottom: 30px !important; display: flex; flex-direction: column"
    >

      <v-card-text>
        <div class="my-4">고객번호 : {{customerInfo.userSeq}}</div>
        <div class="my-4">고객명 : {{customerInfo.userName}}</div>
        <div class="my-4">고객 아이디 : {{customerInfo.userId}}</div>
        <div class="my-4">고객 포인트 : {{customerInfo.userPoint}} 포인트</div>
        <div class="my-4">생년월일 : {{customerInfo.userBirth}}</div>
        <div class="my-4">전화번호 : {{customerInfo.userTell}}</div>
        <div class="my-4">등록일자 : {{customerInfo.userRegDt}}</div>
      </v-card-text>
      <v-divider></v-divider>
      <div class="btn_section">
        <v-btn text class="btn_update" @click="goUpdateCustomer">고객수정</v-btn>
        <v-btn text class="btn_delete" @click="goDeleteCustomer">고객삭제</v-btn>
      </div>
    </v-card>
    <div class="list_section">
      <v-btn text class="btn_list" @click="goListCustomer">고객목록보기</v-btn>
    </div>
  </div>
</template>

<script>
export default {
  name: "AdminDetailCustomer",
  inject: ['customerService'],
  data() {
    return {
      customerInfo: {
          userSeq: '',
          userId: '',
          userName: '',
          userBirth: '',
          userTell: '',
          userRegDt: '',
          userPoint: ''
      },
    }
  },
  // 데이터 매핑
  created() {
    this.customerInfo.userSeq  = this.$route.params.userSeq;
  },
  methods: {
    async getCustomerDetail(){
      const data = await this.customerService.getCustomerDetail(this.customerInfo.userSeq);
      this.customerInfo = data;
    },
    goListCustomer(){
      this.$router.push({
        path: '/admin/customer/list'
      })
    },
    goUpdateCustomer(){
      const _userSeq = this.customerInfo.userSeq
      this.$router.push({
        path: '/admin/customer/update/' + _userSeq
      });
    },
    async goDeleteCustomer(){
      if(confirm('고객 정보를 정말 삭제하시겠습니까 ?')){
        await this.customerService.deleteCustomer(this.customerInfo.userSeq);
        alert('고객정보가 삭제되었습니다.');
        return this.$router.push({
          path: '/admin/customer/list'
        }).catch((e)=> {console.log(e)})
      }
      return;
    }
  },
  async mounted() {
    await this.getCustomerDetail();
  }
}
</script>

<style scoped>
.btn_section {
  display: flex;
  justify-content: space-around;
}
.btn_update{
  color: darkslateblue;
  font-weight: bold;
}
.btn_delete{
  color: maroon;
  font-weight: bold;
}
.detail_section{
  margin: 10% auto 0 auto;
  display: flex;
  flex-direction: column;
  align-items: center;
}
.list_section{
  display: flex;
  justify-content: center;
  margin-bottom: 50px;
}
.btn_list {
  background-color: burlywood;
}
</style>