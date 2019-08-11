<template>
  <div>
    <b-card
      title="サインアップ"
      style="margin: 20px"
    >
      <b-form>
        <b-form-group>
          <b-form-input type="email" placeholder="email" v-model="signUpInfo.email"></b-form-input>
          <b-form-input type="password" placeholder="passwod" v-model="signUpInfo.password"></b-form-input>
        </b-form-group>
        <b-button
          v-on:click="signUp"
          variant="primary">
          ユーザ作成
        </b-button>
      </b-form>
    </b-card>
    <b-card
      title="ログイン"
      style="margin: 20px"
    >
      <b-form>
        <b-form-group>
          <b-form-input type="email" placeholder="email" v-model="loginInfo.email"></b-form-input>
          <b-form-input type="password" placeholder="password" v-model="loginInfo.password"></b-form-input>
        </b-form-group>
        <b-button
          v-on:click="login"
          variant="primary">
          ログイン
        </b-button>
      </b-form>
    </b-card>
  </div>
</template>

<script>
  import Firebase from "../js/Firebase";

  export default {
    data() {
      return {
        loginInfo: {
          email: "",
          password: ""
        },
        signUpInfo: {
          email: "",
          password: ""
        }
      }
    },

    methods: {
      signUp() {
        Firebase.createUser(this.signUpInfo.email, this.signUpInfo.password)
      },
      login() {
        Firebase.login(this.loginInfo.email, this.loginInfo.password);
      }
    }
  }

</script>
