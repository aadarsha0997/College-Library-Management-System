package College.Library.Management.System.Project.config;

import College.Library.Management.System.Project.Repo.StudentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {
    private final StudentRepo repository;

    @Bean
    public UserDetailsService userDetailsService(){
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return repository.findByStudentId(username).orElseThrow(()->new UsernameNotFoundException("User not found"));
            }
        }
    }
}
