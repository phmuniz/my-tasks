import { Component, inject } from '@angular/core';
import { RouterModule } from '@angular/router';
import { FormGroup, FormControl, Validators, FormBuilder, ReactiveFormsModule } from '@angular/forms';
import { UserService } from '../../services/user/user-service';

@Component({
  selector: 'app-signup',
  imports: [RouterModule, ReactiveFormsModule],
  templateUrl: './signup.html',
  styleUrl: './signup.css'
})
export class Signup {

  form: FormGroup
  private userService = inject(UserService)

  constructor(private fb: FormBuilder) {

    this.form = this.fb.group({
      'name': ['', Validators.required],
      'username': ['', Validators.required],
      'password': ['', Validators.required],
    })
  }

  async save() {

    const name = this.form.controls['name'].value
    const username = this.form.controls['username'].value
    const password = this.form.controls['password'].value

    if(name === '' || username === '' || password === ''){
      alert('Preencha todos os campos.')
      return;
    }

    await this.userService.saveUser(name, username, password)

    this.form.controls['name'].setValue('')
    this.form.controls['username'].setValue('')
    this.form.controls['password'].setValue('')
  }
}
