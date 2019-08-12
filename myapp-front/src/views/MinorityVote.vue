<template>
  <div>
    <b-card
      title="投票"
      style="margin: 20px"
    >
      <b-form>
        <b-form-group>
          <b-form-radio-group
            id="btn-radios-2"
            v-model="selected"
            :options="options"
            buttons
            button-variant="outline-secondary"
            stacked
            size="lg"
          ></b-form-radio-group>
        </b-form-group>
        <b-button
          v-on:click="submitVote"
          variant="primary"
        >
          投票
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
        selected: 'yes',
        options: [
          { text: 'YES', value: 'yes' },
          { text: 'NO', value: 'no' },
        ],
        userName: "hoge"
      }
    },

    methods: {
      submitVote() {
        Firebase.submitMinorityVote({
          round: 1,
          value: true,
          userName: this.userName
        })
      }
    },

    computed: {
    }
  }

</script>
<style scoped>
</style>
