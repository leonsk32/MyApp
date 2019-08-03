<template>
  <div class="bootstrap-sample">
    <div v-if="existsRound">
      <h1>[Round Name] {{ round.roundName }}</h1>
      <h4>[Round Id] {{ $route.params.id }}</h4>
    </div>
    <div v-else>
      <b-alert variant="danger" show>
        <h1>{{error}}</h1>
      </b-alert>
    </div>
    <b-form>
      <b-form-group label="入力してね">
        <b-form-input placeholder="name" v-model="name" :state="isValidName"></b-form-input>
        <b-form-input placeholder="0-999" v-model="value" :state="isValidValue"></b-form-input>
      </b-form-group>
      <b-button
        v-on:click="doAdd"
        v-bind:variant="isValid ? 'primary' : 'false'"
        v-bind:disabled="!isValid">
        送る
      </b-button>
    </b-form>
  </div>
</template>

<script>

  import Validator from "../js/Validator";

  export default {
    data() {
      return {
        round: null,
        name: "",
        value: "",
        error: ""
      }
    },

    created: function() {
      const docRef = firebase
        .firestore()
        .collection("hagetaka-rounds")
        .doc(this.$route.params.id)

      const vue = this

      docRef.get().then(function(doc) {
        if (doc.exists) {
          vue.round = doc.data()
        } else {
          vue.round = null
          vue.error = "No round found!"
        }
      }).catch(function(error) {
        vue.round = null
        vue.error = "Error getting round info: " + error
      })
    },

    methods: {
      doAdd() {
        const now = new Date();
        firebase.firestore().collection('hagetaka').add({
          roundId: this.$route.params.id,
          name: this.name,
          value: Number(this.value),
          date: now
        })
      }
    },

    computed: {
      isValidName() {
        if (this.name === "") return null
        return Validator.isAlphabeticalNumerical(this.name)
      },

      isValidValue() {
        if (this.value === "") return null
        if (!Validator.isNumeric(this.value)) return false;

        const number = Number(this.value);
        return number >= 0 && number <= 999
      },

      isValid() {
        return this.isValidName && this.isValidValue
      },

      existsRound() {
        return this.round !== null
      }
    }
  }

</script>
