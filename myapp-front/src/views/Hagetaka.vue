<template>
<div id="board">
    <h2 class="board-title">掲示板</h2>
    <ul class="lists" style="list-style-type: none">
      <h4 v-for="(list, key) in lists"
        :key="key"
        :name="list.name"
        :message="list.message"
        :date="list.date">
      </h4>
      <div class="form-area">名前 : <input v-model="name"></br>
      コメント: <textarea v-model="message"></textarea> </br>
      <button v-on:click="doAdd">書き込む</button></div>
    </ul>
  </div>
</template>

<script>

export default {
  data() {
    return {
        lists: [],
        name: "",
        message: ""
    }
  },
  created: function(){
    var vue = this;

    // リアルタイム
    firebase.firestore().collection('board').onSnapshot(function(snapshot) {
        snapshot.docChanges().forEach(function(change) {
            if (change.type === 'added') {
                console.log(`added doc ${change.doc}`)
                vue.lists.push(change.doc.data())
            }
            if (change.type === 'removed') {
                console.log(`removed doc ${change.doc}`)
            }
        })
    })

    // 初回
    firebase.firestore().collection('board').get().then(function (querySnapshot) {
        querySnapshot.forEach(function (doc) {
            vue.lists.push(doc.data())
        })
    })
  },

  methods: {
    doAdd(){
        var now = new Date();
        firebase.firestore().collection('board').add({
            name: this.name,
            message: this.message,
            date: now.getMonth()+1 + '月' + now.getDate() + '日' + now.getHours() + '時' + now.getMinutes() + '分'
        })
    }
  }
}

</script>
