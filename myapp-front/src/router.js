import Vue from 'vue'
import Router from 'vue-router'
import Home from './views/Home.vue'

import RockScissorsPaper from '@/js/RockScissorsPaper'
import HagetakaAdmin from "./views/HagetakaAdmin";
import Hagetaka from "./views/Hagetaka";
import Login from "./views/Login";

Vue.use(Router)

export default new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home
    },
    {
      path: '/play',
      name: 'play',
      component: () => import('./views/Play.vue'),
      props: { rockScissorsPaper: new RockScissorsPaper() }
    },
    {
      path: '/hagetaka/rounds/:id/result',
      name: 'hagetaka-result',
      component: () => import('./views/HagetakaResult.vue')
    },
    {
      path: '/hagetaka-admin',
      name: 'hagetaka-adimin',
      component: HagetakaAdmin
    },
    {
      path: '/hagetaka/rounds/:id',
      name: 'hagetaka',
      component: Hagetaka
    },
    {
      path: '/login',
      name: 'login',
      component: Login
    }
  ]
})
