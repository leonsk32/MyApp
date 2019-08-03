<template>
  <div class="bootstrap-sample">
    <b-form>
      <b-form-group label="入力してね">
        <b-form-input placeholder="name" v-model="name" :state="isValidName(name)"></b-form-input>
        <b-form-input placeholder="0-999" v-model="value" :state="isValidValue  (value)"></b-form-input>
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
          name: this.name,
          value: this.value,
          date: now
        })
      },

      isValidName(name) {
        if (name === "") return null
        return Validator.isAlphabeticalNumerical(name)
      },

      isValidValue(value) {
        if (value === "") return null
        if (!Validator.isNumeric(value)) return false;

        const number = Number(value);
        return number >= 0 && number <= 999
      }
    },

    computed: {
      isValid() {
        return this.isValidName(this.name) && this.isValidValue(this.value)
      }
    }
  }

</script>
