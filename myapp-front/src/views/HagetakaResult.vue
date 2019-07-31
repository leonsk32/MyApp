<template>
  <div>
    <b-table striped hover :items="lists"></b-table>
  </div>
</template>

<script>
export default {
  data() {
    return {
      lists: []
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
            const answer = change.doc.data()
            let searched = vue.lists.find((item) => {
              return item.value === answer.value
            })

            if (typeof searched === "undefined") {
              vue.lists.push({
                names: [answer.name],
                value: answer.value
              })
            } else {
              searched.names.push(answer.name)
            }

            vue.lists.sort((a, b) => b.value - a.value)
          }
        })
      })
  
  },
  computed: {}
};
</script>
