<template>
  <div>
    <HagetakaRoundInfo
      v-bind:roundId="$route.params.id"
      v-bind:roundName="round.roundName"
    />
    <b-alert variant="primary" show v-if="existsTop">
      <h1>Top Player: {{topAnswer.names[0]}} !!! ({{topAnswer.value}})</h1>
      <router-link v-bind:to="'/hagetaka/rounds/' + $route.params.id">Back To Game Page</router-link>
      <br>
    </b-alert>
    <b-alert variant="secondary" show v-else>
      <h1>no top player...</h1>
    </b-alert>
    <b-table hover :items="results"></b-table>
  </div>
</template>

<script>
  import firebase from 'firebase/app';
  import 'firebase/firestore';
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
      const vue = this

      const docRef = firebase
        .firestore()
        .collection("hagetaka-rounds")
        .doc(this.$route.params.id)

      docRef.get().then(function (doc) {
        if (doc.exists) {
          vue.round = doc.data()
        } else {
          vue.round = null
          vue.error = "No round found!"
        }
      }).catch(function (error) {
        vue.round = null
        vue.error = "Error getting round info: " + error
      })

      firebase
        .firestore()
        .collection("hagetaka")
        .where("roundId", "==", this.$route.params.id)
        .onSnapshot(function (snapshot) {
          snapshot.docChanges().forEach(function (change) {
            if (change.type === "added") {
              const answer = change.doc.data()

              let sameValueAnswer = vue.answers.find(item => item.value === answer.value)
              if (typeof sameValueAnswer !== 'undefined') {
                sameValueAnswer.names.push(answer.name)
              } else {
                vue.answers.push({
                  names: [answer.name],
                  value: answer.value
                })
              }

              vue.answers.sort((a, b) => b.value - a.value)
              vue.topAnswer = vue.answers.find((item) => item.names.length === 1)
            }
          })
        })

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
