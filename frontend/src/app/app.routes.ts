import { Routes } from '@angular/router';
import { Login } from './pages/login/login';
import { Signup } from './pages/signup/signup';
import { Middleware } from './services/middleware';
import { Home } from './pages/home/home/home';

export const routes: Routes = [

  {path: '', component: Login},
  {path: 'signup', component: Signup},
  {path: 'home', component: Home, canActivate: [Middleware]},
];
