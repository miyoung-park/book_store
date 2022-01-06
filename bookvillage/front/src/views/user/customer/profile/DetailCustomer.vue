<template>
  <div class="detail_section">
    <h3> [ 프로필 ] </h3>
    <v-card
        class="mx-auto my-8"
        width="350"
        max-height="550"
        style="margin-bottom: 30px !important; display: flex; flex-direction: column"
    >
      <v-card-text>
        <div class="my-4">고객번호 : {{customerInfo.userSeq}}</div>
        <div class="my-4">고객명 : {{customerInfo.userName}}</div>
        <div class="my-4">아이디 : {{customerInfo.userId}}</div>
        <div class="my-4">생년월일 : {{customerInfo.userBirth}}</div>
        <div class="my-4">전화번호 : {{customerInfo.userTell}}</div>
        <div class="my-4">등록일자 : {{customerInfo.userRegDt}}</div>
      </v-card-text>
    </v-card>
  </div>
</template>

<script>
export default {
  name: "DetailCustomer",
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
      },
    }
  },
  methods: {
    async getCustomerDetail(){
      const data = await this.customerService.getCustomerDetailById();
      this.customerInfo = data;
    }
  },
  mounted() {
    this.getCustomerDetail();
  }
}
</script>

<style scoped>
.detail_section{
  display: flex;
  flex-direction: column;
  align-items: center;
  margin: 10% auto 0 auto
}

</style>