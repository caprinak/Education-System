import {Component, HostListener, OnInit} from '@angular/core';
import {BehaviorSubject, Observable} from "rxjs";
import {AlertService} from "../../../services/alert.service";
import {CourseInfo, CourseInfoPage} from "../../../model/course.info";
import {CourseService} from "../../../services/course.service";
import {AuthService} from "../../../services/auth.service";

@Component({
  selector: 'app-courses',
  templateUrl: './courses.component.html'
})
export class CoursesComponent implements OnInit {

  //Pagination
  pageNumber$: BehaviorSubject<number> = new BehaviorSubject<number>(0)
  //end pagination


  //Data
  courses$: Observable<CourseInfoPage> | undefined
  //end data

  //Filter
  showArchived: boolean = false
  //end filter

  //Modals
  deleteModalOpen: boolean = false
  createCourseModalOpen: boolean = false
  editCourseModalOpen: boolean = false
  //end modals


  //Data passed to children
  courseIdToBeArchived: number | undefined
  editedCourse: CourseInfo | undefined

  //end data


  constructor(private courseService: CourseService,
              private alertService: AlertService,
              public authService: AuthService) {
  }

  ngOnInit(): void {
    this.pageNumber$.subscribe(() => {
      this.getFilteredCourses()
    })
  }

  @HostListener('document:keydown', ['$event']) onKeydownHandler(event: KeyboardEvent) {
    if (event.key === "Escape") {
      this.deleteModalOpen = false
    }
  }

  getFilteredCourses() {
    if (!this.showArchived) {
      this.courses$ = this.courseService.getActiveCourses(this.pageNumber$.value)
    } else {
      this.courses$ = this.courseService.getArchivedCourses(this.pageNumber$.value)
    }
  }

  showActiveCourses() {
    if (this.showArchived) {
      this.showArchived = false
      this.getFilteredCourses()
    }
  }

  showArchivedCourses() {
    if (!this.showArchived) {
      this.showArchived = true
      this.getFilteredCourses()
    }
  }


  previousPage() {
    if (this.pageNumber$.value > 0) {
      this.pageNumber$.next(this.pageNumber$.value - 1)
    }
  }

  nextPage() {
    this.pageNumber$.next(this.pageNumber$.value + 1)
  }

  deleteCourse() {
    if (this.courseIdToBeArchived) {
      this.courseService.archiveCourse(this.courseIdToBeArchived).subscribe((result) => {
        this.alertService.showAlert("success", "Course has been successfully archived.")
        this.getFilteredCourses()
        this.deleteModalOpen = false
      }, error => {
        this.alertService.showAlert("danger", "There was a problem during archiving course. Try again later.")
      })
    }
  }

  showDeleteModal(id: number) {
    this.courseIdToBeArchived = id
    this.deleteModalOpen = true
  }

  showEditModal(course: CourseInfo) {
    this.editedCourse = course
    this.editCourseModalOpen = true
  }
}
