package College.Library.Management.System.Project.auth;

import College.Library.Management.System.Project.DTO.LoginDTO;
import College.Library.Management.System.Project.DTO.StudentCreateDTO;
import College.Library.Management.System.Project.Model.Student;
import College.Library.Management.System.Project.Repo.StudentRepo;
import College.Library.Management.System.Project.Service.StudentService;
import College.Library.Management.System.Project.config.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final StudentService studentService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Transactional
    public AuthenticationResponse register(StudentCreateDTO request) {

        Student savedUser=studentService.createUser(request);
        var jwtToken= jwtService.generateToken(savedUser);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .studentId(savedUser.getStudentId())
                .build();

    }

    public AuthenticationResponse login( LoginDTO request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getStudentId(),
                        request.getPassword()
                )
        );
         var user=studentService.getUserAuthentication(request.getStudentId());

        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .studentId(user.getStudentId())
                .build();
    }
}
