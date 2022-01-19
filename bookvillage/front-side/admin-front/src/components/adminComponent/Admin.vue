<template>
  <div class="whole_section">
    <header>
      <div>
        <AdminNavBar/>
        <div class="header-section">
          <div>
            <a><router-link to='/admin/login'>📕 Book Village 📕</router-link></a>
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
  justify-content: center;
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

</style>