<template>
  <div>
    <b-form>
      <b-form-group label="ラウンド名" label-for="round-name-input" label-align="left">
        <b-form-input
          id="round-name-input"
          placeholder="ROUND NAME"
          v-model="roundName"
          :state="isValidRoundName"></b-form-input>
      </b-form-group>
      <b-form-group label="入力の最小値" label-for="min-value-input" label-align="left">
        <b-form-input
          id="min-value-input"
          placeholder="MIN VALUE"
          v-model="minValue"
          :state="isValidMinValue"></b-form-input>
      </b-form-group>
      <b-form-group label="入力の最大値" label-for="max-value-input" label-align="left">
        <b-form-input
          id="max-value-input"
          placeholder="MAX VALUE"
          v-model="maxValue"
          :state="isValidMaxValue"></b-form-input>
      </b-form-group>
      <b-button
        v-on:click="create"
        v-bind:disabled="!isValid"
        v-bind:variant="isValid ? 'primary' : 'false'"
      >Create New Round
      </b-button>
      <b-alert variant="success" v-bind:show="created">
        Created Round ID: {{createdRoundId}}<br>
        <router-link v-bind:to="'/hagetaka/rounds/' + createdRoundId">Go To Game</router-link><br>
      </b-alert>
    </b-form>
  </div>
</template>

<script>
  import Validator from "../js/Validator";

  export default {
    data() {
      return {
        maxValue: "",
        minValue: "0",
        roundName: "",
        createdRoundId: null
      }
    },

    methods: {
      isValidValue(value) {
        if (value === "") return null
        return Validator.isNumeric(value)
      },
      create() {
        const vue = this
        const now = new Date();
        firebase.firestore().collection('hagetaka-rounds').add({
          roundName: this.roundName,
          minValue: this.minValue,
          maxValue: this.maxValue,
          date: now
        }).then(function (docRef) {
          vue.createdRoundId = docRef.id
        })
      }
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
      },
      isValidRoundName() {
        if (this.roundName === "") return null
        return true
      },
      created() {
        return this.createdRoundId !== null
      }
    }
  }
</script>
