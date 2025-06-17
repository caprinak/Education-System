import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {CourseService} from "../../services/course.service";
import {GradeService} from "../../services/grade.service";
import {CourseInfo} from "../../model/course.info";
import {GradePage} from "../../model/grade";

@Component({
  selector: 'app-grades',
  templateUrl: './grades.component.html'
})
export class GradesComponent implements OnInit {

  public courseId: number | undefined;

  //Data from API
  courseInfo: CourseInfo | undefined
  grades: GradePage | undefined
  //end data


  //Page number
  pageNumber: number = 0
  //


  //Loading indicators
  gradesLoading = false
  courseLoading = false;
  //end loading


  //Subscriptions
  courseSubscription: any;
  gradesSubscription: any;

  //end subscriptions


  constructor(private activatedRoute: ActivatedRoute,
              private courseService: CourseService,
              private gradeService: GradeService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.activatedRoute.paramMap.subscribe((params) => {
      this.clearSubscriptions()
      let courseIdString = params.get('id')!.toString()

      this.courseId = Number(courseIdString)

      this.getCourseInfo(this.courseId)
      this.getGrades(this.courseId)
    })
  }

  clearSubscriptions() {
    this.courseSubscription?.unsubscribe()
    this.gradesSubscription?.unsubscribe()
  }

  getCourseInfo(courseId: number) {
    this.courseLoading = true
    this.courseSubscription = this.courseService.getCourseInfo(courseId).subscribe((result) => {
      this.courseInfo = result
      this.courseLoading = false
    }, error => {
      this.router.navigate(['/forbidden'])
    })
  }


  getGrades(courseId: number) {
    this.gradesLoading = true
    this.gradesSubscription = this.gradeService.getGradesOfCourse(courseId, this.pageNumber).subscribe((result) => {
      this.grades = result
      this.gradesLoading = false
    })
  }

  isLoading() {
    return this.courseLoading || this.gradesLoading
  }

  nextPage() {
    if (!this.grades?.last) {
      this.pageNumber++
      this.getGrades(this.courseId!)
    }
  }

  previousPage() {
    if (this.pageNumber > 0) {
      this.pageNumber--
      this.getGrades(this.courseId!)
    }
  }
}
