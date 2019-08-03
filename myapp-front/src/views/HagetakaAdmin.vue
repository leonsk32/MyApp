<template>
  <div>
    <b-form-group>
      <b-form-input placeholder="MIN VALUE" v-model="minValue" :state="isValidMinValue"></b-form-input>
      <b-form-input placeholder="MAX VALUE" v-model="maxValue" :state="isValidMaxValue"></b-form-input>
    </b-form-group>
    <b-button
      v-on:click="create"
      v-bind:disabled="!isValid"
      v-bind:variant="isValid ? 'primary' : 'false'"
    >Create New Round</b-button>
  </div>
</template>

<script>
  import Validator from "../js/Validator";

  export default {
    data() {
      return {
        maxValue: "",
        minValue: "0"
      }
    },

    methods: {
      isValidValue(value) {
        if (value === "") return null
        return Validator.isNumeric(value)
      },

    },

    computed: {
      isValid() {
        return this.isValidMinValue && this.isValidMaxValue
      },
      isValidMinValue() {
        return this.isValidValue(this.minValue)
      },
      isValidMaxValue() {
        const status = this.isValidValue(this.maxValue);
        if (status !== true) return status
        if (!this.isValidMinValue) return true
        return Number(this.minValue) <= Number(this.maxValue)
      }
    }
  }
</script>
