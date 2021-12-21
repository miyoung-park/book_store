<template>
  <div class="nav_section">
  <v-navigation-drawer
      style="background: #E0F2F1"
      permanent
  > <!--expand-on-hover-->
    <!-- 어떤 계정이 로그인 했느냐에 따라 정보 변경-->
    <v-list-item v-if="info.userId != ''">
      <v-list-item-content>
        <v-list-item-title class="text-h6">
          {{info.userId}}
        </v-list-item-title>
        <v-list-item-subtitle>
          {{info.userName}}
        </v-list-item-subtitle>
      </v-list-item-content>
      <button class="logout_btn" small @click="logout"> 로그아웃</button>
    </v-list-item>
    <v-divider v-if="info.user != ''"></v-divider>


    <!-- 어떤 계정이 로그인했느냐에 따라 메뉴 변경-->
    <v-list dense nav >
      <v-list-item
          v-for="item in items"
          :key="item.title"
          :to="item.url"
          link
      >
        <v-list-item-icon>
          <v-icon>{{ item.icon }}</v-icon>
        </v-list-item-icon>
        <v-list-item-content>
          <v-list-item-title>{{ item.title }}</v-list-item-title>
        </v-list-item-content>
      </v-list-item>
    </v-list>

  </v-navigation-drawer>
  </div>
</template>

<script>
export default {
  name: "AdminNavBar",
  data () {
    return {
      info: {
        userId: '',
        userName: ''
      },
      items: [
        { title: '고객관리', icon: 'mdi-account-circle', url:'/admin/list/customers' },
        { title: '도서관리', icon: 'mdi-book', url:'/admin/list/book' },
        { title: '대여관리', icon: 'mdi-book-multiple', url:'/admin/list/rentals' },
      ]
    }
  },
  async mounted() {
    await this.getDetailAdmin();
  },
  methods: {
    async getDetailAdmin(){
      await this.$axiosInst
                .post('/admin/detail')
                .then( response => {
                  this.info =  response.data;
                }).catch(()=>{
                 alert('다시 로그인 해주세요.');
                 this.$store.dispatch('logout');
                })
    },
    logout(){
      if(confirm('로그아웃 하시겠습니까?')){
        return this.$store.dispatch('logout');
      }
      return;
    }
  },
}
</script>

<style scoped>
.nav_section {
  height: 100%;
}
.logout_btn{
  width: 55px;
  height: 25px !important;
  font-size: x-small !important;
  background-color: darkseagreen;
  border-radius: 10px;
  font-family: 'Gowun Dodum', sans-serif;
}
</style>