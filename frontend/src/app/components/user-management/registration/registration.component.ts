import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import {
  FormBuilder,
  FormsModule,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { Router, RouterLink, RouterOutlet } from '@angular/router';
import { AuthService } from '../../../services/auth/auth.service';
import { ErrorResponse, RegisterRequestParams } from '../../../utils/types';

@Component({
  selector: 'app-registration',
  standalone: true,
  imports: [
    RouterOutlet,
    FormsModule,
    CommonModule,
    RouterLink,
    ReactiveFormsModule,
  ],
  templateUrl: './registration.component.html',
  styleUrl: './registration.component.css',
})
export class RegistrationComponent {
  registerForm = this.formBuilder.group({
    username: [
      '',
      Validators.compose([Validators.required, Validators.minLength(3)]),
    ],
    password: [
      '',
      Validators.compose([Validators.required, Validators.minLength(3)]),
    ],
    confirmPassword: [
      '',
      Validators.compose([Validators.required, Validators.minLength(3)]),
    ],
  });
  error: string = '';

  constructor(
    private router: Router,
    private authService: AuthService,
    private formBuilder: FormBuilder
  ) {}

  register(): void {
    if (!this.validate()) {
      return;
    }

    this.authService
      .register({ ...this.registerForm.value } as RegisterRequestParams)
      .subscribe({
        next: (_) => {
          this.router.navigate(['/login']);
        },
        error: (error: ErrorResponse) => {
          this.updateError(error.title || 'Something went wrong! Try again.');
        },
      });
  }

  validate(): boolean {
    const { password, confirmPassword } = this.registerForm.value;
    if (password !== confirmPassword) {
      this.updateError('Passwords do not match');
      return false;
    }
    if (!this.registerForm.valid) {
      this.updateError('Enter valid input');
      return false;
    }
    return true;
  }

  updateError(msg: string) {
    this.error = msg;
  }
}
