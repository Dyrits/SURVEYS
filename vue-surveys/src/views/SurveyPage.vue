<template>
  <div class="survey" v-if="survey">
    <h1>Survey #{{ survey.id }}</h1>
    <form @submit.prevent="submitSurvey" class="questions">
      <div v-for="question in survey.questions" :key="question.id" class="question">
        <h3>{{ question.text }}</h3>
        <div class="rating">
          <label v-for="value in 5" :key="value">
            <input
                type="radio"
                :name="`question-${question.id}`"
                :value="value"
                v-model="ratings[question.id]"
                required
            >
            {{ value }}
          </label>
        </div>
      </div>
      <button type="submit" class="submit-btn">Submit Survey</button>
    </form>
  </div>
  <div v-else>Loading survey...</div>
</template>

<script setup lang="ts">
import { ref, onMounted } from "vue";
import { useRoute, useRouter } from "vue-router";
import { getSurvey, submitRatings } from "@/services/api";
import type { Question, Survey } from "@/types";

const route = useRoute();
const router = useRouter();
const survey = ref<Survey | null>(null);
const ratings = ref<Record<number, number>>({});

onMounted(async () => {
  try {
    const id = Number(route.params.id);
    const response = await getSurvey(id);
    survey.value = response.data;
  } catch (error) {
    console.error("Error fetching survey:", error);
    alert("An error occurred while fetching the survey. You will be redirected to the home page.");
    await router.push("/");
  }
});

const submitSurvey = async () => {
  if (!survey.value) {
    return;
  }

  try {
    const body = survey.value.questions.map((question: Question) => ({
      questionId: question.id,
      score: ratings.value[question.id]
    }));

    await submitRatings(survey.value.id, body);
    await router.push("/");
  } catch (error) {
    console.error("Error submitting survey:", error);
  }
};
</script>

<style scoped>
.survey {
  padding: 1rem;
  max-width: 800px;
  margin: 0 auto;
}

.questions {
  display: grid;
  gap: 2rem;
}

.question {
  padding: 1.5rem;
  border: 1px solid #ddd;
  border-radius: 8px;
  background: white;
}

.rating {
  display: flex;
  gap: 1.5rem;
  margin-top: 1rem;
}

.rating label {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 0.5rem;
  cursor: pointer;
}

.rating input {
  cursor: pointer;
}

.submit-btn {
  padding: 1rem 2rem;
  background: #4CAF50;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 1rem;
  margin-top: 1rem;
}

.submit-btn:hover {
  background: #45a049;
}
</style>