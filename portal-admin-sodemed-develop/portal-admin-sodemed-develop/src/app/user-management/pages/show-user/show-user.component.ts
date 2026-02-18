import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { TableBaseStylesComponent } from '@shared/components/table-base-styles/table-base-styles.component';
import { TableModule } from 'primeng/table';
import { LisBeneficiary } from '../../models/beneficiary';
import { SessionService } from '@core/security/services/session-service.service';
import { Router } from '@angular/router';
import { NotificationService } from '@core/services/alert/sweet-alert.service';
import { ErrorResponse } from '@core/response/error-response';
import { TranslateKinshipPipe } from '../../pipes/translate-kinship.pipe';
import { TranslateStatusUserPipe } from '../../pipes/translate-status-user.pipe';
import { SkeletonModule } from 'primeng/skeleton';
import { UserManagementService } from '../../services/user-management.service';
import { UserEmployee } from '@auth/models/register-user';
import { Paginator } from '@shared/models/paginator';
import { ButtonModule } from 'primeng/button';
import { EnumStatusUser } from '../../models/status-user';
import { FilterUserComponent } from "../../components/filter-medical-request/filter-users.component";
import { SysUser } from '@auth/models/sys-user';
@Component({
  selector: 'app-show-user',
  templateUrl: './show-user.component.html',
  styleUrls: ['./show-user.component.scss'],
  standalone: true,
  imports: [
    CommonModule,
    TableBaseStylesComponent,
    TableModule,
    TranslateKinshipPipe,
    TranslateStatusUserPipe,
    SkeletonModule,
    ButtonModule,
    FilterUserComponent,
    FilterUserComponent
],

})
export default class ShowUsersComponent implements OnInit {
  public identificationClient: string = null;
  public arrayUserEmployee: UserEmployee[] = [];
  public loadingskeleton: boolean = false;
  public paginator: Paginator = new Paginator();
  public dataUserInSession: SysUser = null;

  public readonly enumStatusUser = EnumStatusUser
  
  public filterUser: any;
  constructor(
    private sessionService: SessionService,
    private notificationService: NotificationService,
    private userManagementService: UserManagementService,
  ) { }

  ngOnInit(): void {

    this.dataUserInSession = this.sessionService?.userData
    this.processUserEmployee(this.paginator.page, this.paginator.row);
  }


  getUserEmployye(filter: any, page: number,row: number) {
    this.loadingskeleton = true;
    this.userManagementService.getUserEmployye(filter,page,row).subscribe({
      next: (response) => {
        this.loadingskeleton = false;
        if (response.success) {
          const userId = this.dataUserInSession.userId
          this.arrayUserEmployee = response.data?.filter(user => user.id !== userId);
          this.paginator = {
            page: response.number,
            row: row,
            totalPages: response.totalPages,
            totalRecords: response.totalElements
          }
        }
      },
      error: (err) => {
        console.log('ERROR', err)
        this.loadingskeleton = false;
        this.arrayUserEmployee = [];
        this.notificationService.toasErrorI18n(err)
      },
    })
  }

  processUserEmployee(page: number,row: number) {
    this.getUserEmployye(this.filterUser,page,row)
  }

  onPageChange1(paginator) {
    this.processUserEmployee(paginator.page + 1, this.paginator.row)
  }

  inactiveEmployee(employee: UserEmployee) {
    
    this.userManagementService.deleteUserEmployee(employee.id).subscribe({
      next: (response) => {
        if (response.success) {
          this.notificationService.modalConfirmAction({
            title: `Usuario ${employee.name} ${employee.lastName}`,
            text: `Se ha inactivado`,
            icon: 'success',
            showCancelButton: false,
            onConfirm:()=> this.processUserEmployee(this.paginator.page, this.paginator.row),
            onCancel:()=> this.processUserEmployee(this.paginator.page, this.paginator.row)
        });
        }
      },
      error: (err) => {
        console.log('ERROR', err)
        this.notificationService.toasErrorI18n(err)
      },
    })

  }

  cleanFilter() {
    // this.arrayUserEmployee = [];
  }

  searchResults(filters: any) {
    this.filterUser = filters
    this.processUserEmployee(this.paginator.page, this.paginator.row)
  }

  activeEmployee(employee: UserEmployee){
    this.userManagementService.activeUserEmployee(employee?.id).subscribe({
      next: (response) => {
        if (response.success) {
          this.notificationService.modalConfirmAction({
            title: `Usuario ${employee.name} ${employee.lastName}`,
            text: `Se ha activado`,
            icon: 'success',
            showCancelButton: false,
            onConfirm:()=> this.processUserEmployee(this.paginator.page, this.paginator.row),
            onCancel:()=> this.processUserEmployee(this.paginator.page, this.paginator.row)
        });
        }
      }
      , error: (err) => {
        console.log('ERROR', err)
        this.notificationService.toasErrorI18n(err)
      }
    })
  }
}
