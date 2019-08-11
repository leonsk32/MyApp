<template>
  <div>
    <b-navbar toggleable="lg" variant="info">
      <b-navbar-brand href="#">MyApp</b-navbar-brand>

      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav>
          <b-nav-item>
            <router-link to="/">Home</router-link>
          </b-nav-item>
          <b-nav-item>
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
  import Authentication from "@/components/Authentication.vue";
  export default {
    name: "navigation",
    components: {
      Authentication
    }
  }
</script>
<style scoped>
</style>
