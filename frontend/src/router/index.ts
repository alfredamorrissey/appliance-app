import { createRouter, createWebHistory } from 'vue-router';
import LoginView from '@/views/LoginView.vue';
import AppliancesView from '@/views/AppliancesView.vue';
import { useAuthStore } from '@/stores/auth';

const routes = [
{ path: '/', redirect: '/login' },
{ path: '/login', component: LoginView },
{
path: '/appliances',
component: AppliancesView,
beforeEnter: () => {
      const auth = useAuthStore();
      return auth.user ? true : '/login';
    },
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
