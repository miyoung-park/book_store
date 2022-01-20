<template>
  <div class="detail_section" ref="bookComponent">
    <v-card
        class="mx-auto my-8"
        width="500"
        max-height="550"
        style="margin: 0 !important; display: flex; flex-direction: column"
    >
      <v-card-title
          style="font-size: small"
      >번호 : {{rentalInfo.rentalSeq}}</v-card-title>
      <v-card-text>
        <div class="my-2">도서번호 : {{rentalInfo.bookSeq}}</div>
        <div class="my-2">도서명 : {{rentalInfo.bookTitle}}</div>
        <div class="my-2">대여날짜 : {{rentalInfo.rentalDt}}</div>
        <div class="my-2">반납예정일 : {{rentalInfo.predictReturnDt}}</div>
        <div class="my-2">반납일 : {{rentalInfo.returnDt}}</div>
        <div class="my-2">대여현황 : {{getStatus()}}</div>
      </v-card-text>
      <v-btn @click="checkLateFee">반납하기</v-btn>
    </v-card>
  </div>
</template>

<script>
export default {
  name: "DetailRental",
  inject: ['rentalService'],
  data(){
    return{
      rentalSeq: null,
      rentalInfo: [],
      userInfo: [],
    }
  },
  created() {
    this.rentalSeq = this.$route.params.rentalSeq;
  },
  methods: {
    async getRentalDetail(){
      try {
        const response = await this.rentalService.getRentalDetail(this.rentalSeq);
        this.rentalInfo = response.rentalInfo;
        this.userInfo = response.userInfo;
      } catch ( error ){
        console.log(error);
      }
    },
    getStatus(){

      if(this.rentalInfo.rentalStatus === '01') {
        return '반납하기'
      }
      if(this.rentalInfo.rentalStatus === '03') {
        return '연체'
      }
    },
    async checkLateFee(){
      // 연체된 경우
      if( this.rentalInfo.rentalStatus === '03') {

        let today = new Date(); // 오늘 날짜
        let predictDate = new Date(this.rentalInfo.predictReturnDt); // 빌린 날짜
        today = new Date(today.getFullYear(), today.getMonth()+1, today.getDate());
        predictDate = new Date(predictDate.getFullYear(), predictDate.getMonth()+1, predictDate.getDate());

        let lateDays = Math.abs(today.getTime() - predictDate.getTime());
        lateDays = Math.ceil(lateDays / (1000 * 3600 * 24));

        if( confirm( lateDays + '일이 연체 되었습니다. \n연체료 정산 후 반납이 가능합니다. \n연체료를 정산하시겠습니까 ?') ){
          this.returnBook( lateDays );
        }
        return;
      }
      // 연체 아닌 경우
      this.returnBook();
    },
    async returnBook( ){
        await this.rentalService.returnBook( this.rentalInfo);
        alert('도서가 반납되었습니다.');
        this.$router.push('/customer/rental/list').catch(e => { console.log(e);});


    }
  },
  async mounted() {
    await this.getRentalDetail();
  }
}
</script>

<style scoped>
.detail_section{
  margin: auto;
}

</style>