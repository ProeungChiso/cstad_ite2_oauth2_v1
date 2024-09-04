package co.istad.oauthv1.init;

import co.istad.oauthv1.domain.Authority;
import co.istad.oauthv1.domain.User;
import co.istad.oauthv1.features.authority.AuthorityRepository;
import co.istad.oauthv1.features.user.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInit {

    private final AuthorityRepository authorityRepository;
    private final UserRepository userRepository;

    @PostConstruct
    public void init() {
        Authority authorityRead = new Authority();
        authorityRead.setName("READ");

        Authority authorityWrite = new Authority();
        authorityWrite.setName("WRITE");

        authorityRepository.saveAll(List.of(authorityRead, authorityWrite));

        User userAdmin = new User();
        userAdmin.setUsername("admin");
        userAdmin.setPassword("admin");
        userAdmin.setAuthorities(List.of(authorityWrite, authorityRead));
        userAdmin.setIsDeleted(false);

        userRepository.save(userAdmin);
    }
}
