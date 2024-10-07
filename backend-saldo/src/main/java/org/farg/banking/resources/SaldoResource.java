package org.farg.banking.resources;

import org.farg.banking.model.Saldo;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.validation.constraints.NotBlank;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/saldo/v1")
public class SaldoResource {
	
    //definição das agências e contas
	private final String AGENCIA_CONTA_01 = "1234-56789";
	private final String AGENCIA_CONTA_02 = "1234-98765";
	private final String AGENCIA_CONTA_03 = "4321-12345";
	private final String AGENCIA_CONTA_ERRO_INTERNO = "9999-99999";

    //definição dos objetos de saldo
	private Saldo saldoConta1 = new Saldo();
	private Saldo saldoConta2 = new Saldo();
	private Saldo saldoConta3 = new Saldo();

    //inicialização dos dados
    public SaldoResource() {
    	saldoConta1.moeda="R$";
    	saldoConta1.valor=100.00;
    	
    	saldoConta1.moeda="R$";
    	saldoConta1.valor=100.00;
    	
    	saldoConta2.moeda="R$";
    	saldoConta2.valor=150.00;
    	
    	saldoConta3.moeda="R$";
    	saldoConta3.valor=50.00;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSaldo(@QueryParam("agencia") @NotBlank String agencia, @QueryParam("conta") @NotBlank String conta) {
    	
        //validação das agências e contas
    	if(AGENCIA_CONTA_01.equals(agencia+"-"+conta)) {
    		JsonObject value = Json.createObjectBuilder()
    			               .add("Saldo", Json.createObjectBuilder()
    			                    .add("moeda", saldoConta1.moeda)
    			                    .add("valor", saldoConta1.valor)
    			               ).build();
    		
    		String json = value.toString();
    	    return Response.ok(json, MediaType.APPLICATION_JSON).build();
    	}
    	
    	if(AGENCIA_CONTA_02.equals(agencia+"-"+conta)) {
    		JsonObject value = Json.createObjectBuilder()
    			               .add("Saldo", Json.createObjectBuilder()
    			                    .add("moeda", saldoConta2.moeda)
    			                    .add("valor", saldoConta2.valor)
    			               ).build();
    		
    		String json = value.toString();
    	    return Response.ok(json, MediaType.APPLICATION_JSON).build();
    	}
    	
    	if(AGENCIA_CONTA_03.equals(agencia+"-"+conta)) {
    		JsonObject value = Json.createObjectBuilder()
    			               .add("Saldo", Json.createObjectBuilder()
    			                    .add("moeda", saldoConta3.moeda)
    			                    .add("valor", saldoConta3.valor)
    			               ).build();
    		
    		String json = value.toString();
    	    return Response.ok(json, MediaType.APPLICATION_JSON).build();
    	}
    	
        //Simulação de erro interno
    	if(AGENCIA_CONTA_ERRO_INTERNO.equals(agencia+"-"+conta)) {
            return Response.serverError().entity("Ocorreu um erro interno").build();
        }
    	
        //Agência ou conta não encontrada
    	JsonObject value = Json.createObjectBuilder().add("Erro", Json.createObjectBuilder()
                .add("codigo", "ErroSaldo01")
                .add("descricao", "Agência ou Conta não encontrados")).build();
    			
    	return Response.status(Response.Status.NOT_FOUND).entity(value.toString()).build();
    }

}
