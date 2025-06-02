import { TaskModel } from "./TaskModel"

export interface UserModel {

  id: string
  name: string
  username: string
  tasks: TaskModel[]
}