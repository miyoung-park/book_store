<template>
  <div class="whole_section">
    <header>
      <div>
        <AdminNavBar/>
        <div class="header-section">
          <a @click="goHomepage">📗 Book Village 📙</a>
          <p>[ Admin Page ]</p>
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
  methods: {
    goHomepage(){
      const path = window.location.pathname;
      const status = path.split("/")[1];
      console.log(status)
      if( status == 'admin' ){
        if(this.$route.path !== '/admin/book/list'){
          return this.$router.push("/admin")
        }
       return;
      }
      return this.$router.push("/")
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
  background-color: #80CBC4;
  display: flex;
  flex-direction: column;
  justify-content: center;
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
}
nav {
  height: 100%;
}
</style>