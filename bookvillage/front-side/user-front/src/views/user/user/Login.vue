<template>
  <div class="login_section">
    <p> - 사용자 로그인 - </p>
    <div class="login_form">
        <v-text-field
            label="아이디"
            :rules="idRules"
            hide-details="auto"
            v-model="userObj.userId"
        ></v-text-field>
      <a>{{validId}}</a>
      <v-text-field
          label="비밀번호"
          :rules="pwRules"
          type="password"
          hide-details="auto"
          v-model="userObj.userPw"
      ></v-text-field>
      <a>{{validPw}}</a>
    </div>
    <div class="login_btn">
      <v-btn class="ma-2"
             style="background-color: #E6EE9C"
             @click="userLogin"
      >사용자로그인</v-btn>
    </div>
  </div>
</template>

<script>
export default {
  name: "Login",
  inject: ['userService'],
  data(){
    return {
      userObj : {
        userId: '',
        userPw: '',
      },
      validId: '',
      validPw: '',
      idRules: [ value => !!value || '아이디를 입력하세요.']
      ,
      pwRules: [ value => !!value || '비밀번호를 입력하세요.']
    }
  },
  methods: {
    async userLogin() {

        if( this.userObj.userId  === '' ) {
            this.validId = '아이디를 입력해주세요.';
            return;
        }
        this.validId = '';

        if( this.userObj.userPw === ''){
          this.validPw= '비밀번호를 입력해주세요.';
          return;
        }
        this.validPw = '';

        const payloads = await this.userService.loginUser(this.userObj);
        if( payloads != null){
          this.$store.commit('setPayloads', payloads);
          location.href = '/'
        }
     },

  }, // $apiErrorHandler(error) {
  //
  //
  // }
  created() {

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