import { mount } from '@vue/test-utils'
import { expect } from 'chai'
import sinon from 'sinon'
import { PlayResults, Hands } from '@/js/constants';
import Play from '@/views/Play';
import RockScissorsPaper from '@/js/RockScissorsPaper';


describe('Play', () => {    
  let rockScissorsPaper
  let wrapper
  
  beforeEach(function(){
    rockScissorsPaper = new RockScissorsPaper()
    wrapper = mount(Play, {
      propsData: {
        rockScissorsPaper: rockScissorsPaper
      }
    })
  });

  it('spy example', () => {
    const spy = sinon.spy(rockScissorsPaper, 'play')

    trigger(wrapper, "rock", "rock");

    sinon.assert.calledWith(spy, Hands.rock, Hands.rock)
  })

  it('stub example', () => {
    sinon.stub(rockScissorsPaper, 'play').returns(PlayResults.draw)

    trigger(wrapper, "rock", "rock");

    const resultElement = wrapper.find("#result")
    expect(resultElement.text()).to.equal('result: Draw...')
  })

  it('renders draw', () => {
    const mock = sinon.mock(rockScissorsPaper)
                      .expects('play')
                      .withArgs(Hands.rock, Hands.rock)
                      .returns(PlayResults.draw)

    trigger(wrapper, "rock", "rock");

    mock.verify()
    const resultElement = wrapper.find("#result")
    expect(resultElement.text()).to.equal('result: Draw...')
  })

  it('renders p1 wins', () => {
    const mock = sinon.mock(rockScissorsPaper)
                      .expects('play')
                      .withArgs(Hands.paper, Hands.rock)
                      .returns(PlayResults.p1Wins)

    trigger(wrapper, "paper", "rock");

    mock.verify()
    const resultElement = wrapper.find("#result")
    expect(resultElement.text()).to.equal('result: Player 1 Wins!')
  })

  it('renders p2 wins', () => {
    const mock = sinon.mock(rockScissorsPaper)
                      .expects('play')
                      .withArgs(Hands.paper, Hands.scissors)
                      .returns(PlayResults.p2Wins)

    trigger(wrapper, "paper", "scissors");

    mock.verify()
    const resultElement = wrapper.find("#result")
    expect(resultElement.text()).to.equal('result: Player 2 Wins!')
  })
})

function trigger(wrapper, p1, p2) {
  const button1 = wrapper.find("#p1Buttons > #" + p1);
  const button2 = wrapper.find("#p2Buttons > #" + p2);
  button1.trigger('click');
  button2.trigger('click');
}

