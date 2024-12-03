<template>
  <div class="home">
    <h1>Available Surveys</h1>
    <div v-if="surveys.length" class="surveys-list">
      <div v-for="survey in surveys" :key="survey.id"
           class="survey-item" @click="goToSurvey(survey.id)">
        <h2>Survey #{{ survey.id }}</h2>
        <p>{{ survey.questions.length }} questions</p>
      </div>
    </div>
    <div v-else>Loading surveys...</div>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from "vue";
import { useRouter } from "vue-router";
import { getSurveys } from "@/services/api";
import type { Survey } from "@/types";

const router = useRouter();
const surveys = ref<Survey[]>([]);

onMounted(async () => {
  try {
    const response = await getSurveys();
    surveys.value = response.data;
  } catch (error) {
    console.error("Error fetching surveys:", error);
  }
});

const goToSurvey = (id: number) => {
  router.push(`/surveys/${ id }`);
};
</script>

<style scoped>
.surveys-list {
  display: grid;
  gap: 1rem;
  padding: 1rem;
}

.survey-item {
  padding: 1rem;
  border: 1px solid #ddd;
  border-radius: 8px;
  cursor: pointer;
}

.survey-item:hover {
  background-color: #f5f5f5;
}
</style>