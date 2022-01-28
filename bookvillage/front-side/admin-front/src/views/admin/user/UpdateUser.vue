<template>
  <div class="content">
    <div class="input_section">
      <v-form>
        <v-text-field
            v-model="userInfo.userId"
            label="아이디"
            required
            readonly
        ></v-text-field>
        <v-text-field
            v-model="userInfo.userPw"
            label= "새 비밀번호"
            type="password"
            required
        ></v-text-field>
        <v-text-field
            v-model="checkPw"
            label= "비밀번호 확인"
            type="password"
            @change="validationPassword"
            required
        ></v-text-field>
        <p style="font-size: small; color: crimson">{{validPw}}</p>
        <v-text-field
            v-model="userInfo.userName"
            label="이름"
            required
        ></v-text-field>
        <v-menu
            ref="menu"
            v-model="menu"
            :close-on-content-click="false"
            :return-value.sync="userInfo.userBirth"
            transition="scale-transition"
            offset-y
            min-width="auto"
        >
          <template v-slot:activator="{ on, attrs }">
            <v-text-field
                v-model="userInfo.userBirth"
                prepend-icon="mdi-calendar"
                readonly
                v-bind="attrs"
                v-on="on"
                label="생일"
            ></v-text-field>
          </template>
          <v-date-picker
              v-model="userInfo.userBirth"
              no-title
              scrollable
          >
            <v-spacer></v-spacer>
            <v-btn text color="primary" @click="menu = false"> 닫기 </v-btn>
            <v-btn text color="primary" @click="$refs.menu.save(userInfo.userBirth)">입력</v-btn>
          </v-date-picker>
        </v-menu>
        <v-text-field
            v-model="userInfo.userTell"
            label="전화번호"
            required
            @change="validationTell"
        ></v-text-field>
        <p style="font-size: small; color: crimson">{{validTell}}</p>
        <div class="btn_section">
          <v-btn class="mr-4" @click="updateUser" style="background-color: #FFE082">수정하기</v-btn>
          <v-btn class="mr-4" @click="goUserDetail" style="background-color: #FFE082">취소</v-btn>
        </div>
      </v-form>
    </div>
  </div>
</template>

<script>
export default {
  name: "UpdateUser",
  inject: ['userService'],
  data() {
    return {
      userInfo: {
        userSeq: '',
        userId : '' ,
        userPw : null ,
        userName: '' ,
        userBirth: '',
        userTell : ''
      },
      checkPw: '',
      validPw: null,
      validTell: null,
      menu: false
    }
  },
  created() {
    this.userInfo.userSeq = this.$route.params.userSeq;
  },
  methods:{
    async getUserDetail(){
      const response = await this.userService.getUserDetail(this.userInfo.userSeq);
      this.userInfo = response;
    },
    validationPassword(){
      if(this.userInfo.userPw != this.checkPw){
        this.validPw = '비밀번호가 다릅니다. 다시 입력해주세요.';
        this.checkPw = ''; //초기화
        return;
      }
      this.validPw = ''; //초기화
    },
    validationTell(){
      const numCheck=/^[0-9]*$/;
      if (!numCheck.test(this.userInfo.userTell)) {
        this.validTell = '숫자만 입력하실 수 있습니다.';
        this.userInfo.userTell = ''; //초기화
        return;
      }
      this.validTell = ''; //초기화
    },
    async updateUser(){
      await this.userService.updateUser(this.userInfo);
      alert('고객정보가 수정되었습니다.');
      await this.$router.push({
        path: '/admin/user/detail/' + this.userInfo.userSeq
      })
    },
    goUserDetail(){
      this.$router.push({
        path: '/admin/user/detail/'+ this.userInfo.userSeq
      })
    }
  },
  async mounted() {
    await this.getUserDetail();
  }
}
</script>

<style scoped>
.content {
  width: 100%;
  display: flex;
  justify-content: center;
  margin: auto;
}
.input_section {
  width: 50%;
  margin-bottom: 100px;
}
.btn_section{
  display: flex;
  justify-content: center;
}
</style>