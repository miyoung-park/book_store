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
    <div class="table_section">
      <v-data-table
          :headers="headers"
          :items="books"
          :search="search"
          @click:row="goDetail"
          :items-per-page="5"
      >
      </v-data-table>

    </div>
  </div>
</template>

<script>
import AxiosInst from "@/axios/AxiosInst";
export default {
  name: "Books",
  data () {
    return {
      search: '',
      headers: [
        { text: '도서 번호', value: 'bookSeq', align: 'start' },
        { text: '도서 제목', value: 'bookTitle'},
        { text: '도서 이미지', value: 'bookImage'},
        { text: '도서 가격', value: 'bookPrice' },
        { text: '대여비', value: 'bookRentalFee' },
        { text: '대여기간', value: 'bookRentalPeriod' },
        { text: '메모', value: 'bookMemo' },
      ],
      books: [],
    }
  },
  methods: {
    goDetail(book){
      const _bookSeq = book.bookSeq
      this.$router.push({
        path: '/book/detail/'+ _bookSeq
      });
    },
     async getList(){
      return await AxiosInst
                  .get('/book/list')
                  .then( response => {
                    this.books = response.data;
                  }).catch( error => {
                    console.log(error);
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
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
.search_section {
  width: 40%;

}
.table_section {
  width: 80%;
  margin-top: 50px;
}
</style>