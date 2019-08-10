const firebaseConfig = {
  apiKey: "AIzaSyBmVsZs6nbSFa0iwU02V3AZCfuvdLD1tSw",
  authDomain: "my-app-7bb29.firebaseapp.com",
  databaseURL: "https://my-app-7bb29.firebaseio.com",
  projectId: "my-app-7bb29",
  storageBucket: "",
  messagingSenderId: "386845991373",
  appId: "1:386845991373:web:b3f5164904229ddf"
}
firebase.initializeApp(firebaseConfig)

const firestore = firebase.firestore()
const firestoreSettings = {timestampsInSnapshots: true}
firestore.settings(firestoreSettings)
