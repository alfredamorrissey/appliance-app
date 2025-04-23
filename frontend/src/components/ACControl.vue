<template>
  <div>
    <h3>AC</h3>
    <!-- Input field for temperature with validation between 0 and 30 -->
    <input
      v-model="newTemperature"
      type="number"
      min="0"
      max="30"
      placeholder="Enter temperature (0-30)"
    />
    <button @click="saveTemp">Save</button>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import api from '@/services/api';

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
</script>

