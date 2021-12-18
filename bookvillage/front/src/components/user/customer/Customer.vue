<template>
  <div>
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

</style>