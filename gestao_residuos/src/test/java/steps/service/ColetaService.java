package steps.service;

import exception.UsuarioNaoEcontradoException;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import model.Coleta;
import repository.ColetaRepository;
import io.restassured.http.ContentType;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.Optional;

import static io.restassured.RestAssured.given;

@Service
public class ColetaService {

    @Autowired
    private ColetaRepository coletaRepository;

    public Coleta gravar(Coleta coletaCadastrada) {
        Coleta coleta = new Coleta();
        BeanUtils.copyProperties(coletaCadastrada, coleta);
        return new Coleta();
    }

    public Coleta buscarPorId(long id) {
        return coletaRepository.findById(id)
                .map(Coleta::new)
                .orElseThrow(() -> new UsuarioNaoEcontradoException("Coleta não encontrada"));
    }

    public Page<Coleta> listarTodosAsColetas(Pageable paginacao) {
        return coletaRepository
                .findAll(paginacao)
                .map(Coleta::new);
    }

    public void excluir(Long id) {
        Optional<Coleta> coletaOptional = coletaRepository.findById(id);

        if (coletaOptional.isPresent()) {
            coletaRepository.delete(coletaOptional.get());
        } else {
            throw new RuntimeException("Contato não encontrado");
        }

    }

    public Coleta atualizar(Coleta coleta) {
        Optional<Coleta> coletaOptional = coletaRepository.findById(coleta.getId());

        if (coletaOptional.isPresent()) {
            return coletaRepository.save(coleta);
        } else {
            throw new RuntimeException("Contato não encontrado");
        }
    }

    public Coleta coleta = new Coleta();
    public final Gson gson = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create();
    public Response response;
    String baseUrl = "http://localhost:8080";

    public void setFieldsDelivery(String field, String value) {
        switch (field) {
            case "endereco" -> coleta.setEndereco(value);
            case "quantidadeResiduos" -> coleta.setQuantidadeResiduos(Integer.parseInt(value));
            default -> throw new IllegalStateException("Unexpected feld" + field);
        }
    }

    public void createDelivery(String endPoint) {
        String url = baseUrl + endPoint;
        String bodyToSend = gson.toJson(coleta);
        response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(bodyToSend)
                .when()
                .post(url)
                .then()
                .extract()
                .response();
    }
}