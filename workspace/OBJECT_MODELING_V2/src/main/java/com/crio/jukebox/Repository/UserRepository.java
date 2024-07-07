package com.crio.jukebox.Repository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import com.crio.jukebox.Entities.User;

public class UserRepository {
    private Map<Integer, User> userMap;
    private Integer autoIncrement = 0;

    public UserRepository() {
        userMap = new HashMap<Integer, User>();
    }

    public UserRepository(Map<Integer, User> userMap) {
        this.userMap = userMap;
        this.autoIncrement = userMap.size();
    }


    public UserRepository(Map<Integer, User> userMap, PlayListRepository playListRepository,
            SongRepository songRepository) {
        this.userMap = userMap;
        this.autoIncrement = userMap.size();
    }

    public User save(User entity) {
        if (entity.getId() == null) {
            autoIncrement++;
            User u = new User(autoIncrement, entity.getName());
            userMap.put(u.getId(), u);
            return u;
        }
        userMap.put(entity.getId(), entity);
        return entity;
    }

    // TODO: CRIO_TASK_MODULE_SERVICES
    // Find all the list of User Present in the Repository
    // Tip:- Use Java Streams


    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        for (User i : userMap.values()) {
            list.add(i);
        }
        return list;
    }


    public Optional<User> findById(Integer userId) {
        return Optional.ofNullable(userMap.get(userId));
    }


    public boolean existsById(Integer id) {
        return userMap.containsKey(id);
    }


    public void delete(User entity) {

        userMap.remove(entity.getId());

    }


    public void deleteById(Integer id) {
        userMap.remove(id);
    }


    public long count() {
        // TODO Auto-generated method stub
        return this.count();
    }

    // TODO: CRIO_TASK_MODULE_SERVICES
    // Find the User Present in the Repository provided name
    // Tip:- Use Java Streams


    public Optional<User> findByName(String name) {
        // User user = this.userMap.get(name);
        // userMap.values().stream().filter(t->t.getName().equals(name)).anyMatch().tocollect();
        Optional<User> optUser = Optional.empty();
        for (User i : userMap.values()) {
            if (i.getName().equals(name))
                optUser = Optional.of(i);
        }
        return optUser;
    }


    // public String loadData(String inputFile) throws IOException {
    //     String line = "";
    //     BufferedReader br = new BufferedReader(new FileReader(inputFile));
    //     while ((line = br.readLine()) != null) // returns a Boolean value
    //     {

    //         String[] value = line.split(",");
    //         String[] str = value[5].split("#");
    //         List<Artist> artistList = new ArrayList<>();
    //         for (int i = 0; i < str.length; i++) {
    //             artistIncrement++;
    //             artistList.add(new Artist(artistIncrement, str[i]));
    //         }
    //         songRepository.save(
    //                 new Song(Integer.parseInt(value[0]), value[1], value[2], value[3], artistList));

    //     }
    //     // System.out.println(songMap);
    //     br.close();
    //     return "Songs Loaded successfully";
    // }

    // public User createUser(User user) {
    //     User users = new User(user.getId(), user.getName());
    //     userMap.put(user.getId(), users);
    //     return user;
    // }

    // public static void main(String[] args) throws IOException {
    // UserRepository userRepository = new UserRepository();
    // userRepository.loadData();
    // }
    // public PlayList createPlayList(Integer userId, String name, List<Integer> songIds) {
    //     PlayList playList = null;
    //     Optional<User> ouser = findById(userId);
    //     if (ouser.isPresent()) {
    //         for (Integer i : songIds) {
    //             Optional<Song> optional = songRepository.findById(i);
    //             if (optional.isEmpty())
    //                 throw new SongNotFoundException("Song Not Found With This UserId " + i);
    //         }

    //         playList = new PlayList(userId, null, name, songIds);
    //         playListRepository.save(playList);
    //     } else
    //         throw new UserNotFoundException("User Not Found with this userId " + userId);
    //     return playList;
    // }

    
}
