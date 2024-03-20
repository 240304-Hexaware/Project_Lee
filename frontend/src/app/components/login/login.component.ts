import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink, RouterOutlet } from '@angular/router';
import { AuthService } from '../../services/auth/auth.service';
import { ErrorResponse } from '../../utils/types';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule, RouterLink, RouterOutlet, CommonModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
})
export class LoginComponent {
  error: string = '';

  constructor(private authService: AuthService, private router: Router) {}

  login(username: string, password: string): void {
    this.authService.login(username, password).subscribe({
      next: (_) => {
        this.router.navigate(['/main']);
      },
      error: (err: ErrorResponse) => {
        this.error =
          err.details.join('\n') ?? 'Something went wrong! Try again.';
        console.error(err);
      },
    });
  }
}
