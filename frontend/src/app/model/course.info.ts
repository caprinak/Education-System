export interface CourseInfoPage {
  content: CourseInfo[],
  totalPages: number,
  last: boolean,
  number: number
}

export interface CourseInfo {
  id: number
  subjectName: string
  teacherId: number
  year: number
  teacherFirstName: string
  teacherLastName: string
  schoolClassName: string
  subjectAbbreviation: string
  imageUrl: string
}
