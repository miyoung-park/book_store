<template>
  <div class="content">
    <div class="search_section">
      <v-card-title>
        <v-text-field
            v-model="search"
            append-icon="mdi-magnify"
            label="Search"
            single-line
            hide-details
        ></v-text-field>
      </v-card-title>
    </div>
    <div class="table_section">
      <v-data-table
          :headers="headers"
          :items="customers"
          :search="search"
          @click:row="goDetail"
          :items-per-page="5"
          style="height: 85%"
      >
      </v-data-table>
    </div>
  </div>
</template>

<script>

export default {
  name: "ListCustomer",
  inject: ['customerService'],
  data () {
    return {
      search: '',
      headers: [
        { text: '고객번호', value: 'userSeq', align: 'start'},
        { text: '고객아이디', value: 'userId' },
        { text: '고객명', value: 'userName'},
        { text: '생년월일', value: 'userBirth'},
        { text: '전화번호', value: 'userTell' },
        { text: '등록일자', value: 'userRegDt' },
        { text: '수정일자', value: 'userUpdateDt' },
      ],
      customers: [],
    }
  },
  async mounted() {
    await this.getList();
  },
  methods: {
    goDetail(customer){
      const _userSeq = customer.userSeq
      this.$router.push({
        path: '/admin/customer/detail/'+ _userSeq
      });
    },
    async getList(){
     const response = await this.customerService.getCustomerList();
     this.customers = response;
    }
  }
}
</script>

<style scoped>
.content{
  width: 100%;
  display: flex;
  flex-direction: column;
  justify-content: space-around;
  align-items: center;
}
.search_section {
  width: 50%;
}
.table_section {
  width: 80%;
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
}
</style>