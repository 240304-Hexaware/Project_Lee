import { NgIf } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth/auth.service';

@Component({
  selector: 'app-user-profile',
  standalone: true,
  imports: [NgIf],
  templateUrl: './user-profile.component.html',
  styleUrl: './user-profile.component.css',
})
export class UserProfileComponent implements OnInit {
  username?: string;
  on: boolean = false;

  constructor(private authService: AuthService, private router: Router) {}
  ngOnInit(): void {
    this.authService.getCurrentUser().subscribe((user) => {
      this.username = user?.username;
      this.on = !!user;
    });
  }

  logout() {
    this.authService.logout();
    this.on = false;
    this.router.navigate(['/login']);
  }
}
