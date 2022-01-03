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
            label="개요"
            required
        ></v-text-field>
        <v-text-field
            v-model="bookInfo.bookImage"
            label="기존이미지"
            multiple
        >
        </v-text-field>

        <v-file-input
            v-model="bookNewImage"
            label="새이미지"
            @change="showImage(bookNewImage)"
            multiple
        ></v-file-input>
      </div>
      <div class="image_section">
        <div class="bookImage_section">
          <a style="color: black">&lt; 기존 이미지 &gt;</a>
          <div class="bookImage" >
            <div v-for="image in bookImages" :key="image.fileSeq">
              <img class="previewImage" :src="concat(image)">
              <v-card-text readonly style="cursor: pointer">
                {{image.originFileName}}  X
              </v-card-text>
            </div>
          </div>
        </div>
        <v-divider></v-divider>
        <div class="newImageSection" v-if="isView">
          <a style="color: black">&lt; 새 이미지 &gt;</a>
          <div v-for="imageUrl in uploadImageFile" :key="imageUrl">
            <div class="image">
              <img class="previewImage" :src="imageUrl">
            </div>
          </div>
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
        { bookMemo: ''}
      ],
      bookImages: [],
      bookNewImage: [],
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
      this.bookInfo = response.bookInfo;
      this.bookImages = response.files
    },
    concat(image){
      return image.savePath + image.renameFileName
    },
    showImage(bookNewImage){
      if( bookNewImage.length > 0 ) {
        if( bookNewImage.length > 4) {
          alert('파일은 4개까지만 업로드 가능합니다.');
          bookNewImage.length = 0;
          return;
        }
        for( let i = 0; i < bookNewImage.length; i++){
          const reader = new FileReader();
          reader.onload = event => {
            this.uploadImageFile.push(event.target.result);
          }
          reader.readAsDataURL(bookNewImage[i])
        }
        return this.isView = true;
      }
      this.uploadImageFile = [];
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
  width: 90%;
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
  flex-direction: column;
  justify-content: center;
  text-align: center;
  margin: auto 0 auto 30px;
}
.image {
  width: 25%;
}
.previewImage {
  width: 100%;
}
.newImageSection {
  width: 100%;
  display: flex;
  justify-content: center;
  flex-direction: column;
}
.bookImage{
  display: flex;
  text-align: center;
}
</style>