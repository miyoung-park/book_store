<template>
 <div class="whole_section">
     <header>
       <div>
         <UserNavBar/>
         <div class="header-section">
           <div>
             <a><router-link to='/'>📗 Book Village 📙</router-link></a>
           </div>
         </div>

       </div>
     </header>
   <div class="main_section">
     <router-view/>
   </div>
 </div>
</template>

<script>
import UserNavBar from './UserNavBar';
export default {
  name: "User",
  components: {
    UserNavBar
  },
  methods: {
  }
}
</script>

<style scoped>
.whole_section {
  height: 100%;
}
.main_section {
  width: 100%;
  height: 100%;
  display: flex;
  font-family: 'Gowun Dodum', sans-serif;
}
.header-section {
  height: 150px;
  background-color: #80CBC4;
  display: flex;
  align-items: center;
  justify-content: center;
  font-family: 'Merriweather', serif !important;
}
.header-section a {
  color: black;
  font-size: 2.7vw;
  font-weight: bold;
  text-decoration:none;
}
</style>