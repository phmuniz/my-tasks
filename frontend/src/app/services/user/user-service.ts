import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private api: HttpClient) { }

  saveUser(name: string, username: string, password: string) {



    const res = this.api.post('http://localhost:8080/users', {
      name: name,
      username: username,
      password: password
    }).subscribe({
      next: () => {
        return true
      },
      error: (err) => {
        return false
      }
    })

    return res

  }
}
