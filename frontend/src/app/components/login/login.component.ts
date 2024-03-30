import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormsModule,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { Router, RouterLink, RouterOutlet } from '@angular/router';
import { AuthService } from '../../services/auth/auth.service';
import { ErrorResponse, LoginRequestParams } from '../../utils/types';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    FormsModule,
    RouterLink,
    RouterOutlet,
    CommonModule,
    ReactiveFormsModule,
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
})
export class LoginComponent implements OnInit {
  loginForm = this.formBuilder.group({
    username: [
      '',
      Validators.compose([Validators.required, Validators.minLength(3)]),
    ],
    password: [
      '',
      Validators.compose([Validators.required, Validators.minLength(3)]),
    ],
  });

  error: string = '';

  constructor(
    private authService: AuthService,
    private router: Router,
    private formBuilder: FormBuilder
  ) {}

  ngOnInit(): void {
    this.authService.getCurrentUser().subscribe((user) => {
      if (user) {
        this.router.navigateByUrl('/main');
      }
    });
  }

  login(): void {
    if (!this.loginForm.valid) {
      return;
    }
    this.authService
      .login({ ...this.loginForm.value } as LoginRequestParams)
      .subscribe({
        next: (_) => {
          this.router.navigate(['/main']);
        },
        error: (err: ErrorResponse) => {
          this.error = err.title ?? 'Something went wrong! Try again.';
          console.error(err);
        },
      });
  }
}
