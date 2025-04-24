<template>
  <ApplianceCard :appliance="appliance">
    <template #title>🌀Fan</template>

    <template #details>
      <p>Speed: {{ appliance.speed }}</p>
    </template>

    <template #controls>
      <button @click="increase"
      class="px-3 py-1 bg-blue-600 text-white rounded-lg hover:bg-blue-700">increase</button>
       <button @click="decrease"
       class="px-3 py-1 bg-blue-600 text-white rounded-lg hover:bg-blue-700">decrease</button>
       <button @click="off"
       class="px-3 py-1 bg-blue-600 text-white rounded-lg hover:bg-blue-700">off</button>
    </template>
  </ApplianceCard>
</template>

<script setup>
import { defineProps, defineEmits } from 'vue';
import api from '@/services/api';
import ApplianceCard from './ApplianceCard.vue';

const props = defineProps({ appliance: Object });
const emit = defineEmits(['update']);

const increase = async () => {
  //const newSpeed = props.appliance.speed < 3 ? props.appliance.speed + 1 : 0;
  //await api.updateAppliance({ ...props.appliance, speed: newSpeed });
  await api.increaseSpeed({ ...props.appliance });
  emit('update');
};

const decrease = async () => {
  //const newSpeed = props.appliance.speed > 0 ? props.appliance.speed - 1 : 3;
  //await api.updateAppliance({ ...props.appliance, speed: newSpeed });
  await api.decreaseSpeed({ ...props.appliance });
  emit('update');
};

const off = async () => {
  await api.turnApplianceOff(props.appliance.id);
  emit('update');
}
</script>
