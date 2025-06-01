import { Component, inject } from '@angular/core';
import { FormGroup, FormControl, Validators, FormBuilder, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { UserService } from '../../services/user/user-service';

@Component({
  selector: 'app-login',
  imports: [RouterModule, ReactiveFormsModule],
  templateUrl: './login.html',
  styleUrl: './login.css'
})
export class Login {

  form: FormGroup
  private userService = inject(UserService)

  constructor(private fb: FormBuilder) {

    this.form = this.fb.group({
      'username': ['', Validators.required],
      'password': ['', Validators.required],
    })
  }

  async auth() {

    const username = this.form.controls['username'].value
    const password = this.form.controls['password'].value

    if(username === '' || password === ''){
      alert('Preencha todos os campos.')
      return;
    }

    await this.userService.authUser(username, password)

    this.form.controls['username'].setValue('')
    this.form.controls['password'].setValue('')
  }
}
