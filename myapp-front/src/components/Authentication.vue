<template>
  <div class="authentication">
    <div v-if="userStatus" class="navbar-item">
      <p class="navbar-item">{{ user.email }}</p>
      <b-button
        @click="doLogout"
        variant="danger">
        ログアウト
      </b-button>
    </div>
    <div v-else>
        <router-link to="/login">Go To Login Page</router-link><br>
    </div>
  </div>
</template>
<script>
  import Firebase from '@/js/Firebase.js';

  export default {
    name: 'authentication',
    created: function() {
      Firebase.onAuth();
    },
    computed: {
      user() {
        return this.$store.getters.user;
      },
      userStatus() {
        return this.$store.getters.isSignedIn;
      }
    },
    methods: {
      doLogout() {
        Firebase.logout();
      }
    }
  };
</script>
<style scoped>
</style>
