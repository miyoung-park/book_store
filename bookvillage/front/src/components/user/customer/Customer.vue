<template>
 <div>
   <header>
     <div class="header-section">
       <a><router-link to='/list/book'>📗 Book Village 📙</router-link></a>
     </div>
   </header>
   <div class="main_section">
     <CustomerNavBar/>
     <router-view/>
   </div>
   <footer>
     <Footer/>
   </footer>
 </div>
</template>

<script>
import CustomerNavBar from './CustomerNavBar';
import Footer from "@/components/Footer";
export default {
  name: "Customer",
  components: {
    CustomerNavBar,
    Footer
  }
}
</script>

<style scoped>
.main_section {
  width: 100%;
  height: 100%;
  display: flex;
}
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