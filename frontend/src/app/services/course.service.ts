import {Injectable} from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {RealisedSubject} from "../model/realised.subject";
import {CourseInfo, CourseInfoPage} from "../model/course.info";

@Injectable({
  providedIn: 'root'
})
export class CourseService {

  constructor(private http: HttpClient) {
  }

  createCourse(classId: number | undefined, subjectId: number | undefined, teacherId: number | undefined, year: number | undefined) {
    return this.http.post(`${environment.apiUrl}/courses`, {
      year: year,
      classId: classId,
      subjectId: subjectId,
      teacherId: teacherId
    }, {observe: "response"})
  }

  updateCourse(courseId: number | undefined, teacherId: number | undefined, year: number | undefined) {
    return this.http.put(`${environment.apiUrl}/courses/${courseId}`, {
      year: year,
      teacherId: teacherId
    }, {observe: "response"})
  }

  getActiveCourses(page: number | undefined) {
    return this.http.get<CourseInfoPage>(`${environment.apiUrl}/courses?page=${page}&size=8`)
  }

  getArchivedCourses(page: number | undefined) {
    return this.http.get<CourseInfoPage>(`${environment.apiUrl}/courses/archived?page=${page}&size=8`)
  }

  getRealisedSubjects() {
    return this.http.get<RealisedSubject[]>(`${environment.apiUrl}/courses/me`)
  }

  getCourseInfo(id: number) {
    return this.http.get<CourseInfo>(`${environment.apiUrl}/courses/${id}`)
  }

  archiveCourse(courseId: number) {
    return this.http.delete(`${environment.apiUrl}/courses/${courseId}`)
  }
}
