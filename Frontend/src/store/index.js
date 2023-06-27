import {createStore} from 'vuex';
import router from '../router/index.js';
import axios from 'axios';
import Swal from 'sweetalert2';

const REST_API = 'http://localhost:9999/api/';
const Toast_verify = Swal.mixin({
  toast: true,
  position: 'top-end',
  showConfirmButton: false,
  timer: 2000,
  timerProgressBar: true,
});

export default createStore({
  state: {
    users: [],
    dupNick: 1,
    dupId: 1,
    loginUser: null,
    boardList: [],
    boardListSize: 0,
    board: {},
    pageNum: 0,
    comments: [],
    category: 0,
    showBeta: false,
    showAlert: false,
  },
  getters: {},
  mutations: {
    DUPLICATE_CHECK_NICK(state, value) {
      state.dupNick = value;
    },
    DUPLICATE_CHECK_ID(state, value) {
      state.dupId = value;
    },
    CREATE_USER: function(state, user) {
      state.users.push(user);
    },
    SET_LOGIN_USER: function(state, user) {
      state.loginUser = user;
    },
    SET_BOARD_LIST(state, boardList) {
      state.boardList = boardList;
    },
    SET_BOARD(state, board) {
      state.board = board;
    },
    SET_COMMENTS(state, comments) {
      state.comments = comments;
    },
    CREATE_COMMENT(state, comment) {
      state.comments.push(comment);
    },
    DELETE_COMMENT(state, idx) {
      for (let i = 0; i < state.comments.length; i++) {
        if (state.comments[i].idx === idx) {
          state.comments.splice(i, 1);
        }
      }
    },
    ADD_BOARD(state, board) {
      state.boardList.push(board);
    },
    CHANGE_CATEGORY(state, classnum) {
      state.category = classnum;
    },
    PLUS_PAGENUM(state) {
      state.pageNum += 1;
    },
    MINUS_PAGENUM(state) {
      state.pageNum -= 1;
    },
    INIT_PAGENUM(state) {
      state.pageNum = 0;
    },
    SHOW_ALERT(state) {
      state.showAlert = true;
    },
    DISABLE_ALERT(state) {
      state.showAlert = false;
    },
  },
  actions: {
    setLoginUser: function({commit}, user) {
      // axios logic
      const API_URL = REST_API + 'login';
      axios({
        url: API_URL,
        method: 'POST',
        data: user,
      })
          .then(async (res) => {
            const loginUser = {
              userId: res.data.userId,
              userNickname: res.data.userNickName,
              prefer1: res.data.prefer1,
              prefer2: res.data.prefer2,
              prefer3: res.data.prefer3,
            };
            sessionStorage.setItem('loginUser', JSON.stringify(loginUser));
            sessionStorage.setItem('access-token', res.data['access-token']);
            commit('SET_LOGIN_USER', loginUser);
            await Toast_verify.fire({
              icon: 'success',
              title: '로그인 성공!!',
            });
            router.go(-1);

            axios({
              url: REST_API + `alert/${res.data.userNickName}`,
              method: 'GET',
            }).then((res) => {
              Swal.fire({
                text: `현재 ${res.data.AlertNum}개의 댓글 알림이 있습니다.`,
                target: 'custom-target',
                customClass: {
                  container: 'position-absolute',
                },
                toast: true,
                confirmButtonText: '확인하러 가기',
                showCloseButton: true,
                position: 'bottom-right',
              }).then((result) => {
                if (result.isConfirmed && res.data.AlertNum != 0) {
                  commit('SHOW_ALERT');
                }
              });
            });
          })
          .catch((err) => {
            console.log(err);
            Toast_verify.fire({
              icon: 'error',
              title: '등록된 회원이 아닙니다.',
            });
          });
    },
    dupcheckNick({commit}, value) {
      console.log(value);
      const API_URL = REST_API + `signup/nickname/${value}`;
      axios({
        url: API_URL,
        method: 'GET',
      }).then((res) => {
        console.log('duplicate? : ' + res.data.result);
        commit('DUPLICATE_CHECK_NICK', res.data.result);
      });
    },
    dupcheckId({commit}, value) {
      console.log(value);
      const API_URL = REST_API + `signup/id/${value}`;
      axios({
        url: API_URL,
        method: 'GET',
      }).then((res) => {
        console.log('duplicate? : ' + res.data.result);
        commit('DUPLICATE_CHECK_ID', res.data.result);
      });
    },
    createUser({commit}, user) {
      console.log(user);
      const API_URL = REST_API + 'signup';
      axios({
        url: API_URL,
        method: 'POST',
        data: user,
      })
          .then((res) => {
            console.log(res.data);
            commit('CREATE_USER', user);
            router.push('/');
            Toast_verify.fire({
              icon: 'success',
              title: '등록되었습니다.',
            });
          })
          .catch((err) => {
            console.log(err);
          });
    },
    logout({commit}) {
      commit;
      // local storage 비우기
      sessionStorage.removeItem('loginUser');
      sessionStorage.removeItem('access-token');
    },
    setBoardList({commit}, payload) {
      if (payload.category == 0) {
        const API_URL = REST_API + `board/${payload.pagenum}`;
        axios({
          url: API_URL,
          method: 'GET',
        }).then((res) => {
          commit('SET_BOARD_LIST', res.data.articles);
        });
      } else {
        const API_URL =
          REST_API + `board/class/${payload.category}/${payload.pagenum}`;
        axios({
          url: API_URL,
          method: 'GET',
        }).then((res) => {
          commit('SET_BOARD_LIST', res.data.articles);
        });
      }
    },
    writeBoard({commit}, board) {
      axios({
        url: `http://localhost:9999/api/board/write`,
        method: 'POST',
        data: board,
      }).then((res) => {
        commit('ADD_BOARD', res.data);
        Toast_verify.fire({
          icon: 'success',
          title: '등록되었습니다.',
        });
      });
    },

  },
  modules: {},
});
