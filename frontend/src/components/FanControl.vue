<template>
  <div>
    <h3>Fan</h3>
    speed: {{ appliance.speed }}
    <button @click="increase">increase</button>
    <button @click="decrease">decrease</button>
  </div>
</template>

<script setup>
import { defineProps, defineEmits } from 'vue';
import api from '@/services/api';

const props = defineProps({ appliance: Object });
const emit = defineEmits(['update']);

const increase = async () => {
  const newSpeed = props.appliance.speed < 3 ? props.appliance.speed + 1 : 0;
  await api.updateAppliance({ ...props.appliance, speed: newSpeed });
  emit('update');
};

const decrease = async () => {
  const newSpeed = props.appliance.speed > 0 ? props.appliance.speed - 1 : 3;
  await api.updateAppliance({ ...props.appliance, speed: newSpeed });
  emit('update');
};
</script>
