import { Component, inject, OnInit } from '@angular/core';
import { UserService } from '../../services/user/user-service';
import { UserModel } from '../../models/UserModel';
import { CommonModule } from '@angular/common';
import { ChangeDetectorRef } from '@angular/core';
import { TaskService } from '../../services/task/task-service';
import { Router, RouterModule } from '@angular/router';
import { FormGroup, FormControl, Validators, FormBuilder, ReactiveFormsModule } from '@angular/forms';
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-home',
  imports: [CommonModule, RouterModule, ReactiveFormsModule],
  templateUrl: './home.html',
  styleUrl: './home.css'
})
export class Home implements OnInit{

  user: UserModel | null = null
  private userService = inject(UserService)
  private taskService = inject(TaskService)
  private cdRef = inject(ChangeDetectorRef);

  showInputTask: boolean = false
  filter: string = 'all'
  form: FormGroup

  constructor(private fb: FormBuilder, private router: Router, private cookieService: CookieService) {

    this.form = this.fb.group({
      'description': ['', Validators.required]
    })
  }

  getUser() {

    this.userService.getUser()
        .subscribe(
          (res: any) => {
            this.user = res as UserModel
            console.log(this.user)
            this.cdRef.detectChanges();
          }
      )
  }

  ngOnInit(): void {
      this.getUser()
  }

  async saveTask() {

    const description = this.form.controls['description'].value

    if(description === ''){
      alert('Preencha todos os campos.')
      return;
    }

    await this.taskService.saveTask(description, this.user?.id)

    this.getUser()
    this.showInputTask = false
  }

  async deleteTask(taskId: string) {

    await this.taskService.deleteTask(taskId)

    this.getUser()
  }

  async handleCompletedTask(taskId: string) {

    await this.taskService.handleCompletedTask(taskId)

    this.getUser()
  }

  handleInputTask() {
    this.showInputTask = !this.showInputTask
  }

  selectFilter(value: string) {

    this.filter = value
  }

  logout() {

    this.cookieService.delete('token')

    window.location.reload()
  }
}
