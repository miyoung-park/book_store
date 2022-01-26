<template>
  <div class="nav_section">
  <v-navigation-drawer
      style="background: antiquewhite"
      permanent
      expand-on-hover
  > <!--expand-on-hover-->

    <!-- profile section -->
    <v-list-item v-if="isAdminLogin">
      <v-list-item-content>
        <v-list-item-title class="text-h6">
          {{info.userId}}
        </v-list-item-title>
        <v-list-item-subtitle>
          {{info.userName}}
        </v-list-item-subtitle>
      </v-list-item-content>
      <button class="logout_btn" small @click="logout" v-if="getToken != null"> 로그아웃</button>
    </v-list-item>

    <v-divider></v-divider>

    <!-- menu section admin -->
    <v-list v-if="getToken != null">
      <v-list-group
          v-for="item in adminItems"
          :key="item.title"
          v-model="item.active"
          :prepend-icon="item.icon"
          no-action

      >
        <template v-slot:activator>
          <v-list-item-content>
            <v-list-item-title v-text="item.title"></v-list-item-title>
          </v-list-item-content>
        </template>

        <v-list-item
            v-for="child in item.items"
            :key="child.title"
            :to="child.url"
        >
          <v-list-item-content>
            <v-list-item-title v-text="child.title"></v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list-group>
    </v-list>
    <!-- menu section none admin -->
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
import AlertModal from "@/components/AlertModal";
export default {
  name: "AdminNavBar",
  components: { AlertModal },
  inject: ['adminService'],
  data () {
    return {
      info: {},
      token: null,
      items: [
        { title: '로그인', icon: 'mdi-account' , url:'/admin/login'},
      ],
      adminItems: [
        {   title: '고객관리'
          , icon: 'mdi-account-circle'
          , items: [
                    { title: '고객목록', url: '/admin/customer/list'},
                    { title: '고객등록', url: '/admin/customer/add'}
                    ]
        },
        {   title: '도서관리'
          , icon: 'mdi-book'
          , items: [
            { title: '도서목록', url: '/admin/book/list'},
            { title: '도서등록', url: '/admin/book/add'}
          ]
        },
        {   title: '대여관리'
          , icon: 'mdi-book-multiple'
          , items: [
            { title: '대여목록', url: '/admin/rental/list'}
          ]
        },
      ],
      alertModal: false
    }
  },
  methods: {
    async isAdminLogin() {
      this.info.userId = this.$store.getters.getUserId;
      this.info.userName = this.$store.getters.getUserName;
      console.log(this.info)
    },
    logout(){
      if(confirm('로그아웃 하시겠습니까?')){
        this.$store.dispatch('logout');
        this.$router.push("/");
      }
      return;
    },
    $apiErrorHandler(apiServiceError){
      const errorMessage = apiServiceError.errorMessage;
     // 토큰 관련 문제이기 때문에 alert 후에 로그아웃 처리
      alert(errorMessage);
      this.$store.commit('logout');

    },
  },
  async mounted() {
    await this.isAdminLogin();
  },
  computed: {
    getToken(){
      this.isAdminLogin();
      return this.$store.getters.getToken;
    }
  },
  created() {
    // TODO: 공통되는 에러로직은 전역에서 처리 //  alert는 띄우는게 기본 정책이라면 공통에 가있어야 한다.
    this.$addApiErrorHandler('610', this.$apiErrorHandler, false );
    this.$addApiErrorHandler('620', this.$apiErrorHandler, false );
    this.$addApiErrorHandler('630', this.$apiErrorHandler, false );
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
  background-color: rosybrown;
  border-radius: 10px;
  font-family: 'Gowun Dodum', sans-serif;
}
</style>