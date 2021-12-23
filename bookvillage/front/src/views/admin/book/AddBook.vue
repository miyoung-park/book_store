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
            label="도서이미지"
            @change="showImage(bookInfo.bookImage)"
          ></v-file-input>
        </div>
        <div class="image_section" v-if="isView" >
          <div class="image">
            <img class="previewImage" :src="uploadImageFile">
          </div>
        </div>
    </div>
    <div class="btn_section">
      <v-btn class="mr-4" @click="addBook" style="background-color: #FFE082">등록하기</v-btn>
    </div>
  </div>
</template>

<script>
export default {
  name: "AddBook",
  data(){
    return{
      bookInfo: [
        { bookTitle: ''},
        { bookPrice: ''},
        { bookRentalFee: ''},
        { bookMemo: ''},
        { bookImage: null}
      ],
      uploadImageFile: null,
      isView: false
    }
  },
  methods: {
    addBook(){

    },
    showImage(){
      if( this.bookInfo.bookImage != null ) {
        const reader = new FileReader();
        reader.onload = e => {
          this.uploadImageFile  = e.target.result;
        }
        reader.readAsDataURL(this.bookInfo.bookImage)
        return this.isView = true;
        }
      return this.isView = false;
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
  width: 50%;
  height: 100%;
  display: flex;
  justify-content: center;
  margin: auto;
}
.previewImage {
  width: 60%;
}
.image {
  width: 100%;
  display: flex;
  justify-content: center;
}
</style>