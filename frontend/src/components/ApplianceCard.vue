<template>
  <div
    class="flex flex-col justify-between border-2 border-gray-300 rounded-xl p-6 shadow-lg hover:shadow-2xl transition bg-white space-y-4">
    <div class="flex items-center justify-between">
      <h3 class="text-xl font-semibold">
        <slot name="title" />
      </h3>
      <span class="text-sm text-gray-500">
        ID: {{ appliance.id }}
      </span>
    </div>

    <div>
      <slot name="details" />
    </div>

    <div>
      <slot name="controls" />
    </div>
  </div>
</template>

<script setup>
defineProps({
  appliance: Object
});
</script>
