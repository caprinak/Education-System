import {Component, OnInit} from '@angular/core';
import {CourseService} from "../../services/course.service";
import {ActivatedRoute, Router} from "@angular/router";
import {CourseInfo} from "../../model/course.info";
import {GradeService} from "../../services/grade.service";
import {PostService} from "../../services/post.service";
import {ActivityService} from "../../services/activity.service";
import {PostPage} from "../../model/post";
import {ActivityPage} from "../../model/activity";
import {AuthService} from "../../services/auth.service";
import {Title} from "@angular/platform-browser";
import {ThemeService} from "../../services/theme.service";

@Component({
  selector: 'app-course',
  templateUrl: './course.component.html'
})
export class CourseComponent implements OnInit {

  courseId: number | undefined

  //Data from API
  courseInfo: CourseInfo | undefined
  grade: number | undefined;
  postPage: PostPage | undefined;
  activityPage: ActivityPage | undefined;
  //end data

  //Page numbers
  postPageNumber: number = 0
  activityPageNumber: number = 0
  //end page numbers

  //Loading indicators
  courseLoading = false
  postsLoading = false
  postPageLoading = false
  activitiesLoading = false
  activityPageLoading = false
  gradeLoading = false
  //end loading

  //Subscriptions
  courseSubscription: any
  gradeSubscription: any
  postsSubscription: any
  activitiesSubscription: any
  //end subscriptions

  //Modals
  createActivityOpened = false
  createPostOpened = false
  editActivityOpened = false

  //end modals

  constructor(private courseService: CourseService,
              private activatedRoute: ActivatedRoute,
              private router: Router,
              private gradeService: GradeService,
              private postService: PostService,
              private activityService: ActivityService,
              public authService: AuthService,
              private titleService: Title,
              public themeService: ThemeService) {
  }

  ngOnInit(): void {
    this.activatedRoute.paramMap.subscribe((params) => {
      this.clearSubscriptions()
      let courseIdString = params.get('id')!.toString();

      this.courseId = Number(courseIdString)

      if (this.authService.getRole() === 'STUDENT') {
        this.getAverageGrade();
      }
      this.getPosts();
      this.getActivities();
      this.getCourseInfo(this.courseId);
    })
  }

  isLoading() {
    return this.courseLoading || this.postsLoading || this.gradeLoading || this.activitiesLoading
  }

  clearSubscriptions() {
    this.courseSubscription?.unsubscribe()
    this.postsSubscription?.unsubscribe()
    this.gradeSubscription?.unsubscribe()
    this.activitiesSubscription?.unsubscribe()
  }

  getAverageGrade() {
    this.gradeLoading = true
    this.gradeSubscription = this.gradeService.getAverageGrade(this.courseId).subscribe((result) => {
      this.grade = result.average
      this.gradeLoading = false
    })
  }

  getPosts() {
    this.postsLoading = true
    this.postsSubscription = this.postService.getCoursePosts(this.courseId, this.postPageNumber).subscribe((result) => {
      this.postPage = result
      this.postsLoading = false
    })
  }

  getActivities() {
    this.activitiesLoading = true
    this.postsSubscription = this.activityService.getIncomingActivitiesByCourse(this.courseId, this.activityPageNumber).subscribe((result) => {
      this.activityPage = result
      this.activitiesLoading = false
    })
  }

  getCourseInfo(courseId: number) {
    this.courseLoading = true
    this.courseSubscription = this.courseService.getCourseInfo(courseId).subscribe((result) => {
      this.courseInfo = result
      this.courseLoading = false
      if (this.authService.getRole() === 'STUDENT') {
        this.titleService.setTitle(this.courseInfo.subjectName + ' - Syllabus');
      } else {
        this.titleService.setTitle(this.courseInfo.schoolClassName + " " + this.courseInfo.subjectAbbreviation + ' - Syllabus');
      }
    }, error => {
      this.router.navigate(['/forbidden'])
    })
  }

  refreshPosts() {
    this.postPageNumber = 0
    this.postPageLoading = true
    this.postService.getCoursePosts(this.courseId, this.postPageNumber).subscribe((result) => {
      this.postPage = result
      this.postPageLoading = false
      this.createPostOpened = false
    })
  }

  refreshActivities() {
    this.activityPageNumber = 0
    this.activityPageLoading = true
    this.activityService.getIncomingActivitiesByCourse(this.courseId, this.postPageNumber).subscribe((result) => {
      this.activityPage = result
      this.activityPageLoading = false
      this.createActivityOpened = false
    })
  }

  getNextPostPage() {
    if (!this.postPage?.last) {
      this.postPageLoading = true
      this.postPageNumber++
       this.postsSubscription?.unsubscribe();
      this.postsSubscription = this.postService.getCoursePosts(this.courseId, this.postPageNumber).subscribe((result) => {
        this.postPage = result
        this.postPageLoading = false
      })
    }
  }

  getPreviousPostPage() {
    if (this.postPageNumber > 0) {
      this.postPageLoading = true
      this.postPageNumber--
      this.postService.getCoursePosts(this.courseId, this.postPageNumber).subscribe((result) => {
        this.postPage = result
        this.postPageLoading = false
      })
    }
  }

  getNextActivityPage() {
    if (!this.activityPage?.last) {
      this.activityPageLoading = true
      this.activityPageNumber++
      this.activityService.getIncomingActivitiesByCourse(this.courseId, this.activityPageNumber).subscribe((result) => {
        this.activityPage = result
        this.activityPageLoading = false
      })
    }
  }

  getPreviousActivityPage() {
    if (this.activityPageNumber > 0) {
      this.activityPageLoading = true
      this.activityPageNumber--
      this.activityService.getIncomingActivitiesByCourse(this.courseId, this.activityPageNumber).subscribe((result) => {
        this.activityPage = result
        this.activityPageLoading = false
      })
    }
  }
}
