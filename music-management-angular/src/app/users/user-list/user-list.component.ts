import {Component, OnInit} from '@angular/core';
import {User} from "../shared/user.model";
import {UserService} from "../shared/user.service";
import {UserResponse} from "../shared/userResponse.model";
import {Router} from "@angular/router";

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {
  users: Array<User> = [];
  selectedUser: User | undefined;

  constructor(private userService: UserService, private router: Router) {
  }

  ngOnInit(): void {
    this.getUsers();
  }

  getUsers() {
    this.userService.getAll().subscribe({
      next: (userResponse: UserResponse) => {
        this.users = userResponse.users;
      }
    })
  }

  onDelete(user: User) {
    this.userService.deleteById(user.id).subscribe({
      next: (userResponse: UserResponse) => {
        this.users = this.users.filter(u => u.id !== user.id);
      }
    });
  }

  onSelect(user: User) {
    this.selectedUser = user;
  }

  gotoDetail() {
    this.router.navigate(['/user-detail', this.selectedUser?.id]);
  }
}
