<template>
  <div class="nav_section">
  <v-navigation-drawer
      style="background: #E0F2F1"
      permanent
      expand-on-hover
  > <!--expand-on-hover-->
    <!-- 어떤 계정이 로그인 했느냐에 따라 정보 변경-->
    <v-list-item v-if="info.user != ''">
      <v-list-item-content>
        <v-list-item-title class="text-h6">
          {{info.userId}}
        </v-list-item-title>
        <v-list-item-subtitle>
          {{info.userName}}
        </v-list-item-subtitle>
      </v-list-item-content>
      <button class="logout_btn" small> 로그아웃</button>
    </v-list-item>
    <v-divider v-if="info.user != ''"></v-divider>


    <!-- 어떤 계정이 로그인했느냐에 따라 메뉴 변경-->
    <v-list shaped v-if="info.user != ''">
      <v-list-item-group
          color="primary"
      >
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
  data () {
    return {
      info: {
              userId : 'id'
            , userName : 'name'
      },
      items: [
        { title: '프로필', icon: 'mdi-account-settings' , url:'/customer/detail'},
        { title: '포인트', icon: 'mdi-credit-card-multiple' , url:'/customer/point/list'},
        { title: '대여목록', icon: 'mdi-book-multiple', url:'/customer/rental/list'},
        { title: '도서목록', icon: 'mdi-book', url:'/'}
      ]
    }
  },
  methods: {

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
</style>