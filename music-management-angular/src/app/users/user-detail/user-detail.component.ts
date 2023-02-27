import {Component, Input, OnInit} from '@angular/core';
import {User} from "../shared/user.model";
import {ActivatedRoute, Params} from "@angular/router";
import {switchMap} from "rxjs";
import {UserService} from "../shared/user.service";
import {UserResponse} from "../shared/userResponse.model";
import {Location} from "@angular/common";

@Component({
  selector: 'app-user-detail',
  templateUrl: './user-detail.component.html',
  styleUrls: ['./user-detail.component.css']
})
export class UserDetailComponent implements OnInit {
  @Input() user!: User;

  constructor(private route: ActivatedRoute, private userService: UserService, private location: Location) {
  }

  ngOnInit(): void {
    this.route.params
      .pipe(switchMap((params: Params) => this.userService.getUserById(+params['id'])))
      .subscribe({
        next: (userResponse: UserResponse) => {
          this.user = userResponse.users.at(0) ?? <User>{}
        }
      });
  }

  goBack() {
    this.location.back();
  }

  save() {
    this.userService.update(this.user).subscribe({
      next: (userResponse: UserResponse) => {
        this.goBack();
      }
    })
  }
}
