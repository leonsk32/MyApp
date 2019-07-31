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
  created: function(){
    var vue = this;

    firebase.firestore().collection('hagetaka').onSnapshot(function(snapshot) {
        snapshot.docChanges().forEach(function(change) {
            if (change.type === 'added') {
                vue.lists.push(change.doc.data())
            }
            // if (change.type === 'removed') {
            //     console.log(`removed doc ${change.doc}`)
            // }
        })
    })
  }
}

</script>
