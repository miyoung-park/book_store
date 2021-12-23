<template>
 <div class="whole_section">
     <header>
       <div>
         <BasicNavBar/>
         <div class="header-section">
           <a><router-link to='/'>📗 Book Village 📙</router-link></a>
         </div>
       </div>
     </header>
   <div class="main_section">
     <router-view/>
   </div>
 </div>
</template>

<script>
import BasicNavBar from './BasicNavBar';
export default {
  name: "Basic",
  components: {
    BasicNavBar
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