import Vue from 'vue';
import App from './App';
//element
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';

import router from './router';
import store from './vuex/store'
//ajax
import axios from 'axios'


//用于移动端的控制台显示
// import vConsole from 'vconsole'
// Vue.prototype.$vConsole= new vConsole()

Vue.use(ElementUI);
Vue.prototype.$ajax = axios;
Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  render: h => h(App)
})
