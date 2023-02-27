import {Song} from "./song.model";

export interface SongDtos {
  songDtoSet: Song[];
  errorMessage: string;
}
