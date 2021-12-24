<template>
  <div class="content">
    <div class="input_section">
      <form>
        <v-text-field
            v-model="customerInfo.userId"
            :counter="10"
            label="아이디"
            required
        ></v-text-field>
        <v-text-field
            v-model="customerInfo.userPw"
            :counter="10"
            label="비밀번호"
            required
        ></v-text-field>
        <v-text-field
            v-model="checkPw"
            :counter="10"
            label="비밀번호 확인"
            required
        ></v-text-field>
        <v-text-field
            v-model="customerInfo.userName"
            :counter="10"
            label="이름"
            required
        ></v-text-field>
        <v-menu
            ref="menu"
            v-model="menu"
            :close-on-content-click="false"
            :return-value.sync="customerInfo.userBirth"
            transition="scale-transition"
            offset-y
            min-width="auto"
        >
          <template v-slot:activator="{ on, attrs }">
            <v-text-field
                v-model="customerInfo.userBirth"
                prepend-icon="mdi-calendar"
                readonly
                v-bind="attrs"
                v-on="on"
                label="생일"
            ></v-text-field>
          </template>
          <v-date-picker
              v-model="customerInfo.userBirth"
              no-title
              scrollable
          >
            <v-spacer></v-spacer>
            <v-btn text color="primary" @click="menu = false"> 닫기 </v-btn>
            <v-btn text color="primary" @click="$refs.menu.save(customerInfo.userBirth)">입력</v-btn>
          </v-date-picker>
        </v-menu>
        <v-text-field
            v-model="customerInfo.userTell"
            :counter="10"
            label="전화번호"
            required
        ></v-text-field>
        <div class="btn_section">
          <v-btn class="mr-4" @click="addCustomer" style="background-color: #FFE082">등록하기</v-btn>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
export default {
  name: "UpdateCustomer",
  data() {
    return {
      customerInfo: [
        {userId: ''},
        {userPw: ''},
        {userName: ''},
        {userBirth: (new Date(Date.now() - (new Date()).getTimezoneOffset() * 60000)).toISOString().substr(0, 10)},
        {userTell: ''}
      ],
      checkPw: '',
      menu: false
    }
  }
}
</script>

<style scoped>

</style>