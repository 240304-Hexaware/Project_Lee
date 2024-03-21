import { Component } from '@angular/core';
import { Router } from '@angular/router';
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

  constructor(private authService: AuthService, private router: Router) {
    // FIXME: after a successful logout, username is not cleared
    this.username = this.authService.getCurrentUserUsername();
  }

  // TODO: it only shows up when user is logged in

  logout() {
    this.authService.logout();
    this.router.navigate(['/login']);
  }
}
