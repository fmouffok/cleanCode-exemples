package fr.km.exemple;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public interface UserRepo {
    List<User> findAll();
}


class UserFacade{
    private UserRepo repo;
    private UserMapper mapper;

    // exemple code
    public List<UserDto> getUsers(){
        final List<User> users = repo.findAll();
        List<UserDto> userDtos = new ArrayList<>();
        for (User user : users){
            UserDto dto = new UserDto();
            dto.setFirstName(user.getFirstName());
            dto.setLastName(user.getLastName());
            dto.setUsernemae(user.getUsernemae());
            dto.setFullName(user.getFirstName() + " "+ user.getLastName().toUpperCase());
            dto.setActive(user.getDeactivatedDate() != null);
            userDtos.add(dto);
        }
    return userDtos;
    }

    public List<UserDto> getUsers1(){
        final List<User> users = repo.findAll();
        List<UserDto> userDtos = new ArrayList<>();
        return users.stream().map(user -> {
            UserDto dto = new UserDto();
            dto.setFirstName(user.getFirstName());
            dto.setLastName(user.getLastName());
            dto.setUsernemae(user.getUsernemae());
            dto.setFullName(user.getFirstName() + " "+ user.getLastName().toUpperCase());
            dto.setActive(user.getDeactivatedDate() == null);
            return dto;
        }).collect(Collectors.toList());
    }

    public List<UserDto> getUsers2(){
        final List<User> users = repo.findAll();
        List<UserDto> userDtos = new ArrayList<>();
        return users.stream().map(mapper::toDto).collect(Collectors.toList());
    }



}

class UserMapper{
    public UserDto toDto(User user) {
        UserDto dto = new UserDto();
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setUsernemae(user.getUsernemae());
        dto.setFullName(user.getFirstName() + " " + user.getLastName().toUpperCase());
        dto.setActive(user.getDeactivatedDate() == null);
        return dto;
    }

}

class User {
    private String firstName;
    private String lastName;
    private String usernemae;
    private Date DeactivatedDate;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsernemae() {
        return usernemae;
    }

    public void setUsernemae(String usernemae) {
        this.usernemae = usernemae;
    }

    public Date getDeactivatedDate() {
        return DeactivatedDate;
    }

    public void setDeactivatedDate(Date deactivatedDate) {
        DeactivatedDate = deactivatedDate;
    }
}
class UserDto {
        private String firstName;
        private String lastName;
        private String usernemae;
        private String fullName;
        private boolean active;

    public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getUsernemae() {
            return usernemae;
        }

        public void setUsernemae(String usernemae) {
            this.usernemae = usernemae;
        }

        public boolean isActive() {
            return active;
        }

        public void setActive(boolean active) {
            this.active = active;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }
    }

