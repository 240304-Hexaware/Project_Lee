import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterOutlet } from '@angular/router';
import { register } from '../../api/apiClient';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [RouterOutlet, FormsModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css',
})
export class RegisterComponent {
  constructor(private router: Router) {}
  async register(username: string, password: string, confirmPassword: string) {
    try {
      await register(username, password, confirmPassword);
      this.router.navigate(['/login']);
    } catch (error) {
      console.error('Registration error:', error);
    }
  }
}
