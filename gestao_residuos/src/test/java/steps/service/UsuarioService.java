package steps.service;

import dto.UsuarioCadastroDto;
import dto.UsuarioExibicaoDto;
import model.Usuario;
import repository.UsuarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioExibicaoDto salvarUsuario(UsuarioCadastroDto usuarioDto){

        String senhaCriptografada = new BCryptPasswordEncoder().encode(usuarioDto.senha());

        Usuario usuario = new Usuario();
        BeanUtils.copyProperties(usuarioDto,usuario);
        usuario.setSenha(senhaCriptografada);

        Usuario usuarioSalvo = usuarioRepository.save(usuario);
        return new UsuarioExibicaoDto(usuarioSalvo);

    }

}