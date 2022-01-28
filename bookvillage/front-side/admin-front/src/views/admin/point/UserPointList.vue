<template>
  <div class="content">
    <div class="table_section">
      <v-data-table
          :headers="headers"
          :items="pointList"
          :items-per-page="5"
          sort-by="pointSeq"
          :sort-desc= true
          primary-key="index"
      >
        <template v-slot:item="{item}">
          <tr>
            <td>{{pointList.indexOf(item) + 1}}</td>
            <td>{{item.rentalSeq}}</td>
            <td>{{item.previousPoint}}</td>
            <td>{{item.pointTransaction}}</td>
            <td>{{item.totalPoint}}</td>
            <td>{{item.pointStatus}}</td>
            <td>{{item.transactionRegDt}}</td>
          </tr>
        </template>
      </v-data-table>
    </div>
  </div>
</template>

<script>

export default {
  name: "UserPointList",
  inject: ['pointService'],
  data () {
    return {
      userSeq : null,
      headers: [
        { text: '번호', align: 'start' },
        { text: '대여 번호', value: 'rentalSeq'},
        { text: '이전 포인트', value: 'previousPoint' },
        { text: '거래 포인트', value: 'pointTransaction' },
        { text: '남은 포인트', value: 'totalPoint' },
        { text: '거래 상태', value: 'pointStatus' },
        { text: '등록날짜', value: 'transactionRegDt' }
      ],
      pointList: [],
      dialog: false,
      pointInfo: {
        pointTransaction : null ,
        pointStatus : '00'
      },
    }
  },
  created() {
    this.userSeq = this.$route.params.userSeq;
  },
  methods: {
    async getPointList(){
      const response = await this.pointService.getPointList(this.userSeq);
      this.pointList = response;
    }
  },
  mounted() {
    this.getPointList();
  }
}
</script>

<style scoped>
.content{
  width: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
.table_section {
  width: 80%;
  height: 100%;
  margin-top: 50px;
}
.charge_section {
  display: flex;
  justify-content: flex-end;
  margin-bottom: 50px;
}
.charge_btn {
  background-color: darkseagreen !important;
}
.btn_section {
  display: flex !important;
  font-family: 'Gowun Dodum', sans-serif !important;
  justify-content: space-between;
}
.dialog_card {
  font-family: 'Gowun Dodum', sans-serif !important;
}
.input_section {
  width: 70%;
  margin-left: 30px;
}
</style>