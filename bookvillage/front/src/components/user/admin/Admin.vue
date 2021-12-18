<template>
  <div>
    <div class="whole_section">
      <AdminNavBar/>
      <div class="main_section">
        <router-view/>
      </div>
    </div>
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
div {
  width: 100%;
  height: 100%;
}
</style>