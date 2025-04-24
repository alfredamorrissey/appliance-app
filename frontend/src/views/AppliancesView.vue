<template>
  <div class="p-6">
    <h2 class="text-2xl font-bold mb-4">Your Appliances</h2>
    <div class="grid gap-6 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4">
      <div v-for="appliance in appliances" :key="appliance.id">
            <LightControl v-if="appliance.type === 'LIGHT'" :appliance="appliance" @update="refresh" />
            <FanControl v-else-if="appliance.type === 'FAN'" :appliance="appliance" @update="refresh" />
            <ACControl v-else-if="appliance.type === 'AC'" :appliance="appliance" @update="refresh" />
          </div>
    </div>
    <button @click="off"
                  class="px-3 py-1 bg-blue-600 text-white rounded-lg hover:bg-blue-700">Turn All Off</button>
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

const off = async () => {
  await api.turnAllAppliancesOff(authStore.user);
  refresh();
};

onMounted(refresh);  // Call refresh when the component is mounted
</script>
