import { createStore } from 'vuex'

export default createStore({
  state: {
    // 全局变量
    member:{ },
  },
  getters: {
  },
  mutations: {
    setMember( state, _member){
      state.member = _member;
    }
  },
  // 任务
  actions: {
  },
  modules: {
  }
})
