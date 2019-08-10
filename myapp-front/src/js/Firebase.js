import firebase from 'firebase/app';
import 'firebase/auth';
import 'firebase/firestore';
import store from "@/store";

const firebaseConfig = {
  apiKey: "AIzaSyBmVsZs6nbSFa0iwU02V3AZCfuvdLD1tSw",
  authDomain: "my-app-7bb29.firebaseapp.com",
  databaseURL: "https://my-app-7bb29.firebaseio.com",
  projectId: "my-app-7bb29",
  storageBucket: "",
  messagingSenderId: "386845991373",
  appId: "1:386845991373:web:b3f5164904229ddf"
}

export default {
  init() {
    firebase.initializeApp(firebaseConfig);

    // settings for auth
    firebase.auth().setPersistence(firebase.auth.Auth.Persistence.SESSION)
  },
  createUser(email, password) {
    firebase.auth().createUserWithEmailAndPassword(email, password).catch(function(error) {
      console.log("create user error")
      const errorCode = error.code
      const errorMessage = error.message
    })
  },
  login(email, password) {
    firebase.auth().signInWithEmailAndPassword(email, password).catch(function(error) {
      console.log("login error")
      const errorCode = error.code;
      const errorMessage = error.message;
    })
  },
  logout() {
    firebase.auth().signOut()
  },
  onAuth() {
    firebase.auth().onAuthStateChanged(user => {
      user = user ? user : {};
      store.commit('onAuthStateChanged', user);
      store.commit('onUserStatusChanged', !!user.uid);
    });
  }
};
