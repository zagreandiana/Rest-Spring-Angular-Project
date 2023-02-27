import {User} from "./user.model";
export interface UserResponse {
  users: User[];
  errorMessage: string;
}
