import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-grade',
  templateUrl: './average-grade.component.html'
})
export class AverageGradeComponent implements OnInit {


  @Input() courseId: number | undefined;
  @Input() grade: number | undefined;


  constructor() {
  }

  ngOnInit(): void {
  }
}
