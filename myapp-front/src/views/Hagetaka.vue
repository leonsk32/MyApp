<template>
  <div>
    <HagetakaRoundInfo
      v-bind:roundId="$route.params.id"
      v-bind:roundName="round.roundName"
    />
    <b-card
      title="投票"
      style="margin: 20px"
    >
      <b-form>
        <b-form-group
          label="ユーザ名"
          label-align="left"
          label-for="input-name"
        >
          <b-form-input
            id="input-name"
            placeholder="Enter name"
            v-model="name"
            type="text"
            :state="isValidName"></b-form-input>
          <b-form-invalid-feedback
            :state="isValidName"
            text-align="left"
          >
            半角英数のみ使用してください
          </b-form-invalid-feedback>
        </b-form-group>

        <b-form-group
          label="数値"
          label-align="left"
          label-for="input-value"
          description="0-999"
        >
          <b-form-input
            id="input-value"
            placeholder="0"
            v-model="value"
            type="number"
            :state="isValidValue"></b-form-input>
          <b-form-invalid-feedback
            :state="isValidValue"
            text-align="left"
          >
            0-999の数値を入力してください
          </b-form-invalid-feedback>
          <b-form-text>

          </b-form-text>
        </b-form-group>

        <b-button
          v-on:click="doAdd"
          v-bind:variant="isValid ? 'primary' : 'false'"
          v-bind:disabled="!isValid">
          送る
        </b-button>
      </b-form>
    </b-card>
  </div>
</template>

<script>
  import Firebase from "../js/Firebase";
  import Validator from "../js/Validator";
  import HagetakaRoundInfo from "../components/HagetakaRoundInfo";

  export default {
    components: {
      HagetakaRoundInfo
    },
    data() {
      return {
        round: {
          roundName: null,
          minValue: null,
          maxValue: null,
          date: null
        },
        name: "",
        value: ""
      }
    },

    created: function () {

      Firebase.getHagetakaRoundInfo(
        this.$route.params.id,
        data => this.round = data
      )
    },

    methods: {
      doAdd() {
        Firebase.submitHagetakaAnswer({
          roundId: this.$route.params.id,
          name: this.name,
          value: Number(this.value),
          date: new Date()
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
<style scoped>
</style>
