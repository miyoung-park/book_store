<template>
  <div class="content">
    <div class="input_section">
        <div class="field_section">
          <v-text-field
              v-model="bookInfo.bookTitle"
              label="도서제목"
              required
          ></v-text-field>
          <v-text-field
              v-model="bookInfo.bookPrice"
              label="도서가격"
              required
          ></v-text-field>
          <v-text-field
              v-model="bookInfo.bookRentalFee"
              label="대여료"
              required
          ></v-text-field>
          <v-text-field
              v-model="bookInfo.bookMemo"
              :counter="100"
              label="메모"
              required
          ></v-text-field>
          <v-file-input
            v-model="bookImage"
            truncate-length="15"
            label="도서이미지"
            multiple
            @change="showImage"
            @click="resetImage"
          ></v-file-input>
        </div>
      <div class="image_section" v-if="isView">
        <div v-for="imageUrl in uploadImageFile" :key="imageUrl">
          <div class="image">
            <img class="previewImage" :src="imageUrl">
          </div>
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
  inject: ['bookService'],
  data(){
    return{
      bookInfo: {
        bookTitle: '',
        bookPrice: '',
        bookRentalFee: '',
        bookMemo: '',
       },
      bookImage: {},
      uploadImageFile: [],
      isView: false
    }
  },
  methods: {
    addBook(){
      const response = this.bookService.addBook(this.bookInfo , this.bookImage);
      console.log(response);
    },
    showImage(images){
      if( images.length > 0 ) {
        if( images.length > 4) {
          alert('파일은 4개까지만 업로드 가능합니다.');
          images.length = 0;
          return;
        }
        for( let i = 0; i < images.length; i++){
          const reader = new FileReader();
          reader.onload = event => {
            this.uploadImageFile.push(event.target.result);
          }
          reader.readAsDataURL(images[i])
        }
        return this.isView = true;
      }
      this.uploadImageFile = [];
      return this.isView = false;
    },
    resetImage(){
      this.uploadImageFile = [];
      this.isView = false;
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
  margin-right: 50px;
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
  width: 100px;
  height: 150px;
}
.image {
  width: 100%;
  display: flex;
  justify-content: center;
}
</style>