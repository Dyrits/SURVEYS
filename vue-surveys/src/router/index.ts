import { createRouter, createWebHistory } from "vue-router";
import HomePage from "@/views/HomePage.vue";
import SurveyPage from "@/views/SurveyPage.vue";

const router = createRouter({
  history: createWebHistory(),
  routes: [
    {
      path: "/",
      name: "home",
      component: HomePage
    },
    {
      path: "/surveys/:id",
      name: "survey",
      component: SurveyPage
    }
  ]
});

export default router;
