<template>
  <div class="detail_section">
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
              :src="concat(file)"
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
      files: [],
    }
  },
  inject:['bookService'],
  created() {
    this.bookSeq = this.$route.params.bookSeq; // 데이터 매핑
  },
  async mounted() {
    await this.getBookDetail();
  },
  methods: {
    async getBookDetail() {
      const data = await this.bookService.getBookDetail(this.bookSeq);
      this.bookInfo = data.bookInfo;
      this.files = data.files;
    },
    concat(file) {
      return file.savePath + file.renameFileName
    },
  },
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