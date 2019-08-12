<template>
  <div id="nav">
    <b-navbar toggleable="lg" variant="info">
      <b-navbar-brand href="#">MyApp</b-navbar-brand>

      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav>
          <b-nav-item>
            <router-link to="/">Home</router-link>
          </b-nav-item>
          <b-nav-item v-if="$store.getters.isSignedIn">
            <router-link to="/hagetaka-admin">HagetakaAdmin</router-link>
          </b-nav-item>
        </b-navbar-nav>

        <!-- Right aligned nav items -->
        <b-navbar-nav class="ml-auto">
          <Authentication />
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
  </div>
</template>
<script>
  import Authentication from "../components/Authentication.vue";
  export default {
    name: "navigation",
    components: {
      Authentication
    }
  }
</script>
<style scoped>
  #nav a {
    font-weight: bold;
    color: white;
  }
  #nav a.router-link-exact-active {
    color: black;
  }
</style>
