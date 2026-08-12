package College.Library.Management.System.Project.auth;

import College.Library.Management.System.Project.DTO.LoginDTO;
import College.Library.Management.System.Project.DTO.StudentCreateDTO;
import College.Library.Management.System.Project.Model.Role;
import College.Library.Management.System.Project.Model.Student;
import College.Library.Management.System.Project.Repo.StudentRepo;
import College.Library.Management.System.Project.config.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final StudentRepo studentRepo;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Transactional
    public AuthenticationResponse register(StudentCreateDTO request) {

        Student student=new Student();
        student.setName(request.getName());
        student.setPassword(passwordEncoder.encode(request.getPassword()));
        student.setFaculty(request.getFaculty());
        student.setSemester(request.getSemester());
        student.setPhoneNumber(request.getPhoneNumber());
        student.setRole(Role.STUDENT);

        Student savedUser= studentRepo.save(student);
        savedUser.setStudentId(savedUser.getFaculty()+savedUser.getId());
        var jwtToken= jwtService.generateToken(savedUser);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();

    }

    public AuthenticationResponse login( LoginDTO request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getStudentId(),
                        request.getPassword()
                )
        );

        var user= studentRepo.findByStudentId(request.getStudentId()).orElseThrow();

        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
