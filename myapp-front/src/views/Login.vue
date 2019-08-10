<template>
  <b-form>
    <b-form-group label="サインアップ">
      <b-form-input type="email" placeholder="email" v-model="email"></b-form-input>
      <b-form-input type="password" placeholder="passwod" v-model="password"></b-form-input>
    </b-form-group>
    <b-button
      v-on:click="signup"
      variant="primary">
    ユーザ作成
    </b-button>
  </b-form>
</template>

<script>
  export default {
    data() {
      return {
        email: "",
        password: ""
      }
    },

    methods: {
      signup() {
        firebase.auth().createUserWithEmailAndPassword(this.email, this.password).catch(function(error) {
          // Handle Errors here.
          var errorCode = error.code
          var errorMessage = error.message
        })
      }
    }
  }

</script>
