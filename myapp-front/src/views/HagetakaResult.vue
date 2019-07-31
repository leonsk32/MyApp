<template>
  <div>
    <b-table striped hover :items="lists"></b-table>
  </div>
</template>

<script>
export default {
  data() {
    return {
      lists: [],
      maxValue: null
    }
  },
  created: function() {
    const vue = this

    firebase
      .firestore()
      .collection("hagetaka")
      .onSnapshot(function(snapshot) {
        snapshot.docChanges().forEach(function(change) {
          if (change.type === "added") {
            const findAnswerByValue = value => {
              const answer = vue.lists.find((item) => item.value === value)
              return {
                exists: typeof answer !== "undefined",
                answer: answer
              }
            }

            let previousMaxAnswer = findAnswerByValue(vue.maxValue)
            if (previousMaxAnswer.exists) {
              previousMaxAnswer.answer._rowVariant = null
            }

            const answer = change.doc.data()

            let duplicatedAnswer = findAnswerByValue(answer.value)
            if (duplicatedAnswer.exists) {
              duplicatedAnswer.answer.names.push(answer.name)
              duplicatedAnswer.answer._rowVariant = 'danger'
            } else {
              vue.lists.push({
                names: [answer.name],
                value: answer.value
              })
            }
            
            vue.lists.sort((a, b) => b.value - a.value)

            let maxValidItem = vue.lists.find((item) => item.names.length === 1)
            if (typeof maxValidItem !== "undefined") {
              vue.maxValue = maxValidItem.value
              maxValidItem._rowVariant = 'primary'
            } else {
              vue.maxValue = null
            }
          
          }
        })
      })
  
  },
  computed: {

  }
};
</script>
