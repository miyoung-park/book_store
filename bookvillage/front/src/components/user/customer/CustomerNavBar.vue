<template>
  <div class="nav_section">
  <v-navigation-drawer
      style="background: #E0F2F1"
      permanent
  > <!--expand-on-hover-->
    <!-- 어떤 계정이 로그인 했느냐에 따라 정보 변경-->
    <v-list-item v-if="info.user != ''">
      <v-list-item-content>
        <v-list-item-title class="text-h6">
          {{info.name}}
        </v-list-item-title>
        <v-list-item-subtitle>
          {{info.user}}
        </v-list-item-subtitle>
      </v-list-item-content>
      <button class="logout_btn" small> 로그아웃</button>
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

    <v-divider></v-divider>

    <!-- default 로 가져가는 도서 검색 / 대여 파트-->
    <v-list dense nav>
      <v-list-item
          v-for="defaultItem in defaultItems"
          :key="defaultItem.title"
          :to="defaultItem.url"
          link
      >
        <v-list-item-icon>
          <v-icon>{{ defaultItem.icon }}</v-icon>
        </v-list-item-icon>
        <v-list-item-content>
          <v-list-item-title>{{ defaultItem.title }}</v-list-item-title>
        </v-list-item-content>
      </v-list-item>
    </v-list>
  </v-navigation-drawer>
  </div>
</template>

<script>
export default {
  name: "CustomerNavBar",
  data () {
    return {
      info: {
              name : 'miyoung'
            , user : 'aldud1529'
      },
      defaultItems: [
        { title: '도서검색', icon: 'mdi-magnify', url: '/' },
      ],
      items: [
        { title: '로그인', icon: 'mdi-account-circle', url: '/login' },
        // test
        { title: '프로필', icon: 'mdi-account-settings' , url:'/customer/detail'},
        { title: '포인트관리', icon: 'mdi-credit-card-multiple' , url:'/customer/list/point'},
        { title: '대여목록', icon: 'mdi-book-multiple', url:'/customer/list/rental'}
      ]
    }
  },
  methods: {
    adminLogin(){
      this.items =  [
        { title: '고객관리', icon: 'mdi-account-circle', url:'/admin/list/customers' },
        { title: '도서관리', icon: 'mdi-book', url:'/admin/list/books' },
        { title: '대여관리', icon: 'mdi-book-multiple', url:'/admin/list/rentals' },
                    ]
    },
    customerLogin(){
      this.items =  [
        { title: '프로필', icon: 'mdi-account-settings' , url:'/customer/detail'},
        { title: '포인트관리', icon: 'mdi-credit-card-multiple' , url:'/customer/point'},
        { title: '대여목록', icon: 'mdi-book-multiple', url:'/customer/list/rental'}
      ]
    },
  }
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