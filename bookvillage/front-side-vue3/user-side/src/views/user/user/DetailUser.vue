<template>
  <div class="detail_section">
    <h3> [ 프로필 ] </h3>
      <div class="user_info_section">
        <v-card>
          <v-simple-table class="table">
            <tbody>
            <tr>
              <td>고객번호 : </td><td class="td">{{userInfo.userSeq}}</td>
            </tr>
            <tr>
              <td>아이디 : </td><td class="td">{{userInfo.userId}}</td>
            </tr>
            <tr>
              <td>고객명 : </td><td class="td">{{userInfo.userName}}</td>
            </tr>
            <tr>
              <td>포인트 : </td><td class="td">{{userInfo.userPoint}} point</td>
            </tr>
            <tr>
              <td>생년월일 : </td><td class="td">{{userInfo.userBirth}}</td>
            </tr>
            <tr>
              <td>전화번호 : </td><td class="td">{{userInfo.userTell}}</td>
            </tr>
            <tr>
              <td>등록일자 : </td><td class="td">{{userInfo.userRegDt}}</td>
            </tr>
            </tbody>
          </v-simple-table>
        </v-card>
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
  methods: {
    async getUserDetail(){
      const data = await this.userService.getUserDetailById();
      // TODO: userPoint 는 store 에 저장해서 꺼내쓰는걸로 하고 point 충전시 dispatch 로 포인트를 가져와서 업데이트 시킨다.
      this.userInfo = data;
    }
  },
  mounted() {
    this.getUserDetail();
  }
}
</script>

<style scoped>
.detail_section{
  display: flex;
  flex-direction: column;
  align-items: center;
  margin: 10% auto 0 auto
}
.user_info_section {
  margin-top: 30px;
  font-size: large;
  color: black;
}
.user_info_section a{
  font-size: large;
  font-weight: bold ;
  color: darkslategrey;
}
.table {
  width: 300px;
}
.td {
  color: black;
  font-size: medium !important;
}
</style>