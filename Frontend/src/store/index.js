import Vue from "vue";
import Vuex from "vuex";
import router from "../router/index.js";
import axios from "axios";
Vue.use(Vuex);

const REST_API = "http://localhost:9999/api/";

export default new Vuex.Store({
    state: {
        users: [],
        dupNick: 1,
        dupId: 1,
        loginUser: null,
        boardList: [],
        board: {},
        comments: [],
    },
    getters: {},
    mutations: {
        DUPLICATE_CHECK_NICK(state, value) {
            state.dupNick = value;
        },
        DUPLICATE_CHECK_ID(state, value) {
            state.dupId = value;
        },
        CREATE_USER: function (state, user) {
            state.users.push(user);
        },
        SET_LOGIN_USER: function (state, user) {
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
    },
    actions: {
        setLoginUser: function ({ commit }, user) {
            //axios logic
            const API_URL = REST_API + "login";
            axios({
                url: API_URL,
                method: "POST",
                data: user,
            })
                .then((res) => {
                    let loginUser = {
                        userId: res.data.userId,
                        userNickname: res.data.userNickName,
                        prefer1: res.data.prefer1,
                        prefer2: res.data.prefer2,
                        prefer3: res.data.prefer3,
                    };
                    sessionStorage.setItem(
                        "loginUser",
                        JSON.stringify(loginUser)
                    );
                    sessionStorage.setItem(
                        "access-token",
                        res.data["access-token"]
                    );
                    alert("로그인 성공!");
                    commit("SET_LOGIN_USER", loginUser);
                })
                .catch((err) => {
                    console.log(err);
                })
                .finally(() => {
                    router.go(-1);
                });
        },
        dupcheckNick({ commit }, value) {
            console.log(value);
            const API_URL = REST_API + `signup/nickname/${value}`;
            axios({
                url: API_URL,
                method: "GET",
            }).then((res) => {
                console.log("duplicate? : " + res.data.result);
                commit("DUPLICATE_CHECK_NICK", res.data.result);
            });
        },
        dupcheckId({ commit }, value) {
            console.log(value);
            const API_URL = REST_API + `signup/id/${value}`;
            axios({
                url: API_URL,
                method: "GET",
            }).then((res) => {
                console.log("duplicate? : " + res.data.result);
                commit("DUPLICATE_CHECK_ID", res.data.result);
            });
        },
        createUser({ commit }, user) {
            console.log(user);
            const API_URL = REST_API + "signup";
            axios({
                url: API_URL,
                method: "POST",
                data: user,
            })
                .then((res) => {
                    console.log(res.data);
                    commit("CREATE_USER", user);
                    alert("등록되었습니다.");
                    router.push("/");
                })
                .catch((err) => {
                    console.log(err);
                });
        },
        logout({ commit }) {
            commit;
            //local storage 비우기
            sessionStorage.removeItem("loginUser");
            sessionStorage.removeItem("access-token");
            alert("로그아웃 되었습니다.");
        },
        setBoardList({ commit }) {
            const API_URL = REST_API + `board/`;
            axios({
                url: API_URL,
                method: "GET",
            }).then((res) => {
                console.log(res.data.articles);
                commit("SET_BOARD_LIST", res.data.articles);
            });
        },
        // deleteBoard({ commit }, idx) {
        //     const API_URL = REST_API + `board/`;
        //     axios({
        //         url: API_URL,
        //         method: "GET",
        //     }).then((res) => {
        //         console.log(res.data.articles);
        //         commit("SET_BOARD_LIST", res.data.articles);
        //     });
        // },
    },
    modules: {},
});
