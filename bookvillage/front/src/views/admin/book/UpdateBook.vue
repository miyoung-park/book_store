<template>
  <div class="content">
    <div class="input_section">
      <div class="field_section">
        <v-text-field
            v-model="bookInfo.bookTitle"
            :counter="10"
            label="도서제목"
            required
        ></v-text-field>
        <v-text-field
            v-model="bookInfo.bookPrice"
            :counter="10"
            label="도서가격"
            required
        ></v-text-field>
        <v-text-field
            v-model="bookInfo.bookRentalFee"
            :counter="10"
            label="대여료"
            required
        ></v-text-field>
        <v-text-field
            v-model="bookInfo.bookMemo"
            :counter="10"
            label="메모"
            required
        ></v-text-field>
        <v-file-input
            v-model="bookInfo.bookImage"
            truncate-length="15"
            label="기존이미지"
            @change="showImage(bookInfo.bookImage)"
        ></v-file-input>
        <v-file-input
            v-model="bookNewImage"
            truncate-length="15"
            label="새이미지"
            @change="showImage(bookNewImage)"
        ></v-file-input>
      </div>
      <div class="image_section">
        <div class="image">
          <img class="previewImage" :src="uploadImageFile">
          <a style="color: black"> &lt; 기존 이미지 &gt; </a>
        </div>
        <div class="newImage" v-if="isView">
          <img class="previewImage" :src="uploadImageFile">
          <a style="color: black">&lt; 새 이미지 &gt;</a>
        </div>
      </div>
    </div>
    <div class="btn_section">
      <v-btn class="mr-4" @click="updateBook" style="background-color: #FFE082">수정하기</v-btn>
    </div>
  </div>
</template>

<script>
export default {
  name: "UpdateBook",
  inject: ['bookService'],
  data(){
    return{
      bookSeq: '',
      bookInfo: [
        { bookTitle: ''},
        { bookPrice: ''},
        { bookRentalFee: ''},
        { bookMemo: ''},
        { bookImage: null}
      ],
      bookNewImage: null,
      uploadImageFile: null,
      isView: false
    }
  },
  created() {
    this.bookSeq = this.$route.params.bookSeq; // 데이터 매핑
  },
  mounted() {
    this.getBookDetail()
  },
  methods: {
    async getBookDetail(){
      const response = await this.bookService.getBookDetail(this.bookSeq);
      this.bookInfo = response.data;
    },
    showImage(){
      if( this.bookNewImage != null ) {
        const reader = new FileReader();
        reader.onload = e => {
          this.uploadImageFile  = e.target.result;
        }
        reader.readAsDataURL(this.bookNewImage)
        return this.isView = true;
      }
      return this.isView = false;
    },
    updateBook(){

    }
  }
}
</script>

<style scoped>
.content {
  width: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  margin: auto;
}
.input_section {
  width: 80%;
  display: flex;
  justify-content: center;
  margin-bottom: 30px;
}
.field_section {
  width: 50%;
}
.btn_section{
  width: 50%;
  display: flex;
  justify-content: center;
}
.image_section {
  width: 40%;
  height: 100%;
  display: flex;
  justify-content: center;
  text-align: center;
  margin: auto 0 auto 60px;
}
.previewImage {
  width: 100%;
}
.image {
  width: 100%;
  margin-right: 20px;
  display: flex;
  flex-direction: column;
  justify-content: center;
}
.newImage {
  width: 100%;
  display: flex;
  justify-content: center;
  flex-direction: column;
}
</style>