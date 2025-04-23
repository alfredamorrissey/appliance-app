<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import { useAuthStore } from '@/stores/auth';


const username = ref('');
const password = ref('');
const router = useRouter();
const auth = useAuthStore();

const login = async () => {
  await auth.login(username.value, password.value);
  if (auth.user) router.push('/appliances');
};

const register = async () => {
  await auth.register(username.value, password.value);
  if (auth.user) router.push('/appliances');
};
</script>

<template>
  <div class="login">
    <h1>Login</h1>
    <input v-model="username" placeholder="Username" />
    <input v-model="password" type="password" placeholder="Password" />
    <button @click="login">Login</button>
    <button @click="register">Register</button>

    <p v-if="auth.error" class="error">{{ auth.error }}</p>
  </div>
</template>
