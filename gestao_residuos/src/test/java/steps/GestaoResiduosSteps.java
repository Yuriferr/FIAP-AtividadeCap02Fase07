package steps;

import model.ErrorMessageModel;
import org.junit.Assert;
import steps.service.ColetaService;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;


import java.util.List;
import java.util.Map;

public class GestaoResiduosSteps{
        ColetaService coletaService = new ColetaService();

        @Dado("que eu tenha os seguintes dados da coleta:")
        public void queEuTenhaOsSeguintesDadosDaColeta(List<Map<String,String>> rows) {
                for(Map<String, String> columns : rows){
                        coletaService.setFieldsDelivery(columns.get("campo"), columns.get("valor"));
                }
        }

        @Quando("eu enviar a requisição para o endpoint {string} de cadastro de coletas")
        public void euEnviarARequisiçãoParaOEndpointDeCadastroDeColetas(String endpoint){
                coletaService.createDelivery(endpoint);
        }

        @Então("o status code da resposta deve ser {int}")
        public void oStatusCodeDaRespostaDeveSer(int statusCode){
                Assert.assertEquals(statusCode, coletaService.response.statusCode());

        }

        @E("a mensagem de erro deve ser {string}")
        public void oCorpoDeRespostaDeErroDaApiDeveRetornarAMensagem(String message) {
                ErrorMessageModel errorMessageModel = coletaService.gson.fromJson(
                        coletaService.response.jsonPath().prettify(), ErrorMessageModel.class);
                Assert.assertEquals(message, errorMessageModel.getMessage());
        }
}
