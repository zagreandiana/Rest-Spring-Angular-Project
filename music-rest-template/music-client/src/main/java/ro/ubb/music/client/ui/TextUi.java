package ro.ubb.music.client.ui;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ro.ubb.music.web.dto.*;
import ro.ubb.music.web.utils.UserRoles;
import ro.ubb.music.web.utils.UserStatuses;
import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Scanner;
import java.util.*;

@NoArgsConstructor
@Service
public class TextUi {

    @Autowired
    private RestTemplate restTemplate;

    static Scanner scanner = new Scanner(System.in);

    public void start() {
        boolean inMenu = true;

        while (inMenu) {
            printMenu();

            String fullCommand;
            List<String> commandParts;

            do {
                System.out.print("Enter valid command: ");
                fullCommand = scanner.nextLine();

                commandParts = List.of(fullCommand.split(" "));
            } while (commandParts.isEmpty() || commandParts.size() > 2);

            String menu = commandParts.get(0).toLowerCase();
            String subMenu = commandParts.size() == 2 ? commandParts.get(1).toLowerCase() : "";

            switch (menu) {
                case "user" -> {
                    switch (subMenu) {
                        case "create" -> createUser();
                        case "read" -> readOneUser();
                        case "read-all" -> readAllUsers();
                        case "filter-status" -> filterStatus();
                        case "filter-role" -> filterRole();
                        case "update" -> updateUser();
                        case "delete" -> deleteUser();
                        default -> System.out.println("Invalid operation. Please Try again.");
                    }
                }
                case "song" -> {
                    switch (subMenu) {
                        case "create" -> addSong();
                        case "read" -> selectOneSong();
                        case "read-all" -> selectAllSongs();
                        case "update" -> updateSong();
                        case "delete" -> deleteSong();
                        case "sortbylength" -> sortByLength();
                        case "getsongbytitle" -> findSongByTitle();
                        case "groupbyalbum" -> groupSongByAlbum();
                        default -> System.out.println("Invalid operation. Please Try again.");
                    }
                }
                case "album" -> {
                    switch (subMenu) {
                        case "create" -> createAlbum();
                        case "read" -> readOneAlbum();
                        case "read-all" -> readAllAlbums();
                        case "update" -> updateAlbum();
                        case "delete" -> deleteAlbum();
                        case "filter-g" -> readAlbumsByGenre();
                        case "sort" -> readAlbumsSorted();
                        default -> System.out.println("Invalid operation. Please try again.");
                    }
                }
                case "artist" -> {
                    switch (subMenu) {
                        case "create" -> createArtist();
                        case "read" -> readOneArtist();
                        case "read-all" -> readAllArtists();
                        case "update" -> updateArtist();
                        case "delete" -> deleteArtist();
                        case "sort-actitity-start-date" -> sortByActivityStartDate();
                        case "alphabetical-sort-first-name" -> alfSortByFirstName();
                        default -> System.out.println("Invalid operation. Please Try again.");
                    }
                }
                case "band" -> {
                    switch (subMenu) {

                        case "create" -> createBand();
                        case "read" -> readOneBand();
                        case "read-all" -> readAllBands();
                        case "update" -> updateBand();
                        case "delete" -> deleteBand();
                        case "sortare-alfabetica" -> sortareAlfabetica();
//                        case "activitate-inceputa-intre" -> activitateInceputaIntre();
                        default -> System.out.println("Invalid operation. Please try again.");
                    }
                }

                case "exit" -> inMenu = false;
                default -> System.out.println("Invalid entity. Please try again.");
            }
        }
    }

    private void createUser() {
        UserDto dto = readUserInformation();
        UserDto savedUser = restTemplate.postForObject("http://localhost:8080/api/users", dto, UserDto.class);

        System.out.println(savedUser);
    }

    private void readAllUsers() {
        UserResponse response = restTemplate.getForObject("http://localhost:8080/api/users", UserResponse.class);

        if (Objects.isNull(response)) {
            System.out.println("Error: Api response is null.");

            return;
        }

        System.out.println(response.getUsers());
    }

    private void readOneUser() {
        Long id = readId("User id: ");
        UserResponse response = restTemplate.getForObject(
                "http://localhost:8080/api/users/{:id}", UserResponse.class, id);

        if (Objects.isNull(response)) {
            System.out.println("Error: Api response is null.");

            return;
        }

        String errorMessage = response.getErrorMessage();
        Set<UserDto> users = response.getUsers();

        if (Objects.nonNull(errorMessage)) {
            System.out.println("Error: " + errorMessage);
        } else {
            System.out.println(users);
        }
    }

    private void updateUser() {
        Long id = readId("User id: ");
        UserDto dto = readUserInformation();

        restTemplate.put("http://localhost:8080/api/users/{:id}", dto, id);
    }

    private void deleteUser() {
        Long id = readId("User id: ");

        restTemplate.delete("http://localhost:8080/api/users/{:id}", id);
    }

    private void filterStatus() {
        System.out.print("Enter the status you would like to filter by [active, inactive]: ");
        String inputStatus = scanner.nextLine();

        UserResponse response = restTemplate.getForObject(
                "http://localhost:8080/api/users/status={:status}", UserResponse.class, inputStatus);

        if (Objects.isNull(response)) {
            System.out.println("Error: Api response is null.");

            return;
        }

        System.out.println(response.getUsers());
    }

    private void filterRole() {
        System.out.print("Enter the role you would like to filter by [user, admin]: ");
        String inputRole = scanner.nextLine();

        UserResponse response = restTemplate.getForObject(
                "http://localhost:8080/api/users/role={:role}", UserResponse.class, inputRole);

        if (Objects.isNull(response)) {
            System.out.println("Error: Api response is null.");

            return;
        }

        System.out.println(response.getUsers());
    }

    private static UserDto readUserInformation() {
        System.out.print("First Name: ");
        final String firstName = scanner.nextLine();
        System.out.print("Last Name: ");
        String lastName = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        System.out.print("Role: ");
        String inputRole = scanner.nextLine();
        System.out.print("Status: ");
        String inputStatus = scanner.nextLine();

        return new UserDto(firstName, lastName, email, password, UserRoles.of(inputRole), UserStatuses.of(inputStatus));
    }

    private void groupSongByAlbum() {
        Integer id = Math.toIntExact(readId("Album id"));
        SongDtos songDtos = restTemplate.getForObject("http://localhost:8080/api/songs/id={:id}", SongDtos.class, id);
        songDtos.getSongDtoSet().forEach(s -> System.out.println(s));
    }

    private void findSongByTitle() {
        System.out.println("Song title");
        String title = scanner.nextLine();

        SongDto songDto = restTemplate.getForObject(
                "http://localhost:8080/api/songs/title={:title}", SongDto.class, title);

        System.out.println(songDto);
    }

    private void sortByLength() {
        SongDtos songDtos = restTemplate.getForObject("http://localhost:8080/api/songs/sort", SongDtos.class);
        System.out.println(songDtos);
    }


    private void sortareAlfabetica() {
        BandDtos bandDtos = restTemplate.getForObject("http://localhost:8080/api/bands/sortare", BandDtos.class);

        bandDtos.getBandDtos().forEach(System.out::println);
    }

    //
    private void sortByActivityStartDate() {
        ArtistDtos dtos = restTemplate.getForObject("http://localhost:8080/api/artists/date", ArtistDtos.class);
        System.out.println(dtos);

    }

    private void alfSortByFirstName() {
        ArtistDtos dtos = restTemplate.getForObject("http://localhost:8080/api/artists/alpha", ArtistDtos.class);
        System.out.println(dtos);
    }

    private void createAlbum() {
        AlbumDto albumDto = getAlbumInformation();

        AlbumDto savedAlbum = restTemplate.postForObject("http://localhost:8080/api/albums", albumDto, AlbumDto.class);
        System.out.println(savedAlbum);
    }

    private void readOneAlbum() {
        Long idAlbum = readId("Album id: ");
        AlbumDto albumDto = restTemplate.getForObject(
                "http://localhost:8080/api/albums/{:id}", AlbumDto.class, idAlbum);

        System.out.println(albumDto);
    }

    private void readAllAlbums() {
        AlbumDtos albumDtos = restTemplate.getForObject("http://localhost:8080/api/albums", AlbumDtos.class);
        albumDtos.getAlbumDtos().forEach(System.out::println);
    }

    private void updateAlbum() {
        AlbumDto albumDto = getAlbumInformation();
        Long albumId = readId("ID of the album you want to update: ");
        albumDto.setId(albumId);
        restTemplate.put("http://localhost:8080/api/albums/{:id}", albumDto, albumDto.getId());
    }

    private void deleteAlbum() {
        Long idAlbum = readId("Album id: ");
        restTemplate.delete("http://localhost:8080/api/albums/{:id}", idAlbum);
    }

    private AlbumDto getAlbumInformation() {
        System.out.println("Album Title:");
        String titleAlbum = scanner.nextLine();
        System.out.println("Album Genre:");
        String genreAlbum = scanner.nextLine();
        System.out.println("Artist id:");
        Long artistIdScan = Long.valueOf(scanner.nextLine());
        Long artistId = artistIdScan == 0 ? null : artistIdScan;
        System.out.println("Band id:");
        Long bandIdScan = Long.valueOf(scanner.nextLine());
        Long bandId = bandIdScan == 0 ? null : bandIdScan;
        System.out.println("Date of release: ");
        Date releaseDate = Date.valueOf(scanner.nextLine());

        return new AlbumDto(titleAlbum, genreAlbum, artistId, bandId, releaseDate);
    }

    private void readAlbumsByGenre() {
        System.out.print("Genre: ");
        String genre = scanner.nextLine();
        AlbumDtos albumDtos = restTemplate.getForObject("http://localhost:8080/api/albums/filter={:genre}", AlbumDtos.class, genre);

        System.out.println("");
        System.out.println("The albums sorted by genre are: ");
        albumDtos.getAlbumDtos().forEach(System.out::println);
    }

    private void readAlbumsSorted() {
        System.out.print("Type of desired sorting (A|Z): ");
        String typeOfSort = scanner.nextLine().toLowerCase();
        AlbumDtos albumDtos = restTemplate.getForObject("http://localhost:8080/api/albums/sort={:sortValue}", AlbumDtos.class, typeOfSort);
        String titleMessage = typeOfSort.equals("a") ? "Albums sorted ASC: " : "Albums sorted DESC: ";
        System.out.println();
        System.out.println(titleMessage);
        albumDtos.getAlbumDtos().forEach(System.out::println);
    }

    private void readAllBands() {
        BandDtos bandDtos = restTemplate.getForObject("http://localhost:8080/api/bands", BandDtos.class);
        bandDtos.getBandDtos().forEach(System.out::println);
    }


    private void readOneBand() {
        Long idBand = readId("Band id: ");
        BandDto bandDto = restTemplate.getForObject(
                "http://localhost:8080/api/bands/{:id}", BandDto.class, idBand);

        System.out.println(bandDto);
    }

    private void deleteBand() {
        Long idBand = readId("Band id: ");
        restTemplate.delete("http://localhost:8080/api/bands/{:id}", idBand);
    }


    private void updateBand() {
        BandDto bandDto = getBandInformation();
        Long bandId = readId("ID of the band you want to update: ");
        bandDto.setId(bandId);
        restTemplate.put("http://localhost:8080/api/bands/{:id}", bandDto, bandDto.getId());
    }

    private BandDto getBandInformation() {
        System.out.println("Band Name:");
        String nameBand = scanner.nextLine();
        System.out.println("Start of activity date: ");
        Date activityStartDate = Date.valueOf(scanner.nextLine());
        System.out.println("End of activity date: ");
        Date activityEndDate = Date.valueOf(scanner.nextLine());

        return new BandDto(nameBand, activityStartDate, activityEndDate);
    }

    private void createBand() {
        BandDto bandDto = getBandInformation();

        BandDto savedBand = restTemplate.postForObject("http://localhost:8080/api/bands", bandDto, BandDto.class);
        System.out.println(savedBand);
    }

    private void createArtist() {
        ArtistDto dto = readArtistInformation();
        ArtistDto savedArtist = restTemplate.postForObject("http://localhost:8080/api/artists", dto, ArtistDto.class);
        System.out.println(savedArtist);

    }

    private void readOneArtist() {
        Long id = readId("Artist id: ");
        ArtistResponse response = restTemplate.getForObject(
                "http://localhost:8080/api/artists/{:id}", ArtistResponse.class, id);

        if(Objects.isNull(response)) {
            System.out.println("Error: Api response is null.");

            return;
        }

        String errorMessage = response.getErrorMessage();
        Set<ArtistDto> artists = response.getArtists();

        if (Objects.nonNull(errorMessage)) {
            System.out.println("Error: " + errorMessage);
        } else {
            System.out.println(artists);
        }
    }

    private void readAllArtists() {
        ArtistResponse response = restTemplate.getForObject("http://localhost:8080/api/artists", ArtistResponse.class);

        if(Objects.isNull(response)) {
            System.out.println("Error: Api response is null.");

            return;
        }

        System.out.println(response.getArtists());
    }

    private void updateArtist() {
        Long id = readId("Artist id: ");
        ArtistDto dto = readArtistInformation();

        restTemplate.put("http://localhost:8080/api/artists/{:id}", dto, id);
    }

    private void deleteArtist() {
        Long id = readId("Artist id: ");

        restTemplate.delete("http://localhost:8080/api/artists/{:id}", id);

    }

    private static ArtistDto readArtistInformation() {
        System.out.print("First Name: ");
        String first_Name = scanner.nextLine();
        System.out.print("Last Name: ");
        String last_Name = scanner.nextLine();
        System.out.print("Stage Name: ");
        String stage_name = scanner.nextLine();
        System.out.print("Activity start date: ");
        String dateStart = scanner.nextLine();
        Date activity_start_date = Date.valueOf(dateStart);
        System.out.print("Activity end date: ");
        String dateEnd = scanner.nextLine();
        Date activity_end_date = Date.valueOf(dateEnd);

        return new ArtistDto(first_Name, last_Name, stage_name, activity_start_date, activity_end_date);
    }
//

    private void selectAllSongs() {
        SongDtos dtos = restTemplate.getForObject("http://localhost:8080/api/songs", SongDtos.class);
        dtos.getSongDtoSet().forEach(s -> System.out.println(s));
    }

    private void selectOneSong() {
        Long id = readId("Song id: ");
        SongDtos response = restTemplate.getForObject("http://localhost:8080/api/songs/{:id}", SongDtos.class, id);

        if (Objects.isNull(response)) {
            System.out.println("Error: Api response is null.");
            return;
        }

        Set<SongDto> songDtosSet = response.getSongDtoSet();
        System.out.println(songDtosSet);
    }

    private void deleteSong() {
        Long id = readId("Song id: ");

        restTemplate.delete("http://localhost:8080/api/songs/{:id}", id);
    }


    /*
    * Long id = readId("User id: ");
        UserDto dto = readUserInformation();

        restTemplate.put("http://localhost:8080/api/users/{:id}", dto, id);
    * */

    /*
    *  private void updateAlbum() {
        AlbumDto albumDto = getAlbumInformation();
        Long albumId = readId("ID of the album you want to update: ");
        albumDto.setId(albumId);
        restTemplate.put("http://localhost:8080/api/albums/{:id}", albumDto, albumDto.getId());
    }

    * */
    private void updateSong() {
/*
        SongDto songDto=getSongInformation();
        Long id=readId("Song id: ");
        songDto.setId(id);

        restTemplate.put("http://localhost:8080/api/song/{:id}", songDto, songDto.getId());
*/

        Long id = readId("Song id: ");
        SongDto songDto = getSongInformation();

        restTemplate.put("http://localhost:8080/api/songs/{:id}", songDto, id);
    }

    private static SongDto getSongInformation() {
        System.out.println("Title: ");
        String title = scanner.nextLine();
        System.out.println("Album id: ");
        Integer albumId = Integer.parseInt(scanner.nextLine());
        System.out.println("Song length (h:m:s): ");
        String timeString = scanner.nextLine();
        Time time = Time.valueOf(timeString);

        return new SongDto(title, albumId, time);
    }

    private void addSong() {
        SongDto newSong = getSongInformation();

        SongDto saveSong = restTemplate.postForObject("http://localhost:8080/api/songs", newSong, SongDto.class);

    }

    private static Long readId(String s) {
        System.out.print(s);
        return Long.parseLong(scanner.nextLine());
    }

    private static void printMenu() {
        System.out.print("""

                       AVAILABLE COMMANDS
                --------------------------------
                user [create,read,read-all,filter-status,filter-role,update,delete]
                song [create,read,read-all,update,delete,sortbylength,groupbyalbum,getsongbytitle]
                album [create,read,read-all,update,delete,sort,filter-g]
                artist [create,read,read-all,update,delete,sort-actitity-start-date,alphabetical-sort-first-name]
                band [create,read,read-all,update,delete,sortare-alfabetica,activitate-inceputa-intre]
                exit
                --------------------------------

                """);
    }
}
