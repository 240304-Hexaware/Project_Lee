import { Routes } from '@angular/router';
import { FileComponent } from './components/file/file.component';
import { LoginComponent } from './components/login/login.component';
import { ParseComponent } from './components/parse/parse.component';
import { RegisterComponent } from './components/register/register.component';
import { SpecComponent } from './components/spec/spec.component';
import { authGuard } from './services/auth/auth.guard';

export const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: '', redirectTo: '/main', pathMatch: 'full' },
  { path: 'register', component: RegisterComponent },
  { path: 'main', component: SpecComponent, canActivate: [authGuard] },
  { path: 'spec', component: SpecComponent, canActivate: [authGuard] },
  { path: 'file', component: FileComponent, canActivate: [authGuard] },
  { path: 'parse', component: ParseComponent, canActivate: [authGuard] },
];
