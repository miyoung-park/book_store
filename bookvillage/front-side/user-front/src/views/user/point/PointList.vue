<template>
  <div class="content">
    <div class="table_section">
      <div class="charge_section">
            <v-dialog
                class="dialog"
                v-model="dialog"
                max-width="400px"
            >
              <template v-slot:activator="{ on, attrs }">
                <v-btn class="charge_btn"
                       v-bind="attrs"
                       v-on="on"
                >포인트 충전
                </v-btn>
              </template>
              <v-card class="dialog_card">
                <v-card-title>포인트 충전</v-card-title>
                <v-divider></v-divider><br>
                <v-card-text>
                  충전할 금액을 입력해주세요.
                </v-card-text>
                <div class="input_section">
                  <v-text-field
                      v-model="pointInfo.pointTransaction"
                      label="충전금액"
                      required
                  ></v-text-field>
                </div>
                <v-divider></v-divider>

                <v-card-actions class="btn_section">
                  <v-btn
                      color="red darken-1"
                      text
                      @click="cancelChargePoint"
                  >
                    닫기
                  </v-btn>
                  <v-btn
                      color="blue darken-1"
                      text
                      @click="chargePoint"
                  >
                    확인
                  </v-btn>
                </v-card-actions>
              </v-card>
            </v-dialog>
      </div>
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
            <td>{{ getPointStatus(item) }}</td>
            <td>{{item.statusReason}}</td>
            <td>{{item.transactionRegDt}}</td>
          </tr>
        </template>
      </v-data-table>
    </div>
  </div>
</template>

<script>

export default {
  name: "PointList",
  inject: ['pointService'],
  data () {
    return {
      search: '',
      headers: [
        { text: '번호', align: 'start' },
        { text: '대여 번호', value: 'rentalSeq'},
        { text: '이전 포인트', value: 'previousPoint' },
        { text: '거래 포인트', value: 'pointTransaction' },
        { text: '남은 포인트', value: 'totalPoint' },
        { text: '거래 상태', value: 'pointStatus' },
        { text: '거래 사유', value: 'statusReason' },
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
  methods: {
    async getList(){
      const response = await this.pointService.getPointList();
      this.pointList = response;
    },
    getPointStatus(item){
      const status = item.pointStatus;
      if(status === '00') {
        return '적립';
      } else if(status === '01') {
        return '차감';
      }
    },
    async chargePoint(){
      const numCheck = /^[0-9]*$/;
      if(!numCheck.test(this.pointInfo.pointTransaction)) {
        alert('양수만 입력해주세요.');
        return;
      }
      if(confirm(this.pointInfo.pointTransaction + '포인트를 충전하시겠습니까 ?')){
        await this.pointService.chargePoint(this.pointInfo);
        alert('포인트 충전이 완료되었습니다.');
        this.dialog = false
        await this.getList();
      }
      this.dialog = false;
      this.pointInfo.pointTransaction = null; // 초기화
    },
    cancelChargePoint(){
      this.pointInfo.pointTransaction = null; // 초기화
      this.dialog = false;
    }
  },
  mounted() {
    this.getList();
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