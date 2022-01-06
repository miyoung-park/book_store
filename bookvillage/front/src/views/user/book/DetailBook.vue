<template>
  <div class="detail_section">
    <div class="btn_section">
      <v-btn class="btn_rental" v-if="this.$store.getters.getRole == 'customer'">도서대여</v-btn>
    </div>
    <BookComponent/>
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
      bookInfo: [
        { bookSeq: '' },
        { bookTitle: ''},
        { bookPrice: '' },
        { bookRentalFee: '' },
        { bookMemo: '' },
        { bookRegDt: '' },
      ],
      isCustomer: false
    }
  },
  inject:['bookService'],
  created() {
    this.bookSeq = this.$route.params.bookSeq; // 데이터 매핑
    this.isAdmin = this.$store.getters.getRole == 'admin' ? true : false;
  },
  mounted() {
    this.getBookDetail();
  },
  methods: {
    async getBookDetail(){
       const response = await this.bookService.getBookDetail(this.bookSeq);
        this.bookInfo = response.data;
    },
    goListBook(){
      this.$router.push({
        path: '/book/list'
      })
    },
    goRentalBook(){

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
</style>