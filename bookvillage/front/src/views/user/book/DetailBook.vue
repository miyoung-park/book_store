<template>
  <div class="detail_section">
    <div class="btn_section" v-if="this.$store.getters.getRole == 'customer'">
      <v-btn class="btn_rental_ing"
             v-if="bookRentalStatus == '0'"
      > 대여신청중 </v-btn>
      <v-btn class="btn_rental_complete"
             v-if="bookRentalStatus == '1'"
      > 대여중</v-btn>
      <v-btn class="btn_rental"
             @click="rentalBook"
             v-if= "bookRentalStatus == ''"
      > 도서대여</v-btn>
    </div>
    <BookComponent @rentalStatus="rentalStatus"/>
    <div class="list_section">
      <v-btn text class="btn_list" @click="goListBook">도서목록보기</v-btn>
    </div>
  </div>
</template>

<script>
import BookComponent from "@/components/book/BookComponent";
export default {
  name: "DetailBook",
  components: {
    BookComponent
  },
  data(){
    return {
      isAdmin: false,
      bookSeq: '',
      isCustomer: false,
      bookRentalStatus: '',
      rentalInfo: {
        rentalDayCount : '',
      }
    }
  },
  inject:['bookService' , 'rentalService'],
  created() {
    this.bookSeq = this.$route.params.bookSeq; // 데이터 매핑
    this.isAdmin = this.$store.getters.getRole == 'admin' ? true : false;
  },
  methods: {
    goListBook(){
      this.$router.push({
        path: '/book/list'
      })
    },
    rentalBook(){
      const numCheck=/^[0-9]*$/;
      const dayCount = prompt("원하시는 대여기간을 입력해주세요.(숫자만 입력하세요)");
      if( dayCount != null) {
        if(!numCheck.test(dayCount)){
          alert('숫자만 입력하실 수 있습니다. 다시 입력해주세요.');
          this.rentalBook();
        }
        this.rentalInfo.rentalDayCount = dayCount;
        this.rentalService.rentalBook(this.bookSeq , this.rentalInfo);
        alert('대여신청이 완료되었습니다.\n관리자 승인 후 대여 처리됩니다');
        location.reload();
      }
     return;
    },
    rentalStatus(status){
     this.bookRentalStatus = status;
    }
  },

}
</script>

<style scoped>
.btn_section {
  display: flex;
  justify-content: space-around;
}
.detail_section{
  margin: auto;
}
.list_section{
  display: flex;
  justify-content: center;
  margin-top: 20px;
  margin-bottom: 50px;
}
.btn_list {
  background-color: burlywood;
}
.btn_rental{
  width: 100%;
  font-weight: bold;
  background-color: darkseagreen !important;
}
.btn_rental_ing{
  width: 100%;
  font-weight: bold;
  background-color: skyblue!important;
}
.btn_rental_complete{
  width: 100%;
  font-weight: bold;
  background-color: cornflowerblue!important;
}
</style>