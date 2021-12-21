<template>
  <div class="main_section">
    <CustomerNavBar/>
    <router-view/>
  </div>
</template>

<script>
import CustomerNavBar from './CustomerNavBar';
export default {
  name: "Customer",
  components: {
    CustomerNavBar
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