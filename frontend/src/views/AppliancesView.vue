<template>
  <div>
    <h1>My Appliances</h1>
    <div v-for="appliance in appliances" :key="appliance.id">
      <LightControl v-if="appliance.type === 'LIGHT'" :appliance="appliance" @update="refresh" />
      <FanControl v-else-if="appliance.type === 'FAN'" :appliance="appliance" @update="refresh" />
      <ACControl v-else-if="appliance.type === 'AC'" :appliance="appliance" @update="refresh" />
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { useAuthStore } from '@/stores/auth';  // Import Pinia store
import api from '@/services/api';
import LightControl from '@/components/LightControl.vue';
import FanControl from '@/components/FanControl.vue';
import ACControl from '@/components/ACControl.vue';

const appliances = ref([]);
const authStore = useAuthStore();  // Access the auth store

// Function to refresh appliances, passing user ID
const refresh = async () => {
  if (authStore.user && authStore.user.id) {
    const response = await api.getAppliances(authStore.user);
    appliances.value = response.data;
  } else {
    // Handle case where user is not logged in or user ID is missing
    appliances.value = [];
  }
};

onMounted(refresh);  // Call refresh when the component is mounted
</script>
