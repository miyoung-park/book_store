<template>
  <div class="detail_section" ref="bookComponent">
    <v-card
        class="mx-auto my-8"
        width="500"
        max-height="550"
        style="margin: 0 !important; display: flex; flex-direction: column"
    >
      <v-card-title
          style="font-size: small"
      >번호 : {{bookInfo.bookSeq}}</v-card-title>
      <div class="image_section">
        <div v-for="file in files"
             :key="file.fileSeq">
          <v-img
              class="image"
              :src=concat(file)
          >
          </v-img>
        </div>
      </div>
      <v-card-title >{{bookInfo.bookTitle}}</v-card-title>
      <v-card-text>
        <div class="my-2">가격 : {{bookInfo.bookPrice}} 원</div>
        <div class="my-2">대여료 : {{bookInfo.bookRentalFee}} 포인트</div>
        <div class="my-2">등록날짜 : {{bookInfo.bookRegDt}}</div>
        <div class="my-2">개요: {{bookInfo.bookMemo}}</div>
      </v-card-text>
    </v-card>
  </div>
</template>
<script>
export default {
  name: "BookComponent",
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
      ],
      files: []
    }
  },
  inject:['bookService'],
  methods: {
    async getBookDetail() {
      const response = await this.bookService.getBookDetail(this.bookSeq);
      this.bookInfo = response.bookInfo;
      this.files = response.files;
      if(this.bookInfo.bookRentalStatus != null){
        this.$emit("rentalStatus" , this.bookInfo.bookRentalStatus);
      }
    },
    concat(file) {
      return file.savePath + file.renameFileName
    },
    $apiErrorHandler( error ){
      alert( error.errorMessage );
      this.$router.push('/admin/book/list');
    }
  },
  async mounted() {
    await this.getBookDetail();
  },
  created() {
    this.bookSeq = this.$route.params.bookSeq; // 데이터 매핑
    this.$addApiErrorHandler( this.$errorCode.DATA_NOT_FOUND , this.$apiErrorHandler, false )
  }
}
</script>

<style scoped>
.detail_section{
  margin: auto;
}
.image_section{
  display: flex;
  justify-content: space-around;
  align-items: center;
  width: 100%;
  margin: auto;
}
.image {
  width: 100px;
  display: flex;
  margin: auto;
}
</style>