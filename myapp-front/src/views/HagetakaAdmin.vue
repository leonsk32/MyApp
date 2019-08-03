<template>
  <div>
    <b-form-group>
      <b-form-input placeholder="MAX VALUE" v-model="maxValue" :state="isValidMaxValue"></b-form-input>
      <b-form-input placeholder="MIN VALUE" v-model="minValue" :state="isValidMinValue"></b-form-input>
    </b-form-group>
    <b-button
      variant="primary"
    >Create New Round</b-button>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        maxValue: "",
        minValue: ""
      }
    },

    computed: {
      isValidMinValue() {
        return this.isValidValue(this.minValue)
      },
      isValidMaxValue() {
        return this.isValidValue(this.maxValue)
      },
    },

    methods: {
      isValidValue(value) {
        if (value === "") {
          return null
        }

        const pattern = /^\d*$/g
        return pattern.test(value)
      },
    }
  }
</script>
