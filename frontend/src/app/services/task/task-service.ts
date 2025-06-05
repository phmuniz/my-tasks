import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';

@Injectable({
  providedIn: 'root'
})
export class TaskService {

  constructor(private api: HttpClient, private cookieService: CookieService) { }

  saveTask(description: string, user_id: string | undefined) {

    if(!user_id){
      alert('Erro ao criar task.')
      return
    }

    const token = this.cookieService.get('token')

    const headers = new HttpHeaders({
      Authorization: `Bearer ${token}`,
      'Custom-Header': 'valor',
    });

    this.api.post('http://localhost:8080/tasks', {
      description,
      user_id
    }, {headers}).subscribe({
      error: (err) => {
        console.log(err)
        alert('Erro ao criar task.')
      }
    })
  }

  deleteTask(taskId: string) {

    if(!taskId){
      alert('Erro ao deletar task.')
      return
    }

    const token = this.cookieService.get('token')

    const headers = new HttpHeaders({
      Authorization: `Bearer ${token}`,
      'Custom-Header': 'valor',
    });

    this.api.delete('http://localhost:8080/tasks', {
      headers,
      body: {taskId}
    }).subscribe({
      error: (err) => {
        console.log(err)
        alert('Erro ao deletar task.')
      }
    })
  }

  handleCompletedTask(taskId: string) {

    if(!taskId){
      alert('Erro ao atualizar task.')
      return
    }

    const token = this.cookieService.get('token')

    const headers = new HttpHeaders({
      Authorization: `Bearer ${token}`,
      'Custom-Header': 'valor',
    });

    this.api.put('http://localhost:8080/tasks', {
      taskId
    }, {headers}).subscribe({
      error: (err) => {
        console.log(err)
        alert('Erro ao atualizar task.')
      }
    })
  }
}
