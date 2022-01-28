<template>
  <div class="detail_section">
    <h3> [ 고객정보 ] </h3>
    <v-card
        class="mx-auto my-8"
        width="350"
        max-height="550"
        style="margin-bottom: 30px !important; display: flex; flex-direction: column"
    >

      <v-card-text>
        <div class="my-4">고객번호 : {{userInfo.userSeq}}</div>
        <div class="my-4">고객명 : {{userInfo.userName}}</div>
        <div class="my-4">고객 아이디 : {{userInfo.userId}}</div>
        <div class="my-4" style="display: flex; align-items: center">
           고객 포인트 : {{userInfo.userPoint}} 포인트
          <button class="point_btn" @click="getPointList">포인트 내역</button>
        </div>
        <div class="my-4">생년월일 : {{userInfo.userBirth}}</div>
        <div class="my-4">전화번호 : {{userInfo.userTell}}</div>
        <div class="my-4">등록일자 : {{userInfo.userRegDt}}</div>
      </v-card-text>
      <v-divider></v-divider>
      <div class="btn_section">
        <v-btn text class="btn_update" @click="goUpdateUser">고객수정</v-btn>
        <v-btn text class="btn_delete" @click="goDeleteUser">고객삭제</v-btn>
      </div>
    </v-card>
    <div class="list_section">
      <v-btn text class="btn_list" @click="goListUser">고객목록보기</v-btn>
      <v-btn text class="btn_list" @click="goListRental">고객대여목록</v-btn>
    </div>
  </div>
</template>

<script>
export default {
  name: "DetailUser",
  inject: ['userService'],
  data() {
    return {
      userInfo: {
          userSeq: '',
          userId: '',
          userName: '',
          userBirth: '',
          userTell: '',
          userRegDt: '',
          userPoint: ''
      },
    }
  },
  // 데이터 매핑
  created() {
    this.userInfo.userSeq  = this.$route.params.userSeq;
  },
  methods: {
    async getUserDetail(){
      const data = await this.userService.getUserDetail(this.userInfo.userSeq);
      this.userInfo = data;
    },
    goListUser(){
      this.$router.push({
        path: '/admin/user/list'
      })
    },
    goUpdateUser(){
      const _userSeq = this.userInfo.userSeq
      this.$router.push({
        path: '/admin/user/update/' + _userSeq
      });
    },
    async goDeleteUser(){
      if(confirm('고객 정보를 정말 삭제하시겠습니까 ?')){
        await this.userService.deleteUser(this.userInfo.userSeq);
        alert('고객정보가 삭제되었습니다.');
        return this.$router.push({
          path: '/admin/user/list'
        }).catch((e)=> {console.log(e)})
      }
      return;
    },
    getPointList(){
      const _userSeq = this.userInfo.userSeq
      this.$router.push('/admin/user/point/list/'+ _userSeq );
    },
    goListRental(){
      const _userSeq = this.userInfo.userSeq
      this.$router.push('/admin/rental/detail/'+ _userSeq );
    },
  },

  async mounted() {
    await this.getUserDetail();
  }
}
</script>

<style scoped>
.btn_section {
  display: flex;
  justify-content: space-around;
}
.btn_update{
  color: darkslateblue;
  font-weight: bold;
}
.btn_delete{
  color: maroon;
  font-weight: bold;
}
.detail_section{
  margin: 10% auto 0 auto;
  display: flex;
  flex-direction: column;
  align-items: center;
}
.list_section{
  display: flex;
  justify-content: center;
  margin-bottom: 50px;
}
.btn_list {
  background-color: burlywood;
}
.point_btn {
  color: purple;
  width: 60px;
  border-radius: 5px;
  border: darkgray 1px solid;
  font-size: x-small;
  margin-left: 10px;
}
</style>