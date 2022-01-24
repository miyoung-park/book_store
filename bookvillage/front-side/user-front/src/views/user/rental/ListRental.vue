<template>
  <div class="content">
    <div class="table_section">
      <v-data-table
          :headers="headers"
          :items="rentalList"
          :items-per-page="5"
          sort-by="rentalSeq"
          :sort-desc= true
          primary-key="index"
      >
        <template v-slot:item="{item}">
          <tr @click="goDetailRental(item.rentalSeq , item.rentalStatus)">
            <td>{{rentalList.indexOf(item) + 1}}</td>
            <td>{{item.bookSeq}}</td>
            <td>{{item.bookTitle}}</td>
            <td>{{item.rentalDt}}</td>
            <td>{{item.predictReturnDt}}</td>
            <td>{{item.returnDt}}</td>
            <td>{{ getStatus(item) }}</td>
            <td>{{item.rentalRegDt}}</td>
            <td>{{item.rentalUpdateDt}}</td>
          </tr>
        </template>
      </v-data-table>
    </div>
  </div>
</template>

<script>
export default {
  name: "ListRentals",
  inject: ['rentalService'],
  data () {
    return {
      search: '',
      headers: [
        { text: '번호', align: 'start' },
        { text: '도서번호', value: 'bookSeq'},
        { text: '도서명', value: 'bookTitle'},
        { text: '대여일', value: 'rentalDt' },
        { text: '반납예정일', value: 'predictReturnDt' },
        { text: '반납일', value: 'returnDt' },
        { text: '대여현황', value: 'rentalStatus' },
        { text: '등록날짜', value: 'rentalRegDt' },
        { text: '수정날짜', value: 'rentalUpdateDt' }
      ],
      rentalList: [],
      rentalStatus: null
    }
  },
  methods: {
    async getRentalList(){
      const response = await this.rentalService.getRentalList();
      this.rentalList = response;
    },
    goDetailRental(_rentalSeq , _rentalStatus){
      if( _rentalStatus === '01' || _rentalStatus === '03') {
        this.$router.push('/customer/rental/detail/' + _rentalSeq , );
      }
      return;
    },
    getStatus(item){
      if(item.rentalStatus === '00') {
        return '대여신청중'
      }
      if(item.rentalStatus === '01') {
        return '대여중'
      }
      if(item.rentalStatus === '02') {
        return '반납완료'
      }
      if(item.rentalStatus === '03') {
        return '연체'
      }
      if(item.rentalStatus === '04') {
        return '대여취소'
      }
    }
  },
  created() {
    this.$addApiErrorHandler('610', this.$apiErrorHandler, false )
    this.$addApiErrorHandler('620', this.$apiErrorHandler, false )
    this.$addApiErrorHandler('630', this.$apiErrorHandler, false )
  },
  mounted() {
    this.getRentalList();
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
.table_section {
  width: 80%;
  height: 100%;
  margin-top: 50px;
}
</style>