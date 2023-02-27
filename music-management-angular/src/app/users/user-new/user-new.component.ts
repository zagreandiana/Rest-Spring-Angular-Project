import {Component, OnInit} from '@angular/core';
import {UserService} from "../shared/user.service";
import {User} from "../shared/user.model";
import {UserResponse} from "../shared/userResponse.model";
import {Location} from "@angular/common";

@Component({
  selector: 'app-user-new',
  templateUrl: './user-new.component.html',
  styleUrls: ['./user-new.component.css']
})
export class UserNewComponent implements OnInit {

  constructor(private userService: UserService, private location: Location) {
  }

  ngOnInit(): void {
  }

  onSave(firstName: string, lastName: string, email: string, password: string, role: string, status: string) {
    const userDto: User = {id: 0, firstName, lastName, email, password, role: role, status: status};

    this.userService.save(userDto).subscribe({
      next: (userResponse: UserResponse) => {
        this.location.back();
      }
    })
  }
}
