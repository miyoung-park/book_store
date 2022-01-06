<template>
  <div class="whole_section">
    <header>
      <div>
        <AdminNavBar/>
        <div class="header-section">
          <div class="button-section">
            <v-btn class="customer-btn" @click="goCustomer">일반 페이지</v-btn>
          </div>
          <div>
            <a><router-link to='/admin/book/list'>📕 Book Village 📕</router-link></a>
            <p>[ Admin Page ]</p>
          </div>
        </div>
      </div>
    </header>
    <div class="main_section">
      <router-view/>
    </div>
  </div>
</template>

<script>
import AdminNavBar from './AdminNavBar'
export default {
  name: "Admin",
  components: {
    AdminNavBar
  },
  data(){
    return{
      isAdmin : null
    }
  },
  methods: {
    /*

    goHomepage(){
      const path = window.location.pathname;
      const status = path.split("/")[1];
      if( status == 'admin' ){
        if(this.$route.path !== '/admin/book/list'){
          return this.$router.push("/admin")
        }
       return;
      }
      return this.$router.push("/")
    },

    */
    goCustomer(){
      this.role = this.$store.getters.getRole;
      // 현재 유저로 로그인 되어있는지 파악 후 로그아웃 처리 후 리스트 이동
      if(this.role == 'admin'){
        if(confirm('일반 페이지는 관리자 계정 로그아웃 후 이용 가능합니다.\n로그아웃 후 일반 페이지로 이동하시겠습니까 ?')){
          this.$store.dispatch('logout');
          this.$router.push({
            path: '/'
          })
        }
        return;
      }
      this.$router.push({
        path: '/'
      })
    }
  }
}
</script>

<style scoped>
.whole_section {
  height: 100%;
}
.main_section {
  width: 100%;
  height: 100%;
  display: flex;
  font-family: 'Gowun Dodum', sans-serif;
}
.header-section {
  height: 150px;
  background-color: beige;
  display: flex;
  flex-direction: column;
  align-items: center;
  font-family: 'Merriweather', serif;
}
.header-section a {
  color: black;
  font-size: 2.7vw;
  font-weight: bold;
  text-decoration:none;

}
.header-section p {
  text-decoration: none;
  text-align: center;
}
nav {
  height: 100%;
}
.button-section {
  width: 100%;
  height: 20%;
  display: flex;
  justify-content: flex-end;
  margin-right: 20px;
  margin-top: 10px;
}
.customer-btn{
  font-family: 'Gowun Dodum', sans-serif;
  font-size: x-small ;
  font-weight: bold ;
  background-color: #CFD8DC !important;
}
</style>