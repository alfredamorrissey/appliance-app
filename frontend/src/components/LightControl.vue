<template>
  <div>
    <h3>Light</h3>
    <button @click="toggle">{{ appliance.isOn ? 'Turn Off' : 'Turn On' }}</button>
  </div>
</template>

<script setup>
import { defineProps, defineEmits } from 'vue';
import api from '@/services/api';

const props = defineProps({ appliance: Object });
const emit = defineEmits(['update']);

const toggle = async () => {
  const newStatus = !props.appliance.isOn;
  await api.updateAppliance({ ...props.appliance, status: newStatus });
  emit('update');
};
</script>
