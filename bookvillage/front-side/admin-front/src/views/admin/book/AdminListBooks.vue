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
      <v-btn text class="btn_list" @click="goWriteBook" v-if="adminLogin == 'admin'">도서등록</v-btn>
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
        <template v-slot:item="{item}">
          <tr style="cursor: pointer" @click="goDetail(item.bookSeq)">
            <td>{{item.bookSeq}}</td>
            <td>{{item.bookTitle}}</td>
            <td>{{item.bookPrice}}</td>
            <td>{{item.bookRentalFee}}</td>
            <td>{{item.bookMemo}}</td>
<!--             TODO: 이 부분을 더 깔끔하게 수정! key 값으로 한번 더 매핑해서 v-model: {{ rentalStatusValue }} 로 뿌려주던지 ... -->
            <td v-if="item.bookRentalStatus == '00'" style="background-color: lightgrey">대여신청중</td>
            <td v-else-if="item.bookRentalStatus == '01'" style="background-color: cornflowerblue">대여중</td>
            <td v-else-if="item.bookRentalStatus == '02'" style="background-color: crimson">대여중</td>
            <td v-else >대여가능</td>
          </tr>
        </template>
      </v-data-table>
<!--      TODO: Paging 처리 따로 진행 ! data table 사용 X  -->
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
  async mounted() {
    await this.getList();
  },
  methods: {
    goDetail(bookSeq){
      this.$router.push({
        path: '/admin/book/detail/'+ bookSeq
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
  computed: {
    adminLogin() {
      return this.$store.getters.getRole;
    }
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