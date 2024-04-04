import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { HeaderComponent } from './components/shared/header/header.component';
import { LoginComponent } from './components/user-management/login/login.component';
import { RegistrationComponent } from './components/user-management/registration/registration.component';

@Component({
  selector: 'app-root',
  standalone: true,
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
  imports: [
    RouterOutlet,
    LoginComponent,
    RegistrationComponent,
    HeaderComponent,
  ],
})
export class AppComponent {
  title = 'File Parser';
}
