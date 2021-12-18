<template>
  <v-app>
    <header>
      <Header/>
    </header>
    <div class="whole_section">
        <router-view/>
    </div>
    <footer>
      <Footer/>
    </footer>
  </v-app>
</template>
<script>
import Header from "@/components/Header";
import Footer from "@/components/Footer";
export default {
  name: 'App',
  components: {
    Header,
    Footer
  }
};
</script>
<style scoped>
.whole_section {
  width: 100%;
  height: 100%;
  display: flex;
  font-family: 'Gowun Dodum', sans-serif;
}
</style>