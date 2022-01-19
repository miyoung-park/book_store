<template>
  <div class="nav_section">
  <v-navigation-drawer
      style="background: #E0F2F1"
      permanent
      expand-on-hover
  ><!-- expand-on-hover-->

    <!-- profile section -->
    <v-list-item  v-if="customerLogin != null">
      <v-list-item-content>
        <v-list-item-title class="text-h6">
          {{info.userId}}
        </v-list-item-title>
        <v-list-item-subtitle>
          {{info.userName}}
        </v-list-item-subtitle>
      </v-list-item-content>
      <button class="logout_btn" small @click="logout" v-if="customerLogin != null">로그아웃</button>
    </v-list-item>

    <v-divider></v-divider>

    <!-- menu section user -->
    <v-list shaped>
      <v-list-item-group color="primary" v-if="customerLogin != null">
        <v-list-item
            v-for="(item, i) in customerItems"
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
      <v-list-item-group color="primary" v-if="customerLogin == null">
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
  name: "BasicNavBar",
  inject: ['customerService'],
  data () {
    return {
      info: {},
      token: null,
      role: null,
      items: [
        { title: '로그인', icon: 'mdi-account' , url:'/login'},
        { title: '도서목록', icon: 'mdi-book', url:'/'}
      ],
      customerItems: [
        { title: '프로필', icon: 'mdi-account-settings' , url:'/customer/detail'},
        { title: '포인트', icon: 'mdi-credit-card-multiple' , url:'/customer/point/list'},
        { title: '대여목록', icon: 'mdi-book-multiple', url:'/customer/rental/list'},
        { title: '도서목록', icon: 'mdi-book', url:'/'}
      ],
    }
  },
  methods: {
    async isCustomerLogin(){
      this.token = this.$store.getters.getToken;
      if( this.token != null){
        const response = await this.customerService.getCustomerDetailById();
        this.info = response;
        return;
      }
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
    await this.isCustomerLogin();
  },
  computed: {
     customerLogin(){
       this.isCustomerLogin();
      return this.$store.getters.getToken;
    }
  }
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
.login_btn{
  width: 55px;
  height: 25px !important;
  font-size: x-small !important;
  background-color: darkseagreen;
  border-radius: 10px;
}
</style>