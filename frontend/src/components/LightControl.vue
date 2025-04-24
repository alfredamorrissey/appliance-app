<template>
  <ApplianceCard :appliance="appliance">
    <template #title>💡 Light</template>

    <template #details>
      <p>Power: {{ appliance.isOn ? 'On' : 'Off' }}</p>
    </template>

    <template #controls>
      <button @click="off"
              class="px-3 py-1 bg-blue-600 text-white rounded-lg hover:bg-blue-700">off</button>
      <button @click="on"
              class="px-3 py-1 bg-blue-600 text-white rounded-lg hover:bg-blue-700">on</button>
    </template>
  </ApplianceCard>
</template>

<script setup>
import { defineProps, defineEmits } from 'vue';
import api from '@/services/api';
import ApplianceCard from './ApplianceCard.vue';

const props = defineProps({ appliance: Object });
const emit = defineEmits(['update']);

const off = async () => {
  const newStatus = !props.appliance.isOn;
  //await api.updateAppliance({ ...props.appliance, isOn: newStatus });

  await api.turnApplianceOff(props.appliance.id);
  emit('update');
};

const on = async () => {
  const newStatus = !props.appliance.isOn;
  //await api.updateAppliance({ ...props.appliance, isOn: newStatus });

  await api.turnApplianceOn({ ...props.appliance });
  emit('update');
};
</script>
