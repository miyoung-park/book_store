<template>
  <div class="content">
    <div class="table_section">
      <div class="charge_section">
            <v-dialog
                class="dialog"
                v-model="dialog"
                max-width="600px"
            >
              <template v-slot:activator="{ on, attrs }">
                <v-btn class="charge_btn"
                       v-bind="attrs"
                       v-on="on"
                >포인트 충전
                </v-btn>
              </template>
              <v-card>
                <v-card-title>포인트 충전</v-card-title>
                <v-divider></v-divider>
                <v-card-text>
                  <a> dialog section</a>
                </v-card-text>
                <v-divider></v-divider>
                <v-card-actions class="btn_section">
                  <v-btn
                      color="red darken-1"
                      text
                      @click="dialog = false"
                  >
                    닫기
                  </v-btn>
                  <v-btn
                      color="blue darken-1"
                      text
                      @click="dialog = false"
                  >
                    확인
                  </v-btn>
                </v-card-actions>
              </v-card>
            </v-dialog>









      </div>
      <v-data-table
          :headers="headers"
          :items="points"
          :search="search"
          :items-per-page="5"
          sort-by="pointSeq"
          :sort-desc= true
      >
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
        { text: '거래 번호', value: 'pointSeq', align: 'start' },
        { text: '유저 번호', value: 'userSeq'},
        { text: '대여 번호', value: 'rentalSeq'},
        { text: '이전 포인트', value: 'previousPoint' },
        { text: '거래 포인트', value: 'pointTransaction' },
        { text: '거래 상태', value: 'pointStatus' },
        { text: '남은 포인트', value: 'totalPoint' },
        { text: '등록날짜', value: 'transactionRegDt' },
        { text: '수정날짜', value: 'transactionUpdateDt' },
      ],
      points: [],
      dialog: false
    }
  },
  methods: {
    async getList(){
      const response = await this.pointService.getPointList();
      this.points = response;
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
</style>