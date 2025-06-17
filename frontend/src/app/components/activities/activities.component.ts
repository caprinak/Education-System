import {Component, HostListener, OnInit} from '@angular/core';
import {CourseInfo} from "../../model/course.info";
import {ActivatedRoute, Router} from "@angular/router";
import {CourseService} from "../../services/course.service";
import {ActivityService} from "../../services/activity.service";
import {Activity, ActivityPage} from "../../model/activity";
import {AlertService} from "../../services/alert.service";
import {BehaviorSubject} from "rxjs";

@Component({
  selector: 'app-activities',
  templateUrl: './activities.component.html'
})
export class ActivitiesComponent implements OnInit {

  public courseId: number | undefined;

  //Data from API
  courseInfo: CourseInfo | undefined
  activities: ActivityPage | undefined
  //end data


  //Page number
  pageNumber: number = 0
  //


  //Loading indicators
  activitiesLoading = false
  courseLoading = false;
  //end loading


  //Subscriptions
  courseSubscription: any;
  activitiesSubscription: any;

  //end subscriptions


  //Delete activity
  deleteModalOpened = false
  activityIdToBeDeleted: number | undefined
  //end delete


  //Edit activity
  editModalOpened = false
  editedActivity: Activity | undefined

  //end edit


  //Grading
  chosenActivity: Activity | undefined
  chosenActivity$: BehaviorSubject<Activity> | undefined

  //end grading


  constructor(private activatedRoute: ActivatedRoute,
              private courseService: CourseService,
              private activityService: ActivityService,
              private alertService: AlertService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.activatedRoute.paramMap.subscribe((params) => {
      this.clearSubscriptions()
      let courseIdString = params.get('id')!.toString()

      this.courseId = Number(courseIdString)

      this.getCourseInfo(this.courseId)
      this.getActivities(this.courseId)
    })
  }

  @HostListener('document:keydown', ['$event']) onKeydownHandler(event: KeyboardEvent) {
    if (event.key === "Escape") {
      this.editModalOpened = false
      this.deleteModalOpened = false
    }
  }

  clearSubscriptions() {
    this.courseSubscription?.unsubscribe()
    this.activitiesSubscription?.unsubscribe()
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


  getActivities(courseId: number) {
    this.activitiesLoading = true
    this.activitiesSubscription = this.activityService.getCourseActivities(courseId, this.pageNumber).subscribe((result) => {
      this.activities = result
      this.activitiesLoading = false
    })
  }

  refreshActivities() {
    this.pageNumber = 0
    this.activitiesLoading = true
    this.activitiesSubscription = this.activityService.getCourseActivities(this.courseId, this.pageNumber).subscribe((result) => {
      this.activities = result
      this.chosenActivity = undefined
      this.activitiesLoading = false
      this.editModalOpened = false
    })
  }

  isLoading() {
    return this.courseLoading || this.activitiesLoading
  }

  nextPage() {
    if (!this.activities?.last) {
      this.pageNumber++
      this.getActivities(this.courseId!)
    }
  }

  previousPage() {
    if (this.pageNumber > 0) {
      this.pageNumber--
      this.getActivities(this.courseId!)
    }
  }

  deleteActivity() {
    if (this.activityIdToBeDeleted) {
      this.activityService.deleteActivity(this.activityIdToBeDeleted).subscribe((result) => {
        this.ngOnInit()
        this.alertService.showAlert('success', 'Activity has been deleted.')
        this.deleteModalOpened = false
        if (this.chosenActivity?.activityId === this.activityIdToBeDeleted) {
          this.chosenActivity = undefined
        }
      }, error => {
        this.alertService.showAlert('danger', 'Something went wrong during deleting activity. Try again later.')
      })
    }
  }

  showDeleteModal(activityId: number) {
    this.activityIdToBeDeleted = activityId
    this.deleteModalOpened = true
  }

  showEditModal(activity: Activity) {
    this.editedActivity = activity
    this.editModalOpened = true
  }

  showStudentList(activity: Activity) {
    this.chosenActivity = activity
    if (!this.chosenActivity$) {
      this.chosenActivity$ = new BehaviorSubject<Activity>(this.chosenActivity)
    } else {
      this.chosenActivity$.next(this.chosenActivity)
    }
  }
}
