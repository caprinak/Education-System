import {Activity} from "./activity";

export interface GradePage {
  content: Grade[],
  totalPages: number,
  last: boolean,
  number: number
}

export interface Grade {
  activityDTO: Activity
  courseId: number
  grade: number
  comment: string
  createdAt: Date
}
