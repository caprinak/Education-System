import {Injectable} from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {ActivityPage} from "../model/activity";
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs/internal/observable/throwError';

@Injectable({
  providedIn: 'root'
})
export class ActivityService {

  constructor(private http: HttpClient) {
  }

  getCourseActivities(courseId: number | undefined, page: number) {
    return this.http.get<ActivityPage>(`${environment.apiUrl}/courses/${courseId}/activities?size=5&page=${page}&sort=createdAt`)
  }

  getIncomingActivitiesByCourse(courseId: number | undefined, page: number) {
    return this.http.get<ActivityPage>(`${environment.apiUrl}/courses/${courseId}/activities/incoming?size=3&page=${page}&sort=createdAt`)
  }

  getAllIncomingActivities(page: number) {
    return this.http.get<ActivityPage>(`${environment.apiUrl}/activities/incoming?size=3&page=${page}&sort=date`)
  }

  // createActivity(name: string, description: string, weight: number, date: any, courseId: number | undefined) {
  //   return this.http.post(`${environment.apiUrl}/activities`, {
  //     name: name,
  //     description: description,
  //     weight: weight,
  //     date: date,
  //     courseId: courseId
  //   }, {observe: "response"})
  // }
  createActivity(name: string, description: string, weight: number, date: any, courseId: number | undefined) {
    const headers = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('Accept', 'application/json');

    const payload = {
      name: name,
      description: description,
      weight: weight,
      date: date instanceof Date ? date.toISOString() : date,
      realisationId: courseId
    };

    console.log('Sending request to:', `${environment.apiUrl}/activities`);
    console.log('Payload:', payload);

    return this.http.post(`${environment.apiUrl}/activities`, payload, {
      headers: headers,
      observe: "response"
    }).pipe(
      catchError(error => {
        console.error('API Error:', error);
        return throwError(() => error);
      })
    );
}
  updateActivity(name: string, description: string, weight: number, date: any, activityId: number | undefined,) {
    return this.http.put(`${environment.apiUrl}/activities/${activityId}`, {
      name: name,
      description: description,
      weight: weight,
      date: date
    }, {observe: "response"})
  }

  deleteActivity(activityId: number) {
    return this.http.delete(`${environment.apiUrl}/activities/${activityId}`)
  }
}
