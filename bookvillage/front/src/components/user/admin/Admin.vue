<template>
  <div>
    <header>
      <div class="header-section">
        <a><router-link to='/admin/book/list'>📗 Book Village📙</router-link></a>
        <p> Admin Page </p>
      </div>
    </header>
    <div class="main_section">
      <AdminNavBar/>
      <router-view/>
    </div>
    <footer>
      <Footer/>
    </footer>
  </div>
</template>

<script>
import AdminNavBar from './AdminNavBar'
import Footer from "@/components/Footer";
export default {
  name: "Admin",
  components: {
    AdminNavBar,
    Footer
  }
}
</script>

<style scoped>
.main_section {
  width: 100%;
  height: 100%;
  display: flex;
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
</style>