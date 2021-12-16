<template>
  <v-app>
    <header>
      <Header/>
    </header>
      <div class="whole_section">
        <NavBar/>
        <div class="main_section">
          <router-view></router-view>
        </div>
      </div>
  </v-app>
</template>
<script>
import Header from "@/components/Header";
import NavBar from "@/components/NavBar";
export default {
  name: 'App',
  components: {
    Header,
    NavBar
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
.main_section {
  width: 100%;
  margin-left: 10px;
}

</style>

