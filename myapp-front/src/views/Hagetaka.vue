<template>
  <div class="bootstrap-sample">
    <b-form>
      <b-form-group id="sample-group" label="入力してね" label-for="sample-text">
        <b-form-input placeholder="name" v-model="name"></b-form-input>
        <b-form-input placeholder="value" v-model="value"></b-form-input>
      </b-form-group>
      <b-button v-on:click="doAdd" variant="primary">送る</b-button>
    </b-form>
  </div>
</template>

<script>

export default {
  data() {
    return {
        name: "",
        value: ""
    }
  },

  methods: {
    doAdd(){
        const now = new Date();
        firebase.firestore().collection('hagetaka').add({
            name: this.name,
            value: this.value,
            date: now
        })
    }
  }
}

</script>
