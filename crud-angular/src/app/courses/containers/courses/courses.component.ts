import { AsyncPipe, NgIf } from '@angular/common';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatCardModule } from '@angular/material/card';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { MatPaginator, MatPaginatorModule, PageEvent } from '@angular/material/paginator';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { MatToolbarModule } from '@angular/material/toolbar';
import { ActivatedRoute, Router } from '@angular/router';
import { catchError, Observable, of, tap } from 'rxjs';

import { ConfirmationDialogComponent } from '../../../shared/components/confirmation-dialog/confirmation-dialog.component';
import { ErrorDialogComponent } from '../../../shared/components/error-dialog/error-dialog.component';
import { CoursesListComponent } from '../../components/courses-list/courses-list.component';
import { Course } from '../../model/course';
import { CoursePage } from '../../model/course-page';
import { CoursesService } from '../../services/courses.service';

@Component({
  selector: 'app-courses',
  templateUrl: './courses.component.html',
  styleUrls: ['./courses.component.scss'],
  standalone: true,
  imports: [
    MatCardModule,
    MatToolbarModule,
    NgIf,
    CoursesListComponent,
    MatProgressSpinnerModule,
    MatSnackBarModule,
    MatDialogModule,
    MatPaginatorModule,
    AsyncPipe
  ]
})
export class CoursesComponent implements OnInit {

  courses$: Observable<Course[]> | null = null;




  constructor(
    private coursesService: CoursesService,
    public dialog: MatDialog,
    private router: Router,
    private route: ActivatedRoute,
    private snackBar: MatSnackBar

    ) {
    this.refresh();
  }

  refresh() {
    this.courses$ = this.coursesService.list()
    .pipe(
      catchError(error => {
        this.onError('Erro ao carregar cursos.')
        return of([])
      })
    );
  }

  onError(errorMsg: string) {
      this.dialog.open(ErrorDialogComponent, {
        data: errorMsg
      });
  }

  ngOnInit(): void {

  }

  onAdd() {
    this.router.navigate(['new'], { relativeTo: this.route });
  }

  onEdit(course: Course) {
    this.router.navigate(['edit', course._id], { relativeTo: this.route });
  }

  onRemove(course: Course) {
    const dialogRef = this.dialog.open(ConfirmationDialogComponent, {
      data: 'Tem certeza que deseja remover esse curso?',
    });

    dialogRef.afterClosed().subscribe((result: boolean) => {
     if(result) {
      this.coursesService.remove(course._id).subscribe(
        () => {
          this.refresh();
          this.snackBar.open('Curso removido com sucesso!', 'X', {
            duration: 5000,
            verticalPosition: 'top',
            horizontalPosition: 'center'
          });
        },
        () => this.onError('Erro ao tentar remover curso')
      );
     }
    });
  }
}
