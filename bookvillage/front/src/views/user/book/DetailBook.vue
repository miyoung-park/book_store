<template>
  <div class="detail_section">
    <v-card
        class="mx-auto my-8"
        width="350"
        max-height="550"
        style="margin-bottom: 30px !important; display: flex; flex-direction: column"
    >
      <div class="btn_section" @click="goRentalBook">
        <a class="btn_rental">도서대여</a>
      </div>
      <v-divider></v-divider>
      <v-card-title
          style="font-size: small"
      >번호 : {{bookInfo.bookSeq}}</v-card-title>

      <div class="image_section">
        <v-img
            src="https://cdn.vuetifyjs.com/images/cards/cooking.png"
        ></v-img>
      </div>

      <v-card-title class="">{{bookInfo.bookTitle}}</v-card-title>

      <v-card-text>
        <div class="my-2">가격 : {{bookInfo.bookPrice}} 원</div>
        <div class="my-2">대여료 : {{bookInfo.bookRentalFee}} 포인트</div>
        <div class="my-2">등록날짜 : {{bookInfo.bookRegDt}}</div>
        <div>기타: {{bookInfo.bookMemo}}</div>
      </v-card-text>

    </v-card>
    <div class="list_section">
      <v-btn text class="btn_list" @click="goListBook">도서목록보기</v-btn>
    </div>
  </div>
</template>

<script>
export default {
  name: "DetailBook",
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
      ]
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
.detail_section{
  margin: auto;
}
.list_section{
  display: flex;
  justify-content: center;
  margin-bottom: 50px;
}
.btn_list {
  background-color: burlywood;
}
.image_section{
  display: flex;
  width: 160px;
  height: 200px;
  margin: auto;
}
.btn_section {
  height: 40px;
  background-color: #cbdb8c;
}
.btn_section :hover {
  background-color: #bbc78c;
}
.btn_rental{
  width: 100%;
  height: 100%;
  color: black;
  display: flex;
  align-items: center;
  justify-content: center;
}
</style>