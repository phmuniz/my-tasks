import { Component, inject, OnInit } from '@angular/core';
import { UserService } from '../../services/user/user-service';
import { UserModel } from '../../models/UserModel';
import { TaskModel } from '../../models/TaskModel';
import { CommonModule } from '@angular/common';
import { ChangeDetectorRef } from '@angular/core';

@Component({
  selector: 'app-home',
  imports: [CommonModule],
  templateUrl: './home.html',
  styleUrl: './home.css'
})
export class Home implements OnInit{

  user: UserModel | null = null
  private userService = inject(UserService)
  private cdRef = inject(ChangeDetectorRef);

  ngOnInit(): void {
      this.userService.getUser()
        .subscribe(
          (res: any) => {
            this.user = res as UserModel
            this.cdRef.detectChanges();
          }
      )
  }
}
