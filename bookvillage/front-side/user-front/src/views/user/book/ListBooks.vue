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
          :items-per-page="10"
          sort-by="bookSeq"
          :sort-desc= true
      >
        <template v-slot:item="{item}">
          <tr style="cursor: pointer" @click="goDetail(item.bookSeq)">
            <td>{{item.bookSeq}}</td>
            <td>{{item.bookTitle}}</td>
            <td>{{item.bookPrice}}</td>
            <td>{{item.bookRentalFee}}</td>
            <td>{{item.bookMemo}}</td>
              <td v-if="item.bookRentalStatus == '00'" style="background-color: lightblue">대여신청중</td>
              <td v-if="item.bookRentalStatus == '01'" style="background-color: cornflowerblue">대여중</td>
              <td v-if="item.bookRentalStatus == '03'" style="background-color: crimson">연체중</td>
              <td v-if="item.bookRentalStatus == null || item.bookRentalStatus == '04'" style="font-weight: bold">대여가능</td>
          </tr>
        </template>
      </v-data-table>

    </div>
  </div>
</template>

<script>
export default {
  name: "Books",
  inject: ['bookService'],
  data () {
    return {
      search: '',
      headers: [
          // TODO : Paging 도 그렇고 Sort 도 내가 직접 구현하는 형태로 해보기 !
        { text: '도서 번호', value: 'bookSeq', align: 'start' },
        { text: '도서 제목', value: 'bookTitle'},
        { text: '도서 가격', value: 'bookPrice' },
        { text: '대여비', value: 'bookRentalFee' },
        { text: '메모', value: 'bookMemo' },
        { text: '도서현황' , value: 'bookRentalStatus'}
      ],
      books: [],
    }
  },
  methods: {
    goDetail(bookSeq){
      this.$router.push({
        path: '/book/detail/'+ bookSeq
      });
    },
     async getList() {
       const response = await this.bookService.getBookList();
       this.books = response;
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
  justify-content: center;
  align-items: center;
}
.search_section {
  width: 40%;

}
.table_section {
  width: 80%;
  height: 100%;
  margin-top: 50px;
}
</style>