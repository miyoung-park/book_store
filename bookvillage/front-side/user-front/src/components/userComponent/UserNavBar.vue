<template>
  <div class="nav_section">
  <v-navigation-drawer
      style="background: #E0F2F1"
      permanent
      expand-on-hover
  ><!-- expand-on-hover-->

    <!-- profile section -->
    <v-list-item  v-if="isUserLogin">
      <v-list-item-content>
        <v-list-item-title class="text-h6">
          {{ userId }}
        </v-list-item-title>
        <v-list-item-subtitle>
          {{ userName }}
        </v-list-item-subtitle>
      </v-list-item-content>
      <button class="logout_btn" small @click="logout" v-if="getToken != null">로그아웃</button>
    </v-list-item>

    <v-divider></v-divider>

    <!-- menu section user -->
    <v-list shaped>
      <v-list-item-group color="primary" v-if="getToken != null">
        <v-list-item
            v-for="(item, i) in userItems"
            :key="i"
            :to="item.url"
        >
          <v-list-item-icon>
            <v-icon v-text="item.icon"></v-icon>
          </v-list-item-icon>
          <v-list-item-content>
            <v-list-item-title v-text="item.title"></v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list-item-group>
    </v-list>
    <!-- menu section none user -->
    <v-list shaped>
      <v-list-item-group color="primary" v-if="getToken == null">
        <v-list-item
            v-for="(item, i) in items"
            :key="i"
            :to="item.url"
        >
          <v-list-item-icon>
            <v-icon v-text="item.icon"></v-icon>
          </v-list-item-icon>
          <v-list-item-content>
            <v-list-item-title v-text="item.title"></v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list-item-group>
    </v-list>
  </v-navigation-drawer>
  </div>
</template>

<script>
export default {
  name: "userNavBar",
  inject: ['userService'],
  data () {
    return {
      userId: '',
      userName: '',
      token: null,
      role: null,
      items: [
        { title: '로그인', icon: 'mdi-account' , url:'/login'},
        { title: '도서목록', icon: 'mdi-book', url:'/'}
      ],
      userItems: [
        { title: '프로필', icon: 'mdi-account-settings' , url:'/user/detail'},
        { title: '포인트', icon: 'mdi-credit-card-multiple' , url:'/user/point/list'},
        { title: '대여목록', icon: 'mdi-book-multiple', url:'/user/rental/list'},
        { title: '도서목록', icon: 'mdi-book', url:'/'}
      ],
    }
  },
  methods: {
    async isUserLogin(){
      this.userId = this.$store.getters.getUserId;
      this.userName = this.$store.getters.getUserName;
    },
    logout(){
      if(confirm('로그아웃 하시겠습니까?')){
        this.$store.dispatch('logout');
        location.reload();
      }
      return;
    }
  },
  async mounted() {
    await this.isUserLogin();
  },
  computed: {
     getToken(){
       this.isUserLogin();
      return this.$store.getters.getToken;
    }
  },
}
</script>

<style scoped>
.nav_section {
  height: 100%;
  position: fixed;
  z-index: 1;
  font-family: 'Gowun Dodum', sans-serif;
}
.logout_btn{
  width: 55px;
  height: 25px !important;
  font-size: x-small !important;
  background-color: darkseagreen;
  border-radius: 10px;
}

</style>