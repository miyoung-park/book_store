<template>
  <div class="login_section">
    <p> - 관리자 로그인 - </p>
    <div class="login_form">
        <v-text-field
            label="아이디"
            :rules="idRules"
            hide-details="auto"
            v-model="userObj.userId"
        ></v-text-field>
      <v-text-field
          label="비밀번호"
          :rules="pwRules"
          type="password"
          hide-details="auto"
          v-model="userObj.userPw"
      ></v-text-field>
    </div>
    <div class="login_btn">
      <v-btn class="ma-2"
             style="background-color: darkseagreen"
             @click="adminLogin"
      >관리자로그인</v-btn>
    </div>
  </div>
</template>

<script>
export default {
  name: "Login",
  inject: ['adminService'],
  data(){
    return {
      userObj : {
        userId: '',
        userPw: '',
      },
      idRules: [ value => !!value || '아이디를 입력하세요.']
      ,
      pwRules: [ value => !!value || '비밀번호를 입력하세요.']
    }
  },
  methods: {
     async adminLogin(){
      const payloads = await this.adminService.adminLogin(this.userObj);
      if( payloads != null){
        this.$store.commit('setPayloads', payloads)
        await this.$router.push( '/admin/book/list')
      }

    },
    $apiErrorHandler(error) {
       const errorMessage = error.errorMessage;
       alert(errorMessage + '\n다시 시도해주세요.')

    }
  },
  created() {
    this.$addApiErrorHandler( this.$errorCode.INVALID_USER , this.$apiErrorHandler , false );
    this.$addApiErrorHandler( this.$errorCode.INVALID_PASSWORD , this.$apiErrorHandler , false );
  }
}
</script>

<style scoped>
.login_section{
  width: 100%;
  height: 500px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;

}
.login_form {
  width: 40vw;
  margin-bottom: 30px;
}
button {
  background-color: darkslateblue;
}
</style>