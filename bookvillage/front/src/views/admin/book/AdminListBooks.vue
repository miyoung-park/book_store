<template>
  <div class="content">
    <div class="search_section">
      <v-card-title>
        <v-text-field
            v-model="search"
            append-icon="mdi-magnify"
            label="Search"
            single-line
            hide-details
        ></v-text-field>
      </v-card-title>
    </div>
    <div class="list_section">
      <v-btn text class="btn_list" @click="goWriteBook">도서등록</v-btn>
    </div>
    <div class="table_section">
      <v-data-table
          class="table"
          :headers="headers"
          :items="books"
          :search="search"
          @click:row="goDetail"
          :items-per-page="10"
          style="height: 85%"
          sort-by="bookSeq"
          :sort-desc= true
      >
      </v-data-table>
    </div>

  </div>
</template>

<script>
export default {
  name: "AdminListBooks",
  inject: ['bookService'],
  data () {
    return {
      search: '',
      headers: [
        { text: '도서 번호', value: 'bookSeq', align: 'start' },
        { text: '도서 제목', value: 'bookTitle'},
        { text: '도서 가격', value: 'bookPrice'},
        { text: '대여비', value: 'bookRentalFee'},
        { text: '메모', value: 'bookMemo' },
        { text: '대여현황' , value: '' }
      ],
      books: [],
    }
  },
  methods: {
    goDetail(book){
      const _bookSeq = book.bookSeq
      this.$router.push({
        path: '/admin/book/detail/'+ _bookSeq
      });
    },
     async getList(){
      const response = await this.bookService.getBookList();
      this.books = response;
    },
    goWriteBook(){
      this.$router.push({
        path: "/admin/book/add"
      })
    }
  },
  mounted() {
    this.getList();
  }
}
</script>

<style scoped>
.content{
  width: 100%;
  display: flex;
  flex-direction: column;
  justify-content: space-around;
  align-items: center;
}
.search_section {
  width: 50%;
}
.table_section {
  width: 80%;
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
}
.list_section{
  display: flex;
  justify-content: flex-end;
}
.btn_list {
  background-color: darksalmon;
}

</style>