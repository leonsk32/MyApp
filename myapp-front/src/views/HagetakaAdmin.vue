<template>
  <div>
    <b-form-group>
      <b-form-input placeholder="MAX VALUE" v-model="maxValue" :state="isValidValue(maxValue)"></b-form-input>
      <b-form-input placeholder="MIN VALUE" v-model="minValue" :state="isValidValue(minValue)"></b-form-input>
    </b-form-group>
    <b-button
      variant="primary"
    >Create New Round</b-button>
  </div>
</template>

<script>
  import Validator from "../js/Validator";

  export default {
    data() {
      return {
        maxValue: "",
        minValue: ""
      }
    },

    computed: {

    },

    methods: {
      isValidValue(value) {
        Validator.isNumeric(value)
      },
    }
  }
</script>
