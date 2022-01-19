<template>
  <div class="content">
    <div class="table_section">
      <v-data-table
          :headers="headers"
          :items="rentalList"
          :items-per-page="5"
          sort-by="rentalSeq"
          :sort-desc= true
          primary-key="index"
      >
        <template v-slot:item="{item}" >
          <tr>
            <td>{{rentalList.indexOf(item) + 1}}</td>
            <td>{{item.userSeq}}</td>
            <td>{{item.bookSeq}}</td>
            <td>{{item.bookTitle}}</td>
            <td>{{item.rentalDt}}</td>
            <td>{{item.predictReturnDt}}</td>
            <td>{{item.returnDt}}</td>
            <!-- 등록일자 -->
            <td>{{item.rentalRegDt}}</td>
            <!-- 수정일자 -->
            <td>{{item.rentalUpdateDt}}</td>
            <!-- 대여확인 -->
              <td class="btn_td" v-if="item.rentalStatus === '00'">
                <v-btn  style="background-color: gold;height: 30px;"
                        @click="approveRental(item.rentalSeq)"
                >확인</v-btn>
                <v-btn  style="background-color: indianred;height: 30px"
                        @click="rejectRental(item.rentalSeq)"
                >취소</v-btn>
              </td>
              <td v-if="item.rentalStatus === '01'" style="background-color: cornflowerblue">승인완료</td>
              <td v-if="item.rentalStatus === '02'" style="background-color: dimgray">반납완료</td>
              <td v-if="item.rentalStatus === '04'" style="background-color: indianred">대여취소</td>
            <td></td>
          </tr>
        </template>
      </v-data-table>
    </div>
  </div>
</template>

<script>
export default {
  name: "AdminListRentals",
  inject: ['rentalService'],
  data () {
    return {
      search: '',
      headers: [
        { text: '번호', align: 'start' },
        { text: '고객번호', value:'userSeq'},
        { text: '도서번호', value: 'bookSeq'},
        { text: '도서명', value: 'bookTitle'},
        { text: '대여일', value: 'rentalDt' },
        { text: '반납예정일', value: 'predictReturnDt' },
        { text: '반납일', value: 'returnDt' },
        { text: '등록날짜', value: 'rentalRegDt' },
        { text: '수정날짜', value: 'rentalUpdateDt'},
        { text: '대여확인' }
      ],
      rentalSeq: "",
      rentalList: [],
      dialog : false,
    }
  },
  methods: {
    async getRentalList(){
      const response = await this.rentalService.getRentalAllList();
      this.rentalList = response;
    },
    async approveRental(rentSeq){
      await this.rentalService.approveRental(rentSeq);
      alert('승인이 완료되었습니다.');
      await this.getRentalList();
    },
    async rejectRental(rentSeq){
      if(confirm('대여를 취소 하시겠습니까 ?')) {
        await this.rentalService.rejectRental(rentSeq);
        alert('대여신청을 거절했습니다.');
        location.reload();
      }
      return;
    },
    cancelBtn(){
      this.dialog = false;
    }
  },
  async mounted() {
    await this.getRentalList();
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
.btn_td{
  display: flex;
  align-items: center;
  justify-content: space-between;

}
</style>