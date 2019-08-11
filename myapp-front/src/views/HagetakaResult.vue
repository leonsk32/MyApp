<template>
  <div>
    <HagetakaRoundInfo
      v-bind:roundId="$route.params.id"
      v-bind:roundName="round.roundName"
    />
    <b-card title="Top Player" style="margin: 20px">
      <div v-if="existsTop">
        <h1>⭐ {{topAnswer.names[0]}} ⭐</h1>
        <b-badge>value</b-badge>
        {{topAnswer.value}}
      </div>
      <div v-else>
        <h1>No Top Player...</h1>
      </div>
    </b-card>
    <b-table hover :items="results"></b-table>
  </div>
</template>

<script>
  import Firebase from '../js/Firebase.js'
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
        answers: [],
        topAnswer: null
      }
    },
    created: function () {

      Firebase.getHagetakaRoundInfo(
        this.$route.params.id,
        data => this.round = data
      )

      const vue = this
      Firebase.onHagetakaSubmitted(
        this.$route.params.id,
        function (data) {
          let sameValueAnswer = vue.answers.find(answer => answer.value === data.value)
          if (typeof sameValueAnswer !== 'undefined') {
            sameValueAnswer.names.push(data.name)
          } else {
            vue.answers.push({
              names: [data.name],
              value: data.value
            })
          }

          vue.answers.sort((a, b) => b.value - a.value)
          vue.topAnswer = vue.answers.find((answer) => answer.names.length === 1)
        }
      )
    },
    computed: {
      results() {
        return this.answers.map(answer => {
          let status = null
          if (answer === this.topAnswer) {
            status = 'primary'
          } else if (answer.names.length > 1) {
            status = 'secondary'
          }

          return {
            player: answer.names.join(', '),
            value: answer.value,
            _rowVariant: status
          }
        })
      },
      existsTop() {
        return (typeof this.topAnswer !== 'undefined' && this.topAnswer !== null)
      },
    }
  }
</script>
