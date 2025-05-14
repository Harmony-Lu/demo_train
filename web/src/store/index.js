import { createStore } from 'vuex'

const MEMBER = "MEMBER";

export default createStore({
  state: {
    // 全局变量
    member: window.SessionStorage.get(MEMBER) || {},  // ||{}避免空指针异常
  },
  getters: {
  },
  mutations: {
    setMember( state, _member){
      state.member = _member;
      window.SessionStorage.set(MEMBER, _member);
    }
  },
  // 任务
  actions: {
  },
  modules: {
  }
})
