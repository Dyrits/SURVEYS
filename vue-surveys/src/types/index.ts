export interface Survey {
  id: number;
  questions: Question[];
}

export interface Question {
  id: number;
  text: string;
}
