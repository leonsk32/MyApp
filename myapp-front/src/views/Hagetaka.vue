<template>
  <div class="bootstrap-sample">
    <h1>{{ $route.params.id }}</h1>
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
        name: "",
        value: ""
      }
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
      }
    }
  }

</script>
