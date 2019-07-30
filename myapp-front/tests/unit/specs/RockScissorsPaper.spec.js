import { expect } from 'chai'
import { PlayResults, Hands } from '@/js/constants';
import RockScissorsPaper from '@/js/RockScissorsPaper';

describe('Play', () => {
  const play = new RockScissorsPaper();
  it('rock wins scissors', () => {
    expect(play.play(Hands.rock, Hands.scissors)).to.equal(PlayResults.p1Wins)
    expect(play.play(Hands.scissors, Hands.rock)).to.equal(PlayResults.p2Wins)
  })
  it('scissors wins paper', () => {
    expect(play.play(Hands.scissors, Hands.paper)).to.equal(PlayResults.p1Wins)
    expect(play.play(Hands.paper, Hands.scissors)).to.equal(PlayResults.p2Wins)
  })
  it('paper wins rock', () => {
    expect(play.play(Hands.paper, Hands.rock)).to.equal(PlayResults.p1Wins)
    expect(play.play(Hands.rock, Hands.paper)).to.equal(PlayResults.p2Wins)
  })
  it('draw', () => {
    expect(play.play(Hands.rock, Hands.rock)).to.equal(PlayResults.draw)
    expect(play.play(Hands.scissors, Hands.scissors)).to.equal(PlayResults.draw)
    expect(play.play(Hands.paper, Hands.paper)).to.equal(PlayResults.draw)
  })
})
