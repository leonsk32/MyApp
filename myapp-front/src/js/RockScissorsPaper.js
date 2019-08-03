import {Hands, PlayResults} from './constants'

export default class RockScissorsPaper {
  constructor() {
  }

  play(p1Hand, p2Hand) {
    if (p1Hand === p2Hand) {
      return PlayResults.draw
    }

    if ((p1Hand === Hands.rock && p2Hand === Hands.scissors) ||
      (p1Hand === Hands.scissors && p2Hand === Hands.paper) ||
      (p1Hand === Hands.paper && p2Hand === Hands.rock)) {
      return PlayResults.p1Wins
    } else {
      return PlayResults.p2Wins
    }
  }
}
