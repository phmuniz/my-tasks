import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { firstValueFrom } from 'rxjs';
import { CookieService } from 'ngx-cookie-service';

@Injectable({
  providedIn: 'root'
})
export class TaskService {

  constructor(private api: HttpClient, private cookieService: CookieService) { }

  async saveTask(description: string, user_id: string | undefined) {

    if(!user_id){
      alert('Erro ao criar task.')
      return
    }

    const token = this.cookieService.get('token')

    const headers = new HttpHeaders({
      Authorization: `Bearer ${token}`,
      'Custom-Header': 'valor',
    });


    try {

      await firstValueFrom(this.api.post('http://localhost:8080/tasks', {
        description,
        user_id
      }, {headers}))
    }
    catch(err) {
      console.log(err)
      alert('Erro ao criar task.')
    }

  }

  async deleteTask(taskId: string) {

    if(!taskId){
      alert('Erro ao deletar task.')
      return
    }

    const token = this.cookieService.get('token')

    const headers = new HttpHeaders({
      Authorization: `Bearer ${token}`,
      'Custom-Header': 'valor',
    });

    try {

      await firstValueFrom(this.api.delete('http://localhost:8080/tasks', {
        headers,
        body: {taskId}
      }))
    }
    catch(err) {
      console.log(err)
      alert('Erro ao deletar task.')
    }
  }

  async handleCompletedTask(taskId: string) {

    if(!taskId){
      alert('Erro ao atualizar task.')
      return
    }

    const token = this.cookieService.get('token')

    const headers = new HttpHeaders({
      Authorization: `Bearer ${token}`,
      'Custom-Header': 'valor',
    });

    try {

      await firstValueFrom(this.api.put('http://localhost:8080/tasks', {
        taskId
      }, {headers}))
    }
    catch(err) {
      console.log(err)
      alert('Erro ao atualizar task.')
    }
  }
}
