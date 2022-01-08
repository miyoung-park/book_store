<template>
  <div class="detail_section">
    <h3> [ 프로필 ] </h3>
      <div class="customer_info_section">
        <v-card>
          <v-simple-table class="table">
            <tbody>
            <tr>
              <td>고객번호 : </td><td class="td">{{customerInfo.userSeq}}</td>
            </tr>
            <tr>
              <td>아이디 : </td><td class="td">{{customerInfo.userId}}</td>
            </tr>
            <tr>
              <td>고객명 : </td><td class="td">{{customerInfo.userName}}</td>
            </tr>
            <tr>
              <td>포인트 : </td><td class="td">{{customerInfo.userPoint}} point</td>
            </tr>
            <tr>
              <td>생년월일 : </td><td class="td">{{customerInfo.userBirth}}</td>
            </tr>
            <tr>
              <td>전화번호 : </td><td class="td">{{customerInfo.userTell}}</td>
            </tr>
            <tr>
              <td>등록일자 : </td><td class="td">{{customerInfo.userRegDt}}</td>
            </tr>
            </tbody>
          </v-simple-table>
        </v-card>
      </div>
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
        userPoint: ''
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
.customer_info_section {
  margin-top: 30px;
  font-size: large;
  color: black;
}
.customer_info_section a{
  font-size: large;
  font-weight: bold ;
  color: darkslategrey;
}
.table {
  width: 300px;
}
.td {
  color: black;
  font-size: medium !important;
}
</style>