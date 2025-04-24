<template>
  <ApplianceCard :appliance="appliance">
      <template #title>❄️ AC</template>

      <template #details>
        <p>Power: {{ appliance.isOn ? 'On' : 'Off' }}</p>
        <p><input
                 v-model="newTemperature"
                 type="number"
                 min="0"
                 max="30"
                 placeholder="Enter temperature (0-30)"
               /></p>
      </template>

      <template #controls>
        <button @click="saveTemp"
        class="px-3 py-1 bg-blue-600 text-white rounded-lg hover:bg-blue-700">Save</button>
        <button @click="off"
        class="px-3 py-1 bg-blue-600 text-white rounded-lg hover:bg-blue-700">off</button>
        <button @click="on"
                class="px-3 py-1 bg-blue-600 text-white rounded-lg hover:bg-blue-700">on</button>
      </template>
    </ApplianceCard>

</template>

<script setup>
import { ref } from 'vue';
import api from '@/services/api';
import ApplianceCard from './ApplianceCard.vue';

const props = defineProps({ appliance: Object });
const emit = defineEmits(['update']);

// Make temperature reactive
const newTemperature = ref(props.appliance.temperature);

const saveTemp = async () => {
  if (newTemperature.value < 0 || newTemperature.value > 30) {
    alert("Temperature must be between 0 and 30.");
    return;
  }

  await api.updateAppliance({
    ...props.appliance,
    temperature: newTemperature.value,
  });

  emit('update');
};

const off = async () => {
  await api.turnApplianceOff(props.appliance.id);
  emit('update');
}

const on = async () => {
  const newStatus = !props.appliance.isOn;
  //await api.updateAppliance({ ...props.appliance, isOn: newStatus });

  await api.turnApplianceOn({ ...props.appliance });
  emit('update');
};
</script>

