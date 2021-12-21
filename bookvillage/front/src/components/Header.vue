<template>
  <div class="header-section">
    <a><router-link to='/'>📗 Book Village 📙</router-link></a>
  </div>
</template>

<script>
export default {
  name: "Header",
  data(){
    return {
      url : '',
    }
  },
  mounted() {
    this.getRole();
  },
  methods: {
    getRole(){
      const role = this.$store.getters.getRole;
      this.url = role + '/list/book';
    }
  }
}
</script>

<style scoped>
.header-section {
  height: 100px;
  background-color: #80CBC4;
  display: flex;
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
</style>