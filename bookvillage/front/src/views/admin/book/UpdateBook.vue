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
        <v-file-input
            v-model="bookNewImages"
            label="새이미지"
            @change="showImage(bookNewImages)"
            @click="resetImage"
            multiple
        ></v-file-input>
      </div>

      <!-- **** preview Image[s] **** -->
      <div class="image_section">
        <div class="bookImage_section">
          <a style="color: black">&lt; 기존 이미지 &gt;</a>
          <div class="bookImage" >
            <p v-if="bookImages.length == 0"
               style="color: gray; font-size: small">
              이미지 없음
            </p>
            <div v-for="image in bookImages" :key="image.fileSeq">
              <img class="previewImage" :src="concatPath(image)">
              <v-card-text readonly style="cursor: pointer" @click="addDeleteFiles(image.fileSeq)">
                {{image.originFileName}}  X
              </v-card-text>
            </div>
          </div>
        </div>
        <v-divider></v-divider>
        <div class="newImageSection" v-if="isView">
          <a style="color: black">&lt; 새 이미지 &gt;</a>
          <div class="bookImage" >
            <div v-for="imageUrl in uploadImageFile" :key="imageUrl">
                <div class="image">
                  <img class="previewImage" :src="imageUrl">
                </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- **** preview Image[e] **** -->

    <div class="btn_section">
      <v-btn class="mr-4" @click="updateBook" style="background-color: darksalmon; margin-bottom: 10px">게시글 수정</v-btn>
      <v-btn class="mr-4" @click="goDetail" style="background-color: #FFE082">게시글 보기</v-btn>
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
      bookNewImages: [],
      uploadImageFile: [],
      deleteFiles: [],
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
    goDetail() {
      this.$router.push({
        path: '/admin/book/detail/' + this.bookSeq
      });
    },
    addDeleteFiles(fileSeq){
      this.deleteFiles.push(fileSeq);
      for(let i = 0; i < this.bookImages.length; i++){
        // 해당 fileSeq 뺴주기( for image preview )
        if( this.bookImages[i].fileSeq == fileSeq){
            let index = i;
            this.bookImages.splice(index , 1);
        }
      }
    },
    async updateBook(){
      await this.bookService.updateBook(this.bookInfo , this.bookNewImages, this.deleteFiles)
      alert('도서정보가 수정되었습니다.');
      this.$router.push({
        path: '/admin/book/detail/' + this.bookSeq
      }).catch(e => { console.log(e)});
    },

    /**** preview Image[s] ****/
    concatPath(image){
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
    resetImage(){
      this.uploadImageFile = [];
      this.isView = false;
    },
    /**** preview Image[e] ****/
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
  width: 150px;
  display: flex;
  flex-direction: column;
  justify-content: center;
}
.image_section {
  width: 40%;
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  text-align: center;
  margin: auto 0 auto 30px;
}
.bookImage_section {
  display: flex;
  flex-direction: column;
  align-items: center;
}
.image {
  width: 25%;
}
.previewImage {
  width: 100px;
  height: 140px;
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
  justify-content: space-around;
  margin-top: 20px;
}

</style>