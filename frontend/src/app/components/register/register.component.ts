import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterOutlet } from '@angular/router';
import { AuthService } from '../../services/auth/auth.service';
import { RegisterRequestParams } from '../../utils/types';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [RouterOutlet, FormsModule, CommonModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css',
})
export class RegisterComponent {
  username: string = '';
  password: string = '';
  confirmPassword: string = '';
  error: string = '';

  constructor(private router: Router, private authService: AuthService) {}

  register(): void {
    const params: RegisterRequestParams = {
      username: this.username,
      password: this.password,
      confirmPassword: this.confirmPassword,
    };

    this.authService.register(params).subscribe({
      next: (_) => {
        this.router.navigate(['/login']);
      },
      error: (error) => {
        if (error instanceof Error) {
          console.error(error.message);
        } else {
          console.error(error);
        }
        this.error = 'Something went wrong! Try again.';
      },
    });
  }
}
