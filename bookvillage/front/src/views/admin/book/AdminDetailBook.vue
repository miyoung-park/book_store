<template>
  <div class="detail_section">
    <div class="btn_section">
      <v-btn class="btn_delete" @click="goDeleteBook">도서삭제</v-btn>
      <v-btn class="btn_update" @click="goUpdateBook">도서수정</v-btn>
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
  name: "AdminDetailBook",
  components: {
    BookComponent
  },
  data(){
    return {
      bookSeq: '',
    }
  },
  inject:['bookService'],
  created() {
    this.bookSeq = this.$route.params.bookSeq; // 데이터 매핑
    },
  methods: {
    goUpdateBook(){
      this.$router.push({
        path: '/admin/book/update/' + this.bookSeq
      })
    },
     async goDeleteBook(){
      if(confirm('정말 삭제하시겠습니까 ?')){
        await this.bookService.deleteBook(this.bookSeq);
        alert('도서정보 삭제가 완료되었습니다.')
        this.$router.push({
          path: '/admin/book/list'
        }).catch((e)=> {console.log(e)})
      }
      return;
    },
    goListBook(){
      this.$router.push({
        path: '/admin/book/list'
      })
    }
  },

}
</script>

<style scoped>
.detail_section{
  margin: auto;
}
.btn_section {
  display: flex;
  justify-content: space-around;
}
.btn_update{
  width: 50%;
  background-color: rosybrown !important;
  font-weight: bold;
}
.btn_delete{
  width: 50%;
  background-color: indianred !important;
  font-weight: bold;
}
.list_section{
  width: 100%;
  display: flex;
  justify-content: center;
  margin: auto;
}
.btn_list {
  background-color: rosybrown;
  font-weight: bold;
  margin-top: 20px;
}

</style>