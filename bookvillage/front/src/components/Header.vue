<template>
  <div class="header-section">
    <a><router-link to="/list/book">📗 Book Village 📙</router-link></a>
  </div>
</template>

<script>
export default {
  name: "Header"
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