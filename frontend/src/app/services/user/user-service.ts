import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private api: HttpClient, private cookieService: CookieService, private router: Router) { }

  saveUser(name: string, username: string, password: string) {

    const res = this.api.post('http://localhost:8080/users', {
      name: name,
      username: username,
      password: password
    }).subscribe({
      next: () => {
        alert('Cadastro realizado com sucesso.')
      },
      error: (err) => {
        alert('Erro ao cadastrar usuário.')
      }
    })

    return res

  }

  authUser(username: string, password: string) {

    this.api.post('http://localhost:8080/users/auth', {
      username: username,
      password: password
    }).subscribe({
      next: (res: any) => {

        if(res.token) {

          this.cookieService.set('token', res.token, {
            expires: 1, // expira em 1 dia
            path: '/',
            secure: false
          });

          console.log('entrou')

          this.router.navigate(['/home'])
        }
      },
      error: (err) => {
        alert('Erro ao fazer login.')
      }
    })

  }
}
