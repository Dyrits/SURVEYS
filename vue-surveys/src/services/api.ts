import axios from "axios";
import type { Survey } from "@/types";

const api = axios.create({
  baseURL: "http://localhost:3000/api"
});

export const getSurveys = () => api.get<Survey[]>("/surveys");
export const getSurvey = (id: number) =>
  api.get<Survey>(`/surveys/${id}/results`);
export const submitRatings = (
  surveyId: number,
  ratings: { questionId: number; score: number }[]
) => api.post(`/surveys/${surveyId}/ratings`, ratings);
