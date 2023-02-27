import {Artist} from "./artist.model";

export interface ArtistResponse {
  artists: Artist[];
  errorMessage: string;
}
