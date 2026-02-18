import { CommonModule } from '@angular/common';
import { ChangeDetectionStrategy, Component, OnInit } from '@angular/core';
import { TableBaseStylesComponent } from '@shared/components/table-base-styles/table-base-styles.component';
import { TableModule } from 'primeng/table';
import { TranslateKinshipPipe } from '../../pipes/translate-kinship.pipe';
import { TranslateStatusUserPipe } from '../../pipes/translate-status-user.pipe';
import { SkeletonModule } from 'primeng/skeleton';
import { ButtonModule } from 'primeng/button';
import { FilterUserComponent } from '../../components/filter-medical-request/filter-users.component';
import { SessionService } from '@core/security/services/session-service.service';
import { NotificationService } from '@core/services/alert/sweet-alert.service';
import { RoleEmployee } from '../../models/role';
import { Paginator } from '@shared/models/paginator';
import { RoleUserService } from '../../services/role-user.service';
import { DialogModule } from 'primeng/dialog';
import { EditRoleComponent } from '../../components/edit-role/edit-role.component';
import { CreateRolComponent } from '../../components/create-rol/create-rol.component';

@Component({
    selector: 'app-role-management',
    templateUrl: './role-management.component.html',
    styleUrl: './role-management.component.scss',
    standalone: true,
    imports: [
        CommonModule,
        TableBaseStylesComponent,
        TableModule,
        TranslateStatusUserPipe,
        SkeletonModule,
        ButtonModule,
        DialogModule,
        EditRoleComponent,
        ButtonModule,
        CreateRolComponent
    ],
})
export default class RoleManagementComponent implements OnInit {
  public arrayRoleEmployee: RoleEmployee[] = [];
  public paginator: Paginator = new Paginator();
  public loadingskeleton: boolean = false;
  public enableEditRole: boolean = false;
  public currentRole: RoleEmployee = null;
  public loadingUpdateRole: boolean = false;
  public showDialogCreate: boolean = false;
  public arrayPermissions: string[] = [];

    constructor(
        private notificationService: NotificationService,
        private roleUserService : RoleUserService
      ) { }


  ngOnInit(): void {
    this.getRoles()
  }

  adminRole(role:RoleEmployee){
    this.currentRole= role;
    this.enableEditRole = true;
    this.getAvailablePermissions()
  }


  getRoles() {
    this.loadingskeleton = true;
    this.roleUserService.getRoles().subscribe({
      next: (response) => {
        this.loadingskeleton = false;
        if (response.success) {
          this.arrayRoleEmployee = response.data;
        }
      },
      error: (err) => {
        console.log('ERROR', err)
        this.loadingskeleton = false;
        this.arrayRoleEmployee = [];
        this.notificationService.toasErrorI18n(err)
      },
    })
  }

  createRole(roleEmployee: RoleEmployee) {
    this.loadingUpdateRole=true;
    this.roleUserService.createRole(roleEmployee).subscribe({
      next: (response) => {
        this.loadingUpdateRole = false;
        if (response.success) {
            this.showDialogCreate = false
            this.notificationService.modalConfirmAction({
                title: 'Realizado con éxito',
                text: 'El rol ha sido creado correctamente',
                icon: 'success',
                showCancelButton: false,
                onConfirm: () =>{
                  this.getRoles()    
                }
            })
        }
      },
      error: (err) => {
          this.loadingUpdateRole = false;
          console.log('ERROR', err)
        this.notificationService.toasErrorI18n(err)
      },
    })
  }

  updateRole(roleEmployee: RoleEmployee) {
    this.loadingUpdateRole=true;
    this.roleUserService.updateRole(roleEmployee).subscribe({
      next: (response) => {
        this.loadingUpdateRole = false;
        if (response.success) {
          this.enableEditRole = false
          this.notificationService.modalBasic(
            'Realizado con éxito',
            '',
            'success',
            () => {
              this.getRoles()    
            }
          );
        }
      },
      error: (err) => {
        console.log('ERROR', err)
        this.notificationService.toasErrorI18n(err)
        this.loadingUpdateRole = false;
      },
    })
  }


  save(roleEmployee: RoleEmployee) {
    this.updateRole(roleEmployee);
  }
  
  processCreate(roleEmployee: RoleEmployee, formValid: boolean) {
    if (!formValid) {
        return;
    }
    this.createRole(roleEmployee);
  }


  getAvailablePermissions(){
    this.roleUserService.getAvailablePermissions().subscribe({
      next: (response) => {
        if (response.success) {
          this.arrayPermissions = response.data
        }
      },
      error: (err) => {
        console.log('ERROR', err)
        this.notificationService.toasErrorI18n(err)
      },
    })
  }

  showCreateRol(){
    this.showDialogCreate = true;
    this.getAvailablePermissions()
  }

}
