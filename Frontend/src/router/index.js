import Vue from "vue";
import VueRouter from "vue-router";
import HomeView from "../views/HomeView.vue";
import BoardView from "../views/BoardView.vue";
import BaseView from "../views/BaseView.vue";
import SignupView from "../components/SignupView.vue";
import LoginView from "../components/LoginView.vue";
import BoardDetail from "@/components/BoardDetail.vue";

Vue.use(VueRouter);

const routes = [
    {
        path: "/",
        name: "home",
        component: HomeView,
    },
    {
        path: "/signup",
        name: "SignupView",
        component: SignupView,
    },
    {
        path: "/login",
        name: "LoginView",
        component: LoginView,
    },
    {
        path: "/base",
        name: "BaseView",
        component: BaseView,
        children: [
            {
                path: "",
                name: "BoardView",
                component: BoardView,
                children: [
                    {
                        path: ":idx",
                        name: "BoardDetail",
                        component: BoardDetail,
                    },
                ],
            },
        ],
    },
];

const router = new VueRouter({
    mode: "history",
    base: process.env.BASE_URL,
    routes,
});

export default router;
