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
              <td v-if="item.rentalStatus == '0'">대여신청</td>
              <td v-if="item.rentalStatus == '1'">대여中</td>
              <td v-if="item.rentalStatus == '2'" style="color: crimson">연체</td>
            <td>{{item.rentalRegDt}}</td>
            <td>{{item.rentalUpdateDt}}</td>
              <td v-if="item.rentalStatus == '0'">
                <v-btn  style="background-color: skyblue" @click="approveRental(item.rentalSeq)">확인</v-btn>
              </td>
              <td v-if="item.rentalStatus == '1'" style="background-color: cornflowerblue">승인완료</td>
              <td v-if="item.rentalStatus == '2'" style="background-color: crimson">연체</td>
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
        { text: '대여현황', value: 'rentalStatus' },
        { text: '등록날짜', value: 'rentalRegDt' },
        { text: '수정날짜', value: 'rentalUpdateDt'},
        { text: '대여확인' }
      ],
      rentalList: [],
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
</style>