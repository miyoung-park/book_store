<template>
  <v-footer padless class="footer">
    <v-col
        class="text-center"
        cols="12"
    >
      {{ new Date().getFullYear() }} — <strong>Book Village</strong>
    </v-col>
  </v-footer>
</template>

<script>
export default {
  name: "Footer"
}
</script>

<style scoped>
.footer{
  background-color: #CFD8DC;
}
</style>