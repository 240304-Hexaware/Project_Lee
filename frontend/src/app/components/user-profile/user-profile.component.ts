import { Component } from '@angular/core';
import { AuthService } from '../../services/auth/auth.service';

@Component({
  selector: 'app-user-profile',
  standalone: true,
  imports: [],
  templateUrl: './user-profile.component.html',
  styleUrl: './user-profile.component.css',
})
export class UserProfileComponent {
  public username: string = '';

  constructor(private authService: AuthService) {}

  // TODO: it only shows up when user is logged in

  logout() {
    this.authService.logout();
  }
}
