import { Routes } from '@angular/router';
import { FileComponent } from './components/file-management/file/file.component';
import { ParseComponent } from './components/parse-management/parse/parse.component';
import { SpecComponent } from './components/spec-management/spec/spec.component';
import { LoginComponent } from './components/user-management/login/login.component';
import { RegistrationComponent } from './components/user-management/registration/registration.component';
import { ViewDataComponent } from './components/view-data/view-data/view-data.component';
import { authGuard } from './services/auth/auth.guard';

export const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: '', redirectTo: '/main', pathMatch: 'full' },
  { path: 'register', component: RegistrationComponent },
  { path: 'main', component: SpecComponent, canActivate: [authGuard] },
  { path: 'spec', component: SpecComponent, canActivate: [authGuard] },
  { path: 'file', component: FileComponent, canActivate: [authGuard] },
  { path: 'parse', component: ParseComponent, canActivate: [authGuard] },
  { path: 'view', component: ViewDataComponent, canActivate: [authGuard] },
];
