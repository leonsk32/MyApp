<template>
  <b-nav-item-dropdown
    v-bind:text="userStatus ? user.email : 'User'"
    right
  >
      <b-dropdown-item
        v-if="userStatus"
        @click="doLogout"
      >ログアウト</b-dropdown-item>
      <b-dropdown-item
        v-else
        @click="goToLogin"
      >
        ログインページへ
      </b-dropdown-item>
  </b-nav-item-dropdown>
</template>
<script>
  import Firebase from '../js/Firebase.js'

  export default {
    name: 'authentication',
    created: function() {
      Firebase.onAuth();
    },
    computed: {
      user() {
        return this.$store.getters.user
      },
      userStatus() {
        return this.$store.getters.isSignedIn
      }
    },
    methods: {
      doLogout() {
        Firebase.logout()
      },
      goToLogin() {
        this.$router.push("/login")
      }
    }
  };
</script>
<style scoped>
</style>
