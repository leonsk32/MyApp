<template>
  <b-form>
    <b-form-group label="サインアップ">
      <b-form-input type="email" placeholder="email" v-model="signUpInfo.email"></b-form-input>
      <b-form-input type="password" placeholder="passwod" v-model="signUpInfo.password"></b-form-input>
    </b-form-group>
    <b-button
      v-on:click="signUp"
      variant="primary">
      ユーザ作成
    </b-button>
    <b-form-group label="サインイン">
      <b-form-input type="email" placeholder="email" v-model="signInInfo.email"></b-form-input>
      <b-form-input type="password" placeholder="passwod" v-model="signInInfo.password"></b-form-input>
    </b-form-group>
    <b-button
      v-on:click="signIn"
      variant="primary">
      サインイン
    </b-button>
  </b-form>
</template>

<script>
  export default {
    data() {
      return {
        signInInfo: {
          email: "",
          password: ""
        },
        signUpInfo: {
          email: "",
          password: ""
        }
      }
    },

    created: function () {
      // firebase.auth().onAuthStateChanged(function(user) {
      //   if (user) {
      //     console.log("sign in")
      //     // User is signed in.
      //     console.log(user)
      //     var displayName = user.displayName;
      //     var email = user.email;
      //     var emailVerified = user.emailVerified;
      //     var photoURL = user.photoURL;
      //     var isAnonymous = user.isAnonymous;
      //     var uid = user.uid;
      //     var providerData = user.providerData;
      //     // ...
      //   } else {
      //     console.log("sign out")
      //     // User is signed out.
      //     // ...
      //   }
      // })
    },

    methods: {
      signUp() {
        firebase.auth().createUserWithEmailAndPassword(this.signUpInfo.email, this.signUpInfo.password).catch(function(error) {
          // Handle Errors here.
          var errorCode = error.code
          var errorMessage = error.message
        })
      },
      signIn() {
        console.log("signIn button pressed")
        firebase.auth().signInWithEmailAndPassword(this.signInInfo.email, this.signInInfo.password).catch(function(error) {
          // Handle Errors here.
          console.log("error")
          var errorCode = error.code;
          var errorMessage = error.message;
          // ...
        })
      }
    }
  }

</script>
