<template>
  <v-card
      class="mx-auto my-12"
      max-width="374"
  >
    <template slot="progress">
      <v-progress-linear
          color="deep-purple"
          height="10"
          indeterminate
      ></v-progress-linear>
    </template>

    <v-card-title style="font-size: small">번호 : {{bookInfo.bookSeq}}</v-card-title>
    <v-img
        height="250"
        src="https://cdn.vuetifyjs.com/images/cards/cooking.png"
    ></v-img>

    <v-card-title>{{bookInfo.bookTitle}}</v-card-title>

    <v-card-text>


      <div class="my-2">가격 : {{bookInfo.bookPrice}} 원</div>
      <div class="my-2">대여료 : {{bookInfo.bookRentalFee}} 포인트</div>
      <div class="my-2">등록날짜 : {{bookInfo.bookRegDt}}</div>
      <div>기타: {{bookInfo.bookMemo}}</div>
    </v-card-text>
    <v-divider></v-divider>
    <div class="btn_section">
      <v-btn text class="btn_update" @click="goUpdateBook">도서수정</v-btn>
      <v-btn text class="btn_delete" @click="goDeleteBook">도서삭제</v-btn>
    </div>
  </v-card>
</template>

<script>
export default {
  name: "DetailBook",
  data(){
    return {
      bookSeq: '',
      bookInfo: [
        { bookSeq: '' },
        { bookTitle: ''},
        { bookPrice: '' },
        { bookRentalFee: '' },
        { bookMemo: '' },
        { bookRegDt: '' },
      ]
    }
  },
  inject:['bookService'],
  created() {
    this.bookSeq = this.$route.params.bookSeq; // 데이터 매핑
  },
  mounted() {
    this.getBookDetail();
  },
  methods: {
    async getBookDetail(){
       const response = await this.bookService.getBookDetail(this.bookSeq);
        this.bookInfo = response.data;
    },
    goUpdateBook(){

    },
    async goDeleteBook(){
      if(confirm('정말 삭제하시겠습니까 ?')){
        try {
          await this.bookService.deleteBook(this.bookSeq);
          alert('도서정보 삭제가 완료되었습니다.')
          this.$router.push({
            path: '/admin/book/list'
          }).catch((e)=> {console.log(e)})
        }catch(error) {
          console.log(error);
          alert('도서정보 삭제가 실패했습니다. 다시 진행해주세요.')
        }

      }
      return;
    }
  },

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
</style>