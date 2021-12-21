<template>
  <div class="main_section">
    <AdminNavBar/>
    <router-view/>
  </div>
</template>

<script>
import AdminNavBar from './AdminNavBar'
export default {
  name: "Admin",
  components: {
    AdminNavBar
  }
}
</script>

<style scoped>
.main_section {
  width: 100%;
  height: 100%;
  display: flex;
}
</style>