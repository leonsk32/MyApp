<template>
  <div>
    <p>player1: {{p1Hand}}</p>
    <input type="text" v-model="p1Hand">
    <br>
    <div id="p1Buttons">
      <button @click="rock" id="rock">rock</button>
      <button @click="scissors" id="scissors">scissors</button>
      <button @click="paper" id="paper">paper</button>
    </div>
    <p>player2: {{p2Hand}}</p>
    <input type="text" v-model="p2Hand">
    <br>
    <div id="p2Buttons">
      <button @click="rock" id="rock">rock</button>
      <button @click="scissors" id="scissors">scissors</button>
      <button @click="paper" id="paper">paper</button>
    </div>
    <p id="result">result: {{result}}</p>
  </div>
</template>

<script>
import { Hands, PlayResults } from "../js/constants"

export default {
  props: {
    rockScissorsPaper: Object
  },

  data() {
    return {
      p1Hand: Hands.rock,
      p2Hand: Hands.paper
    }
  },
  methods: {
    setHand(event, hand) {
      if (event.target.parentElement.id == "p1Buttons") {
        this.p1Hand = hand
      } else if (event.target.parentElement.id == "p2Buttons") {
        this.p2Hand = hand
      }
    },
    rock(event) {
      this.setHand(event, Hands.rock)
    },
    scissors(event) {
      this.setHand(event, Hands.scissors)
    },
    paper(event) {
      this.setHand(event, Hands.paper)
    }
  },
  computed: {
    result() {
      const result = this.rockScissorsPaper.play(this.p1Hand, this.p2Hand)
      if (result == PlayResults.p1Wins) {
        return "Player 1 Wins!"
      } else if (result == PlayResults.p2Wins) {
        return "Player 2 Wins!"
      } else if (result == PlayResults.draw) {
        return "Draw..."
      }
    },
    refHands() {
      return Hands
    }
  }
}
</script>
