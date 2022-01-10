<template>
 <div class="whole_section">
     <header>
       <div>
         <CustomerNavBar/>
         <div class="header-section">
           <div class="button-section">
             <v-btn class="admin-btn" @click="goAdmin">관리자 페이지</v-btn>
             </div>
           <div>
             <a><router-link to='/'>📗 Book Village 📙</router-link></a>
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
import CustomerNavBar from './CustomerNavBar';
export default {
  name: "Customer",
  components: {
    CustomerNavBar
  },
  methods: {
    async goAdmin(){
      const path = this.$router.currentRoute.path;
      this.role =  this.$store.getters.getRole;
      // 유저 로그인 O --- 로그아웃 처리 후 관리자 페이지 이동
      if(this.role == 'customer'){
        if(confirm('관리자 페이지는 일반 계정 로그아웃 후 이용 가능합니다.\n로그아웃 후 관리자 페이지로 이동하시겠습니까 ?')){
          await this.$store.dispatch('logout');
          await this.$router.push({
            path: '/admin/login'
          })
        }
        return;
      }
      // 유저 로그인 X --- 현재 페이지가 /admin/login 이라면 새로고침
      path == '/admin/login' ? location.reload() : await this.$router.push({ path: '/admin/login' })
    },
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
  background-color: #80CBC4;
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
.button-section {
  width: 100%;
  height: 20%;
  display: flex;
  justify-content: flex-end;
  margin-right: 20px;
  margin-top: 10px;
}
.admin-btn{
  font-family: 'Gowun Dodum', sans-serif;
  font-size: x-small ;
  font-weight: bold ;
  background-color: #FFE082 !important;
}

</style>