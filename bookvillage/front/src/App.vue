<template>
  <v-app class="app">
    <router-view/>
    <Footer/>
  </v-app>
</template>

<script>
import Footer from "@/components/Footer";
export default {
  name: 'App',
  components: {
    Footer
  }
};
</script>
<style scoped>
.app {
  height: 100%;
}
</style>