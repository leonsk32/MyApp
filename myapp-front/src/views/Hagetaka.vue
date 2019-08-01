<template>
  <div class="bootstrap-sample">
    <b-form>
      <b-form-group label="入力してね">
        <b-form-input placeholder="name" v-model="name" :state="isValidName"></b-form-input>
        <b-form-input placeholder="0-999" v-model="value" :state="isValidValue"></b-form-input>
      </b-form-group>
      <b-button
        v-on:click="doAdd"
        v-bind:variant="(!isValidName || !isValidValue) ? false : 'primary'"
        v-bind:disabled="!isValidName || !isValidValue">
        送る
      </b-button>
    </b-form>
  </div>
</template>

<script>

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
      }
    },

    computed: {
      isValidName() {
        if (this.name === "") {
          return null
        }

        const pattern = /^[0-9a-zA-Z]*$/g
        return pattern.test(this.name)
      },
      isValidValue() {
        if (this.value === "") {
          return null
        }

        const pattern = /^\d*$/g
        if (!pattern.test(this.value)) return false;

        const number = Number(this.value);
        return number >= 0 && number <= 999
      },
      isValid() {
        return this.isValidName && this.isValidName
      }
    }
  }

</script>
