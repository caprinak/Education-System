import {Component, EventEmitter, HostListener, OnInit, Output} from '@angular/core';
import {Observable} from "rxjs";
import {UserPage} from "../../../../model/user";
import {AlertService} from "../../../../services/alert.service";
import {ClassService} from "../../../../services/class.service";
import {UserService} from "../../../../services/user.service";
import {ClassPage} from "../../../../model/class";
import {SubjectPage} from "../../../../model/subject";
import {SubjectService} from "../../../../services/subject.service";
import {CourseService} from "../../../../services/course.service";

@Component({
  selector: 'app-create-course',
  templateUrl: './create-course.component.html'
})
export class CreateCourseComponent implements OnInit {

  //Data
  year: number = new Date().getFullYear()
  subjectId: number | undefined
  classId: number | undefined
  teacherId: number | undefined

  subjects$: Observable<SubjectPage> = this.subjectService.getAllActiveSubjects()
  classes$: Observable<ClassPage> = this.classService.getAllActiveClasses()
  teachers$: Observable<UserPage> = this.userService.getAllActiveTeachers()
  //end data


  //Loading
  loading = false
  //end loading

  @Output() close: EventEmitter<any> = new EventEmitter()
  @Output() success: EventEmitter<any> = new EventEmitter()

  constructor(private alertService: AlertService,
              private classService: ClassService,
              private subjectService: SubjectService,
              private userService: UserService,
              private courseService: CourseService) {
  }

  ngOnInit(): void {
  }

  @HostListener('document:keydown', ['$event']) onKeydownHandler(event: KeyboardEvent) {
    if (event.key === "Escape") {
      this.closeModal()
    }
  }

  submit() {
    if (this.teacherId === undefined || this.classId === undefined || this.subjectId === undefined || this.year === undefined) {
      this.alertService.showAlert('warning', 'Fill all the required fields.')
      return
    }

    this.loading = true
    this.courseService.createCourse(this.classId, this.subjectId, this.teacherId, this.year).subscribe((result) => {
      this.alertService.showAlert('success', 'Course has been successfully created!')
      this.success.emit()
    }, error => {
      if (error.status === 409) {
        this.alertService.showAlert('danger', 'This class currently realises this subject.')
      } else {
        this.alertService.showAlert('danger', 'Something went wrong during creating a course. Make sure form is valid')
      }
      this.loading = false
    })
  }

  closeModal() {
    this.close.emit()
  }
}
